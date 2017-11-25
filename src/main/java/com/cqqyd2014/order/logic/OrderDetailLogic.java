package com.cqqyd2014.order.logic;

import org.hibernate.Session;

public class OrderDetailLogic {
	
	public static com.cqqyd2014.order.model.OrderDetail getModelFromView(com.cqqyd2014.hibernate.entities.VOrderDetail vod){
		com.cqqyd2014.order.model.OrderDetail od=new com.cqqyd2014.order.model.OrderDetail();
		od.setDetail_id(vod.getId().getCDetailId());
		od.setGoods_id(vod.getId().getCGoodsId());
		od.setGoods_name(vod.getId().getCName());
		od.setNum(vod.getId().getCCount());
		od.setOrder_no(vod.getId().getCOrderId());
		od.setPrice(vod.getId().getCPrice());
		od.setTotal1(vod.getId().getCCount().multiply(vod.getId().getCPrice()));
		od.setUnit(vod.getId().getUnit());
		od.setCom_id(vod.getId().getComId());
		od.setPrice2(vod.getId().getCPrice2());
		od.setReg_tax(vod.getId().getCRegTax2());
		od.setTax(vod.getId().getCTax2());
		od.setOrder_detail_dat(vod.getId().getCTime());
		od.setTotal2(vod.getId().getTotal2());
		return od;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods){
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail > ods=new java.util.ArrayList<>();
		for (int i=0;i<vods.size();i++) {
			com.cqqyd2014.order.model.OrderDetail od=getModelFromView(vods.get(i));
			ods.add(od);
		}
		return ods;
	}
	
	public static void save(Session session,com.cqqyd2014.order.model.OrderDetail od) {
		com.cqqyd2014.hibernate.entities.COrderDetail od_h=new com.cqqyd2014.hibernate.entities.COrderDetail();
		od_h.setCCount(od.getNum());
		od_h.setCDetailId(od.getDetail_id());
		od_h.setCGoodsId(od.getGoods_id());
		od_h.setComId(od.getCom_id());
		od_h.setCOrderId(od.getOrder_no());
		od_h.setCPrice(od.getPrice());
		od_h.setCPrice2(od.getPrice2());
		od_h.setCRegTax2(od.getReg_tax());
		od_h.setCTax2(od.getTax());
		od_h.setCTime(od.getOrder_detail_dat());
		od_h.setTotal2(od.getTotal2());
		od_h.setTotall(od.getTotal1());
		
		session.saveOrUpdate(od_h);
	}

}
