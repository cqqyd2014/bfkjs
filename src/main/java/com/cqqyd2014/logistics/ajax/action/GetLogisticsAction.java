package com.cqqyd2014.logistics.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;


import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;


@ParentPackage("bfkjs-json-default")
@Namespace("/logistics")
@SuppressWarnings("serial")
public class GetLogisticsAction extends UserLoginedAction{
	private Map<String, Object> msg;
public Map<String, Object> getMsg() {
		return msg;
	}
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
@Action(value = "get_logistics", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_logistics", privilege = "*", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		session = HibernateSessionFactory.getSession();
		
		try {
			
			
			
			java.util.ArrayList<com.cqqyd2014.logistics.model.LogisticsCom> vs=com.cqqyd2014.logistics.logic.LogisticsComLogic.getArrayListModelFromArrayListEntities(
					com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO.getArrayListEntities(session));
			sm.setO(vs);
			sm.setSuccess(true);
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		
		finally {
			HibernateSessionFactory.closeSession();
		}



		msg = sm.toMap();
		return "success";
	}

}