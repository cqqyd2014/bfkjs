package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VIntoWhContractDDAO {
	public com.cqqyd2014.hibernate.entities.VIntoWhContractD getObjectByUuidGoodsId(Session session,String com_id,String uuid,String goods_id){
		String hql="from VIntoWhContractD where id.intoWhUuid=:uuid and id.comId=:com_id and id.goodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("uuid",uuid);
		q.setParameter("com_id",com_id);
		q.setParameter("goods_id",goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VIntoWhContractD> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			return list.get(0);
		}
	}
}
