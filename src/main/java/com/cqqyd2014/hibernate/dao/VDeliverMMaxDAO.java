package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VDeliverMMaxDAO {
	public String getMax(Session session,String order_no,String com_id){
	String hql="from DeliverM where id.comId=:com_id and id.orderNo=:order_no order by id.seq desc";
	Query q = session.createQuery(hql);
	q.setParameter("order_no", order_no);
	q.setParameter("com_id", com_id);
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
	if (list.size()==0){
		return "0000";
		
	}
	else{
		return list.get(0).getId().getSeq();
	}
	
	}
	public String getNewOrderSeq(Session session,String order_no,String com_id){
		String s_max=getMax(session,order_no,com_id);
		long l=Long.valueOf(s_max);
		return com.cqqyd2014.util.NumberUtil.numToStr0((l+1), 4);
	}
}
