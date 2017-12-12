package com.cqqyd2014.wh.inventory.bywhgoodsid.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class InventoryByWhGoodsIdInit  extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	


	java.util.LinkedHashMap giList = new java.util.LinkedHashMap();  
	





	

	java.util.LinkedHashMap whList = new java.util.LinkedHashMap();  

	

	public java.util.LinkedHashMap getGiList() {
		return giList;
	}

	public void setGiList(java.util.LinkedHashMap giList) {
		this.giList = giList;
	}








	public java.util.LinkedHashMap getWhList() {
		return whList;
	}

	public void setWhList(java.util.LinkedHashMap whList) {
		this.whList = whList;
	}

	

	@Override
	public String execute() throws Exception {
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis =  new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();
			this.session.put("temp_inventory_by_wh_goods_id", odis);
			



		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {

			
			//System.out.println(type_id);
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao=new com.cqqyd2014.hibernate.dao.WareHouseDAO();

			
			whList=whdao.getUserWareHouseMapByComId(session, com_id);
			
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			giList=gidao.getGoodsInfosMapInUse(session, com_id);
			
			
			
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