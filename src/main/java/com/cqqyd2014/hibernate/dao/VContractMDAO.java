package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VContractMDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractM> getListAll(Session session,String com_id){
		String hql="from VContractM where id.comId=:com_id order by id.paperDat desc";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);

		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractM>)q.list();
	}

}
