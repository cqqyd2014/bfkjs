package com.cqqyd2014.hibernate.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

public class LogisticsVehicleDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsVehicle> getArrayEntities(Session session){
		 CriteriaBuilder builder=session.getCriteriaBuilder();    //创建一个Criteria生成器
	        CriteriaQuery<com.cqqyd2014.hibernate.entities.LogisticsVehicle> criteria = builder.createQuery(com.cqqyd2014.hibernate.entities.LogisticsVehicle.class);    //创建一个CriteriaQuery对象，指定至返回值类型为Person对象
	        Root<com.cqqyd2014.hibernate.entities.LogisticsVehicle> root=criteria.from(com.cqqyd2014.hibernate.entities.LogisticsVehicle.class);//指定查询的root类型
	        criteria.select(root);//指定返回的结果集中的类型
	        //criteria.where(builder.equal(root.get("logisticsId"), id));//指定查询条件
	        java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsVehicle> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsVehicle>)session.createQuery(criteria).getResultList();//通过CriteriaQuery创建查询语句，并查询
		
		return list;
	}
}
