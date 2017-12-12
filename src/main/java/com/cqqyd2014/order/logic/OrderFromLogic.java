package com.cqqyd2014.order.logic;

import org.hibernate.Session;

public class OrderFromLogic {
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.OrderFrom> oneToArrayList(com.cqqyd2014.order.model.OrderFrom of){
		java.util.ArrayList<com.cqqyd2014.order.model.OrderFrom> ofs=new java.util.ArrayList<>();
		ofs.add(of);
		return ofs;
	}
	
	public static com.cqqyd2014.order.model.OrderFrom getModelFromEntityView(com.cqqyd2014.hibernate.entities.VOrderFrom vof){
		com.cqqyd2014.order.model.OrderFrom of=new com.cqqyd2014.order.model.OrderFrom(vof.getId().getOrderTypeCode(), vof.getId().getOrderTypeName());
		of.setCom_id(vof.getId().getComId());
		of.setE_id(vof.getId().getEId());
		of.setE_name(vof.getId().getEName());
		of.setSender(vof.getId().getSender());
		of.setSender_addr(vof.getId().getSenderAddr());
		of.setSender_city(vof.getId().getSenderCity());
		of.setSender_com(vof.getId().getSenderCom());
		of.setSender_district(vof.getId().getSenderDistrict());
		of.setSender_full_addr(vof.getId().getSenderFullAddr());
		of.setSender_province(vof.getId().getSenderProvince());
		of.setSender_tell(vof.getId().getSenderTell());
		
		
		return of;
	}
	
	public static void save(Session session,com.cqqyd2014.order.model.OrderFrom of) {
		com.cqqyd2014.hibernate.entities.OrderFrom of_h=new com.cqqyd2014.hibernate.entities.OrderFrom();
		of_h.setId(new com.cqqyd2014.hibernate.entities.OrderFromId(of.getCom_id(), of.getId(), of.getE_id()));
		of_h.setOrderTypeDesc(of.getDesc());
		of_h.setOrderTypeName(of.getName());
		of_h.setSender(of.getSender());
		of_h.setSenderAddr(of.getSender_addr());
		of_h.setSenderCity(of.getSender_city());
		of_h.setSenderCom(of.getSender_com());

		of_h.setSenderDistrict(of.getSender_district());
		of_h.setSenderFullAddr(of.getSender_full_addr());
		of_h.setSenderProvince(of.getSender_province());
		of_h.setSenderTell(of.getSender_tell());
		of_h.setSeq(of.getSeq());
		session.saveOrUpdate(of_h);
	}

}
