package com.cqqyd2014.express.sf.logic;

public class RouteLogic {
	
	public static com.cqqyd2014.order.model.Route getModelFromEntity(com.cqqyd2014.hibernate.entities.SfResponseRouteBack rb){
		com.cqqyd2014.order.model.Route rt=new com.cqqyd2014.order.model.Route();
		rt.setAccept_addr(rb.getAcceptAddress());
		rt.setAccept_time(rb.getAcceptTimeDat());
		rt.setExpress_no(rb.getMailno());
		rt.setRemark(rb.getRemark());
		return rt;
	}
	public static java.util.ArrayList<com.cqqyd2014.order.model.Route> getArrayListModelFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfResponseRouteBack> rbs){
		java.util.ArrayList<com.cqqyd2014.order.model.Route> rts=new java.util.ArrayList<>();
		for (int i=0;i<rbs.size();i++) {
			com.cqqyd2014.order.model.Route rt=getModelFromEntity(rbs.get(i));
					rts.add(rt);
		}
		return rts;
	}

}
