package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VFinanceGoodsPriceDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice> getArrayListView(Session session,String com_id){
		//能查询到符合记录的商品
		String hql="from VFinanceGoodsPrice where id.comId=:com_id  order by id.CId,id.startDat";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice> list1=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice>)q.list();
		
		
		return list1;
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice> getArrayListViewByGoodsId(Session session,String com_id,String goods_id){
		String hql="from VFinanceGoodsPrice where id.comId=:com_id and id.CId=:goods_id order by id.startDat";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("goods_id", goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice> list1=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice>)q.list();
		return list1;
	}

}
