package com.cqqyd2014.order.logic;



import org.hibernate.Session;


import com.cqqyd2014.hibernate.dao.SfResponseOrderBackDAO;

import com.cqqyd2014.order.model.Order;

public class DeliverMLogic {
	
	
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> transOneToArray(com.cqqyd2014.order.model.DeliverBill db){
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=new java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill>();
		dbs.add(db);
		return dbs;
	}
	
	//测试是否是顺丰电子运单
	public static boolean ifSfElecBill(Session session,com.cqqyd2014.order.model.DeliverBill db) {
		boolean b=false;
		
		SfResponseOrderBackDAO dao=new SfResponseOrderBackDAO();
		com.cqqyd2014.hibernate.entities.SfResponseOrderBack back=dao.getEntityByOrderSeq(session, db.getOrder_no(), db.getSeq());
		if (back!=null) {
			b=true;
		}
		
		return b;
	}
	
	
	public static java.util.ArrayList<String> getExpressNosArrayList(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs){
		java.util.ArrayList<String> strs=new java.util.ArrayList<>();
		for (int i=0;i<dbs.size();i++) {
			strs.add(dbs.get(i).getExpress_no());
		}
		return strs;
	}
	
	public static   java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getArrayListModelWithDetail(Session session,java.util.ArrayList<String> arraylist_delivers,String com_id) throws Exception{
		com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
		com.cqqyd2014.hibernate.dao.VDeliverDDAO vdddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=new java.util.ArrayList<>();
		for (int i=0;i<arraylist_delivers.size();i++) {
			
			com.cqqyd2014.hibernate.entities.VDeliverM vdm=vdmdao.getEntityViewByDeliverNo(session, arraylist_delivers.get(i), com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=vdddao.getArrayListViewByDeliverNo(session, com_id,arraylist_delivers.get(i));
			com.cqqyd2014.order.model.DeliverBill db=getModelFromEntityV(vdm, vdds);
			dbs.add(db);
		}
		
		
		
		return dbs;
		
	}	
	
	
	public static   java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getArrayListFullModelWithDetail(Session session,java.util.ArrayList<String> arraylist_orders,java.util.ArrayList<String> arraylist_seqs,String com_id) throws Exception{
		com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
		com.cqqyd2014.hibernate.dao.VDeliverDDAO vdddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=new java.util.ArrayList<>();
		for (int i=0;i<arraylist_orders.size();i++) {
			
			com.cqqyd2014.hibernate.entities.VDeliverM vdm=vdmdao.getEntityViewByOrderNoSeq(session, arraylist_orders.get(i), arraylist_seqs.get(i), com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=vdddao.getArrayListViewByOrderNoSeq(session, com_id,arraylist_orders.get(i), arraylist_seqs.get(i));
			com.cqqyd2014.order.model.DeliverBill db=getModelFromEntityV(vdm, vdds);
			dbs.add(db);
		}
		
		
		
		return dbs;
		
	}
	
	
	//顺丰预分配
	public static com.cqqyd2014.order.model.DeliverBill sfNewDeliverM(Session session,Order order,String seq,String mailno,String logistics_vehicle,String com_id,String wh_id,String package_userid) throws Exception{
		
		com.cqqyd2014.order.model.DeliverBill db=new com.cqqyd2014.order.model.DeliverBill();
		db.setActual_weight(new java.math.BigDecimal(0));
		db.setCancel_confirm_memo("");
		db.setCancel_confirm_userid("");
		db.setCancel_confrim_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		db.setCancel_request_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		db.setCancel_request_memo("");
		db.setCancel_request_userid("");
		db.setCancel_status("");
		db.setCarton_type("0001");
		db.setCarton_weight(new java.math.BigDecimal( 0));
		db.setCom_id(com_id);
		db.setPackage_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		db.setDeliver_bill_status("分配单号");
		db.setDeliver_no(order.getOrder_no()+seq);
		db.setEffective(true);
		db.setExpress_com("500528000A");
		db.setExpress_no(mailno);
		db.setLogistics_fb_code("");
		db.setLogistics_fb_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		db.setLogistics_fb_memo("");
		db.setLogistics_fb_status("");
		db.setNum(new java.math.BigDecimal(0));
		db.setOrder_no(order.getOrder_no());
		db.setPackage_userid(package_userid);
		db.setPackage_weight(new java.math.BigDecimal(0));
		db.setPre_package_barcode("");
		db.setSend_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		db.setSend_user_assgin_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		db.setSeq(seq);
		db.setSend_userid("");
		db.setVehicle_id(logistics_vehicle);
		db.setWh_id(wh_id);
		db.setWh_name("");
		db.setMemo_barcodes("");
		db.setMemo_names("");
		save(session, db);
		return db;
	}
	
	public static void save(Session session,com.cqqyd2014.order.model.DeliverBill db) {
		com.cqqyd2014.hibernate.entities.DeliverM dm=new com.cqqyd2014.hibernate.entities.DeliverM();
		dm.setActualWeight(db.getActual_weight());
		dm.setId(new com.cqqyd2014.hibernate.entities.DeliverMId(db.getCom_id(), db.getOrder_no(), db.getSeq()));
		dm.setCancelConfirmDat(db.getCancel_confrim_dat());
		dm.setCancelConfirmMemo(db.getCancel_confirm_memo());
		dm.setCancelConfirmUserid(db.getCancel_confirm_userid());
		dm.setCancelRequestDat(db.getCancel_request_dat());
		dm.setCancelRequestMemo(db.getCancel_request_memo());
		dm.setCancelRequestUserid(db.getCancel_request_userid());
		dm.setCancelStatus(db.getCancel_status());
		dm.setDDat(db.getPackage_dat());
		dm.setDeliverBillStatus(db.getDeliver_bill_status());
		dm.setDeliverNo(db.getDeliver_no());
		dm.setEffective(db.isEffective());
		dm.setExpressCom(db.getExpress_com());
		dm.setExpressNo(db.getExpress_no());
		dm.setLogisticsFbCode(db.getLogistics_fb_code());
		dm.setLogisticsFbDat(db.getLogistics_fb_dat());
		dm.setLogisticsFbMemo(db.getLogistics_fb_memo());
		dm.setLogisticsFbStatus(db.getLogistics_fb_status());
		dm.setLogisticsVehicle(db.getVehicle_id());
		dm.setPackageUserid(db.getPackage_userid());
		dm.setPackageWeight(db.getPackage_weight());
		dm.setPrePackageBarcode(db.getPre_package_barcode());
		dm.setSendDat(db.getSend_dat());
		dm.setSended(db.isSended());
		dm.setSendUserAssignTime(db.getSend_user_assgin_dat());
		dm.setWhId(db.getWh_id());
		dm.setCartonType(db.getCarton_type());
		dm.setCartonWeight(db.getCarton_weight());
		dm.setNum(db.getNum());
		dm.setSendUserid(db.getSend_userid());
		dm.setMemoBarcodes(db.getMemo_barcodes());
		dm.setMemoNames(db.getMemo_names());
		session.saveOrUpdate(dm);
		
		if (db.getDbds()!=null) {
			for (int i=0;i<db.getDbds().size();i++) {
				com.cqqyd2014.order.model.DeliverBillDetail dbd=db.getDbds().get(i);
				com.cqqyd2014.hibernate.entities.DeliverD dd=new com.cqqyd2014.hibernate.entities.DeliverD();
				dd.setGoodsBarcode(dbd.getGoods_barcode());
				dd.setGoodsId(dbd.getGoods_id());
				dd.setGrossWeight(dbd.getGross_weight());
				dd.setId(new com.cqqyd2014.hibernate.entities.DeliverDId(dbd.getCom_id(), com.cqqyd2014.util.StringUtil.getUUID()));
				dd.setNetWeight(dbd.getNet_weight());
				dd.setNum(new java.math.BigDecimal(1));
				dd.setOrderNo(dbd.getOrder_no());
				dd.setPackageWeight(dbd.getPackage_weight());
				dd.setSeq(dbd.getSeq());
				session.saveOrUpdate(dd);
				
			}
		}
		session.flush();
		
		if (db.getDeliver_bill_status().equals("拣货完成")) {
			//更新拣货状态
			
			com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.OrderMainDAO();
			com.cqqyd2014.hibernate.entities.COrderMain om=omdao.getOrder(session, db.getOrder_no(), db.getCom_id());
			
			com.cqqyd2014.hibernate.dao.VPickupYueAllDAO vddao=new com.cqqyd2014.hibernate.dao.VPickupYueAllDAO();
			com.cqqyd2014.hibernate.entities.VPickupYueAll vpya=vddao.getViewByOrderNoComId(session, db.getOrder_no(), db.getCom_id());
			if (vpya.getId().getYue().compareTo(new java.math.BigDecimal(0))==0) {
				om.setGtStatus("拣货完毕");
			}
			
			
		
			
			//如果第一个发货单，更新快递状态为，等待出库
			
			if (om.getEmsStatus().equals("")){
				om.setEmsStatus("等待出库");
				session.saveOrUpdate(om);
			}
			
			
				/*
			//如果快递是顺丰，查看是否电子面单，如果电子面单，做确认处理
			if (db.getExpress_com().equals("500528000A")) {
				SfResponseOrderBackDAO sfdao=new SfResponseOrderBackDAO();
				com.cqqyd2014.hibernate.entities.SfResponseOrderBack back=sfdao.getEntityByOrderSeq(session, db.getOrder_no(), db.getSeq());
				if (back!=null) {
					//电子面单，进行确认处理



					//BillSf bill_sf=new BillSf();
					BspHttpClientOrderConfirm bhco=new BspHttpClientOrderConfirm(session, db);
					
					bhco.setDealtype("1");
					bhco.initBill();
					bhco.post();
					 session.flush();
				}
			}
			*/
		}
		
		
		
		
	}
	
	public static com.cqqyd2014.order.model.DeliverBill getModelFromEntityV(com.cqqyd2014.hibernate.entities.VDeliverM vdm,
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds){
		com.cqqyd2014.order.model.DeliverBill db=getModelFromEntityV(vdm);
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(vdds);
		db.setDbds(dbds);
		return db;
	
	}
	
	public static com.cqqyd2014.order.model.DeliverBill getModelFromEntityV2(com.cqqyd2014.hibernate.entities.VDeliverM vdm,java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverSeq> vdss){
		com.cqqyd2014.order.model.DeliverBill db=getModelFromEntityV(vdm);
		
		db.setDbds(com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayEntityView(vdss));
		return db;
	}
	
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> vdms_h){
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=new java.util.ArrayList<>();
		for (int i=0;i<vdms_h.size();i++) {
			com.cqqyd2014.order.model.DeliverBill db=getModelFromEntityV(vdms_h.get(i));
			dbs.add(db);
		}
		return dbs;
	}
	
	
	
	public static com.cqqyd2014.order.model.DeliverBill getModelFromEntityV(com.cqqyd2014.hibernate.entities.VDeliverM vdm){
		com.cqqyd2014.order.model.DeliverBill db=new com.cqqyd2014.order.model.DeliverBill();
		db.setCom_id(vdm.getId().getComId());
		db.setOrder_no(vdm.getId().getOrderNo());
		db.setSeq(vdm.getId().getSeq());
		db.setWh_id(vdm.getId().getWhId());
		db.setNum(vdm.getId().getNum());
		db.setCarton_type(vdm.getId().getCartonType());
		db.setCarton_weight(vdm.getId().getCartonWeight());
		db.setWh_name(vdm.getId().getWhName());
		db.setSend_dat(vdm.getId().getSendDat());
		db.setSend_user_assgin_dat(vdm.getId().getSendUserAssignTime());
		db.setOrder_dat(vdm.getId().getOrderDat());
		db.setPre_package_barcode(vdm.getId().getPrePackageBarcode());
		db.setPackage_userid(vdm.getId().getPackageUserid());
		db.setDeliver_bill_status(vdm.getId().getDeliverBillStatus());
		db.setActual_weight(vdm.getId().getActualWeight());
		db.setCancel_status(vdm.getId().getCancelStatus());
		db.setCancel_confirm_memo(vdm.getId().getCancelConfirmMemo());
		db.setCancel_confirm_userid(vdm.getId().getCancelConfirmUserid());
		db.setCancel_confrim_dat(vdm.getId().getCancelConfirmDat());
		db.setCancel_request_dat(vdm.getId().getCancelRequestDat());
		db.setCancel_request_memo(vdm.getId().getCancelRequestMemo());
		db.setCancel_request_userid(vdm.getId().getCancelRequestUserid());
		db.setDeliver_no(vdm.getId().getDeliverNo());
		db.setEffective(vdm.getId().getEffective());
		db.setLogistics_fb_dat(vdm.getId().getLogisticsFbDat());
		db.setLogistics_fb_memo(vdm.getId().getLogisticsFbMemo());
		db.setLogistics_fb_code(vdm.getId().getLogisticsFbCode());
		db.setLogistics_fb_status(vdm.getId().getLogisticsFbStatus());
		db.setExpress_com(vdm.getId().getExpressCom());
		db.setExpress_com_name(vdm.getId().getLogisticsName());
		db.setExpress_no(vdm.getId().getExpressNo());
		db.setOrder_no(vdm.getId().getOrderNo());
		db.setPackage_weight(vdm.getId().getPackageWeight());
		db.setSended(vdm.getId().getSended());
		db.setSeq(vdm.getId().getSeq());
		db.setVehicle_id(vdm.getId().getVehicleId());
		db.setVehicle_name(vdm.getId().getVehicleName());
		db.setSend_userid(vdm.getId().getSendUserid());
		db.setCom_name(vdm.getId().getComName());
		db.setReceive_addr(vdm.getId().getCUserAddr());
		db.setReceive_user(vdm.getId().getCUserName());
		db.setReceive_province(vdm.getId().getAddrProvince());
		db.setReceive_district(vdm.getId().getAddrDistrict());
		db.setReceive_addr_full(vdm.getId().getAddrProvince()+" "+vdm.getId().getAddrCity()+" "+vdm.getId().getAddrDistrict()
				+vdm.getId().getCUserAddr()+" "+vdm.getId().getUserCom());
		db.setReceive_city(vdm.getId().getAddrCity());
		db.setReceive_com(vdm.getId().getUserCom());
		db.setCreate_userid(vdm.getId().getCreateUserid());
		db.setCreate_username(vdm.getId().getCreateUsername());
		db.setPackage_dat(vdm.getId().getPackageDat());
		db.setOrder_dat(vdm.getId().getOrderDat());
		db.setMemo_barcodes(vdm.getId().getMemoBarcodes());
		db.setMemo_names(vdm.getId().getMemoNames());
		db.setOriginal_id(vdm.getId().getOriginalId());
		db.setReceive_mobile(vdm.getId().getReceiverMobile());
		db.setReceive_tell(vdm.getId().getTell2());
		return db;
	}

}
