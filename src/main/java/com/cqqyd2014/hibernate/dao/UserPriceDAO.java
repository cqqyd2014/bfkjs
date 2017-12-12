package com.cqqyd2014.hibernate.dao;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserPriceDAO {
	
	public BigDecimal getPrice(Session session,String userid,String goodsid,java.util.Date date){

		String hql="from UserPrice where id.userId=:userId and id.goodsId=:goodsId and startTime<=:t1 and endTime>=:t2";
		Query q = session.createQuery(hql);
		q.setParameter("userId", userid);
		q.setParameter("goodsId", goodsid);
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice>) q
				.list();

		return rs.get(0).getUserPrice();
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> getArrayEntiesByUser(Session session,String com_id,String userid){
		String hql="from UserPrice where userId=:userid and id.com_id=:com_id order by goodsId,startTime";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice>) q
				.list();

		return rs;
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> getArrayEntityByUseridDatGoodsId(Session session,String com_id,String userid,java.util.Date dat,String goods_id){
		String hql="from UserPrice where userId=:userid and id.comId=:com_id and startTime<=:dat and endTime>=:dat and goodsId=:goods_id order by goodsId,startTime";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		q.setParameter("dat", dat);
		q.setParameter("goods_id", goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice>) q
				.list();

		return rs;
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> getArrayEntityByUseridDatGoodsidNotUuid(Session session,String com_id,String userid,java.util.Date dat,String uuid,String goods_id){
		String hql="from UserPrice where effective=true and userId=:userid and id.uuid<>:uuid and id.comId=:com_id and startTime<=:dat and endTime>=:dat and goodsId=:goods_id order by goodsId,startTime";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		q.setParameter("dat", dat);
		q.setParameter("uuid", uuid);
		q.setParameter("goods_id", goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice>) q
				.list();

		return rs;
	}

	public com.cqqyd2014.hibernate.entities.UserPrice getArrayEntyByUserUuid(Session session,String com_id,String userid,String uuid){
		String hql="from UserPrice where userId=:userid and id.comId=:com_id and id.uuid=:uuid order by goodsId,startTime";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		q.setParameter("uuid", uuid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice>) q
				.list();

		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
		
	}
}
