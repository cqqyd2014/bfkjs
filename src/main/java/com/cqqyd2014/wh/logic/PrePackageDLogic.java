package com.cqqyd2014.wh.logic;

import org.hibernate.Session;

public class PrePackageDLogic {
	public static void save (Session session,com.cqqyd2014.wh.model.PrePackageD ppd) {
		com.cqqyd2014.hibernate.entities.PrePackageD ppd_h=new com.cqqyd2014.hibernate.entities.PrePackageD();
		ppd_h.setGoodsId(ppd.getGoods_id());
		ppd_h.setId(new com.cqqyd2014.hibernate.entities.PrePackageDId(ppd.getCom_id(), ppd.getPackege_barcode(), ppd.getGoods_barcode()));
		
		session.saveOrUpdate(ppd_h);
	}
	
	public static com.cqqyd2014.wh.model.PrePackageD getModelFromView(com.cqqyd2014.hibernate.entities.VPrepackageD ppd_h){
		com.cqqyd2014.wh.model.PrePackageD ppd=new com.cqqyd2014.wh.model.PrePackageD();
		ppd.setCom_id(ppd_h.getId().getComId());
		ppd.setGoods_barcode(ppd_h.getId().getGoodsBarcode());
		ppd.setGoods_id(ppd_h.getId().getCId());
		ppd.setGoods_name(ppd_h.getId().getCName());
		ppd.setPackage_weight(ppd_h.getId().getPackageWeight());
		ppd.setPackege_barcode(ppd_h.getId().getPackageBarcode());
		ppd.setGross_weight(ppd_h.getId().getGrossWeight());
		ppd.setNet_weight(ppd_h.getId().getNetWeight());
		return ppd;
	}
	public static java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageD> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackageD> vpds){
		java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageD> ppds=new java.util.ArrayList<>();
		for (int i=0;i<vpds.size();i++) {
			com.cqqyd2014.wh.model.PrePackageD ppd=getModelFromView(vpds.get(i));
			ppds.add(ppd);
		}
		return ppds;
	}

}
