package com.cqqyd2014.system.logic;

public final class ComInfoLogic {
	public static com.cqqyd2014.system.model.ComInfo getModelFromView(com.cqqyd2014.hibernate.entities.VComInfo vif){
		com.cqqyd2014.system.model.ComInfo ci=new com.cqqyd2014.system.model.ComInfo();
		ci.setCom_code(vif.getId().getCId());
		ci.setCom_name(vif.getId().getCName());
		ci.setContract_name(vif.getId().getContactName());
		ci.setEffective(vif.getId().getEffective());
		ci.setMessage_mail(vif.getId().getMessageMail());
		ci.setOrder_head(vif.getId().getOrderHead());
		ci.setSeq(vif.getId().getSeq());
		ci.setService_tell(vif.getId().getServiceTell());
		ci.setWarning_mail(vif.getId().getWaringMail());
		
		
		return ci;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.system.model.ComInfo > getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComInfo> vcis){
		java.util.ArrayList<com.cqqyd2014.system.model.ComInfo> cis=new java.util.ArrayList<>();
		for (int i=0,length=vcis.size();i<length;i++){
			com.cqqyd2014.system.model.ComInfo ci=getModelFromView(vcis.get(i));
			cis.add(ci);
		}
		return cis;
	}

}
