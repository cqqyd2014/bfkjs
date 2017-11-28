package com.cqqyd2014.order.common;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrderPagesInit extends ActionSupport {
	int pageSize;// 每页的记录数
	 java.util.LinkedHashMap<String, String> logisticsList;
	
	 java.util.LinkedHashMap<String, java.math.BigDecimal> experss_no_len_map;
	 java.util.LinkedHashMap<String, String> wh_map;
		String vehicle;
		java.util.LinkedHashMap<String, String> vehicle_map;
		String default_warehouse;




	public String getDefault_warehouse() {
			return default_warehouse;
		}


		public void setDefault_warehouse(String default_warehouse) {
			this.default_warehouse = default_warehouse;
		}


	public String getVehicle() {
			return vehicle;
		}


		public void setVehicle(String vehicle) {
			this.vehicle = vehicle;
		}


		public java.util.LinkedHashMap<String, String> getVehicle_map() {
			return vehicle_map;
		}


		public void setVehicle_map(java.util.LinkedHashMap<String, String> vehicle_map) {
			this.vehicle_map = vehicle_map;
		}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public java.util.LinkedHashMap<String, String> getLogisticsList() {
		return logisticsList;
	}


	public void setLogisticsList(java.util.LinkedHashMap<String, String> logisticsList) {
		this.logisticsList = logisticsList;
	}


	public java.util.LinkedHashMap<String, java.math.BigDecimal> getExperss_no_len_map() {
		return experss_no_len_map;
	}


	public void setExperss_no_len_map(java.util.LinkedHashMap<String, java.math.BigDecimal> experss_no_len_map) {
		this.experss_no_len_map = experss_no_len_map;
	}


	public java.util.LinkedHashMap<String, String> getWh_map() {
		return wh_map;
	}


	public void setWh_map(java.util.LinkedHashMap<String, String> wh_map) {
		this.wh_map = wh_map;
	}


	public String getDefault_logistics() {
		return default_logistics;
	}


	public void setDefault_logistics(String default_logistics) {
		this.default_logistics = default_logistics;
	}


	String default_logistics;
	String userid;
	public void init() throws Exception{

		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		 userid= (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.SysCodeDAO scdao=new com.cqqyd2014.hibernate.dao.SysCodeDAO();
			com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO lcdao=new com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO();
			
			
			logisticsList=lcdao.getNameMap(session);
			
			experss_no_len_map=lcdao.getBillLengthMap(session);

			com.cqqyd2014.hibernate.dao.UserParDAO updao=new com.cqqyd2014.hibernate.dao.UserParDAO();
			default_logistics=updao.getValue(session, userid, com_id, "default_logistics_com");
			pageSize=Integer.parseInt(updao.getValue(session, userid, com_id, "default_rows_in_page"));
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao=new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			
			wh_map=whdao.getUserWareHouseMapByComId(session, com_id);
			com.cqqyd2014.hibernate.dao.LogisticsVehicleDAO lvdao=new com.cqqyd2014.hibernate.dao.LogisticsVehicleDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsVehicle> lvs=lvdao.getArrayEntities(session);
			
			
			
			vehicle_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMap(lvs, "getVehicleId", "getVehicleName");
			
			
			vehicle=updao.getValue(session, userid, com_id, "default_logistics_vehicle");
			default_warehouse=updao.getValue(session, userid, com_id, "default_warehouse");
			tx.commit();
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

}