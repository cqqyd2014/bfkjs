package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class ComParCodeDAO {
	
	
	
	public String getWeixinWelcome(Session session,String com_id){
		
		
		return getValue(session,"CQQY","default_weixin_sub_welcome");
	}
	
	public String getValue(Session session,String com_id,String par_code){
		com.cqqyd2014.hibernate.entities.ComParCode o=getParCodeObject(session,com_id,par_code);
		if (o==null){
			return null;
		}
		else{
			return o.getParValue();
		}
	}
	public String setValue(Session session,String com_id,String par_code,String par_value){
		com.cqqyd2014.hibernate.entities.ComParCode o=getParCodeObject(session,com_id,par_code);
		if (o==null){
			return new String("False");
		}
		else{
			o.setParValue(par_value);
			session.saveOrUpdate(o);
			session.flush();
			return new String("True");
		}
	}
	
	public com.cqqyd2014.hibernate.entities.ComParCode getParCodeObject(Session session,String com_id,String par_code){
		String hql="from ComParCode where id.comId=:com_id and id.parCode=:par_code";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("par_code", par_code);

		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComParCode> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComParCode>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			System.out.println("不能得到公司："+com_id+",参数："+par_code+"的值");
			return null;
		}
	}

}
