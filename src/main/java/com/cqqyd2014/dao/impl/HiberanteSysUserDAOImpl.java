package com.cqqyd2014.dao.impl;





import org.springframework.stereotype.Repository;

import com.cqqyd2014.common.hibernate.BaseDaoHibernate;
import com.cqqyd2014.dao.inter.SysUserDAO;
import com.cqqyd2014.hibernate.entities.VSysUser;
import com.cqqyd2014.system.model.SysUser;


@Repository("sysUserDAO")
public class HiberanteSysUserDAOImpl extends BaseDaoHibernate<com.cqqyd2014.hibernate.entities.VSysUser,com.cqqyd2014.hibernate.entities.SysUser,com.cqqyd2014.system.model.SysUser> implements SysUserDAO {




	@Override
	public SysUser getModelFromEntity(VSysUser v) {
		// TODO Auto-generated method stub
		com.cqqyd2014.system.model.SysUser m=new com.cqqyd2014.system.model.SysUser();
		m.setCom_id(v.getId().getComId());
		m.setCreate_dat(v.getId().getCreateTime());
		m.setCreate_dat_chinese_print(com.cqqyd2014.util.DateUtil.getLocalFullString(v.getId().getCreateTime()));
		m.setCreate_dat_print(com.cqqyd2014.util.DateUtil.getPrintFullString(v.getId().getCreateTime()));
		m.setEffective(v.getId().getEffective());
		m.setEmail(v.getId().getEmail());
		m.setLast_modify_dat(v.getId().getLastModifyDat());
		m.setLast_modify_uuid(v.getId().getLastModifyUuid());
		m.setLast_online_dat(v.getId().getLastOnlineTime());
		m.setLast_online_dat_chinese_print(com.cqqyd2014.util.DateUtil.getLocalFullString(v.getId().getLastOnlineTime()));
		m.setLast_online_dat_print(com.cqqyd2014.util.DateUtil.getPrintFullString(v.getId().getLastOnlineTime()));
		m.setLogin_name(v.getId().getUserLogin());
		m.setOnline(v.getId().getOnline());
		m.setPassword_md5(v.getId().getPwd());
		m.setPickup_weighting(v.getId().getPickupWeighting());
		m.setQuota_add(v.getId().getQuotaAdd());
		m.setQuota_current(v.getId().getQuotaCurrent());
		m.setQuota_freez(v.getId().getQuotaFreez());
		m.setQuota_subtract(v.getId().getQuotaSubtract());
		m.setSend_wetighting(v.getId().getSendWeighting());
		m.setTell(v.getId().getTel());
		m.setUser_id(v.getId().getUserId());
		m.setUser_name(v.getId().getName());
		
		
		return m;
	}

	@Override
	public com.cqqyd2014.hibernate.entities.SysUser getEntityFromModel(SysUser m) {
		// TODO Auto-generated method stub
		
		com.cqqyd2014.hibernate.entities.SysUser h=new com.cqqyd2014.hibernate.entities.SysUser();
		h.setCreateTime(m.getCreate_dat());
		
		h.setEffective(m.isEffective());
		h.setEmail(m.getEmail());
		h.setId(new com.cqqyd2014.hibernate.entities.SysUserId(m.getCom_id(), m.getUser_id()));
		h.setLastModifyDat(m.getLast_modify_dat());
		h.setLastModifyUuid(m.getLast_modify_uuid());
		h.setLastOnlineTime(m.getLast_online_dat());
		h.setName(m.getUser_name());
		h.setOnline(m.isOnline());
		h.setPickupWeighting(m.getPickup_weighting());
		h.setPwd(m.getPassword_md5());
		h.setQuotaAdd(m.getQuota_add());
		h.setQuotaCurrent(m.getQuota_current());
		h.setQuotaFreez(m.getQuota_freez());
		h.setQuotaSubtract(m.getQuota_subtract());
		h.setSendWeighting(m.getSend_wetighting());
		h.setTel(m.getTell());
		h.setUserLogin(m.getLogin_name());
		return h;
	}



	@Override
	public SysUser getModelByUserId(String user_id, String com_id) {
		// TODO Auto-generated method stub
		String hql="from VSysUser where id.userId=? and id.comId=?";
		java.util.List<com.cqqyd2014.system.model.SysUser> sus=findModel(hql,user_id,com_id);
		if (sus.size()==0){
			return null;
		}
		else{
			return sus.get(0);
		}
	}

	@Override
	public SysUser getModelByLoginName(String login_name, String com_id) {
		// TODO Auto-generated method stub
		String hql="from VSysUser where id.userLogin=? and id.comId=?";
		java.util.List<com.cqqyd2014.system.model.SysUser> sus=findModel(hql,login_name,com_id);
		if (sus.size()==0){
			return null;
		}
		else{
			return sus.get(0);
		}
	}

	@Override
	public void setOnline(String user_id, String com_id) {
		// TODO Auto-generated method stub
		SysUser su=getModelByUserId(user_id,com_id);
		su.setOnline(true);
		su.setLast_online_dat(new java.util.Date());
		updateModel(su);
	}

	
	

}
