package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.VUserMenuM;

public class VUserMenuMDAO {
	public static java.util.ArrayList<VUserMenuM> getArrayListViewByUserId(Session session,String user_id) {
		String hql = "from VUserMenuM where id.userId=:user_id order by id.orderId";

		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);

		@SuppressWarnings("unchecked")
		java.util.ArrayList<VUserMenuM> sws = (java.util.ArrayList<VUserMenuM>) q
				.list();
		return sws;
	}
}
