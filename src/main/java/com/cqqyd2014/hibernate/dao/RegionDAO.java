package com.cqqyd2014.hibernate.dao;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.cqqyd2014.common.hibernate.GetModelFromEntityViewDAO;
import com.cqqyd2014.system.model.Region;

public final class RegionDAO extends GetModelFromEntityViewDAO{
	
	
	public java.math.BigDecimal getMaxRegionId(Session session){
		String hql="select max(regionId) from Region";
		@SuppressWarnings("rawtypes")
		Query q=session.createQuery(hql);
		return  (java.math.BigDecimal)(q.uniqueResult());
	}

	public com.cqqyd2014.system.model.Region getRegion(Session session,java.math.BigDecimal id){
		String hql="from Region where regionId=:id";
		@SuppressWarnings("rawtypes")
		Query q=session.createQuery(hql);
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> regions=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region>)q.list();
		if (regions.size()==0){
			return null;
		}
		else{
			com.cqqyd2014.hibernate.entities.Region h=regions.get(0);
			com.cqqyd2014.system.model.Region m=(com.cqqyd2014.system.model.Region)getModelFromViewEntity(h);
			return m;
		}
	}
	
	//得到下级行政单位
	@SuppressWarnings("unchecked")
	public  java.util.ArrayList<com.cqqyd2014.system.model.Region> getSubRegion(Session session,java.math.BigDecimal pid){
		String hql="from Region where parentId=:pid";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql);
		q.setParameter("pid",pid);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> regions=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region>)q.list();
		if (regions.size()==0){
			//如果该地址没有下级单位，需要增加一个默认选项“市辖区”，不会有“省下面没有市的情况，所以不会有”省辖区“
			com.cqqyd2014.system.model.Region new_region=new com.cqqyd2014.system.model.Region();
			new_region.setParent_id(pid);
			new_region.setRegion_name("市辖区");
			//得到最大的region_id
			new_region.setRegion_id(getMaxRegionId(session).add(new java.math.BigDecimal("1")));
			save(session,new_region);
			
				java.util.ArrayList<com.cqqyd2014.system.model.Region> districts=new  java.util.ArrayList<com.cqqyd2014.system.model.Region>();
				districts.add(new_region);
				return districts;
				
			
			
		}
		else{
			return (java.util.ArrayList<com.cqqyd2014.system.model.Region>)getArrayListModelFromArrayListViewEntity(regions);
		}
		
	}


	

	@Override
	public <T> void save(Session session, T t) {
		// TODO Auto-generated method stub
		com.cqqyd2014.system.model.Region m=(com.cqqyd2014.system.model.Region)t;
		com.cqqyd2014.hibernate.entities.Region h=new com.cqqyd2014.hibernate.entities.Region();
		h.setParentId(m.getParent_id());
		h.setRegionId(m.getRegion_id());
		h.setRegionName(m.getRegion_name());
		session.save(h);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getModelFromViewEntity(S s) {
		// TODO Auto-generated method stub
		com.cqqyd2014.system.model.Region m=new com.cqqyd2014.system.model.Region();
		com.cqqyd2014.hibernate.entities.Region h=(com.cqqyd2014.hibernate.entities.Region)s;
		m.setRegion_id(h.getRegionId());
		m.setRegion_name(h.getRegionName());
		m.setParent_id(h.getParentId());
		
		return (T)m;
	}
	
	
	

}
