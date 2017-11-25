package com.cqqyd2014.usergroup.logic;

import org.hibernate.Session;

public class SysUserLogic {
	
	
	public com.cqqyd2014.usergroup.model.SysUser getModelFromEntity(com.cqqyd2014.hibernate.entities.SysUser su_h){
		com.cqqyd2014.usergroup.model.SysUser su=new com.cqqyd2014.usergroup.model.SysUser();
		su.setCom_id(su_h.getComId());
		su.setCreate_date(su_h.getCreateTime());
		su.setDisplay_name(su_h.getName());
		su.setEffective(su_h.getEffective());
		su.setEmail(su_h.getEmail());
		su.setLast_online_dat(su_h.getLastOnlineTime());
		su.setLogin_user(su_h.getUserLogin());
		su.setOn_line(su_h.getOnline());
		//su.setPassword(su_h.getPwd());
		su.setPassword_md5(su_h.getPwd());
		su.setPickup_weighting(su_h.getPickupWeighting());
		su.setQuota_add(su_h.getQuotaAdd());
		su.setQuota_current(su_h.getQuotaCurrent());
		su.setQuota_freez(su_h.getQuotaFreez());
		su.setQuota_substract(su_h.getQuotaSubtract());
		su.setSend_weighting(su_h.getSendWeighting());
		su.setTell(su_h.getTel());
		su.setUuid(su_h.getId());
		return su;
	}

	public void save(Session session,com.cqqyd2014.usergroup.model.SysUser su) {
		com.cqqyd2014.hibernate.entities.SysUser su_h=new com.cqqyd2014.hibernate.entities.SysUser();
		su_h.setId(su.getUuid());
		su_h.setCreateTime(su.getCreate_date());
		su_h.setEffective(su.isEffective());
		su_h.setLastOnlineTime(su.getLast_online_dat());
		su_h.setName(su.getDisplay_name());
		su_h.setOnline(su.isOn_line());
		su_h.setPickupWeighting(su.getPickup_weighting());
		su_h.setPwd(su.getPassword_md5());
		su_h.setQuotaAdd(su.getQuota_add());
		su_h.setQuotaCurrent(su.getQuota_current());
		su_h.setQuotaFreez(su.getQuota_freez());
		su_h.setQuotaSubtract(su.getQuota_substract());
		su_h.setSendWeighting(su.getSend_weighting());
		su_h.setTel(su.getTell());
		su_h.setUserLogin(su.getLogin_user());
		su_h.setEmail(su.getEmail());
		su_h.setComId(su.getCom_id());
		session.saveOrUpdate(su_h);
	}
}
