package com.cqqyd2014.hibernate.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;

public final class LogisticsVehicleFeeDAO {
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee> getArrayListEntities(Session session,String logistics_code,boolean not_air,String com_id){
		String hql=null;
		if (not_air){
			hql="from VLogisticsVehicleFee where id.vehicle<>'AIR_' and id.comId=:com_id and id.logistics=:logistics_code order by id.vehicleOrder";
		}
		else{
			hql="from VLogisticsVehicleFee where  id.comId=:com_id and id.logistics=:logistics_code order by id.vehicleOrder";
		}
		
		Query q = session.createQuery(hql);
		q.setParameter("logistics_code", logistics_code);
		
		q.setParameter("com_id", com_id);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee>) q
				.list();
		return rs;
	}
	public static com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee getViewByLogisticsVehicle(Session session,String com_id,String logistics_code,String vehicle_code){
		java.math.BigDecimal n=new java.math.BigDecimal("0");
		String hql="from VLogisticsVehicleFee where id.comId=:com_id and id.logistics=:logistics_code and id.vehicle=:vehicle_code";
		Query q = session.createQuery(hql);
		q.setParameter("logistics_code", logistics_code);
		q.setParameter("vehicle_code", vehicle_code);
		q.setParameter("com_id", com_id);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
		
	}
}
