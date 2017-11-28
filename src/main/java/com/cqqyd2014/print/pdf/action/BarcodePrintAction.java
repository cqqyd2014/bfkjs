package com.cqqyd2014.print.pdf.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.wh.model.Goods;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ParentPackage("struts-default")
@Namespace("/wh")

@Results({@Result(name = "success", type = "stream", params = {  
        "contentType", "application/pdf",  
        "inputName", "inputStream", "contentDisposition",  
        "attachment;filename=\"${file_name}\"", "bufferSize",  
        "4096" })})  


public class BarcodePrintAction   extends ActionSupport implements ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	ServletContext servletContext;
	InputStream inputStream;
	

	public String getFile_name() throws UnsupportedEncodingException {
		return URLEncoder.encode("条码"+com.cqqyd2014.util.DateUtil.JDateToFullCompatString(new java.util.Date()), "UTF-8") + ".pdf";
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}



	String file_name;



	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;

	}

	public InputStream getInputStream()  {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {

			String reportDestination = null;

			reportDestination = servletContext.getRealPath("/jasper/barcode/barcode20170716-90-20.jasper");


			// 获得jasper报表文件的输入流
			InputStream inputStreamJasper = new FileInputStream(reportDestination);

			HashMap<String,Object> map=new HashMap<>();
			JRDataSource dataSource = createReportDataSource(session,user_id,com_id);
			inputStream = new ByteArrayInputStream(JasperRunManager.runReportToPdf(inputStreamJasper, map, dataSource));
			tx.commit();
			
		}
		
catch (Exception e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		

		 finally {
				HibernateSessionFactory.closeSession();
			}
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	@Action(value="barcode_print", results = { @Result(name = "success", type = "stream", params = {  
	        "contentType", "application/pdf",  
	        "inputName", "inputStream", "contentDisposition",  
	        "attachment;filename=\"${file_name}\"", "bufferSize",  
	        "4096"  }) })  
	public String barcode_print() throws Exception {  
        
        return SUCCESS;  
    }  


	

	private JRDataSource createReportDataSource(Session session,String user_id,String com_id) {
		JRBeanCollectionDataSource  dataSource;
		java.util.ArrayList<Goods> reportRows = initializeBeanArray(session,user_id,com_id);
		dataSource = new JRBeanCollectionDataSource (reportRows);
		return dataSource;
	}

	public   java.util.ArrayList<Goods> initializeBeanArray(Session session,String user_id,String com_id){
		com.cqqyd2014.hibernate.dao.VGoodsDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> goodsList=gdao.getEntityVUnPrinted(session, com_id, user_id);
		return com.cqqyd2014.wh.logic.GoodsLogic.getArrayModelFromArrayEntityV( goodsList);
		
	}

}