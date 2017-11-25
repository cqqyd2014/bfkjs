package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VComGoodsDAO {
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComGoods> getComGoods(Session session ,String com_id){

		String hql="from VComGoods where id.comId=:com_id order by id.CName";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComGoods>)q.list();
	}

}
