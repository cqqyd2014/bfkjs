package com.cqqyd2014.hibernate.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.LogisticsCompany;


public final class LogisticsCompanyDAO {
	public static  com.cqqyd2014.hibernate.entities.LogisticsCompany getObjectById(Session session,String id) throws Exception{
		 CriteriaBuilder builder=session.getCriteriaBuilder();    //创建一个Criteria生成器
	        CriteriaQuery<com.cqqyd2014.hibernate.entities.LogisticsCompany> criteria = builder.createQuery(com.cqqyd2014.hibernate.entities.LogisticsCompany.class);    //创建一个CriteriaQuery对象，指定至返回值类型为Person对象
	        Root<com.cqqyd2014.hibernate.entities.LogisticsCompany> root=criteria.from(com.cqqyd2014.hibernate.entities.LogisticsCompany.class);//指定查询的root类型
	        criteria.select(root);//指定返回的结果集中的类型
	        criteria.where(builder.equal(root.get("logisticsId"), id));//指定查询条件
	        List<com.cqqyd2014.hibernate.entities.LogisticsCompany> list=session.createQuery(criteria).getResultList();//通过CriteriaQuery创建查询语句，并查询
		
		
		if (list.size()==0){
			throw new Exception("不能找到"+id+"对应的物流供应商");
		}
		else{
			return list.get(0);
		}
	}
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsCompany> getArrayListEntities(Session session){
		 CriteriaBuilder builder=session.getCriteriaBuilder();    //创建一个Criteria生成器
	        CriteriaQuery<com.cqqyd2014.hibernate.entities.LogisticsCompany> criteria = builder.createQuery(com.cqqyd2014.hibernate.entities.LogisticsCompany.class);    //创建一个CriteriaQuery对象，指定至返回值类型为Person对象
	        Root<com.cqqyd2014.hibernate.entities.LogisticsCompany> root=criteria.from(com.cqqyd2014.hibernate.entities.LogisticsCompany.class);//指定查询的root类型
	        criteria.select(root);//指定返回的结果集中的类型
	        //criteria.where(builder.equal(root.get("logisticsId"), id));//指定查询条件
	        java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsCompany> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsCompany>)session.createQuery(criteria).getResultList();//通过CriteriaQuery创建查询语句，并查询
		
		return list;
	}
	public static  java.util.LinkedHashMap<String, String> getNameMap(Session session){
		java.util.ArrayList<LogisticsCompany> list=getArrayListEntities(session);
		java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();
		for (int i=0;i<list.size();i++){
			LogisticsCompany lc=list.get(i);
			map.put(lc.getLogisticsId(), lc.getLogisticsName());
		}
		return map;
	}
	public static java.util.LinkedHashMap<String, java.math.BigDecimal> getBillLengthMap(Session session){
		java.util.ArrayList<LogisticsCompany> list=getArrayListEntities(session);
		java.util.LinkedHashMap<String, java.math.BigDecimal> map=new java.util.LinkedHashMap<>();
		for (int i=0;i<list.size();i++){
			LogisticsCompany lc=list.get(i);
			map.put(lc.getLogisticsId(), lc.getLogisticsBillLength());
		}
		return map;
	}

}
