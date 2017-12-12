package com.cqqyd2014.tools.logistics.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class LogisticsInit extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}



	java.util.HashMap logistics_map = new java.util.LinkedHashMap();



	public java.util.HashMap getLogistics_map() {
		return logistics_map;
	}

	public void setLogistics_map(java.util.HashMap logistics_map) {
		this.logistics_map = logistics_map;
	}

	public void setLogistics_map(java.util.LinkedHashMap logistics_map) {
		this.logistics_map = logistics_map;
	}

	@Override
	public String execute() throws Exception {
		

		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id = (String) this.session.get("com_code");

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO lcdao=new com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO();
			logistics_map = lcdao.getNameMap(session);
			

			tx.commit();
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
		return super.execute();
	}

}