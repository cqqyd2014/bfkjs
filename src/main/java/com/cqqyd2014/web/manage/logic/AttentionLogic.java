package com.cqqyd2014.web.manage.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.Session;

public class AttentionLogic {
	
	
	
	
	public static java.util.ArrayList<com.cqqyd2014.web.manage.model.Attention> getArrayListModelFromArrayListView(Session session,java.util.ArrayList<com.cqqyd2014.system.model.MenuD> mds,String user_id,String com_id){
		java.util.ArrayList<com.cqqyd2014.web.manage.model.Attention> atts=new java.util.ArrayList<>();
		for (int i=0;i<mds.size();i++) {
			com.cqqyd2014.web.manage.model.Attention att=getModelFromView(session,mds.get(i),user_id,com_id);
			atts.add(att);
		}
		return atts;
	}
	
	public static com.cqqyd2014.web.manage.model.Attention getModelFromView(Session session,com.cqqyd2014.system.model.MenuD md,String user_id,String com_id){
	
		com.cqqyd2014.web.manage.model.Attention att=new com.cqqyd2014.web.manage.model.Attention();
		att.setUser_id(user_id);
		att.setM_id(md.getM_id()+md.getM_d_id());
		try {
			Class<?> clazz = Class.forName(md.getGet_num_class());
			Method method = clazz.getDeclaredMethod(md.getGet_num_method(), Session.class,String.class); 
			java.math.BigDecimal num=(java.math.BigDecimal)method.invoke(null, session,com_id);
			att.setCount(num);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return att;
		/*
		//不同的统计数据来自是否有访问m_d_id的权限
		com.cqqyd2014.hibernate.dao.MenuDDAO mddao=new com.cqqyd2014.hibernate.dao.MenuDDAO();
		com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
		com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
		//0001/0006待付款订单
		if (mddao.checkIfHaveMenuD(session, userid, "0001", "0006", com_id)) {
			
			att.setUn_paid_count(vomdao.getUnPaidCount(session, com_id));
		}
		if (mddao.checkIfHaveMenuD(session, userid, "0001", "0007", com_id)) {
			att.setWait_assign_package(vomdao.getWaitAssginPackageCount(session, com_id));
		}
		if (mddao.checkIfHaveMenuD(session, userid, "0001", "0003", com_id)) {
			att.setWait_package(vomdao.getWaitPackageCount(session, com_id,userid));
		}
		if (mddao.checkIfHaveMenuD(session, userid, "0001", "0004", com_id)) {
			att.setWait_deliver(vdmdao.getWaitDeliverCount(session, com_id));
		}
		*/
		
	}
	
}
