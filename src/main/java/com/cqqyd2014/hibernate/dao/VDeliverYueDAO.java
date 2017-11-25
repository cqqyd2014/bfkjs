package com.cqqyd2014.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class VDeliverYueDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverYue> getViewByOrderNo(Session session,String order_no){
		String hql="from VDeliverYue where id.orderNo=:order_no";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverYue>)q.list();
	}
}
