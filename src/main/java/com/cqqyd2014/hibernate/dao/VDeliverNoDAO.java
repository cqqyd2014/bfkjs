package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VDeliverNoDAO {
	public String getNo(Session session ,String order_no,String seq){
		String hql="from VDeliverNo where id.orderNo=:order_no and id.seq=:seq";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("seq", seq);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverNo> nos=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverNo>)q.list();
		return nos.get(0).getId().getDeliverNo();
	}

}
