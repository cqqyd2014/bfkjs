package com.cqqyd2014.express.bill_to_pdf.action;

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


import com.cqqyd2014.express.common.ExpressBillModel;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



public abstract class ExpressBillToPDFAction    extends ActionSupport implements ServletResponseAware, ServletContextAware {

	
	String order_no;
	String seq;
	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}



	String express_bill_templet;
	
	public String getExpress_bill_templet() {
		return express_bill_templet;
	}

	public void setExpress_bill_templet(String express_bill_templet) {
		this.express_bill_templet = express_bill_templet;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	ServletContext servletContext;
	InputStream inputStream;
	

	public String getFile_name() throws UnsupportedEncodingException {
		return URLEncoder.encode("快递单"+order_no+seq, "UTF-8") + ".pdf";
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
		Map session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {

			String reportDestination = null;

			reportDestination = servletContext.getRealPath("/jasper/express/"+express_bill_templet+".jasper");


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



	

	private JRDataSource createReportDataSource(Session session,String order_no,String seq) {
		JRBeanCollectionDataSource  dataSource;
		java.util.ArrayList<ExpressBillModel> reportRows = initializeBeanArray(session,order_no,seq);
		dataSource = new JRBeanCollectionDataSource (reportRows);
		return dataSource;
	}

	public abstract  java.util.ArrayList<ExpressBillModel> initializeBeanArray(Session session,String order_no,String seq);

}
