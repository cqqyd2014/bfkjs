package com.cqqyd2014.usergroup.logic;

import org.hibernate.Session;

public class UserRoleLogic {
	
	public void save(Session session,com.cqqyd2014.usergroup.model.UserRole ur) {
		com.cqqyd2014.hibernate.entities.SysUserRole sur=new com.cqqyd2014.hibernate.entities.SysUserRole();
		sur.setId(new com.cqqyd2014.hibernate.entities.SysUserRoleId(ur.getUser_uuid(), ur.getRole_id(), ur.getCom_id()));
		
		session.saveOrUpdate(sur);
		
		
	}

}
