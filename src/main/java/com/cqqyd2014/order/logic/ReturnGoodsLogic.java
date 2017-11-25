package com.cqqyd2014.order.logic;

import org.hibernate.Session;

public class ReturnGoodsLogic {
	public static com.cqqyd2014.order.model.ReturnGoods getModelFromView (com.cqqyd2014.hibernate.entities.VReturnGoods vrg){
		com.cqqyd2014.order.model.ReturnGoods rg=new com.cqqyd2014.order.model.ReturnGoods();
		rg.setGoods_barcode(vrg.getId().getGoodsBarcode());
		rg.setGoods_id(vrg.getId().getGoodsId());
		rg.setGoods_name(vrg.getId().getCName());
		rg.setMemo(vrg.getId().getMemo());
		rg.setOp_date(vrg.getId().getOpDat());
		rg.setOp_date_chinese(com.cqqyd2014.util.DateUtil.ChineseDate(vrg.getId().getOpDat()));
		rg.setOrder_create_userid(vrg.getId().getOrderCreateUserid());
		rg.setOrder_create_username(vrg.getId().getOrderCreateUsername());
		rg.setReturn_pirce(vrg.getId().getReturnPrice());
		rg.setReturn_userid(vrg.getId().getReturnUserid());
		rg.setReturn_username(vrg.getId().getReturnUsername());
		rg.setCom_id(vrg.getId().getComId());
		rg.setUuid(vrg.getId().getUuid());
		rg.setOrder_no(vrg.getId().getOrderNo());
		rg.setSeq(vrg.getId().getSeq());
		return rg;
	}
	public static void save(Session session,com.cqqyd2014.order.model.ReturnGoods rg) {
		com.cqqyd2014.hibernate.entities.ReturnGoods rg_h=new com.cqqyd2014.hibernate.entities.ReturnGoods();
		rg_h.setGoodsBarcode(rg.getGoods_barcode());
		rg_h.setGoodsId(rg.getGoods_id());
		rg_h.setId(new com.cqqyd2014.hibernate.entities.ReturnGoodsId(rg.getCom_id(), com.cqqyd2014.util.StringUtil.getUUID()));
		rg_h.setMemo(rg.getMemo());
		rg_h.setOpDat(rg.getOp_date());
		rg_h.setOrderCreateUserid(rg.getOrder_create_userid());
		rg_h.setOrderNo(rg.getOrder_no());
		rg_h.setReturnPrice(rg.getReturn_pirce());
		rg_h.setReturnUserid(rg.getReturn_userid());
		rg_h.setSeq(rg.getSeq());
		session.saveOrUpdate(rg_h);
	}

}
