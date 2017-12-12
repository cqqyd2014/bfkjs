package com.cqqyd2014.tools.big_event.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class BigEventInit extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	String b_date;

	

	public String getB_date() {
		return b_date;
	}



	public void setB_date(String b_date) {
		this.b_date = b_date;
	}



	@Override
	public String execute() throws Exception {
		

		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id = (String) this.session.get("com_code");
		b_date = com.cqqyd2014.util.DateUtil.JDateToSimpleString(new java.util.Date());

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			


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