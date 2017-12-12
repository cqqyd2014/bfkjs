package com.cqqyd2014.system.run.night;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.*;

public class SystemClean {

	public static void main(String[] args) {
		try{
			System.out.println("开始清理");
		SystemClean sc = new SystemClean();
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}

	}

	public SystemClean() {

		Session session = HibernateSessionFactory.getSession();
		
		Transaction tx = session.beginTransaction();
		try {

			System.out.println("共删除过期的微信申报记录" + delWeixinIdCard(session));

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
		// TODO Auto-generated constructor stub
	}

	

	
	//清理微信过期的申报
	private int delWeixinIdCard(Session session) {

		String hql = "from COrderMain where CStatus='微信申报' and CEffective='有效' and weixinStatus='微信申报' and CTime<:ctime";
		Query q = session.createQuery(hql);
		// 清除3天以前微信申报
		com.cqqyd2014.hibernate.dao.SysParDAO spdao=new com.cqqyd2014.hibernate.dao.SysParDAO();
		
		int day = spdao.getWxPubDays(session);
		java.util.Date ctime = com.cqqyd2014.util.DateUtil.getNearDays(
				new java.util.Date(), day*-1);
		q.setParameter("ctime", ctime);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q
				.list();
		int i = 0;
		for (i = 0; i < rs.size(); i++) {
			com.cqqyd2014.hibernate.entities.COrderMain com = rs.get(i);
			com.setEffective(false);
			session.saveOrUpdate(com);
		}
		return i;

	}

}