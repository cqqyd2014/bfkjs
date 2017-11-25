package com.cqqyd2014.wh.current_wh.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class CurrentWhInit extends ActionSupport implements SessionAware {
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	private Map<String, Object> session;
	

	
	
	String com_id;
	


	@Override
	public String execute() throws Exception {

		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		com_id=(String) this.session.get("com_code");
		Session session = HibernateSessionFactory.getSession();
		

		return SUCCESS;
		
	}



	public String getCom_id() {
		return com_id;
	}



	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}


}

