package com.cqqyd2014.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqqyd2014.common.hibernate.BaseDaoHibernate;

import com.cqqyd2014.dao.inter.UserParDAO;
import com.cqqyd2014.hibernate.entities.VUserPar;

import com.cqqyd2014.system.model.UserPar;

@Repository("userParDAO")
public class HiberanteUserParDAOImpl extends BaseDaoHibernate<com.cqqyd2014.hibernate.entities.VUserPar,com.cqqyd2014.hibernate.entities.UserPar,com.cqqyd2014.system.model.UserPar> implements UserParDAO {



	@Override
	public UserPar getModelFromEntity(VUserPar v) {
		// TODO Auto-generated method stub
		UserPar m=new UserPar();
		m.setCom_id(v.getId().getComId());
		m.setPar_code(v.getId().getParCode());
		m.setPar_desc(v.getId().getParDesc());
		m.setPar_value(v.getId().getParValue());
		return m;
	}

	@Override
	public com.cqqyd2014.hibernate.entities.UserPar getEntityFromModel(UserPar m) {
		// TODO Auto-generated method stub
		com.cqqyd2014.hibernate.entities.UserPar e=new com.cqqyd2014.hibernate.entities.UserPar();
		e.setId(new com.cqqyd2014.hibernate.entities.UserParId(m.getUser_id(), m.getCom_id(), m.getPar_code()));
		e.setParDesc(m.getPar_desc());
		e.setParValue(m.getPar_value());
		return e;
	}

	@Override
	public List<UserPar> getArrayListModelByUserId(String user_id, String com_id) {
		// TODO Auto-generated method stub
		String hql="from VUserPar where id.userId=? and id.comId=?";
		java.util.List<com.cqqyd2014.system.model.UserPar> sus=findModel(hql,user_id,com_id);
		
			return sus;
		
		
	}

}
