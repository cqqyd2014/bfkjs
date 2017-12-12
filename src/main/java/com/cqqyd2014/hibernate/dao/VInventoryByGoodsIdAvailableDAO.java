package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VInventoryByGoodsIdAvailableDAO {
	
	
	public com.cqqyd2014.hibernate.entities.VInventoryByGoodsIdAvailable getObjectByGoodsIdNum(Session session,String com_id,String goods_id,java.math.BigDecimal num){
		String hql="from VInventoryByGoodsIdAvailable where  id.comId=:com_id and id.goodsId=:goods_id and id.sumAvailable>=:num";
		Query q = session.createQuery(hql);

		q.setParameter("com_id",com_id);
		q.setParameter("goods_id",goods_id);
		q.setParameter("num",num);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VInventoryByGoodsIdAvailable> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			return list.get(0);
		}
	}

}
