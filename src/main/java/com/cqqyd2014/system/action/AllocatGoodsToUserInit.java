package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class AllocatGoodsToUserInit extends ActionSupport implements SessionAware {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Integer I_type = ((Integer) this.session.get("TYPE"));
		int type = I_type.intValue();
		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
		com.cqqyd2014.hibernate.dao.VComGoodsDAO hbddao=new com.cqqyd2014.hibernate.dao.VComGoodsDAO();
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VComGoods> hbds=hbddao.getComGoods(session, com_id) ;
		
		for(int i=0;i<hbds.size();i++){
			goodsList.put(hbds.get(i).getId().getCId(), hbds.get(i).getId().getCName());
		}
		
		
		com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> sus=sudao.getUsersByCom(session, com_id);
		for(int i=0;i<sus.size();i++){
			userList.put(sus.get(i).getId(), sus.get(i).getName());
		}
		tx.commit();
		}
		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		
		
		return super.execute();
	}
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	java.util.HashMap goodsList = new java.util.LinkedHashMap();  
	java.util.HashMap userList = new java.util.LinkedHashMap();
	public java.util.HashMap getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(java.util.HashMap goodsList) {
		this.goodsList = goodsList;
	}
	public java.util.HashMap getUserList() {
		return userList;
	}
	public void setUserList(java.util.HashMap userList) {
		this.userList = userList;
	}  
}
