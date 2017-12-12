package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VUserSendingDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserSending> getListByComId(Session session,String com_id){
		String hql = "from VUserSending where id.comId=:com_id and id.sendWeighting >=0 order by id.sendingCount,id.sendWeighting desc";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserSending> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserSending>) q.list();
		return oms;
	}
}
