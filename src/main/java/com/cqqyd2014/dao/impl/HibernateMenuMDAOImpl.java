package com.cqqyd2014.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqqyd2014.common.hibernate.BaseDaoHibernate;
import com.cqqyd2014.dao.inter.MenuDDAO;
import com.cqqyd2014.dao.inter.MenuMDAO;
import com.cqqyd2014.hibernate.entities.VUserMenuM;
import com.cqqyd2014.system.model.MenuM;
@Repository("menuMDAO")
public class HibernateMenuMDAOImpl extends BaseDaoHibernate<com.cqqyd2014.hibernate.entities.VUserMenuM,com.cqqyd2014.hibernate.entities.MenuM,com.cqqyd2014.system.model.MenuM> implements MenuMDAO {

	@Override
	public MenuM getModelFromEntity(VUserMenuM v) {
		// TODO Auto-generated method stub
		com.cqqyd2014.system.model.MenuM m=new com.cqqyd2014.system.model.MenuM();
		m.setCom_id(v.getId().getComId());
		m.setM_desc(v.getId().getDesc());
		m.setM_id(v.getId().getMenuId());
		m.setM_name(v.getId().getMenuName());
		m.setOrder_id(v.getId().getOrderId());
		m.setUserid(v.getId().getUserId());
		
		
		return m;
	}

	@Override
	public com.cqqyd2014.hibernate.entities.MenuM getEntityFromModel(MenuM m) {
		// TODO Auto-generated method stub
		com.cqqyd2014.hibernate.entities.MenuM e=new com.cqqyd2014.hibernate.entities.MenuM();
		e.setDesc(m.getM_desc());
		e.setId(new com.cqqyd2014.hibernate.entities.MenuMId(m.getCom_id(), m.getM_id()));
		e.setMenuName(m.getM_name());
		e.setOrderId(m.getOrder_id());
		
		
		return e;
	}

	@Override
	public List<MenuM> getListModelByUserId(String user_id,String com_id) {
		// TODO Auto-generated method stub
		String hql="from VUserMenuM where id.userId=? and id.comId=? order by id.orderId";
		java.util.List<com.cqqyd2014.system.model.MenuM> sus=findModel(hql,user_id,com_id);
		
			return sus;
	}

}
