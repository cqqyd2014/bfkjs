package com.cqqyd2014.order.logic;

import org.hibernate.Session;

public class OrderFromUserLogic {
	
	public static com.cqqyd2014.order.model.OrderFromUser getModelFromEntityView(com.cqqyd2014.hibernate.entities.VOrderFromUser vofu){
		com.cqqyd2014.order.model.OrderFromUser ofu=new com.cqqyd2014.order.model.OrderFromUser();
		ofu.setType_code(vofu.getId().getOrderTypeCode());
		ofu.setType_name(vofu.getId().getOrderTypeName());
		ofu.setCom_id(vofu.getId().getComId());
		ofu.setDesc(vofu.getId().getOrderTypeDesc());
		ofu.setE_id(vofu.getId().getEId());
		ofu.setE_name(vofu.getId().getEName());
		ofu.setSeq(vofu.getId().getSeq());
		ofu.setUserid(vofu.getId().getUserId());
		
		return ofu;
	}
	public static java.util.ArrayList<com.cqqyd2014.order.model.OrderFromUser> getArrayModelFromArrayEntityView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> vofus){
		java.util.ArrayList<com.cqqyd2014.order.model.OrderFromUser> ofus=new java.util.ArrayList<>();
		for (int i=0;i<vofus.size();i++) {
			com.cqqyd2014.hibernate.entities.VOrderFromUser vofu=vofus.get(i);
			com.cqqyd2014.order.model.OrderFromUser ofu=getModelFromEntityView(vofu);
			ofus.add(ofu);
		}
		return ofus;
	}
	
	 public static void save(Session session,com.cqqyd2014.order.model.OrderFromUser ofu) {
		 com.cqqyd2014.hibernate.entities.OrderFromUser ofu_h=new com.cqqyd2014.hibernate.entities.OrderFromUser();
		 ofu_h.setId(new com.cqqyd2014.hibernate.entities.OrderFromUserId(ofu.getCom_id(),ofu.getType_code(),ofu.getUserid()));
		 session.save(ofu_h);
	 }

}
