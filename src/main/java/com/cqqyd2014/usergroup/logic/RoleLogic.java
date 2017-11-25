package com.cqqyd2014.usergroup.logic;

import org.hibernate.Session;

public class RoleLogic {
	public java.util.LinkedHashMap<String, String> getMapFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysRole> srs){
		java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();
		for (int i=0;i<srs.size();i++) {
			map.put(srs.get(i).getId().getRoleId(), srs.get(i).getRoleName());
		}
		return map;
	}
	
	public com.cqqyd2014.usergroup.model.SysRole getModelFromEntity(com.cqqyd2014.hibernate.entities.SysRole r) {
		com.cqqyd2014.usergroup.model.SysRole sr=new com.cqqyd2014.usergroup.model.SysRole();
		sr.setRole_id(r.getId().getRoleId());
		sr.setRole_name(r.getRoleName());
		sr.setCom_id(r.getId().getComId());
		return sr;
	}
	
	public void save(Session session,com.cqqyd2014.usergroup.model.SysRole sr) {
		com.cqqyd2014.hibernate.entities.SysRole sr_h=new com.cqqyd2014.hibernate.entities.SysRole();
		sr_h.setId(new com.cqqyd2014.hibernate.entities.SysRoleId(sr.getRole_id(), sr.getCom_id()));
		sr_h.setRoleName(sr.getRole_name());
		session.saveOrUpdate(sr_h);
	}
	

}
