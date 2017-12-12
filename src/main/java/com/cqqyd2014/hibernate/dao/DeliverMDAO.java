package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class DeliverMDAO {
	
	
	//寻找运单号相同，状态为‘分配单号’的记录，只取其一
	public com.cqqyd2014.hibernate.entities.DeliverM getEntityByExpressNoAssign(Session session,String order_no,String express_no,String com_id){

		
		String hql="from DeliverM where effective=true and id.comId=:com_id and id.orderNo=:order_no and expressNo=:express_no";
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		q.setParameter("order_no", order_no);
		q.setParameter("express_no", express_no);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		if (list.size()>0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM > getArrayListByDeliverNoArray(Session session, java.util.ArrayList<String> deliver_no_list,String com_id){
		
		String in_str=com.cqqyd2014.util.StringUtil.arrayListToSQLInString(deliver_no_list);
		
		String hql="from DeliverM where effective=true and id.comId=:com_id and deliverNo in "+in_str;
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		return list;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM > getArrayListEntity(Session session, String com_id){
		

		String hql="from DeliverM where id.comId=:com_id ";
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		return list;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> getDelivers(Session session,String order_no,String com_id){
		String hql="from DeliverM where id.orderNo=:order_no and effective=true and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		return list;
	}
	
	public com.cqqyd2014.hibernate.entities.DeliverM getDeliverM(Session session,String order_no,String seq,String com_id){
		
		String hql="from DeliverM where id.orderNo=:order_no and id.seq=:seq and effective=true and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("seq", seq);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		return list.get(0);
	}
	
	public com.cqqyd2014.hibernate.entities.DeliverM getDeliverM(Session session,String deliver_no,String com_id){
		
		String hql="from DeliverM where deliverNo=:deliver_no and effective=true and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("deliver_no", deliver_no);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		if (list.size()==0) {
			return null;
		}
		else {
			return list.get(0);
		}
		
	}
	/*
	//是否所有货单都发了
	
	public boolean getIfAllDeliverd(Session session,String order_no,String com_id){
		String hql="from DeliverM where id.orderNo=:order_no and effective=true and id.comId=:com_id and sended=false";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
		if(list.size()>0){
			return false;
		}
		else{
			return true;
		}
	}
*/
	
	

}
