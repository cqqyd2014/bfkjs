package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class SysroleDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysRole> getArrayListEntityByCom(Session session,String com_id){
		String hql="from SysRole where id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysRole> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysRole>)q.list();
		return list;
		
	}

}
