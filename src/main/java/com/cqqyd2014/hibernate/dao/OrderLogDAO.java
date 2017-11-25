package com.cqqyd2014.hibernate.dao;
import org.hibernate.Session;



public class OrderLogDAO {
	
	
	/*
	 * 0001新增订单
	 * 0002订单发出
	 * 0003订单退货
	 * 0004删除订单
	 * 
	 * 
	 */
	public void addLog(Session session, String order_no, String memo,String type,String com_id,String user_id) {
		
		com.cqqyd2014.hibernate.entities.OrderLog ol = new com.cqqyd2014.hibernate.entities.OrderLog();
		ol.setId(new com.cqqyd2014.hibernate.entities.OrderLogId(order_no,com_id,new java.util.Date()));
		ol.setMemo(memo);
		ol.setType(type);
		ol.setUserId(user_id);
		
		//ol.setComId(com_id);
		session.save(ol);
		ol=null;
		
		

	}

}