package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.SysUser;

public class TaobaoGoodsDDAO {
	
		public java.util.ArrayList<com.cqqyd2014.hibernate.entities.TaobaoGoodsD> getListByGId(Session session,String com_id,String g_id,String order_type_code){
			
			String hql="from TaobaoGoodsD where id.comId=:com_id and id.GId=:g_id and id.orderTypeCode=:order_type_code";
			Query q = session.createQuery(hql);
			q.setParameter("com_id", com_id);
			q.setParameter("g_id", g_id);
			q.setParameter("order_type_code", order_type_code);
			
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.TaobaoGoodsD> tgds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.TaobaoGoodsD>)q.list();
			
			
			
			return tgds;
		}

}
