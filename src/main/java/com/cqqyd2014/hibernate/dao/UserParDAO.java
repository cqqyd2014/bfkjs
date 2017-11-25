package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserParDAO {

	public String getValue(Session session,String user_id,String com_id,String par_code){
		com.cqqyd2014.hibernate.entities.UserPar o=getParCodeObject(session,user_id,com_id,par_code);
		if (o==null){
			return "";
		}
		else{
			return o.getParValue();
		}
	}
	public void setValue(Session session,String user_id,String com_id,String par_code,String par_value){
		com.cqqyd2014.hibernate.entities.UserPar o=getParCodeObject(session,user_id,com_id,par_code);
		if (o==null){
			com.cqqyd2014.hibernate.entities.UserPar up=new com.cqqyd2014.hibernate.entities.UserPar();
			com.cqqyd2014.hibernate.entities.UserParId upid=new com.cqqyd2014.hibernate.entities.UserParId();
			upid.setComId(com_id);
			upid.setParCode(par_code);
			upid.setUserId(user_id);
			up.setId(upid);
			up.setParDesc("");
			up.setParValue(par_value);
			session.save(up);
			session.flush();
			
		}
		else{
			o.setParValue(par_value);
			session.saveOrUpdate(o);
			session.flush();
			
		}
	}
	
	public com.cqqyd2014.hibernate.entities.UserPar getParCodeObject(Session session,String user_id,String com_id,String par_code){
		String hql="from UserPar where id.userId=:user_id and id.comId=:com_id and id.parCode=:par_code";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("par_code", par_code);

		q.setParameter("user_id", user_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			System.out.println("不能得到用户："+",参数："+par_code+"的值");
			return null;
		}
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar> getArrayListEntityByUserId(Session session,String com_id,String userid){
		String hql="from UserPar where id.userId=:userid and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);


		q.setParameter("userid", userid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar>) q
				.list();
		return rs;
	}

}
