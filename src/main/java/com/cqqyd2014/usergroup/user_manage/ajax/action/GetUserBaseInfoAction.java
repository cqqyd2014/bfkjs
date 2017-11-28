package com.cqqyd2014.usergroup.user_manage.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/usergroup") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
public class GetUserBaseInfoAction    extends ActionSupport {
	private Map<String, Object> msg;
	private String user_id;
	public Map<String, Object> getMsg() {
		return msg;
	}
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "get_user_base_info", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String get_user_base_info() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		
Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			com.cqqyd2014.hibernate.dao.SysUserDAO cmdao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			com.cqqyd2014.hibernate.entities.SysUser cm=cmdao.getUserFromUserid(session, user_id, com_id);
			
				
					
				 sm.setSuccess(true);
				
			sm.setO(cm);
			
			
			
			
			
tx.commit();
		
		
	}

	catch (HibernateException e) {
		
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}