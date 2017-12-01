package com.cqqyd2014.quota.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.quota.logic.QuotaTransLogic;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/system")
public class RedFlagQuotaAction     extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	String sys_user_id;
	public String getSys_user_id() {
		return sys_user_id;
	}

	public void setSys_user_id(String sys_user_id) {
		this.sys_user_id = sys_user_id;
	}

	String uuid;


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	@Action(value = "red_flag_quota", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "red_flag_quota", privilege = "[00050003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);

Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.QuotaTransDAO qtdao=new com.cqqyd2014.hibernate.dao.QuotaTransDAO();
			com.cqqyd2014.hibernate.entities.QuotaTrans qt=qtdao.getObjectByUuid(session, com_id, uuid, user_id);
			
			qt.setStatus("已经冲销");
			session.saveOrUpdate(qt);
			
			QuotaTransLogic.changeQuota(session, com_id, sys_user_id, user_id, "0007", qt.getTransAmount(), "", "", uuid);
			
				
				sm.setSuccess(true);
				
			
			
			
			
tx.commit();
		
		
	}

	catch (Exception e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		
		
	}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}