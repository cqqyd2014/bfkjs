package com.cqqyd2014.print.pdf.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.cqqyd2014.common.action.PdfPrintInitAbstractAction;
import com.cqqyd2014.common.action.UserLoginedPdfAbastractAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.wh.model.Goods;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ParentPackage("struts-default")
@Namespace("/wh")



public class PrepackageBarcodePrintAction   extends UserLoginedPdfAbastractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public String setDownloadFileName() {
		// TODO Auto-generated method stub
		return "预包装条码.pdf";
	}

	@Override
	public String setJasperPath() {
		// TODO Auto-generated method stub
		return "/jasper/barcode/";
	}

	@Override
	public String setJasperFileName() {
		// TODO Auto-generated method stub
		return "prepackage_barcode.jasper";
	}

	@Override
	public String setImagesPath() {
		// TODO Auto-generated method stub
		return "";
	}
	@Action(value="prepackage_barcode_print", results = { @Result(name = "success", type = "stream", params = {  
	        "contentType", "application/pdf",  
	        "inputName", "inputStream", "contentDisposition",  
	        "attachment;filename=\"${file_name}\"", "bufferSize",  
	        "4096"  }) })  
	@Override
	public String execute_pdf_print() {
		// TODO Auto-generated method stub
		super.execute();
		return SUCCESS;
	}

	@Override
	public ArrayList<? extends Object> initializeBeanArray() {
		// TODO Auto-generated method stub
		session = HibernateSessionFactory.getSession();
		java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageM> ppms=null;
		try {
		
		com.cqqyd2014.hibernate.dao.PrePackageDAO gdao=new com.cqqyd2014.hibernate.dao.PrePackageDAO();
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> goodsList=gdao.getUnPrintPreSn(session, com_id, user_id);
		
		ppms= com.cqqyd2014.wh.logic.PrePackageMLogic.getArrayListModelFromArrayListEntity(goodsList);
		}
		
		catch (Exception e) {
					
					
					
					System.out.println(e.getMessage());
					e.printStackTrace();
					
				}
				

				 finally {
						HibernateSessionFactory.closeSession();
					}
		return ppms;
	}

}