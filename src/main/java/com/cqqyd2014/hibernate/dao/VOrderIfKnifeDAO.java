package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderIfKnifeDAO {
	public boolean check(Session session,String orderNo){
		String hql = "from VOrderIfKnife where id.COrderId=:orderNo";

		Query q = session.createQuery(hql);
		q.setParameter("orderNo", orderNo);
		java.util.List list=q.list();
		if (list.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
}
