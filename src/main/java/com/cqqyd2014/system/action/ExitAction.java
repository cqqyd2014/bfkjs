package com.cqqyd2014.system.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.*;

@SuppressWarnings("serial")
@Scope("prototype") // 支持多例
@ParentPackage("struts-default") // 表示继承的父包
@Namespace(value = "/system") // 表示当前Action所在命名空间
public class ExitAction extends UserLoginedAction {

	@Actions({

			@Action( // 表示请求的Action及处理方法
					value = "exit", // 表示action的请求名称
					results = { // 表示结果跳转

							@Result(name = "success", params = { "actionName", "login_init", "namespace", "/login",
									"method", "login_init" }, type = "chain")

					})

	})
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		if (user_id==null||user_id.equals("")) {
			return "success";
		}

		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao = new com.cqqyd2014.hibernate.dao.SysUserDAO();
			com.cqqyd2014.hibernate.entities.SysUser su = sudao.getUserFromUserid(session, user_id, com_id);
			su.setLastOnlineTime(new java.util.Date());
			su.setOnline(false);
			session.saveOrUpdate(su);

			tx.commit();

		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}

		// com.cqqyd2014.util.AjaxSuccessMessage sm = new
		// com.cqqyd2014.util.AjaxSuccessMessage();
		sm.setSuccess(true);

		session_http.put("user_id", "");
		session_http.put("user_name", "");

		return "success";
	}

}
