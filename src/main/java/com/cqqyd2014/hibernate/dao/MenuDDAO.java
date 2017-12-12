package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class MenuDDAO {
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> getWebAttentionMenuD(Session session,String user_id,String com_id){
		String hql="from VUserMenuD where id.comId=:com_id and id.userId=:userid and id.webAttention=true";
		Query q = session.createQuery(hql);

		q.setParameter("com_id",com_id);

		q.setParameter("userid",user_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> list=(java.util.ArrayList)q.list();
		return list;
	}
	public boolean checkIfHaveMenuD(Session session,String userid,String m_id,String m_d_id,String com_id) {
		String hql="from VUserMenuD where id.comId=:com_id and id.userId=:userid and id.menuId=:m_id and id.menuDId=:m_d_id";
		Query q = session.createQuery(hql);

		q.setParameter("com_id",com_id);
		q.setParameter("m_id",m_id);
		q.setParameter("m_d_id",m_d_id);
		q.setParameter("userid",userid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> list=(java.util.ArrayList)q.list();
		if (list.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	public java.util.ArrayList<com.cqqyd2014.system.model.MenuD> getMenuDAll(Session session,String com_id,String user_id){
		String hql="from VUserMenuD where id.comId=:com_id and id.userId=:user_id order by id.menuDId";
		Query q = session.createQuery(hql);

		q.setParameter("com_id",com_id);
		q.setParameter("user_id",user_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			java.util.ArrayList<com.cqqyd2014.system.model.MenuD> mds=new java.util.ArrayList<>();
			for (int i=0;i<list.size();i++){
				com.cqqyd2014.system.model.MenuD md=new com.cqqyd2014.system.model.MenuD();
				com.cqqyd2014.hibernate.entities.VUserMenuD o=list.get(i);
				md.setM_id(o.getId().getMenuId());
				md.setM_d_id(o.getId().getMenuDId());
				md.setM_d_name(o.getId().getMenuDName());
				md.setM_d_js_method(o.getId().getMenuDJsMethod());
				md.setM_d_js_url(o.getId().getMenuDJsUrl());
				mds.add(md);
				
			}
			return mds;
		}
	}
	/*
	public java.util.ArrayList<com.cqqyd2014.bfkjs.mainframe.model.MenuD> getMenuDByMId(Session session,String com_id,String m_id){
		String hql="from MenuD where id.comId=:com_id and id.menuId=:m_id";
		Query q = session.createQuery(hql);
		q.setParameter("m_id",m_id);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.MenuD> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			java.util.ArrayList<com.cqqyd2014.bfkjs.mainframe.model.MenuD> mds=new java.util.ArrayList<>();
			for (int i=0;i<list.size();i++){
				com.cqqyd2014.bfkjs.mainframe.model.MenuD md=new com.cqqyd2014.bfkjs.mainframe.model.MenuD();
				com.cqqyd2014.hibernate.entities.MenuD o=list.get(i);
				md.setM_id(o.getId().getMenuId());
				md.setM_d_id(o.getId().getMenuDId());
				md.setM_d_name(o.getMenuDName());
				md.setM_d_js_method(o.getMenuDJsMethod());
				md.setM_d_js_url(o.getMenuDJsUrl());
				
				mds.add(md);
				
			}
			return mds;
		}
	}
	*/
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> getMenuDByMId(Session session,String com_id,String m_id,String user_id){
		String hql="from VUserMenuD where id.comId=:com_id and id.userId=:user_id and id.menuId=:m_id order by id.orderId";
		Query q = session.createQuery(hql);

		q.setParameter("com_id",com_id);
		q.setParameter("user_id",user_id);
		q.setParameter("m_id",m_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}else {
		return list;
		}
	}

}
