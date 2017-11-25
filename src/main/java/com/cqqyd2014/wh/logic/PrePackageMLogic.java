package com.cqqyd2014.wh.logic;

import org.hibernate.Session;

public class PrePackageMLogic {
	
	

	
	//private static com.cqqyd2014.hibernate.dao.SnMaxDAO smd=new com.cqqyd2014.hibernate.dao.SnMaxDAO();
	public static void makeNewPrepackageBarcode(Session session,String com_id,java.math.BigDecimal c_goods_count,java.math.BigDecimal num,String userid){
		//com.cqqyd2014.hibernate.dao.SnMaxDAO smd=new com.cqqyd2014.hibernate.dao.SnMaxDAO();
		java.util.Date now=new java.util.Date();
		
		
		long end=com.cqqyd2014.hibernate.dao.SnMaxDAO.getNew(session, num.longValue(), "PREPAC", com_id, now);
		for (int i=0;i<num.intValue();i++){
			
			com.cqqyd2014.wh.model.PrePackageM ppm=new com.cqqyd2014.wh.model.PrePackageM();
			ppm.setActual_weight(new java.math.BigDecimal(0));
			ppm.setCom_id(com_id);
			ppm.setCreate_dat(now );
			ppm.setEffective(true);
			ppm.setMemo_barcodes("");
			ppm.setMemo_names("");
			ppm.setNum(c_goods_count);
			ppm.setPackage_weight(new java.math.BigDecimal(0));
			
			
			long current=end-num.longValue()+i+1;
			String prepackage_barcode=String.valueOf(c_goods_count)+com.cqqyd2014.util.DateUtil.JDateToCompatString(now)+com.cqqyd2014.util.NumberUtil.numToStr0(current, 9);
			System.out.println(prepackage_barcode);
			ppm.setPrepackage_barcode(prepackage_barcode);
			ppm.setPrepackage_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			ppm.setPrinted(false);
			ppm.setSend_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			ppm.setSended(false);
			ppm.setUn_package_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			ppm.setUn_package_userid("");
			ppm.setCreate_userid(userid);
			ppm.setWh_id("");
			ppm.setPackage_userid("");
			
			com.cqqyd2014.wh.logic.PrePackageMLogic.save(session, ppm);
			
			
			
		}
		
	}
	
	
	
	public static void save (Session session,com.cqqyd2014.wh.model.PrePackageM pm) {
		com.cqqyd2014.hibernate.entities.PrePackageM ppm_h=new com.cqqyd2014.hibernate.entities.PrePackageM();
		ppm_h.setActualWeight(pm.getActual_weight());
		ppm_h.setCreateDat(pm.getCreate_dat());
		ppm_h.setCreateUserid(pm.getCreate_userid());
		ppm_h.setEffective(pm.isEffective());
		ppm_h.setId(new com.cqqyd2014.hibernate.entities.PrePackageMId(pm.getPrepackage_barcode(), pm.getCom_id()));
		ppm_h.setMemoBarcodes(pm.getMemo_barcodes());
		ppm_h.setMemoNames(pm.getMemo_names());
		ppm_h.setNum(pm.getNum());
		ppm_h.setPackaged(pm.isPackaged());
		ppm_h.setPackageTime(pm.getPrepackage_dat());
		ppm_h.setPackageUserid(pm.getPackage_userid());
		ppm_h.setPackageWeight(pm.getPackage_weight());
		ppm_h.setPrinted(pm.isPrinted());
		ppm_h.setSendDat(pm.getSend_dat());
		ppm_h.setSended(pm.isSended());
		ppm_h.setUnPackageTime(pm.getUn_package_dat());
		ppm_h.setUnPackageUser(pm.getUn_package_userid());
		ppm_h.setWhId(pm.getWh_id());
		//session.merge(ppm_h);
		session.saveOrUpdate(ppm_h);
		if (pm.getPpds()!=null) {
			for (int i=0;i<pm.getPpds().size();i++) {
				com.cqqyd2014.wh.model.PrePackageD ppd=pm.getPpds().get(i);
				com.cqqyd2014.hibernate.entities.PrePackageD ppd_h=new com.cqqyd2014.hibernate.entities.PrePackageD();
				ppd_h.setGoodsId(ppd.getGoods_id());
				ppd_h.setId(new com.cqqyd2014.hibernate.entities.PrePackageDId(ppd.getCom_id(), ppd.getPackege_barcode(), ppd.getGoods_barcode()));
				session.merge(ppd_h);
				session.saveOrUpdate(ppd_h);
			}
		}
		
	}
	
	
	public static java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageM> getArrayListModelFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> ppms_h) throws Exception{
		java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageM> ppms=new java.util.ArrayList<>();
		for (int i=0;i<ppms_h.size();i++) {
			com.cqqyd2014.wh.model.PrePackageM ppm=getModelFromEntity(ppms_h.get(i));
			ppms.add(ppm);
		}
		return ppms;
	}
	
	
	public static com.cqqyd2014.wh.model.PrePackageM getModelFromEntity(com.cqqyd2014.hibernate.entities.PrePackageM ppm_h) {
		com.cqqyd2014.wh.model.PrePackageM ppm=new com.cqqyd2014.wh.model.PrePackageM();
		ppm.setActual_weight(ppm_h.getActualWeight());
		ppm.setCom_id(ppm_h.getId().getComId());
		ppm.setCreate_dat(ppm_h.getCreateDat());
		ppm.setEffective(ppm_h.getEffective());
		ppm.setMemo_barcodes(ppm_h.getMemoBarcodes());
		ppm.setMemo_names(ppm_h.getMemoNames());
		ppm.setPackage_weight(ppm_h.getPackageWeight());
		ppm.setPrepackage_barcode(ppm_h.getId().getPackageBarcode());
		ppm.setPrepackage_dat(ppm_h.getPackageTime());
		ppm.setPrinted(ppm_h.getPrinted());
		ppm.setSend_dat(ppm_h.getSendDat());
		ppm.setSended(ppm_h.getSended());
		ppm.setUn_package_dat(ppm_h.getUnPackageTime());
		java.util.Date now=new java.util.Date();
		long last=now.getTime()-ppm_h.getPackageTime().getTime();
		ppm.setTime_last(last);
		ppm.setUn_package_userid(ppm_h.getUnPackageUser());
		ppm.setCreate_userid(ppm_h.getCreateUserid());
		ppm.setPackage_userid(ppm.getPackage_userid());
		ppm.setWh_id(ppm_h.getWhId());
		ppm.setNum( ppm_h.getNum());
		ppm.setPackaged(ppm_h.getPackaged());
		ppm.setTime_last_chinese(com.cqqyd2014.util.DateUtil.getDistanceChinese(last));
		return ppm;
	}

}
