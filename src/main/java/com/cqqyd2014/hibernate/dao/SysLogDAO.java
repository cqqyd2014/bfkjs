package com.cqqyd2014.hibernate.dao;

import org.hibernate.Session;

public class SysLogDAO {
	public  String saveLog(Session session,String user_id,String msg,String type,String com_id){
		String s=com.cqqyd2014.util.StringUtil.getUUID();
		com.cqqyd2014.hibernate.entities.SysLog sl=new com.cqqyd2014.hibernate.entities.SysLog(s, user_id, new java.sql.Timestamp((new java.util.Date()).getTime()), msg, type,com_id);
		session.save(sl);
		return s;
	}

}
