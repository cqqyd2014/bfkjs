package com.cqqyd2014.order.logic;

public class OrderTypeLenLogic {
	
	
	public static com.cqqyd2014.order.model.OrderTypeLen getModelFromEntityView(com.cqqyd2014.hibernate.entities.VOrderFromUserLen voful){
		com.cqqyd2014.order.model.OrderTypeLen otl=new com.cqqyd2014.order.model.OrderTypeLen();
		otl.setLen(new java.math.BigDecimal(voful.getId().getOrderNoLen()));
		otl.setType_code(voful.getId().getOrderTypeCode());
		return otl;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.OrderTypeLen> getArrayModelArraryEntityView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUserLen > vofuls){
		java.util.ArrayList<com.cqqyd2014.order.model.OrderTypeLen> otls=new java.util.ArrayList<>();
		for (int i=0;i<vofuls.size();i++) {
			com.cqqyd2014.order.model.OrderTypeLen otl=getModelFromEntityView(vofuls.get(i));
			otls.add(otl);
		}
		return otls;
	}

}
