package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderDetailDAO {
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> getArrayListViewsByOrderNo(Session session,String com_id,String order_no){
		String hql = "from VOrderDetail where id.COrderId=:order_no and id.comId=:com_id";

		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> ds = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail>) q
				.list();
		return ds;
	}

}
