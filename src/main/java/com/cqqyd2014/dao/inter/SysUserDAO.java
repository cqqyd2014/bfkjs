package com.cqqyd2014.dao.inter;



public interface SysUserDAO {
	public com.cqqyd2014.system.model.SysUser getModelByUserId(String user_id,String com_id);
	public com.cqqyd2014.system.model.SysUser getModelByLoginName(String login_name,String com_id);
	public void setOnline(String user_id,String com_id);

}
