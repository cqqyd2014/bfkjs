package com.cqqyd2014.system.ajax.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;     

import org.apache.struts2.convention.annotation.Actions;  
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/system") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class SetUserOnLineAjaxAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "set_user_online", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String set_user_online() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		
		if (session_http.get("USER")==null) {
			sm.setSuccess(false);
			sm.setBody("未登陆系统");
		}
		else {
			String user = (String) session_http.get("USER");
			String user_name = (String) session_http.get("USER_NAME");
			String user_id = (String) session_http.get("USER_ID");
			String com_id = (String) session_http.get("com_code");
			
			
			
			
			String result = "";
			Session session = HibernateSessionFactory.getSession();
			com.cqqyd2014.hibernate.entities.SysUser b = null;
			
			Transaction tx = session.beginTransaction();
			try {
				if (user==null||user_name==null||user_id==null||com_id==null) {
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("用户未登录");
				}

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
		}
		
		msg=sm.toMap();
		return ActionSupport.SUCCESS;
	}

}
