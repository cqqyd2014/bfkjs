package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class IntoWhMDAO {
	public com.cqqyd2014.hibernate.entities.IntoWhM getObjectByUuid(Session session,String com_id,String intoWhUuid){
		String hql="from IntoWhM where id.uuid=:intoWhUuid and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("intoWhUuid",intoWhUuid);

		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.IntoWhM> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			return list.get(0);
		}
	}
}
