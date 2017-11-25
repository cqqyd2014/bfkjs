package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class SupplyInfoDAO {
	
	//得到供应商列表
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.SupplyInfo> getSupplyList(Session session,String com_id){
		
		String hql="from SupplyInfo where id.comId=:com_id and effective='是'";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SupplyInfo> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SupplyInfo>) q
				.list();
		return rs;
		
	}
	public java.util.LinkedHashMap<String, String> getSupplyMap(Session session,String com_id){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SupplyInfo> sis=getSupplyList(session,com_id);
		java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();
		for (int i=0;i<sis.size();i++){
			com.cqqyd2014.hibernate.entities.SupplyInfo si=sis.get(i);
			map.put(si.getId().getSupplyId(), si.getSupplyName());
			
		}
		return map;
	}
}
