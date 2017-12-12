package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderFromDAO {
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFrom> getArrayViewByComId(Session session,String com_id){
		String hql="from VOrderFrom where id.comId=:com_id";
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFrom> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFrom>) q
				.list();
		
		return rs;
	}
	
	public com.cqqyd2014.hibernate.entities.VOrderFrom getViewByComIdType(Session session,String com_id,String type){
		String hql="from VOrderFrom where id.comId=:com_id and id.orderTypeCode=:type";
		Query q = session.createQuery(hql);
		q.setParameter("type", type);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFrom> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFrom>) q
				.list();
		if (rs.size()>0) {
			return rs.get(0);
		}
		else {
			return null;
		}
		
	}

}
