package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class DeliverDDAO {
	

	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> getDeliverSn(Session session,String order_no,String goods_id,String com_id){
		String hql="from DeliverD where id.orderNo=:order_no and id.goodsId=:goods_id and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("goods_id", goods_id);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD>)q.list();
		
	}

	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> getArrayListEntityByOrderNo(Session session,String order_no,String com_id){
		String hql="from DeliverD where id.orderNo=:order_no  and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD>)q.list();
		
	}



	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> getDeliverD(Session session,String order_no,String seq,String goods_id,String com_id){
		String hql="from DeliverD where id.orderNo=:order_no and id.seq=:seq and id.goodsId=:goods_id and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("seq", seq);
		q.setParameter("goods_id", goods_id);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD>)q.list();

		return list;
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> getEntityByOrderNoSeq(Session session,String order_no,String seq,String com_id){
		String hql="from DeliverD where orderNo=:order_no and seq=:seq and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("seq", seq);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD>)q.list();
		return list;
	}
	
	public com.cqqyd2014.hibernate.entities.DeliverD getObjectByOrderNoBarcode(Session session,String com_id,String order_no,String barcode){
		String hql="from DeliverD where id.orderNo=:order_no and id.goodsBarcode=:barcode and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("barcode", barcode);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD>)q.list();
		if (list.size()>0){
			return list.get(0);
		}
		else{
			return null;
		}

	}
	
	
	

}
