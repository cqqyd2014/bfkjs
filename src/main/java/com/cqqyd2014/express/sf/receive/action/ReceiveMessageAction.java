package com.cqqyd2014.express.sf.receive.action;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import java.net.URLDecoder;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/express/sf")
@SuppressWarnings("serial")
public class ReceiveMessageAction extends ActionSupport {
	
	String xml;
	String response_text;

	public String getResponse_text() {
		return response_text;
	}

	public void setResponse_text(String response_text) {
		this.response_text = response_text;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@Action(value = "receive_message", results = { @Result(name = "success", location = "/WEB-INF/express/sf/response.jsp") })
	public String receive_message() throws Exception {
		
		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			String xml_decode=URLDecoder.decode(xml, "UTF-8");
			
			
			
			tx.commit();
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return SUCCESS;
	}

		

}