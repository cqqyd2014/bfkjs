package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class GoodsInfoDAO {
	public com.cqqyd2014.hibernate.entities.GoodsInfo getEntityByGoodsId(Session session,String goods_id,String com_id){
		String hql="from GoodsInfo where id.CId=:goods_id and id.comId=:com_id";
		Query q = session.createQuery(hql);
		
		q.setParameter("goods_id", goods_id);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.GoodsInfo> list= (java.util.ArrayList<com.cqqyd2014.hibernate.entities.GoodsInfo>)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			return list.get(0);
		}
		
	}

}
