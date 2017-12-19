package com.cqqyd2014.order.common;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@SuppressWarnings("serial")
public class OrderPagesInit extends UserLoginedAction {
	

	
	 java.util.LinkedHashMap<String, java.math.BigDecimal> experss_no_len_map;
	 java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> wh_list;
		java.util.LinkedHashMap<String, String> logistics_map;
	java.util.LinkedHashMap<String, String> vehicle_map;	



	public java.util.LinkedHashMap<String, String> getVehicle_map() {
		return vehicle_map;
	}





	public void setVehicle_map(java.util.LinkedHashMap<String, String> vehicle_map) {
		this.vehicle_map = vehicle_map;
	}





	public java.util.LinkedHashMap<String, String> getLogistics_map() {
			return logistics_map;
		}





		public void setLogistics_map(java.util.LinkedHashMap<String, String> logistics_map) {
			this.logistics_map = logistics_map;
		}





		public java.util.LinkedHashMap<String, java.math.BigDecimal> getExperss_no_len_map() {
			return experss_no_len_map;
		}





	public void setExperss_no_len_map(java.util.LinkedHashMap<String, java.math.BigDecimal> experss_no_len_map) {
		this.experss_no_len_map = experss_no_len_map;
	}





	public java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> getWh_list() {
		return wh_list;
	}





	public void setWh_list(java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> wh_list) {
		this.wh_list = wh_list;
	}





	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();

		
		
		session = HibernateSessionFactory.getSession();
		
		try {
			//com.cqqyd2014.hibernate.dao.SysCodeDAO scdao=new com.cqqyd2014.hibernate.dao.SysCodeDAO();
			//com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO lcdao=new com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO();
			
			
			wh_list=com.cqqyd2014.wh.logic.WareHouseLogic.getArrayListModelFromArrayListEntity(com.cqqyd2014.hibernate.dao.WareHouseDAO.getAllByComId(session, com_id));
			
			
			experss_no_len_map=com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO.getBillLengthMap(session);

			logistics_map=com.cqqyd2014.logistics.logic.LogisticsComLogic.getHashMap(com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO.getArrayListEntities(session));
			
			vehicle_map=new java.util.LinkedHashMap<>();
			vehicle_map.put("AIR_", "航空");
			vehicle_map.put("CAR_", "陆运");
			vehicle_map.put("SHIP", "水运");
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
		return "";
	}


	

}