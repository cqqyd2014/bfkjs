package com.cqqyd2014.mobile.express;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class ExpressMainInit extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String com_name;
	
	
	public String getCom_name() {
		return com_name;
	}


	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	java.util.LinkedHashMap<String,String> goods_id_map = new java.util.LinkedHashMap<String,String>();  
	
	
	@Override
	public String execute() throws Exception {

				String user_name = (String) this.session.get("USER_NAME");
				String user_id = (String) this.session.get("USER_ID");
				String com_id=(String) this.session.get("com_code");
				com_name=(String) this.session.get("com_name");
				
				Session session = HibernateSessionFactory.getSession();
				Transaction tx = session.beginTransaction();
				try {
					com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
					goods_id_map=gidao.getGoodsInfosMapInUse(session, com_id);
					

					
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


	public java.util.LinkedHashMap<String, String> getGoods_id_map() {
		return goods_id_map;
	}


	public void setGoods_id_map(java.util.LinkedHashMap<String, String> goods_id_map) {
		this.goods_id_map = goods_id_map;
	}



	

}
