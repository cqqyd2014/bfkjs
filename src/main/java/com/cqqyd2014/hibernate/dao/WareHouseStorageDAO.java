package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class WareHouseStorageDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorage> getStoragesByComId(Session session,String com_id,String WhId ){
		
			String hql = "from WarehouseStorage where id.comId=:com_id and id.whId=:WhId order by id.SId";
			
			Query q = session.createQuery(hql);
			q.setParameter("com_id", com_id);
			q.setParameter("WhId", WhId);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorage> whs= (java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorage>)q.list();
			
			return whs;
		}
	public java.util.ArrayList<com.cqqyd2014.wh.model.Storage> getStorageModelByComIdWhId(Session session,String com_id,String WhId){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorage> wss=getStoragesByComId(session,com_id,WhId);
		java.util.ArrayList<com.cqqyd2014.wh.model.Storage> wss_model=new java.util.ArrayList<>();
		com.cqqyd2014.hibernate.dao.VWareHouseStorageDetailDAO wsddao=new com.cqqyd2014.hibernate.dao.VWareHouseStorageDetailDAO();
		for (int i=0;i<wss.size();i++){
			com.cqqyd2014.hibernate.entities.WarehouseStorage ws=wss.get(i);
			com.cqqyd2014.wh.model.Storage ws_model=new com.cqqyd2014.wh.model.Storage();
			ws_model.setS_id(ws.getId().getSId());
			ws_model.setS_name(ws.getSName());
			ws_model.setWh_id(ws.getId().getWhId());
			ws_model.setSds(wsddao.getModelByComWhStorage(session, com_id, ws.getId().getWhId(), ws.getId().getSId()));
			wss_model.add(ws_model);
			
		}
		wss.clear();
		wss=null;
		return wss_model;
	}
		
	

}
