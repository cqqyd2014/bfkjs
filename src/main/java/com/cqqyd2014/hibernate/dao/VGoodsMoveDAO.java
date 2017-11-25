package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VGoodsMoveDAO {
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsMove> getListByBarcode(Session session,String com_id,String barcode){
		String hql="from VGoodsMove where id.comId=:com_id and id.goodsBarcode=:barcode order by id.dat";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("barcode", barcode);
		
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsMove> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsMove>)q.list();
		return list;
		
	}
	public java.util.ArrayList<com.cqqyd2014.wh.model.GoogsMoveLog> getModelListByBarcode(Session session,String com_id,String barcode){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsMove> vgms=getListByBarcode(session,com_id,barcode);
		java.util.ArrayList<com.cqqyd2014.wh.model.GoogsMoveLog> gmls=new java.util.ArrayList<>();
		for (int i=0;i<vgms.size();i++){
			com.cqqyd2014.hibernate.entities.VGoodsMove vgm=vgms.get(i);
			com.cqqyd2014.wh.model.GoogsMoveLog bml=new com.cqqyd2014.wh.model.GoogsMoveLog();
			bml.setBarcode(barcode);
			bml.setDat(com.cqqyd2014.util.DateUtil.JDateToFullString(vgm.getId().getDat()));
			bml.setFrom_storage_name(vgm.getId().getFromStorageName());
			bml.setFrom_wh_name(vgm.getId().getFromWhName());
			bml.setMove_type_name(vgm.getId().getMoveTypeName());
			bml.setTo_storage_name(vgm.getId().getToStorageName());
			bml.setTo_wh_name(vgm.getId().getToWhName());
			gmls.add(bml);
		}
		return gmls;
	}

}
