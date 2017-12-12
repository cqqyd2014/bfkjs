package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VPickupYueDAO {
	public com.cqqyd2014.hibernate.entities.VPickupYue getYue(Session session,String order_no,String goods_id,String com_id){
		String hql="from VPickupYue where id.COrderId=:order_no and id.CGoodsId=:goods_id and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("goods_id", goods_id);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue>)q.list();
		if (list.size()==0){
			return null;
		}
		else{
		return list.get(0);
		}
	}

	
	
	public boolean ifNeeded(Session session,String order_no,String goods_id,String com_id){
		com.cqqyd2014.hibernate.entities.VPickupYue yue=getYue(session,order_no,goods_id,com_id);
		if (yue==null){
			return false;
		}
		if (yue.getId().getYue().compareTo(new java.math.BigDecimal(0))==1){
			return true;
		}
		else{
			return false;
		}
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue> getArrayListViewByOrderNo(Session session,String order_no,String com_id){
		String hql="from VPickupYue where id.orderNo=:order_no and id.comId=:com_id ";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue>)q.list();
		
	}
	
}
