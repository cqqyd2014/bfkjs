package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class ComInfoDAO {
	
	
	
	
	//得到单位列表
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComInfo> getList(Session session){
		String hql="from ComInfo where effective=true";
		Query query = session.createQuery(hql);
		
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComInfo> cis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComInfo>)query.list();
		return cis;
	}
	
	

	//得到warning mail addr
	public String getWarningMailAddr(Session session,String c_id){
		com.cqqyd2014.hibernate.entities.ComInfo ci=getComInfo(session,c_id);
		if (ci==null){
			
			System.out.println("得到warning mail addr出错，参数是："+c_id);
			return null;
		}
		return ci.getWaringMail();
	}
	
	
	//得到message_mail
	public String getMessageMailAddr(Session session,String c_id){
		com.cqqyd2014.hibernate.entities.ComInfo ci=getComInfo(session,c_id);
		if (ci==null){
			
			System.out.println("得到message_mail出错，参数是："+c_id);
			return null;
		}
		return ci.getMessageMail();
	}
	
	
	
	//得到service_tell
	public String getServiceTell(Session session,String c_id){
		com.cqqyd2014.hibernate.entities.ComInfo ci=getComInfo(session,c_id);
		if (ci==null){
			
			System.out.println("得到service_tell出错，参数是："+c_id);
			return null;
		}
		return ci.getServiceTell();
	}

	
	
	
	
	
	
	
	//系统订单号前三位
	public String getOrderHead(Session session,String c_id){
		com.cqqyd2014.hibernate.entities.ComInfo ci=getComInfo(session,c_id);
if (ci==null){
			
			System.out.println("系统订单号前三位出错，参数是："+c_id);
			return null;
		}
		return ci.getOrderHead();
	}
	
	

	
	
	
	
	//企业名称
	public String getName(Session session,String c_id){
		com.cqqyd2014.hibernate.entities.ComInfo ci=getComInfo(session,c_id);
		if (ci!=null){
			return ci.getCName();
		}
		else{
			System.out.println("得到企业名称出错，参数是："+c_id);
			return null;
		}
		
	}
	
	
	
	
	
	
	public  com.cqqyd2014.hibernate.entities.ComInfo getComInfo(Session session ,String com_id){
		String hql="from ComInfo where CId=:c_id and effective=true";
		Query query = session.createQuery(hql);
		query.setParameter("c_id", com_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComInfo> cis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ComInfo>)query.list();
		if (cis.size()>0){
			return cis.get(0);
		}
		else{
			System.out.println("找不到这个单位信息："+com_id);
			return null;
		}
		
	}

}