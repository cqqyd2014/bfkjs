package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderFromUserLenDAO {
	public  java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen> getArrayViewByUserID(Session session,String com_id,String userid){
		
		String hql="from VOrderFromUserLen where id.userId=:userid and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen>) q
				.list();
		
		return rs;
		
	}

}
