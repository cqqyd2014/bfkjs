package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ComInfo extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	com.cqqyd2014.hibernate.entities.ComInfo ci;

	public com.cqqyd2014.hibernate.entities.ComInfo getCi() {
		return ci;
	}

	public void setCi(com.cqqyd2014.hibernate.entities.ComInfo ci) {
		this.ci = ci;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Integer I_type = ((Integer) this.session.get("TYPE"));
		int type = I_type.intValue();
		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			com.cqqyd2014.hibernate.dao.ComInfoDAO cidao=new com.cqqyd2014.hibernate.dao.ComInfoDAO();
			com.cqqyd2014.hibernate.entities.ComInfo oldci=cidao.getComInfo(session, com_id);
			oldci.setContactTell(ci.getContactTell());
			
			
			
			tx.commit();
		ActionContext.getContext().put("msg","修改公司信息成功");
	}
	catch (HibernateException e) {
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		System.out.println(e.getMessage());
		e.printStackTrace();
		ActionContext.getContext().put("msg","修改公司信息出错");
		
	} finally {
		HibernateSessionFactory.closeSession();
	}
		return super.execute();
	}
	

}
