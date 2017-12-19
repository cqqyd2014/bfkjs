package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public final class VUserPriceAvailableDAO {

	
	//模糊查询
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> getGoodsInfosLike(Session session,String goods_id,String com_id,String user_id,java.util.Date date){
		String hql="from VUserPriceAvailable where id.comId=:com_id and id.userId=:user_id and id.startTime<=:t1 and id.endTime>=:t2 and id.goodsId like :goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("goods_id", "%"+goods_id+"%");
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable>) q
				.list();
		return rs;
	}
	public static com.cqqyd2014.hibernate.entities.VUserPriceAvailable getGoodsInfos(Session session,String goods_id,String com_id,String user_id,java.util.Date date) {
		String hql="from VUserPriceAvailable where id.comId=:com_id and id.userId=:user_id and id.startTime<=:t1 and id.endTime>=:t2 and id.goodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("goods_id",goods_id);
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
	}
}