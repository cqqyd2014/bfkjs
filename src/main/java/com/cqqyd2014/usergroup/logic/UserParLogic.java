package com.cqqyd2014.usergroup.logic;

import org.hibernate.Session;

public class UserParLogic {
	
	
	public void save(Session session,com.cqqyd2014.usergroup.model.UserPar up) {
		com.cqqyd2014.hibernate.entities.UserPar up_h=new com.cqqyd2014.hibernate.entities.UserPar();
		up_h.setId(new com.cqqyd2014.hibernate.entities.UserParId(up.getUserid(),up.getCom_id(),up.getParam()));
		up_h.setParDesc(up.getParam_desc());
		up_h.setParValue(up.getValue());
		session.saveOrUpdate(up_h);
	}
	
	public java.util.LinkedHashMap<String, String> getMapFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar> ups_h){
		java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();
for (int i=0;i<ups_h.size();i++) {
			
			com.cqqyd2014.usergroup.model.UserPar up=getModelFromEntity(ups_h.get(i));
			map.put(up.getParam(), up.getValue());
		}
return map;
	}
	
	public java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPar> getArrayListModelEntityFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar> ups_h){
		java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPar> ups=new java.util.ArrayList<>();
		for (int i=0;i<ups_h.size();i++) {
			
			com.cqqyd2014.usergroup.model.UserPar up=getModelFromEntity(ups_h.get(i));
			ups.add(up);
		}
		return ups;
	}
	
	public com.cqqyd2014.usergroup.model.UserPar getModelFromEntity(com.cqqyd2014.hibernate.entities.UserPar up_h){
		com.cqqyd2014.usergroup.model.UserPar up=new com.cqqyd2014.usergroup.model.UserPar();
		up.setCom_id(up_h.getId().getComId());
		up.setParam(up_h.getId().getParCode());
		up.setParam_desc(up_h.getParDesc());
		up.setUserid(up_h.getId().getUserId());
		up.setValue(up_h.getParValue());
		return up;
	}

}
