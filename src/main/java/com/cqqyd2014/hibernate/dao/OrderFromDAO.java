package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class OrderFromDAO {
	
	

	
	
	public com.cqqyd2014.hibernate.entities.OrderFrom getObjectByOrderNo(Session session,String order_no,String com_id){
		
		String order_from=order_no.substring(3, 5);
		
		String hql="from OrderFrom where id.comId=:com_id and id.orderTypeCode=:order_from";
		
		//System.out.println(hql);
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("order_from", order_from);
		//q.setParameter("storageId", storageId);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.OrderFrom> ofs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.OrderFrom>)q.list();
		if (ofs.size()==0){
			return null;
		}
		else{
			return ofs.get(0);
		}
	}
	
public com.cqqyd2014.hibernate.entities.OrderFrom getEntityByTypeCode(Session session,String type_code,String com_id){
		
		
		
		String hql="from OrderFrom where id.comId=:com_id and id.orderTypeCode=:type_code";
		
		//System.out.println(hql);
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("type_code", type_code);
		//q.setParameter("storageId", storageId);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.OrderFrom> ofs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.OrderFrom>)q.list();
		if (ofs.size()==0){
			return null;
		}
		else{
			return ofs.get(0);
		}
	}

}
