package com.cqqyd2014.system.action.ajax;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class SetCompanyDefaultAjax extends ActionSupport implements  SessionAware{
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	String par_code;
	String par_value;
	public String getPar_code() {
		return par_code;
	}
	public void setPar_code(String par_code) {
		this.par_code = par_code;
	}
	public String getPar_value() {
		return par_value;
	}
	public void setPar_value(String par_value) {
		this.par_value = par_value;
	}
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getResult(){
		return inputStream;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Integer I_type=((Integer)this.session.get("TYPE"));
		int type=I_type.intValue();
		String user_name=(String)this.session.get("USER_NAME");
		String user_id=(String)this.session.get("USER_ID");
		String com_id=(String)this.session.get("com_code");

		// TODO Auto-generated method stub

		
		
		Session session = HibernateSessionFactory.getSession();
		//Transaction tx = session.beginTransaction();
		
		try {
			
			java.util.HashMap<String, String> result=new java.util.HashMap();
			
			
			com.cqqyd2014.hibernate.dao.ComParCodeDAO cpcdao=new com.cqqyd2014.hibernate.dao.ComParCodeDAO();
			String b=cpcdao.setValue(session, com_id, par_code, par_value);
			inputStream=new ByteArrayInputStream(b.getBytes("UTF-8"));
			

		//tx.commit();
		// session.close();
	}

	catch (HibernateException e) {
		/*
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		*/
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
		return super.execute();
	}

}
