package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VSnCodeFlagDAO {
	
	public java.math.BigDecimal getCurrentFlag(Session session,String com_id){
		String hql="from VSnCodeFlag where id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VSnCodeFlag> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VSnCodeFlag>) q
				.list();
		if (rs.size()>0){
			return rs.get(0).getId().getSnCode();
		}
		else{
			
			return new java.math.BigDecimal("0");
		}
	}

}
