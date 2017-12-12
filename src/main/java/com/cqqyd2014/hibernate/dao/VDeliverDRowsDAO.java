package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VDeliverDRowsDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverDRows> getRows(Session session,String order_no){
		String hql="from VDeliverDRows where id.orderNo=:order_no";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);

		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverDRows>)q.list();
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverDRows> getRowsTemp(Session session,String order_no){
		String hql="from VDeliverDRows where id.orderNo=:order_no and id.seq=\'0000\'";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);

		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverDRows>)q.list();
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverDRows> getRowsSeq(Session session,String order_no,String seq){
		String hql="from VDeliverDRows where id.orderNo=:order_no and id.seq=:seq'";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("seq", seq);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverDRows>)q.list();
	}

}
