package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public final class WareHouseDAO {

		
		
		
		
		public static String getHwTypeByHwName(Session session,String hwName,String com_id){
			String hql = "from Warehouse where id.comId=:com_id and effective=true and whName=:hwName";

			Query q = session.createQuery(hql);
			q.setParameter("hwName", hwName);
			q.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse>)q.list();
			return list.get(0).getId().getWhId();
		}
		
		

		
		
		
		public  static com.cqqyd2014.hibernate.entities.Warehouse getByHwType(Session session,String hw_type,String com_id){

			if (hw_type.equals("")||hw_type==null||hw_type.equals("0")){
				return null;
			}
			String hql = "from Warehouse where id.comId=:com_id and effective=true and id.whId=:hw_type";

			Query q = session.createQuery(hql);
			q.setParameter("hw_type", hw_type);
			q.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse>)q.list();
			return list.get(0);
		}
		
		public  static java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> getModelByComId(Session session,String com_id){
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs=getAllByComId(session,com_id);
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> wh_models=new java.util.ArrayList<>();
			com.cqqyd2014.hibernate.dao.WareHouseStorageDAO wsdao=new com.cqqyd2014.hibernate.dao.WareHouseStorageDAO();
			for (int i=0;i<whs.size();i++){
				com.cqqyd2014.hibernate.entities.Warehouse wh=whs.get(i);
				com.cqqyd2014.wh.model.WareHouse wh_model=new com.cqqyd2014.wh.model.WareHouse();
				wh_model.setWh_id(wh.getId().getWhId());
				wh_model.setWh_name(wh.getWhName());
				
				wh_model.setSs(wsdao.getStorageModelByComIdWhId(session, com_id,wh.getId().getWhId()));
				
				
				wh_models.add(wh_model);
				
			}
			return wh_models;
		}
		
		public static  java.util.LinkedHashMap<String, String> getUserWareHouseMapByComId(Session session,String com_id){
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs=getUserWarehose(session,com_id);
			java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();

			com.cqqyd2014.hibernate.dao.WareHouseStorageDAO wsdao=new com.cqqyd2014.hibernate.dao.WareHouseStorageDAO();
			for (int i=0;i<whs.size();i++){
				com.cqqyd2014.hibernate.entities.Warehouse wh=whs.get(i);
				map.put(wh.getId().getWhId(), wh.getWhName());
				
			}
			return map;
		}
		
		
		public static  java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> getUserWareHouseModelByComId(Session session,String com_id){
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs=getUserWarehose(session,com_id);
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> wh_models=new java.util.ArrayList<>();
			com.cqqyd2014.hibernate.dao.WareHouseStorageDAO wsdao=new com.cqqyd2014.hibernate.dao.WareHouseStorageDAO();
			for (int i=0;i<whs.size();i++){
				com.cqqyd2014.hibernate.entities.Warehouse wh=whs.get(i);
				com.cqqyd2014.wh.model.WareHouse wh_model=new com.cqqyd2014.wh.model.WareHouse();
				wh_model.setWh_id(wh.getId().getWhId());
				wh_model.setWh_name(wh.getWhName());
				
				wh_model.setSs(wsdao.getStorageModelByComIdWhId(session, com_id,wh.getId().getWhId()));
				
				
				wh_models.add(wh_model);
				
			}
			return wh_models;
		}
		public static  java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> getUserCustomWareHouseModelByComId(Session session,String com_id){
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs=getUserCustomWarehose(session,com_id);
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> wh_models=new java.util.ArrayList<>();
			com.cqqyd2014.hibernate.dao.WareHouseStorageDAO wsdao=new com.cqqyd2014.hibernate.dao.WareHouseStorageDAO();
			for (int i=0;i<whs.size();i++){
				com.cqqyd2014.hibernate.entities.Warehouse wh=whs.get(i);
				com.cqqyd2014.wh.model.WareHouse wh_model=new com.cqqyd2014.wh.model.WareHouse();
				wh_model.setWh_id(wh.getId().getWhId());
				wh_model.setWh_name(wh.getWhName());
				
				wh_model.setSs(wsdao.getStorageModelByComIdWhId(session, com_id,wh.getId().getWhId()));
				
				
				wh_models.add(wh_model);
				
			}
			whs.clear();
			whs=null;
			return wh_models;
		}
		
		

		
		//所有仓库
		public static  java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> getAllByComId(Session session,String com_id){
			String hql = "from Warehouse where effective=true and id.comId=:com_id";
			
			Query q = session.createQuery(hql);
			q.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs= (java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse>)q.list();
			
			return whs;
		}
		public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> getUserWarehose(Session session,String com_id){
			String hql = "from Warehouse where effective=true and id.comId=:com_id and id.whId<>'SUPPLY' and id.whId<>'CUSTOM' and id.whId<>'ORDER_'";
			
			Query q = session.createQuery(hql);
			q.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse>)q.list();
			return list;
		}
		public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> getUserCustomWarehose(Session session,String com_id){
			String hql = "from Warehouse where effective=true and id.comId=:com_id and id.whId<>'SUPPLY' order by id.whId";
			
			Query q = session.createQuery(hql);
			q.setParameter("com_id", com_id);
			return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse>)q.list();
		}
			

		public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> getAllByCity(Session session,String city,String com_id){
			String hql = "from Warehouse where id.comId=:com_id and effective=true and id.whId like :city order by priority";

			Query q = session.createQuery(hql);
			q.setParameter("city", city + "%");
			q.setParameter("com_id", com_id);
			return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse>)q.list();
		}
		
		
		
		


	}
