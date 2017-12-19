package com.cqqyd2014.system.logic;

import org.hibernate.Session;

public final class SysParLogic {
	

	
	
	//smtp发件服务器信息
	public static String getSmtpServer(Session session){
		
		return com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session,"smtp_server");
	}

	
	public static String getSmtpUser(Session session){
		
		return com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session,"smtp_user");
	}
	public static String getSmtpPwd(Session session){
		
		return com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session,"smtp_pwd");
	}
	
	
	
	
	//强制下线时间
public static java.math.BigDecimal getOfflineTime(Session session){
		
		return com.cqqyd2014.hibernate.dao.SysParDAO.getDecValueByCode(session, "offline_time");
	}
	
	
	
	
	
	//返回用户保持登录时间
	public static java.math.BigDecimal getIntervalTime(Session session){
		
		return com.cqqyd2014.hibernate.dao.SysParDAO.getDecValueByCode(session, "interval_time");
	}
	
	
	
	
	public static com.cqqyd2014.system.model.SysPar getModleFromEntity(com.cqqyd2014.hibernate.entities.SysPar sp_h){
		com.cqqyd2014.system.model.SysPar sp=new com.cqqyd2014.system.model.SysPar();
		sp.setCode(sp_h.getCode());
		sp.setDescription(sp_h.getCodeDesc());
		sp.setValue(sp_h.getValue());
		java.util.ArrayList<String> items=com.cqqyd2014.util.StringUtil.ArrayToArrayList(sp_h.getSelectItems().split(","));
		
		
		return sp;
	}
	public static String getStringValue(com.cqqyd2014.system.model.SysPar sp){
		return sp.getValue();
	}
	public static java.math.BigDecimal getDecValue(com.cqqyd2014.system.model.SysPar sp){
		return new java.math.BigDecimal(sp.getValue());
	}


}
