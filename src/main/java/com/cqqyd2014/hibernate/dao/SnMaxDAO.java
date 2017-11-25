package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;



public class SnMaxDAO {
	public static com.cqqyd2014.hibernate.entities.SnMax  getMax(Session session,String sn_type,String com_id,java.util.Date dat){
	String hql="from SnMax where id.comId=:com_id and id.snType=:sn_type and id.dat=:dat";
	Query q = session.createQuery(hql);
	
		q.setDate("dat", dat);
	
	
	q.setString("com_id", com_id);
	q.setString("sn_type", sn_type);
	
	
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.SnMax> sms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SnMax>)(q.list());
	if (sms.size()==0){
		return null;
	}
	else{
		return sms.get(0);
	}
}
	
	
	
	public static long  getNew(Session session,long i,String sn_type,String com_id,java.util.Date dat){
		synchronized(SnMaxDAO.class){
		
		com.cqqyd2014.hibernate.entities.SnMax max=getMax(session,sn_type,com_id,dat);
		long newid=0;
		if (max==null){
			//没有这个，新建
			com.cqqyd2014.hibernate.entities.SnMax sn_max=new com.cqqyd2014.hibernate.entities.SnMax();
			com.cqqyd2014.hibernate.entities.SnMaxId sn_max_id=new com.cqqyd2014.hibernate.entities.SnMaxId();
			sn_max_id.setComId(com_id);
			sn_max_id.setSnType(sn_type);
			
				sn_max_id.setDat(dat);
			
			sn_max.setId(sn_max_id);
			sn_max.setMaxid(i);
			session.save(sn_max);
			newid=i;
		}
		else{
			newid=max.getMaxid()+i;
max.setMaxid(newid);
			session.saveOrUpdate(max);
			
		}
		
		
		
		
		
		return newid;}
	}


}
