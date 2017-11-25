package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class IntoWhDDAO {
	public com.cqqyd2014.hibernate.entities.IntoWhD getIntoWhD(Session session,String com_id,String intoWhUuid,String goods_id){
		String hql="from IntoWhD where id.uuid=:intoWhUuid and id.comId=:com_id and id.goodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("intoWhUuid",intoWhUuid);
		q.setParameter("goods_id",goods_id);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.IntoWhD> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			return list.get(0);
		}
	}

}
