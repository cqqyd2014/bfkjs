package com.cqqyd2014.print.pdf.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ParentPackage("struts-default")
@Namespace("/print")

@Results({@Result(name = "success", type = "stream", params = {  
        "contentType", "application/pdf",  
        "inputName", "inputStream", "contentDisposition",  
        "attachment;filename=\"${file_name}\"", "bufferSize",  
        "4096" })})  

public class PackageListPrintAction    extends ActionSupport implements ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	ServletContext servletContext;
	InputStream inputStream;
	
	
	String order_nos;
	String seqs;
	


	public String getOrder_nos() {
		return order_nos;
	}

	public void setOrder_nos(String order_nos) {
		this.order_nos = order_nos;
	}

	public String getSeqs() {
		return seqs;
	}

	public void setSeqs(String seqs) {
		this.seqs = seqs;
	}

	public String getFile_name() throws UnsupportedEncodingException {
		return URLEncoder.encode("发货单"+com.cqqyd2014.util.DateUtil.JDateToFullCompatString(new java.util.Date()), "UTF-8") + ".pdf";
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


		String com_id = (String) session_http.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {

			String reportDestination = null;

			reportDestination = servletContext.getRealPath("/jasper/package_list/deliver.jasper");


			// 获得jasper报表文件的输入流
			InputStream inputStreamJasper = new FileInputStream(reportDestination);

			HashMap<String,Object> parameters = new HashMap<String,Object>();
			//parameters.put("param_deliverbill", bill);
			parameters.put("SUBREPORT_DIR",servletContext.getRealPath("/jasper/package_list/"));
			parameters.put("IMAGES_DIR",servletContext.getRealPath("/jasper/package_list/"));
			
			java.util.ArrayList<String> arraylist_orders=com.cqqyd2014.util.StringUtil.ArrayToArrayList(order_nos.split(","));
			java.util.ArrayList<String> arraylist_seqs=com.cqqyd2014.util.StringUtil.ArrayToArrayList(seqs.split(","));
			
			
			JRDataSource dataSource = createReportDataSource(session,arraylist_orders,arraylist_seqs,com_id);
			inputStream = new ByteArrayInputStream(JasperRunManager.runReportToPdf(inputStreamJasper, parameters, dataSource));
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
	@Action(value="package_list_print", results = { @Result(name = "success", type = "stream", params = {  
	        "contentType", "application/pdf",  
	        "inputName", "inputStream", "contentDisposition",  
	        "attachment;filename=\"${file_name}\"", "bufferSize",  
	        "4096"  }) })  
	public String package_list_print() throws Exception {  
        
        return SUCCESS;  
    }  


	

	private JRDataSource createReportDataSource(Session session,java.util.ArrayList<String> arraylist_orders,java.util.ArrayList<String> arraylist_seqs,String com_id) throws Exception {
		JRBeanCollectionDataSource  dataSource;
		

		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> reportRows =com.cqqyd2014.order.logic.DeliverMLogic.getArrayListFullModelWithDetail(session,arraylist_orders,arraylist_seqs,com_id);
		dataSource = new JRBeanCollectionDataSource (reportRows);
		return dataSource;
	}

	public   java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> initializeBeanArray(Session session,java.util.ArrayList<String> arraylist_orders,java.util.ArrayList<String> arraylist_seqs,String com_id) throws Exception{
		com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
		com.cqqyd2014.hibernate.dao.VDeliverDDAO vdddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=new java.util.ArrayList<>();
		for (int i=0;i<arraylist_orders.size();i++) {
			
			com.cqqyd2014.hibernate.entities.VDeliverM vdm=vdmdao.getEntityViewByOrderNoSeq(session, arraylist_orders.get(i), arraylist_seqs.get(i), com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=vdddao.getArrayListViewByOrderNoSeq(session, com_id,arraylist_orders.get(i), arraylist_seqs.get(i));
			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(vdm, vdds);
			dbs.add(db);
		}
		
		
		
		return dbs;
		
	}

}