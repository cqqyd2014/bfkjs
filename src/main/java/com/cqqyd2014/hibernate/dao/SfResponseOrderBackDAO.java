package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class SfResponseOrderBackDAO {
	public com.cqqyd2014.hibernate.entities.SfResponseOrderBack
	getEntityByOrderSeq(Session session,String order_no,String seq){

		
		String hql="from SfResponseOrderBack where orderid=:orderid";
		
		//System.out.println(hql);
		Query q = session.createQuery(hql);
		q.setParameter("orderid", order_no+"_"+seq);

		//q.setParameter("storageId", storageId);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfResponseOrderBack> ofs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfResponseOrderBack>)q.list();
		if (ofs.size()==0){
			return null;
		}
		else{
			return ofs.get(0);
		}
	}

}
