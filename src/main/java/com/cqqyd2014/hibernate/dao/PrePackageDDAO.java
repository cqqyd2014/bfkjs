package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class PrePackageDDAO {

	
	//得到包裹中商品明细
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageD> getArrayListByPrepackageBarcode(Session session,String com_id,String pickup_barcode){
String hql="from PrePackageD where  id.comId=:com_id and id.packageBarcode=:pickup_barcode";
		
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("pickup_barcode", pickup_barcode);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageD> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageD>)q.list();
		return sws;
	}

}
