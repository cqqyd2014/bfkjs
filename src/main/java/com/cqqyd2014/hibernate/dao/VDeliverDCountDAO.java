package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VDeliverDCountDAO {
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliveredCount> getCountList(Session session,String com_id,String order_no){
		String hql="from VDeliveredCount where id.orderNo=:order_no and id.com_id=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);

		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliveredCount>)q.list();
		
	}

}
