package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.*;
import com.cqqyd2014.util.exception.AjaxSuccessMessageException;

public class SysUserDAO {
	
	
	
	//在线用户与模块用户的交集
	private java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> getMenuOnlineUser(Session session,String com_id,java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> sus_online,String menu_m,String menu_d){
		
		String hql="from VUserMenuD where id.comId=:com_id and id.menuDId=:menu_d and id.menuId=:menu_m";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("menu_m", menu_m);
		q.setParameter("menu_d", menu_d);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> vumds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD>)q.list();
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> sus_pickup=new java.util.ArrayList<SysUser>();
		
		for (int i=0;i<vumds.size();i++){
			com.cqqyd2014.hibernate.entities.SysUser su=getUserFromUserid(session,vumds.get(i).getId().getUserId(),com_id);
			sus_pickup.add(su);
		}
		sus_online.retainAll(sus_pickup);
		
		return sus_online;
	}
	
	
	
	
	
	
	
	public void setTimeOutForceOffline(Session session,String com_id) throws Exception{
		
		String hql="from SysUser where effective=true and comId=:com_id and online=true";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.Date now=new java.util.Date();
		//得到参数，多少秒不更新在线时间，强制下线
		
		int offline_time=com.cqqyd2014.system.logic.SysParLogic.getOfflineTime(session).intValue();
		
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> sus2=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>)q.list();
		for (int i=0;i<sus2.size();i++){
			com.cqqyd2014.hibernate.entities.SysUser su=sus2.get(i);
			
			//相隔的秒数
			long user_last_on_line_distance=com.cqqyd2014.util.DateUtil.getDistanceSecends(now,su.getLastOnlineTime());
			//System.out.println("用户"+su.getName());
			//System.out.println("上次上线距离时间"+user_last_on_line_distance);
			//System.out.println("强制下线时间"+offline_time);
			if ((user_last_on_line_distance/60)>offline_time){
				//心跳超时，强制下线
				su.setOnline(false);
				session.saveOrUpdate(su);
				
			}
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	public void setOnline(Session session,String user_id,String com_id) throws AjaxSuccessMessageException{
		com.cqqyd2014.hibernate.entities.SysUser su=getUserFromUserid(session,user_id,com_id);
		if (su==null) {
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("用户不存在");
		}
		su.setOnline(true);
		su.setLastOnlineTime(new java.util.Date());
		session.saveOrUpdate(su);
		
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> getUsersByCom(Session session,String com_id){
		String hql="from SysUser where effective=true and comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>)q.list();
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> getUsersAllByCom(Session session,String com_id){
		String hql="from SysUser where comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>)q.list();
	}
	
	/*
	
	//得到com_id
	public String getComIdByUserId(Session session,String userid){
String hql="from SysUser where effective=true and id=:userid";
		

		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> su=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>)q.list();
		if (su.size()>0){
			return su.get(0).getComId();
		}
		else{
			System.out.println(userid+"不存在或停用");
			return null;
		}
	}
	
	*/
	
	
	public  java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> getAllUser(Session session,String com_id){
		String hql="from SysUser where effective=true and comId=:com_id";
		

		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>)q.list();
	}
	
	
	public  void updatePwd(Session session,String userid,String pwd,String com_id){
		com.cqqyd2014.hibernate.entities.SysUser b=getUserFromUserid(session,userid,com_id);
		b.setPwd(com.cqqyd2014.util.StringUtil.md5(pwd));
		session.saveOrUpdate(b);
	}
	

	
	
	

	
	public  com.cqqyd2014.hibernate.entities.SysUser getEntiyByLogin(Session session,String login,String com_id){
		com.cqqyd2014.hibernate.entities.SysUser b=null;
		String hql="from SysUser where comId=:com_id and userLogin=:login";
		Query q = session.createQuery(hql);
		q.setParameter("login", login);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>) q
				.list();
		if (rs.size()>0){
			b=rs.get(0);
		}
		return b;
	

	}
	
	public  com.cqqyd2014.hibernate.entities.SysUser getUserFromUsername(Session session,String username,String com_id){
		com.cqqyd2014.hibernate.entities.SysUser b=null;
		String hql="from SysUser where comId=:com_id and name=:username and effective=true";
		Query q = session.createQuery(hql);
		q.setParameter("id", username);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>) q
				.list();
		if (rs.size()>0){
			b=rs.get(0);
		}
		session.flush();
		return b;
	}
	
	
	public  com.cqqyd2014.hibernate.entities.SysUser getUserFromUserid(Session session,String userid,String com_id){
		com.cqqyd2014.hibernate.entities.SysUser b=null;
		String hql="from SysUser where id=:id and comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("id", userid);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser>) q
				.list();
		if (rs.size()>0){
			b=rs.get(0);
			
			
		}
		else{
			return null;
		}
		rs=null;
		q=null;
		return b;
	}
	

	
	
	
}