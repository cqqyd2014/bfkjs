package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class SysCodeDAO {
	
	public String getValue(Session session,String s_id,String s_code){
		String hql="from SysCode where id.SId=:s_id and id.SCode=:s_code";
		Query q = session.createQuery(hql);
		q.setParameter("s_id", s_id);
		q.setParameter("s_code", s_code);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode>) q
				.list();

		if (rs.size()==0){
			System.out.println("不能得到："+s_id+"|"+s_code);
			return null;
		}
		else{
			return rs.get(0).getSValue();
		}
		
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode> getValues(Session session,String s_id){
		String hql="from SysCode where id.SId=:s_id ";
		Query q = session.createQuery(hql);
		q.setParameter("s_id", s_id);

		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode>) q
				.list();
		return rs;
	}
	
	public java.util.LinkedHashMap<String,String> getValuesMap(Session session,String s_id){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode> list=getValues(session,s_id);
		java.util.LinkedHashMap<String,String> map=new java.util.LinkedHashMap<String,String>();
		for (int i=0;i<list.size();i++){
			com.cqqyd2014.hibernate.entities.SysCode sc=list.get(i);
			map.put(sc.getId().getSCode(), sc.getId().getSCode()+"|"+sc.getSValue());
		}
		return map;
	}
	public java.util.LinkedHashMap<String,String> getValuesMap2(Session session,String s_id){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysCode> list=getValues(session,s_id);
		java.util.LinkedHashMap<String,String> map=new java.util.LinkedHashMap<String,String>();
		for (int i=0;i<list.size();i++){
			com.cqqyd2014.hibernate.entities.SysCode sc=list.get(i);
			map.put(sc.getId().getSCode(), sc.getSValue());
		}
		return map;
	}

}
