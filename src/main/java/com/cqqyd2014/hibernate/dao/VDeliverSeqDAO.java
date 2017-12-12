package com.cqqyd2014.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class VDeliverSeqDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverSeq> getDeliverListSeq(Session session,String order_no,String seq){
		String hql="from VDeliverSeq where id.COrderId=:order_no and id.seq=:seq";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("seq", seq);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverSeq>)q.list();
	}

}
