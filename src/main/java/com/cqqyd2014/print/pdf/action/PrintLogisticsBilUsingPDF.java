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


import com.cqqyd2014.express.common.ExpressBillModel;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

public abstract class PrintLogisticsBilUsingPDF  extends ActionSupport implements SessionAware,ServletResponseAware, ServletContextAware {

	HttpServletResponse response;
	ServletContext servletContext;
	InputStream inputStream;
	//String orderNo;
	String bill;
	
	String com_id;
	private Map<String, Object> session;
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}


	java.util.HashMap<String, Object> pars=new java.util.HashMap<>();
	String file_name;

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}



	String logistics;
	String logistics_type;

	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getLogistics_type() {
		return logistics_type;
	}
	public void setLogistics_type(String logistics_type) {
		this.logistics_type = logistics_type;
	}
	public java.util.List<com.cqqyd2014.express.common.ExpressBillModel> getEbs() {
		return ebs;
	}
	public void setEbs(java.util.List<com.cqqyd2014.express.common.ExpressBillModel> ebs) {
		this.ebs = ebs;
	}
	public ServletContext getServletContext() {
		return servletContext;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}



	java.util.List<com.cqqyd2014.express.common.ExpressBillModel> ebs;

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
		com_id = (String) this.session.get("com_code");
		long start = System.currentTimeMillis();
		try {

			String reportDestination = null;

			reportDestination = servletContext.getRealPath("/jasper/" + logistics+bill + ".jasper");

			ServletOutputStream servletOutputStream = response.getOutputStream();
			// 获得jasper报表文件的输入流
			InputStream inputStream = new FileInputStream(reportDestination);

			JRDataSource dataSource = createReportDataSource();
			pars.put("path_of_report", servletContext.getRealPath("/jasper/"));
			JasperRunManager.runReportToPdfStream(inputStream, servletOutputStream, pars, dataSource);
			// 设置格式
			response.setContentType("application/pdf");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(file_name, "UTF-8") + ".pdf");
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.err.println("Filling time : " + (System.currentTimeMillis() -
		// start));
		return null;
	}

	private JRDataSource createReportDataSource() throws Exception {
		JRBeanArrayDataSource dataSource;
		ExpressBillModel[] reportRows = initializeBeanArray();
		dataSource = new JRBeanArrayDataSource(reportRows);
		return dataSource;
	}

	public abstract  ExpressBillModel[] initializeBeanArray()  throws Exception;

}