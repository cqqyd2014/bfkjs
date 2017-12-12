package com.cqqyd2014.finance.admin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AccountAdminInit extends ActionSupport implements  SessionAware{
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceAccount> fas=new java.util.ArrayList<>();
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceAccount> getFas() {
		return fas;
	}
	public void setFas(java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceAccount> fas) {
		this.fas = fas;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Integer I_type=((Integer)this.session.get("TYPE"));
		int type=I_type.intValue();
		
		String user_name=(String)this.session.get("USER_NAME");
		com_id=(String)this.session.get("com_code");
		com.cqqyd2014.hibernate.dao.FinanceAccountDAO fadao=new com.cqqyd2014.hibernate.dao.FinanceAccountDAO();
		
		
		return super.execute();
	}

}
