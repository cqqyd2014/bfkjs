package com.cqqyd2014.web.manage.main.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("prototype") // 支持多例

@ParentPackage("bfkjs-default")
@Namespace(value = "/web/manage") // 表示当前Action所在命名空间
public class MainInitAction extends UserLoginedAction {
	java.util.ArrayList<com.cqqyd2014.system.model.MenuM> menu;

	public java.util.ArrayList<com.cqqyd2014.system.model.MenuM> getMenu() {
		return menu;
	}

	public void setMenu(java.util.ArrayList<com.cqqyd2014.system.model.MenuM> menu) {
		this.menu = menu;
	}

	java.util.ArrayList<com.cqqyd2014.system.model.MenuD> javascrpits_method;

	public java.util.ArrayList<com.cqqyd2014.system.model.MenuD> getJavascrpits_method() {
		return javascrpits_method;
	}

	public void setJavascrpits_method(java.util.ArrayList<com.cqqyd2014.system.model.MenuD> javascrpits_method) {
		this.javascrpits_method = javascrpits_method;
	}

	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Actions({

			@Action( // 表示请求的Action及处理方法
					value = "main_init", // 表示action的请求名称
					results = { // 表示结果跳转
							@Result(name = "success", location = "/WEB-INF/web/manage/main.jsp") }, interceptorRefs = {
									@InterceptorRef("authorityInterceptor") })

	})

	@Authority(module = "web_main_init", privilege = "*", error_url = "authority_web_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.MenuDDAO mddao = new com.cqqyd2014.hibernate.dao.MenuDDAO();
			javascrpits_method = mddao.getMenuDAll(session, com_id, user_id);
			com.cqqyd2014.hibernate.dao.MenuMDAO mmdao = new com.cqqyd2014.hibernate.dao.MenuMDAO();
			menu = mmdao.getMenu(session, com_id, user_id);

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

		return "success";
	}

}
