package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public final class VOrderFromUserLenDAO {
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen> getArrayViewByUserID(Session session,String com_id,String userid){
		
		String hql="from VOrderFromUserLen where id.userId=:userid and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen>) q
				.list();
		
		return rs;
		
	}
	//某个平台可能存在多个订单号长度
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen> getArrayListViewByUserIDOrderFrom(Session session,String com_id,String userid,String order_from){
		
		String hql="from VOrderFromUserLen where id.userId=:userid and id.comId=:com_id and id.orderTypeCode=:order_from";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		q.setParameter("order_from", order_from);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen>) q
				.list();
		
		return rs;
		
	}

}
