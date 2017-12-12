package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.quota.logic.QuotaTransLogic;

public class QuotaTransDAO {
	public java.util.ArrayList<com.cqqyd2014.quota.model.QuotaTrans> getArrayByUserid(Session session,String com_id,String userid){
		String hql="from VQuotaTrans where id.userid=:userid and id.comId=:com_id order by id.opTime desc";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VQuotaTrans> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VQuotaTrans>)q.list();
		QuotaTransLogic qtl=new QuotaTransLogic();
		return qtl.transFromArrayEnties(list);
	}
	public com.cqqyd2014.quota.model.QuotaTrans getModelByUuid(Session session,String com_id,String uuid,String userid){
		com.cqqyd2014.hibernate.entities.VQuotaTrans vqt=getViewObjectByUuid(session,com_id,uuid,userid);
		QuotaTransLogic qtl=new QuotaTransLogic();
			return qtl.transFromEnties(vqt);
		
		
	}
	public com.cqqyd2014.hibernate.entities.VQuotaTrans getViewObjectByUuid(Session session,String com_id,String uuid,String userid){
		String hql="from VQuotaTrans where id.userid=:userid and id.comId=:com_id and id.uuid=:uuid";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		q.setParameter("uuid", uuid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VQuotaTrans> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VQuotaTrans>)q.list();
		if (list.size()>0){
			//QuotaTransLogic qtl=new QuotaTransLogic();
			return list.get(0);//qtl.transFromEnties(list.get(0));
		}
		else{
			return null;
		}
		
	}
	public com.cqqyd2014.hibernate.entities.QuotaTrans getObjectByUuid(Session session,String com_id,String uuid,String userid){
		String hql="from QuotaTrans where userid=:userid and id.comId=:com_id and id.uuid=:uuid";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		q.setParameter("uuid", uuid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.QuotaTrans> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.QuotaTrans>)q.list();
		if (list.size()>0){
			//QuotaTransLogic qtl=new QuotaTransLogic();
			return list.get(0);//qtl.transFromEnties(list.get(0));
		}
		else{
			return null;
		}
	}
	

}
