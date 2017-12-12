package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VUserPriceDAO {
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> getArrayEntiesByUserid(Session session,String com_id,String userid){
		String hql="from VUserPrice where id.effective=true and id.comId=:com_id and id.userId=:userid  order by id.goodsId";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice>) q
				.list();
		return rs;
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice>
	getUserPrice(Session session,String user_id,java.util.Date date,String com_id){
		String hql="from VUserPrice where id.comId=:com_id and id.userId=:user_id and id.startTime<=:t1 and id.endTime>=:t2 order by id.goodsId";
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice>) q
				.list();
		return rs;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> getGoodsInfosLike(Session session,String goods_id,String com_id,String user_id,java.util.Date date){
		String hql="from VUserPrice where id.comId=:com_id and id.userId=:user_id and id.startTime<=:t1 and id.endTime>=:t2 and id.goodsId like :goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("goods_id", "%"+goods_id+"%");
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		q.setParameter("com_id", com_id);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice>) q
				.list();
		return rs;
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> getListByDate(Session session,String com_id,String user_id,java.util.Date dat){
		//能查询到符合记录的商品
		String hql="from VUserPrice where id.comId=:com_id and id.userId=:user_id and id.startTime<:dat and id.endTime>:dat order by id.goodsId,id.startTime";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("user_id", user_id);
		q.setParameter("dat", dat);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> list1=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice>)q.list();
		
		
		return list1;
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> getListByGoodsId(Session session,String com_id,String user_id,String goods_id){
		String hql="from VUserPrice where id.comId=:com_id and id.userId=:user_id and id.goodsId=:goods_id order by id.startTime";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("goods_id", goods_id);
		q.setParameter("user_id", user_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> list1=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice>)q.list();
		return list1;
	}
}
