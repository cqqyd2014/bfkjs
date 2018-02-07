package com.cqqyd2014.service.inter;



public interface SysUserService {
	
	public com.cqqyd2014.system.model.SysUser checkLoginName(String login_name,String password,String com_id) throws com.cqqyd2014.util.exception.ServcieException;

	public void setOnline(String user_id,String com_id);
}
