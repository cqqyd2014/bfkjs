package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.VDeliverM;

public class VDeliverMDAO {
	


	
	public static java.math.BigDecimal getWaitDeliverCount(Session session,String com_id){
		String hql="select count(*) from VDeliverM where id.comId=:com_id and id.deliverBillStatus=\'分派发货\' and id.effective=true ";
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);

		Long l=(Long)q.uniqueResult();
		return new java.math.BigDecimal(l);
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> getArrayListViewByBarcode(Session session,String goods_barcode,String com_id){
		String hql="from VDeliverM where id.effective=true and id.comId=:com_id and id.memoBarcodes like :goods_barcode";
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		q.setParameter("goods_barcode", "%"+goods_barcode+"%");
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
		return list;
	}
	
	
	//寻找运单号相同，状态为‘分配单号’的记录，只取其一
		public com.cqqyd2014.hibernate.entities.VDeliverM getEntityByExpressNoAssign(Session session,String order_no,String express_no,String com_id){

			
			String hql="from VDeliverM where id.effective=true and id.comId=:com_id and id.orderNo=:order_no and id.expressNo=:express_no";
			Query q = session.createQuery(hql);
			
			q.setParameter("com_id", com_id);
			q.setParameter("order_no", order_no);
			q.setParameter("express_no", express_no);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
			if (list.size()>0) {
				return list.get(0);
			}
			else {
				return null;
			}
		}
		
		public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM > getArrayListByDeliverNoArray(Session session, java.util.ArrayList<String> deliver_no_list,String com_id){
			
			String in_str=com.cqqyd2014.util.ArrayListTools.arrayListToSQLInString(deliver_no_list);
			
			String hql="from DeliverM where effective=true and id.comId=:com_id and deliverNo in "+in_str;
			Query q = session.createQuery(hql);
			
			q.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>)q.list();
			return list;
		}
		
		
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> getArrayListViewAll(Session session,String com_id){

		String hql="from VDeliverM where id.comId=:com_id";

		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
		return list;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> getArrayListViewByOrders(Session session,java.util.ArrayList<String> order_nos,String com_id){
		if (order_nos.size()==0) {
			return new java.util.ArrayList<>();
		}
		String hql="from VDeliverM where id.comId=:com_id and id.orderNo in ";
		String order_no_in =com.cqqyd2014.util.ArrayListTools.arrayListToSQLInString(order_nos);
		hql=hql+order_no_in;
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
		return list;
	}
	
	public VDeliverM getEntityViewByDeliverNo(Session session,String deliver_no,String com_id) throws Exception{
	String hql="from VDeliverM where id.comId=:com_id and id.deliverNo=:deliver_no";
	Query q = session.createQuery(hql);
	q.setParameter("deliver_no", deliver_no);
	q.setParameter("com_id", com_id);
	
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
	if (list.size()==0){
		throw new Exception("发货单"+deliver_no+"的运单找不到");
		
	}
	else{
		return list.get(0);
	}
	}
	
	public VDeliverM getEntityViewByOrderNoSeq(Session session,String order_no,String seq,String com_id) {
	String hql="from VDeliverM where id.comId=:com_id and id.orderNo=:order_no and id.seq=:seq";
	Query q = session.createQuery(hql);
	q.setParameter("order_no", order_no);
	q.setParameter("com_id", com_id);
	q.setString("seq", seq);
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
	if (list.size()==0){
		return null;
		
	}
	else{
		return list.get(0);
	}
	
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> getArrayEntityViewByOrderNo(Session session,String order_no,String com_id) {
	String hql="from VDeliverM where id.comId=:com_id and id.orderNo=:order_no";
	Query q = session.createQuery(hql);
	q.setParameter("order_no", order_no);
	q.setParameter("com_id", com_id);

	java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM>)q.list();
	
		return list;
	
	
	}
	
}
