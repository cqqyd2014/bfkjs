package com.cqqyd2014.order.createorder.ajax.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.util.message.IfMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class ProvinceAndCityAjaxAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private String id;
	@Action(value = "province_and_city", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String province_and_city() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.RegionDAO rdao=new com.cqqyd2014.hibernate.dao.RegionDAO();
			 java.util.ArrayList<com.cqqyd2014.hibernate.entities.Region> rs=rdao.getSubCity(session, id);
			
			sm.setSuccess(true);
			sm.setO(rs);
			
			
			tx.commit();
			
		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);

		}
		
		
		finally {
			HibernateSessionFactory.closeSession();
		}

		msg=sm.toMap();
		return SUCCESS;
	}
	
}
