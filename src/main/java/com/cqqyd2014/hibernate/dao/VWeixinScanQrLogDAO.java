package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VWeixinScanQrLogDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWeixinScanQrLog> getLogsByBarcode(Session session,String com_id,String barcode){
		String hql="from VWeixinScanQrLog where id.goodsBarcode=:barcode and id.comId=:com_id order by id.scanTime";
		Query q = session.createQuery(hql);
		q.setParameter("barcode", barcode);
		q.setParameter("com_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWeixinScanQrLog> list = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWeixinScanQrLog>) q
				.list();
		return list;
		}
	
}
