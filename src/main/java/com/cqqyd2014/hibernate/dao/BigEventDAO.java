package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.util.exception.AjaxSuccessMessageException;


public class BigEventDAO {
	private java.util.ArrayList<com.cqqyd2014.hibernate.entities.BigEvent> getListByComId(Session session,String com_id){
		String hql="from BigEvent where id.comId=:com_id order by BDate desc";
		Query q = session.createQuery(hql);

		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.BigEvent>)q.list();
	}

	public java.util.ArrayList<com.cqqyd2014.tools.big_event.model.BigEventItem> getListModelByComId(Session session,String com_id){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.BigEvent> list=getListByComId(session,com_id);
		java.util.ArrayList<com.cqqyd2014.tools.big_event.model.BigEventItem> beis=new java.util.ArrayList<>();
		for (int i=0;i<list.size();i++){
			com.cqqyd2014.tools.big_event.model.BigEventItem bei=new com.cqqyd2014.tools.big_event.model.BigEventItem();
			com.cqqyd2014.hibernate.entities.BigEvent be=list.get(i);
			bei.setB_date(com.cqqyd2014.util.DateUtil.JDateToSimpleString(be.getBDate()));
			bei.setContent(be.getContent());
			bei.setTitle(be.getTitle());
			bei.setUuid(be.getId().getUuid());
			beis.add(bei);
			
			
		}
		return beis;
	}
	private com.cqqyd2014.hibernate.entities.BigEvent getObjectByUuid(Session session,String com_id,String uuid){
		String hql="from BigEvent where id.comId=:com_id and id.uuid=:uuid";
		Query q = session.createQuery(hql);

		q.setParameter("com_id", com_id);
		q.setParameter("uuid", uuid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.BigEvent>  bes=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.BigEvent>)q.list();
		if (bes.size()==0){
			return null;
		}
		else{
			return bes.get(0);
		}
	}
	
	public com.cqqyd2014.tools.big_event.model.BigEventItem getModelByUuid(Session session,String com_id,String uuid) throws AjaxSuccessMessageException{
		com.cqqyd2014.hibernate.entities.BigEvent o=getObjectByUuid(session,com_id,uuid);
		if (o==null){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("找不到大事记对象"+uuid);
		}
		com.cqqyd2014.tools.big_event.model.BigEventItem bei=new com.cqqyd2014.tools.big_event.model.BigEventItem();
		bei.setB_date(com.cqqyd2014.util.DateUtil.JDateToSimpleString(o.getBDate()));
		bei.setContent(o.getContent());
		bei.setTitle(o.getTitle());
		bei.setUuid(o.getId().getUuid());
		return bei;
	}
	public boolean deleteObjectByUuid(Session session,String com_id,String uuid) throws AjaxSuccessMessageException{
		com.cqqyd2014.hibernate.entities.BigEvent be=getObjectByUuid(session,com_id,uuid);
		if (be==null){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("找不到大事记对象"+uuid);
		}
		session.delete(be);
		return true;
		
	}
}
