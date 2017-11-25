package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VPrepackageDDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackageD> getViewByPrepackageBarcode(Session session,String prepackage_barcode,String com_id){
		String hql="from VPrepackageD where id.packageBarcode=:prepackage_barcode and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("prepackage_barcode", prepackage_barcode);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackageD> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackageD>)q.list();
		return list;
	}

}
