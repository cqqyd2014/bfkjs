package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.*;
import com.opensymphony.xwork2.ActionSupport;

public class SystemInfo extends ActionSupport implements  SessionAware{

	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		user_name=(String)this.session.get("USER_NAME");
		userid=(String)this.session.get("USER_ID");
		String com_id=(String)this.session.get("com_code");
		if (user_name==null){
			return "loginError";
		}
		
		
		Session session = HibernateSessionFactory.getSession();
    	Transaction tx = session.beginTransaction();
    	try{
    		com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
		user=sudao.getUserFromUserid(session, userid,com_id);
		sudao=null;
		tx.commit();
    	}
    	catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
    	finally {
			HibernateSessionFactory.closeSession();
		}
		return super.execute();
	}
	String user_name;
	String userid;
	com.cqqyd2014.hibernate.entities.SysUser user;
	public com.cqqyd2014.hibernate.entities.SysUser getUser() {
		return user;
	}
	public void setUser(com.cqqyd2014.hibernate.entities.SysUser user) {
		this.user = user;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Map<String, Object> getSession() {
		return session;
	}

}
