package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class LogistiscBillPrintTempletDAO {
	public com.cqqyd2014.hibernate.entities.LogisticsBillPrintTemplet getObject(Session session,String logistics_code,String logistics_bill_code){
		String hql="from LogisticsBillPrintTemplet where id.logisticsBillCode=:logistics_bill_code and id.logisticsCode=:logistics_code  and effective=true";
		Query q = session.createQuery(hql);
		q.setParameter("logistics_code", logistics_code);
		q.setParameter("logistics_bill_code", logistics_bill_code);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsBillPrintTemplet> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsBillPrintTemplet>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			
			return null;
		}
	}

	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsBillPrintTemplet> getArrayEntitesByLogisticsType(Session session,String logistics_code,String print_type){
		String hql="from LogisticsBillPrintTemplet where id.logisticsCode=:logistics_code and printType=:print_type and effective=true order by id.logisticsBillCode";
		Query q = session.createQuery(hql);
		q.setParameter("logistics_code", logistics_code);
		q.setParameter("print_type", print_type);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsBillPrintTemplet> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsBillPrintTemplet>) q
				.list();
		return rs;
	}
}
