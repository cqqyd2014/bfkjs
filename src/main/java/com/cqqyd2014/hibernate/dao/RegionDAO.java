package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class RegionDAO {

	public com.cqqyd2014.hibernate.entities.Region getCity(Session session,java.math.BigDecimal id){
		String hql="from Region where regionId=:id";
		Query q=session.createQuery(hql);
		q.setParameter("id", id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> citys=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region>)q.list();
		if (citys.size()==0){
			return null;
		}
		else{
			return citys.get(0);
		}
	}
	
	//得到下级行政单位，主要是“区一级”
	public  java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> getSubCity(Session session,String pid){
		String hql="from Region where parentId=:pid";
		Query q = session.createQuery(hql);
		q.setParameter("pid",new java.math.BigDecimal(pid));
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> rs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region>)q.list();
		if (rs.size()==0){
			//该地址为‘市’
			com.cqqyd2014.hibernate.entities.Region r=getCity(session,new java.math.BigDecimal(pid));
			if (r.getParentId().intValue()!=1){
				//确定为“市级别”，生成默认区
				com.cqqyd2014.hibernate.entities.Region district=new com.cqqyd2014.hibernate.entities.Region();
				district.setRegionName("市辖区");
				district.setRegionCode("1000000");
				district.setRegionId(new java.math.BigDecimal("9999999"));
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> districts=new  java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region>();
				districts.add(district);
				return districts;
				
			}
			
		}
		return rs;
	}
	public  String getNameById(Session session,String id){
		String hql="from Region where regionId=:id";
		Query q = session.createQuery(hql);
		q.setParameter("id",new java.math.BigDecimal(id));
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> rs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region>)q.list();
		if (rs.size()>0){
			String regionName=rs.get(0).getRegionName();
			return regionName;
		}
		else{
			return "";
		}
	}
	
	
	

}
