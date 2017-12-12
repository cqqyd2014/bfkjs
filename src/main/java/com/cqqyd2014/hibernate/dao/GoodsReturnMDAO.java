package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class GoodsReturnMDAO {
	//seq的最大值
	public String getSeqMax(Session session,String com_id,String order_no){
		String hql="from GoodsReturnM where id.comId=:com_id and id.orderNo=:order_no order by id.seq desc";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("order_no", order_no);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.GoodsReturnM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.GoodsReturnM>)q.list();
		if (list.size()==0){
			return "0000";
			
		}
		else{
			return list.get(0).getId().getSeq();
		}
		
		
		
		
	}
	public String getNewSeq(Session session,String com_id,String order_no){
		String value=getSeqMax(session,com_id,order_no);
		return com.cqqyd2014.util.NumberUtil.numToStr0((new java.math.BigDecimal(value)).longValue()+1, 4);
		
	}

}
