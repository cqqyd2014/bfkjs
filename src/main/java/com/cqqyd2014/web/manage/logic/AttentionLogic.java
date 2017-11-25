package com.cqqyd2014.web.manage.logic;

import org.hibernate.Session;

public class AttentionLogic {
	
	public static com.cqqyd2014.web.manage.model.Attention getModelByUserId(Session session,String userid,String com_id){
	
		com.cqqyd2014.web.manage.model.Attention att=new com.cqqyd2014.web.manage.model.Attention();
		att.setUserid(userid);
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
		return att;
	}
	
}
