package com.cqqyd2014.order.logic;

public class EBusinessLogic {
	
	public static com.cqqyd2014.order.model.EBusiness getModelFromEntiy(com.cqqyd2014.hibernate.entities.Ebusiness eb_h){
		com.cqqyd2014.order.model.EBusiness eb=new com.cqqyd2014.order.model.EBusiness();
		eb.setE_id(eb_h.getEId());
		eb.setE_name(eb_h.getEName());
		return eb;
	
	}
	
	public static java.util.LinkedHashMap<String, String> getMapFromArrayListEntiy(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Ebusiness> ebs_h){
		java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();
		for (int i=0;i<ebs_h.size();i++) {
			map.put(ebs_h.get(i).getEId(), ebs_h.get(i).getEName());
		}
		
		
		return map;
	}

}
