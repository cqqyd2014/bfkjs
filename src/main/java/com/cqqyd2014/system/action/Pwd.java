package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class Pwd extends ActionSupport implements  Preparable,SessionAware{
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		user_name=(String)this.session.get("USER_NAME");
	}
	private Map<String, Object> session;
	
String pwdOld;
String pwdNew1;
String pwdNew2;
	public String getPwdOld() {
	return pwdOld;
}
public void setPwdOld(String pwdOld) {
	this.pwdOld = pwdOld;
}
public String getPwdNew1() {
	return pwdNew1;
}
public void setPwdNew1(String pwdNew1) {
	this.pwdNew1 = pwdNew1;
}
public String getPwdNew2() {
	return pwdNew2;
}
public void setPwdNew2(String pwdNew2) {
	this.pwdNew2 = pwdNew2;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
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
		
		user_name=(String)this.session.get("USER_NAME");
		String user_id=(String)this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");
		if (user_name==null){
			return "loginError";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			sudao.updatePwd(session, user_id, pwdNew1,com_id);
			sudao=null;
			tx.commit();
		}
		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		ActionContext.getContext().put("msg","修改密码成功");
		return super.execute();
	}
	@Override
	public void validate() {
		String user_id=(String)this.session.get("USER_ID");
		String user=(String)this.session.get("USER");
		
		Integer I_type = ((Integer) this.session.get("TYPE"));
		int type = I_type.intValue();
		String user_name = (String) this.session.get("USER_NAME");
		
		String com_id=(String) this.session.get("com_code");
		// TODO Auto-generated method stub
		this.clearFieldErrors();
		// TODO Auto-generated method stub

		//不等
		if (this.getPwdOld().trim().length()==0||this.getPwdOld()==null){
			this.addFieldError("userName",  
			         "请输入原密码");  
		}
		if (this.getPwdNew1().trim().length()==0||this.getPwdNew1()==null){
			this.addFieldError("userName",  
			         "请输入新密码");  
		}
		if (this.getPwdNew2().trim().length()==0||this.getPwdNew2()==null){
			this.addFieldError("userName",  
			         "请再次输入新密码");  
		}
		if (!this.getPwdNew1().equals(this.getPwdNew1())){
			this.addFieldError("userName",  
			         "两次输入的新密码不相同");  
		}
		boolean b=false;
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			if (sudao.getEntiyByLogin(session, user, com_id) !=null){
				b=true;
			}
			sudao=null;
			tx.commit();
		}
		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			HibernateSessionFactory.closeSession();
		}
		if (!b){
			this.addFieldError("userName",  
			         "原始密码错误");  
		}
		super.validate();
	}

}
