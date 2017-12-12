package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class LogisticsFeeDAO {
	public com.cqqyd2014.hibernate.entities.LogisticsFee getEntityByLogisticsVehicleNum(Session session,String com_id,String logistics_code,String vehicle){
		java.math.BigDecimal n=new java.math.BigDecimal("0");
		String hql="from LogisticsFee where id.comId=:com_id and id.logistics=:logistics_code and id.vehicle=:vehicle";
		Query q = session.createQuery(hql);
		q.setParameter("logistics_code", logistics_code);
		q.setParameter("vehicle", vehicle);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsFee> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsFee>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
		
	}

}
