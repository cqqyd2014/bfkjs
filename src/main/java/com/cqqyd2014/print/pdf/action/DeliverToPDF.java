package com.cqqyd2014.print.pdf.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DeliverToPDF extends ActionSupport implements SessionAware,ServletResponseAware, ServletContextAware {

	HttpServletResponse response;
	ServletContext servletContext;
	InputStream inputStream;
	
	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	String order_no;
	String seq;

	

	

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;

	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub long start =
		// System.currentTimeMillis();
		String com_id=(String) this.session.get("com_code");
		long start = System.currentTimeMillis();
		Session session = HibernateSessionFactory.getSession();
		try {

			String reportSource1 =servletContext.getRealPath("/jasper/deliver.jrxml");
			String reportDestination1 =servletContext.getRealPath("/jasper/deliver.jasper");
			String reportSource2 =servletContext.getRealPath("/jasper/deliver_sub.jrxml");
			String reportDestination2 =servletContext.getRealPath("/jasper/deliver_sub.jasper");
			JasperCompileManager.compileReportToFile(reportSource1,reportDestination1);

			JasperCompileManager.compileReportToFile(reportSource2,reportDestination2);
			com.cqqyd2014.hibernate.dao.VDeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			com.cqqyd2014.hibernate.entities.VDeliverD vdd=dddao.getArrayListViewByOrderNoSeq(session, com_id, order_no, seq).get(0);
			
			com.cqqyd2014.order.model.DeliverBillDetail bill=com.cqqyd2014.order.logic.DeliverDLogic.getModelFromEntity(vdd);
			ServletOutputStream servletOutputStream = response.getOutputStream();
			// 获得jasper报表文件的输入流
			InputStream inputStream = new FileInputStream(reportDestination1);
			HashMap parameters = new HashMap();
			parameters.put("param_deliverbill", bill);
			parameters.put("SUBREPORT_DIR",servletContext.getRealPath("/jasper/"));
			parameters.put("IMAGES_DIR",servletContext.getRealPath("/jasper/"));
			
			JasperRunManager.runReportToPdfStream(inputStream, servletOutputStream,parameters, new JREmptyDataSource());

			//byte[] bytes = JasperRunManager.runReportToPdf(reportDestination1, parameters, new JREmptyDataSource());  
			// 设置格式
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(order_no+"的发货单", "UTF-8") + ".pdf"); 
			response.setContentType("application/pdf");
			//servletOutputStream.write(bytes, 0, bytes.length);  
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		// System.err.println("Filling time : " + (System.currentTimeMillis() -
		// start));
		return null;
	}

	

	

}