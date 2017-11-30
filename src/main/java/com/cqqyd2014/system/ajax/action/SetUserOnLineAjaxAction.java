package com.cqqyd2014.system.ajax.action;

import java.util.Map;


import org.apache.struts2.convention.annotation.Action;     


import org.apache.struts2.convention.annotation.InterceptorRef;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class SetUserOnLineAjaxAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "set_user_online", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "[00010001]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
			
			
			
			//String result = "";
			Session session = HibernateSessionFactory.getSession();
			//com.cqqyd2014.hibernate.entities.SysUser b = null;
			
			Transaction tx = session.beginTransaction();
			try {
				

				com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
				sudao.setOnline(session, user_id, com_id);
				sm.setSuccess(true);
				sm.setO(com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date()));

				tx.commit();

			}

			catch (HibernateException e) {

				if (null != tx) {
					tx.rollback();// 撤销事务

				}

				sm.setSuccess(false);
				sm.setBody(e.toString());

			} 
			
			catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e) {
				if (null != tx) {
					tx.rollback();// 撤销事务

				}
				sm.setSuccess(false);
				sm.setBody(e.getMessageString());
			}
			finally {
				HibernateSessionFactory.closeSession();
			}
		
		
		msg=sm.toMap();
		return ActionSupport.SUCCESS;
	}

}
