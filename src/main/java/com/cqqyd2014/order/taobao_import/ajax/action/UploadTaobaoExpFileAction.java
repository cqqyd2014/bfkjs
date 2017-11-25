package com.cqqyd2014.order.taobao_import.ajax.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.dao.VInventoryByGoodsIdAvailableDAO;
import com.cqqyd2014.order.taobao_import.logic.TwoFileToOrders;
import com.cqqyd2014.util.message.IfMessage;
import com.cqqyd2014.util.table.Table;
import com.cqqyd2014.util.table.element.Row;
import com.cqqyd2014.util.taobao.OrderAutoAnalysisException;
import com.csvreader.CsvReader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")

@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class UploadTaobaoExpFileAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String order_from;

	public String getOrder_from() {
		return order_from;
	}

	public void setOrder_from(String order_from) {
		this.order_from = order_from;
	}
	// 上传的文件，struts会自动帮我们填充至此，因为多文件，所以用List
	private List<File> file;
	// 上传的文件的文件名，因为多文件，所以用List
	private List<String> fileFileName;
	// 上传的文件的类型，因为多文件，所以用List
	private List<String> fileContentType;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * 文件上传关键方法。
	 */
	@Action(value = "upload", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String upload() {
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		// 文件所放的文件夹。，
		// 有关路径问题，请参考另一篇博文：http://www.cnblogs.com/xiaoMzjm/p/3878758.html
		//String root = ServletActionContext.getServletContext().getRealPath("/") + File.separator+"upload"+File.separator;
		// 循环上传的文件
		java.util.ArrayList<Table> ts=new java.util.ArrayList<>();
		for (int i = 0; i < file.size(); i++) {

			InputStream is = null;
			//OutputStream os = null;
			try {
				// 获取当前遍历到的文件，new 一个文件输入流，连接到该文件。
				is = new FileInputStream(file.get(i));
				/*
				// new 一个文件，连接到要存储的文件夹处。
				File destFile = new File(root, this.getFileFileName().get(i));
				// new 一个输出流，连接到要存储的文件处。
				os = new FileOutputStream(destFile);
				// 字节流，规定可写入的字节数。
				byte[] buffer = new byte[is.available()];
				int length = 0;
				// 开始写入文件
				
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				
				StringBuffer sb= new StringBuffer("");
		           */
				
				InputStreamReader isr=new InputStreamReader(is,"gbk");
				//
				Table t=new Table();
				ts.add(t);
				CsvReader csvReader = new CsvReader(isr);
				csvReader.readHeaders();
				String[] heads=csvReader.getHeaders();
				t.setHead(heads);
				int col_count=heads.length;
				//System.out.println(heads.length);
				java.util.ArrayList<Row> rows=new java.util.ArrayList<>();
	            while (csvReader.readRecord()){
	            	com.cqqyd2014.util.table.element.Row row=new com.cqqyd2014.util.table.element.Row();
	            	String fields[] = new String[col_count];
	            	for (int j=0;j<col_count;j++){
	            		fields[j]=csvReader.get(j);
	            	}
	            	
	            	row.setFields(fields);
	            	rows.add(row);
	                
	            }
				t.setRows(rows);
				/*
	            BufferedReader br = new BufferedReader(isr);
				String str;
				while((str = br.readLine()) != null) {
	                  sb.append(str+"/n");
	                 
	                  System.out.println(str);
	            }
	            */
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//try {
				//	os.close();
				//}
				//catch (IOException e) {
				//	e.printStackTrace();
				//}
			}
		}
		//采集了数据，导入
		if (ts.size()==2){
			TwoFileToOrders tft=new TwoFileToOrders();
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			
			try {
				Map session_http = ActionContext.getContext().getSession();


				String com_id = (String) session_http.get("com_code");
				String user_id = (String) session_http.get("USER_ID");
				//多次提交之后，order_from会重复“TB,TB”
				order_from=order_from.substring(0,order_from.indexOf(","));
			tft.process(session, ts.get(0), ts.get(1), com_id, order_from,user_id);
			tx.commit();
			}

			catch (HibernateException e) {

				if (null != tx) {
					tx.rollback();// 撤销事务

				}

				System.out.println(e.getMessage());
				e.printStackTrace();
				sm.setSuccess(false);

			}catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
				sm.setSuccess(false);
				sm.setBody(e.getMessageString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (null != tx) {
					tx.rollback();// 撤销事务

				}

				System.out.println(e.getMessage());
				e.printStackTrace();
				sm.setSuccess(false);
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}