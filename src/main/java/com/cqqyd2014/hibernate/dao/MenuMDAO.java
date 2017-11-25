package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class MenuMDAO {
	public java.util.ArrayList<com.cqqyd2014.system.model.MenuM> getMenu(Session session,String com_id,String user_id){
		String hql="from VUserMenuM where id.comId=:com_id and id.userId=:user_id";
		Query q = session.createQuery(hql);

		q.setParameter("com_id",com_id);
		q.setParameter("user_id",user_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuM> list=(java.util.ArrayList)q.list();
		if (list.size()==0){
			return null;
		}
		else{
			java.util.ArrayList<com.cqqyd2014.system.model.MenuM> mms=new java.util.ArrayList<>();
			com.cqqyd2014.hibernate.dao.MenuDDAO mddao=new com.cqqyd2014.hibernate.dao.MenuDDAO();
			for (int i=0;i<list.size();i++){
				com.cqqyd2014.system.model.MenuM mm=new com.cqqyd2014.system.model.MenuM();
				com.cqqyd2014.hibernate.entities.VUserMenuM o=list.get(i);
				mm.setM_id(o.getId().getMenuId());
				mm.setM_name(o.getId().getMenuName());
				mm.setM_desc(o.getId().getDesc());
				mm.setMenu_d(mddao.getMenuDByMId(session, com_id, o.getId().getMenuId(),user_id));
				
				mms.add(mm);
				
			}
			return mms;
		}
		
	}

}
