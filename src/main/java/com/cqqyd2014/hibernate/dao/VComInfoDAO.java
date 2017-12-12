package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public final class VComInfoDAO {

	
	
	//得到单位列表
	
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComInfo> getArrayListEntity(Session session){
		String hql="from VComInfo where id.effective=true";
		Query query = session.createQuery(hql);
		
		
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComInfo> cis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComInfo>)query.list();
		return cis;
	}
	
	

	
	
	
	
	
	public  static com.cqqyd2014.hibernate.entities.VComInfo getViewByComId(Session session ,String com_id){
		String hql="from VComInfo where id.CId=:c_id and id.effective=true";
		Query query = session.createQuery(hql);
		query.setParameter("c_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComInfo> cis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComInfo>)query.list();
		if (cis.size()>0){
			return cis.get(0);
		}
		else{
			System.out.println("找不到这个单位信息："+com_id);
			return null;
		}
		
	}
}
