package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class EBusinessDAO {
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.Ebusiness> getArrayListEntity(Session session){
		String hql="from Ebusiness";
		Query q = session.createQuery(hql);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Ebusiness> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Ebusiness>)q.list();
		return list;
	}

}
