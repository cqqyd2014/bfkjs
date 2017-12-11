package com.cqqyd2014.print.pdf.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;


import com.cqqyd2014.hibernate.HibernateSessionFactory;


import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;

@ParentPackage("struts-default")
@Namespace("/print")

@Results({ @Result(name = "success", type = "stream", params = { "contentType", "application/pdf", "inputName",
		"inputStream", "contentDisposition", "attachment;filename=\"${file_name}\"", "bufferSize", "4096" }) })

public class TestPrint extends ActionSupport implements ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	ServletContext servletContext;
	InputStream inputStream;


	public String getFile_name() throws UnsupportedEncodingException {
		return URLEncoder.encode("发货单" + com.cqqyd2014.util.DateUtil.JDateToFullCompatString(new java.util.Date()),
				"UTF-8") + ".pdf";
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

	public InputStream getInputStream() {
		
		try {

			String reportDestination = null;

			reportDestination = servletContext.getRealPath("/jasper/BillM_A4.jasper");

			// 获得jasper报表文件的输入流
			InputStream inputStreamJasper = new FileInputStream(reportDestination);

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			// parameters.put("param_deliverbill", bill);
			java.util.ArrayList<gov.cqaudit.finance.bills.model.BillM> bms=new java.util.ArrayList<>();
			bms.add(new gov.cqaudit.finance.bills.model.BillM());

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bms);
			byte[] bytes=null;
			try {
				System.out.println("ddd1");
			bytes=JasperRunManager.runReportToPdf(inputStreamJasper, parameters, dataSource);
			System.out.println("ddd2");
			}
			catch(JRFontNotFoundException e) {
				System.out.println("找不到字体错误"+e.toString());
			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			catch(Exception e) {
				System.out.println("新错误:"+e.toString());
			}
			System.out.println("ddd3");
			inputStream = new ByteArrayInputStream(bytes);
			

		}

		catch (Exception e) {

			

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

	@Action(value = "bill_print", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "application/pdf", "inputName", "inputStream", "contentDisposition",
			"attachment;filename=\"${file_name}\"", "bufferSize", "4096" }) })
	public String bill_print() {

		return SUCCESS;
	}

	

}