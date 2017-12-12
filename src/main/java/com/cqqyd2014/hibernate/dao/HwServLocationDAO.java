package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class HwServLocationDAO {
	
	public String getHW(Session session,String province_name,String com_id){
		String hql="from HwServLocation where provinceName=:province_name and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("province_name", province_name);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.HwServLocation> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.HwServLocation>) q
				.list();
		if (rs.size()==0){
			System.out.println(province_name+"没有按地域省份分配的仓库");
			return null;
		}
		else{
			return rs.get(0).getHw();
		}
		
	}

}
