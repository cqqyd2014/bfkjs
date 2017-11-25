package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VUserPickingDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPicking> getListByComId(Session session,String com_id){
		String hql = "from VUserPicking where id.comId=:com_id and id.pickupWeighting >=0 order by id.pickingCount,id.pickupWeighting desc";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPicking> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPicking>) q.list();
		return oms;
	}
}
