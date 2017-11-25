package com.cqqyd2014.system.ajax.action;

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
@Namespace(value="/system") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class SetUserDefaultAction {
	private Map<String, Object> msg;
	String par_code;
	String par_value;
	public String getPar_code() {
		return par_code;
	}

	public void setPar_code(String par_code) {
		this.par_code = par_code;
	}

	public String getPar_value() {
		return par_value;
	}

	public void setPar_value(String par_value) {
		this.par_value = par_value;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "set_user_default", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String set_user_default() {
		Map session_http = ActionContext.getContext().getSession();
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		
		
			String user = (String) session_http.get("USER");
			String user_name = (String) session_http.get("USER_NAME");
			String user_id = (String) session_http.get("USER_ID");
			String com_id = (String) session_http.get("com_code");
			
			
			
			
			String result = "";
			Session session = HibernateSessionFactory.getSession();
			com.cqqyd2014.hibernate.entities.SysUser b = null;
			
			Transaction tx = session.beginTransaction();
			try {
				//System.out.println(par_code);
				
				
				
				
				com.cqqyd2014.hibernate.dao.UserParDAO cpcdao=new com.cqqyd2014.hibernate.dao.UserParDAO();
				cpcdao.setValue(session,user_id, com_id, par_code, par_value);

				sm.setSuccess(true);
				tx.commit();

			}

			catch (HibernateException e) {

				if (null != tx) {
					tx.rollback();// 撤销事务

				}

				sm.setSuccess(false);
				sm.setBody(e.toString());

			} 
			

			finally {
				HibernateSessionFactory.closeSession();
			}
		
		
		msg=sm.toMap();
		return ActionSupport.SUCCESS;
	}

}