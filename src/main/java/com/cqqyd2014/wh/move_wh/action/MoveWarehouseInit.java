package com.cqqyd2014.wh.move_wh.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class MoveWarehouseInit  extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
	
	


	java.util.LinkedHashMap wh_map = new java.util.LinkedHashMap();  
	
	
	String move_date;
	


	public java.util.LinkedHashMap getWh_map() {
		return wh_map;
	}



	public void setWh_map(java.util.LinkedHashMap wh_map) {
		this.wh_map = wh_map;
	}



	public String getMove_date() {
		return move_date;
	}



	public void setMove_date(String move_date) {
		this.move_date = move_date;
	}



	@Override
	public String execute() throws Exception {
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis =  new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();
			this.session.put("temp_move_goods", odis);
			

			move_date=com.cqqyd2014.util.DateUtil.JDateToFullString(new java.util.Date());

		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.UserParDAO cpcdao=new com.cqqyd2014.hibernate.dao.UserParDAO();
			
			//System.out.println(type_id);
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao=new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			wh_map=whdao.getUserWareHouseMapByComId(session, com_id);
			
			
			
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
		return super.execute();
	}

}
