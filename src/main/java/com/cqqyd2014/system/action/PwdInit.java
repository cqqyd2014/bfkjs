package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class PwdInit extends ActionSupport implements  SessionAware{
	private Map<String, Object> session;
	String msg;
	

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String user_name;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Integer I_type=((Integer)this.session.get("TYPE"));
		int type=I_type.intValue();
		user_name=(String)this.session.get("USER_NAME");
		String user_id=(String)this.session.get("USER_ID");
		if (user_name==null){
			return "loginError";
		}
		return super.execute();
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
