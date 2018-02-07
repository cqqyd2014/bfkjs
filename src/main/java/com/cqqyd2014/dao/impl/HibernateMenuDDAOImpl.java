package com.cqqyd2014.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqqyd2014.common.hibernate.BaseDaoHibernate;
import com.cqqyd2014.dao.inter.MenuDDAO;
import com.cqqyd2014.dao.inter.MenuMDAO;
import com.cqqyd2014.hibernate.entities.VUserMenuD;
import com.cqqyd2014.system.model.MenuD;

@Repository("menuDDAO")
public class HibernateMenuDDAOImpl extends BaseDaoHibernate<com.cqqyd2014.hibernate.entities.VUserMenuD,com.cqqyd2014.hibernate.entities.MenuD,com.cqqyd2014.system.model.MenuD> implements MenuDDAO {

	@Override
	public List<MenuD> getListModelByUserId(String user_id, String com_id) {
		// TODO Auto-generated method stub
		String hql="from VUserMenuD where id.userId=? and id.comId=? order by id.orderId";
		java.util.List<com.cqqyd2014.system.model.MenuD> sus=findModel(hql,user_id,com_id);
		
			return sus;
	}

	@Override
	public MenuD getModelFromEntity(VUserMenuD v) {
		// TODO Auto-generated method stub
		com.cqqyd2014.system.model.MenuD m=new com.cqqyd2014.system.model.MenuD();
		m.setD_name(v.getId().getMenuDName());
		m.setGet_num_class(v.getId().getGetNumClass());
		m.setGet_num_method(v.getId().getGetNumMethod());
		m.setM_d_id(v.getId().getMenuDId());
		m.setM_d_js_method(v.getId().getMenuDJsMethod());
		m.setM_id(v.getId().getMenuId());
		m.setMenu_d_js_url(v.getId().getMenuDJsMethod());
		m.setUserid(v.getId().getUserId());
		m.setWeb_attention(v.getId().getWebAttention());
		m.setWeb_attention_tips(v.getId().getWebAttentionTips());
		m.setOrder_id(v.getId().getOrderId());
		return m;
	}

	@Override
	public com.cqqyd2014.hibernate.entities.MenuD getEntityFromModel(MenuD m) {
		// TODO Auto-generated method stub
		
		com.cqqyd2014.hibernate.entities.MenuD e =new com.cqqyd2014.hibernate.entities.MenuD();
		e.setGetNumClass(m.getGet_num_class());
		e.setGetNumMethod(m.getGet_num_method());
		e.setId(new com.cqqyd2014.hibernate.entities.MenuDId(m.getM_id(), m.getM_d_id(), m.getCom_id()));
		e.setMenuDJsMethod(m.getM_d_js_method());
		e.setMenuDJsUrl(m.getMenu_d_js_url());
		e.setMenuDName(m.getD_name());
		e.setOrderId(m.getOrder_id());
		e.setWebAttention(m.isWeb_attention());
		e.setWebAttentionTips(m.getWeb_attention_tips());
		return e;
	}

}
