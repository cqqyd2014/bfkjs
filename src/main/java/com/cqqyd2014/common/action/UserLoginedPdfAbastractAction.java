package com.cqqyd2014.common.action;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

public abstract class UserLoginedPdfAbastractAction extends PdfPrintInitAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -980695777034629048L;
	public com.cqqyd2014.util.AjaxSuccessMessage sm;
	public String user_id;
	public String user_name;
	public String com_id;
	public String com_name;
	public String sys_role;
	
	public Session session;
	public Transaction tx;


	public String getUser_id() {
		return user_id;
	}




	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}






	public String getUser_name() {
		return user_name;
	}




	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}




	public String getCom_id() {
		return com_id;
	}




	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}




	public String getCom_name() {
		return com_name;
	}




	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}




	public String getSys_role() {
		return sys_role;
	}




	public void setSys_role(String sys_role) {
		this.sys_role = sys_role;
	}



	String user_login;

	public String getUser_login() {
		return user_login;
	}



	

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}


	public Map<String,Object> session_http;

	public String execute() {
		session_http= ActionContext.getContext().getSession();

		user_login = (String) session_http.get("user_login");
		user_name = (String) session_http.get("user_name");
		user_id = (String) session_http.get("user_id");
		com_id = (String) session_http.get("com_code");
		com_name=(String) session_http.get("com_name");
		sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		
		return null;
		
	}
	public String end_execute() {
		
		return null;
	}




	public com.cqqyd2014.util.AjaxSuccessMessage getSm() {
		return sm;
	}




	public void setSm(com.cqqyd2014.util.AjaxSuccessMessage sm) {
		this.sm = sm;
	}




	public Map<String, Object> getSession_http() {
		return session_http;
	}




	public void setSession_http(Map<String, Object> session_http) {
		this.session_http = session_http;
	}
	
}
