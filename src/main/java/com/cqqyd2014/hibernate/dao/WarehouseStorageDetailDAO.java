package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class WarehouseStorageDetailDAO {

	
	
	public com.cqqyd2014.hibernate.entities.WarehouseStorageDetail findById(Session session,String goodsId,String com_id,String whId,String storateId){
		String hql = "from WarehouseStorageDetail where id.goodsId=:goodsId and  id.comId=:com_id and id.whId=:whId and id.storageId=:storageId";

		Query q = session.createQuery(hql);
		q.setParameter("goodsId", goodsId);
		q.setParameter("storageId", storateId);
		q.setParameter("com_id", com_id);
		q.setParameter("whId", whId);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail> hbds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail>)q.list();
		if (hbds.size()>0){
			return hbds.get(0);
		}
		else{
			return null;
		}
	}
	//返回所有记录,以仓库编号
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail> getAllByHgzc(Session session,String hgZc,String com_id,String hw_type){
		String hql = "from WarehouseStorageDetail where id.hgzc=:hgZc and id.comId=:com_id and id.hwType=:hw_type";

		Query q = session.createQuery(hql);
		q.setParameter("hgZc", hgZc);
		q.setParameter("com_id", com_id);
		q.setParameter("hw_type", hw_type);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail> hbds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail>)q.list();
		return hbds;
	}
	//返回所有记录,以公司编号
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail> getAllByComId(Session session,String com_id){
		String hql = "from WarehouseStorageDetail where id.comId=:com_id ";

		Query q = session.createQuery(hql);

		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail> hbds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.WarehouseStorageDetail>)q.list();
		return hbds;
	}
	
	

	
}
