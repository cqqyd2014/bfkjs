package com.cqqyd2014.order.logic;

import org.hibernate.Session;

public class DeliverDLogic {
	
	


	
	public static java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> getArrayModelFromArrayEntityView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverSeq> vdss){
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=new java.util.ArrayList<>();
		for (int i=0;i<vdss.size();i++) {
			com.cqqyd2014.order.model.DeliverBillDetail dbd=getModelFromEntityV(vdss.get(i));
			dbds.add(dbd);
		}
		return dbds;
	}
	
	
	public static com.cqqyd2014.order.model.DeliverBillDetail getModelFromEntityV(com.cqqyd2014.hibernate.entities.VDeliverSeq vds){
		com.cqqyd2014.order.model.DeliverBillDetail dbd=new com.cqqyd2014.order.model.DeliverBillDetail();
		dbd.setCom_id(vds.getId().getComId());
		dbd.setGoods_id(vds.getId().getCGoodsId());
		dbd.setGoods_name(vds.getId().getCName());
		dbd.setOrder_no(vds.getId().getCOrderId());
		dbd.setSeq(vds.getId().getSeq());
		dbd.setSended_count(vds.getId().getSendCount());
		dbd.setOrder_count(vds.getId().getCCount());
		dbd.setYue(vds.getId().getYue());
		dbd.setUnit(vds.getId().getUnit());
		dbd.setNum(vds.getId().getCCount());
		return dbd;
	}
	
	public static com.cqqyd2014.order.model.DeliverBillDetail getModelFromView2(com.cqqyd2014.hibernate.entities.VPickupYue vpy){
		com.cqqyd2014.order.model.DeliverBillDetail dbd=new com.cqqyd2014.order.model.DeliverBillDetail();
		dbd.setCom_id(vpy.getId().getComId());
		dbd.setGoods_id(vpy.getId().getCGoodsId());
		dbd.setGoods_name(vpy.getId().getCName());
		dbd.setOrder_no(vpy.getId().getOrderNo());
		dbd.setSended_count(new java.math.BigDecimal(vpy.getId().getSendedCount()));
		dbd.setOrder_count(vpy.getId().getCCount());
		dbd.setYue(vpy.getId().getYue());
		dbd.setUnit(vpy.getId().getUnitName());
		return dbd;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> getArrayListModelFromArrayListView2(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue> vdss){
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=new java.util.ArrayList<>();
		for (int i=0;i<vdss.size();i++) {
			com.cqqyd2014.order.model.DeliverBillDetail dbd=getModelFromView2(vdss.get(i));
			dbds.add(dbd);
		}
		return dbds;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.wh.model.Goods> getBarcodes(Session session,java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds){
		com.cqqyd2014.hibernate.dao.VGoodsDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();

		//java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=getArrayEntityView(session,com_id,order_no,seq);
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> gbs=new java.util.ArrayList<>();
	
		for (int i=0;i<dbds.size();i++){
			com.cqqyd2014.order.model.DeliverBillDetail dbd=dbds.get(i);
			com.cqqyd2014.hibernate.entities.VGoods vg=gdao.getEntityViewByBarcode(session, dbd.getCom_id(), dbd.getGoods_barcode());
			com.cqqyd2014.wh.model.Goods gb=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(vg);
			
			
			gbs.add(gb);
		}
		return gbs;
	}
	
	
	
	public static void save(Session session,java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds){
		for (int i=0;i<dbds.size();i++){
			save(session,dbds.get(i));
		}
	}
	public static  void save(Session session,com.cqqyd2014.order.model.DeliverBillDetail dbd){
		
		com.cqqyd2014.hibernate.entities.DeliverD dd=new com.cqqyd2014.hibernate.entities.DeliverD();
		com.cqqyd2014.hibernate.entities.DeliverDId ddid=new com.cqqyd2014.hibernate.entities.DeliverDId();
		ddid.setComId(dbd.getCom_id());
		ddid.setUuid(dbd.getUuid());
		dd.setId(ddid);
		dd.setGoodsBarcode(dbd.getGoods_barcode());
		dd.setGoodsId(dbd.getGoods_id());
		dd.setOrderNo(dbd.getOrder_no());
		dd.setPackageWeight(dbd.getPackage_weight());
		dd.setSeq(dbd.getSeq());
		dd.setGrossWeight(dbd.getGross_weight());
		dd.setNetWeight(dbd.getNet_weight());
		dd.setPackageWeight(dbd.getPackage_weight());
		dd.setReturned(dbd.isReturned());
		dd.setReturnedDat(dbd.getReturned_dat());
		dd.setReturnedMemo(dbd.getReturned_memo());
		dd.setReturnedUserid(dbd.getReturned_userid());
		session.saveOrUpdate(dd);
	}
	
	
	public static com.cqqyd2014.order.model.DeliverBillDetail getModelFromEntity(com.cqqyd2014.hibernate.entities.VDeliverD vdd){
		com.cqqyd2014.order.model.DeliverBillDetail dbd=new com.cqqyd2014.order.model.DeliverBillDetail();
		dbd.setReceiver_addr(vdd.getId().getCUserAddr());
		dbd.setReceiver_city(vdd.getId().getAddrCity());
		dbd.setReceiver_district(vdd.getId().getAddrDistrict());
		dbd.setReceiver_province(vdd.getId().getAddrProvince());
		dbd.setReceiver_full_addr(vdd.getId().getAddrProvince()+" "+vdd.getId().getAddrCity()+" "+vdd.getId().getAddrDistrict()+" "+vdd.getId().getCUserAddr());
		dbd.setReceiver(vdd.getId().getCUserName());
		dbd.setOrder_dat(vdd.getId().getOrderDat());
		dbd.setMobile(vdd.getId().getCTell());
		dbd.setTell(vdd.getId().getTell2());
		dbd.setOriginal_id(vdd.getId().getOriginalId());
		dbd.setCreate_user_name(vdd.getId().getCreateUserName());
		dbd.setWh_name(vdd.getId().getWhName());
		dbd.setCom_id(vdd.getId().getComId());
		dbd.setGoods_barcode(vdd.getId().getGoodsBarcode());
		dbd.setGoods_id(vdd.getId().getGoodsId());
		dbd.setGoods_name(vdd.getId().getCName());
		dbd.setOrder_no(vdd.getId().getOrderNo());
		dbd.setSeq(vdd.getId().getSeq());
		dbd.setUuid(vdd.getId().getDeliverDUuid());
		dbd.setExpress_com(vdd.getId().getDeliverExpressCom());
		dbd.setExpress_com_name(vdd.getId().getDeliverExpressComName());
		dbd.setExpress_no(vdd.getId().getDeliverExpressNo());
		dbd.setNet_weight(vdd.getId().getNetWeight());
		dbd.setGross_weight(vdd.getId().getGrossWeight());
		dbd.setPackage_weight(vdd.getId().getPackageWeight());
		dbd.setUnit(vdd.getId().getUnit());
		dbd.setReturned(vdd.getId().getReturned());
		dbd.setReturned_dat(vdd.getId().getReturnedDat());
		dbd.setReturned_memo(vdd.getId().getReturnedMemo());
		dbd.setReturned_userid(vdd.getId().getReturnedUserid());
		dbd.setReturn_dat_chinese(com.cqqyd2014.util.DateUtil.getLocalFullString(vdd.getId().getReturnedDat()));
		dbd.setReceiver_full_addr(vdd.getId().getAddrProvince()+" "+vdd.getId().getAddrCity()+" "+vdd.getId().getAddrDistrict()+" "+vdd.getId().getCUserAddr()+" ");
		dbd.setReceiver_addr(vdd.getId().getCUserAddr());
		dbd.setReceiver_city(vdd.getId().getAddrCity());
		dbd.setReceiver_district(vdd.getId().getAddrDistrict());
		dbd.setReceiver_province(vdd.getId().getAddrProvince());
		dbd.setReceiver(vdd.getId().getCUserName());

		/*
		//得到商品的重量
		com.cqqyd2014.hibernate.dao.GoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.GoodsInfoDAO();
		com.cqqyd2014.hibernate.entities.GoodsInfo gi=gidao.getEntityByGoodsId(session, vdd.getId().getGoodsId(), vdd.getId().getComId());
		dbd.setNet_weight(gi.getNetWeight().multiply(vdd.getId().getNum()));
		dbd.setGross_weight(gi.getCWeight().multiply(vdd.getId().getNum()));
		dbd.setPackage_weight(gi.getPackageWeight().multiply(vdd.getId().getNum()));
		*/
		return dbd;
	}
	public static java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> getArrayModelFromArrayView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds){
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dds=new java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail>();
		for (int i=0;i<vdds.size();i++){
			com.cqqyd2014.hibernate.entities.VDeliverD vd=vdds.get(i);
			com.cqqyd2014.order.model.DeliverBillDetail dd=getModelFromEntity(vd);
			dds.add(dd);
		}
		return dds;
	}
}
