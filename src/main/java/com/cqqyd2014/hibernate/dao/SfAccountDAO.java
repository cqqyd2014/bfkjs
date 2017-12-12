package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class SfAccountDAO {
	
	public com.cqqyd2014.hibernate.entities.SfAccount getObjectByComId(Session session,String com_id) {
		String hql="from SfAccount where comId=:com_id";
		Query query = session.createQuery(hql);
		query.setString("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfAccount> cis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfAccount>)query.list();
		if (cis.size()==1){
			return cis.get(0);
		}
		else{
			return null;
		}

	}

}
