package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class FinanceGoodsPriceDAO {
	public com.cqqyd2014.hibernate.entities.FinanceGoodsPrice getEntityByGoodsIdDate(Session session,String com_id,String goods_id,java.util.Date dat){
		String hqlString = "from FinanceGoodsPrice where id.comId=:com_id and goodsId=:goods_id and startDat<:dat and endDat>:dat";

		Query query = session.createQuery(hqlString);
		query.setParameter("goods_id", goods_id);
		query.setParameter("com_id", com_id);
		query.setParameter("dat", dat);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceGoodsPrice> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceGoodsPrice>)query.list();
		if (gis.size()!=0){
			return gis.get(0);
		}
		else{
			return null;
		}
		
	}
	public com.cqqyd2014.hibernate.entities.FinanceGoodsPrice getEntityByGoodsIdDateNotUuid(Session session,String com_id,String goods_id,java.util.Date dat,String uuid){
		String hqlString = "from FinanceGoodsPrice where id.comId=:com_id and id.uuid<>:uuid and goodsId=:goods_id and startDat<:dat and endDat>:dat";

		Query query = session.createQuery(hqlString);
		query.setParameter("goods_id", goods_id);
		query.setParameter("com_id", com_id);
		query.setParameter("dat", dat);
		query.setParameter("uuid", uuid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceGoodsPrice> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceGoodsPrice>)query.list();
		if (gis.size()!=0){
			return gis.get(0);
		}
		else{
			return null;
		}
		
	}

}
