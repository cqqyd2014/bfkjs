package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VPickupYueAllDAO {
	
	
	public com.cqqyd2014.hibernate.entities.VPickupYueAll getViewByOrderNoComId(Session session,String order_no,String com_id){
		String hql="from VPickupYueAll where id.orderNo=:order_no and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYueAll> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYueAll>)q.list();
		if (list.size()>0) {
			return list.get(0);
		}
		else {
			return null;
		}
		
		
		
	}

}
