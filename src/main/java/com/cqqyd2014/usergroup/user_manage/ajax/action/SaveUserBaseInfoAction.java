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
public class SaveUserBaseInfoAction     extends ActionSupport {
	private Map<String, Object> msg;
	String user_id;
	String effective;
	String online;
	String tell;
	String pickup_weighting;
	String send_weighting;
	public String getPickup_weighting() {
		return pickup_weighting;
	}
	public void setPickup_weighting(String pickup_weighting) {
		this.pickup_weighting = pickup_weighting;
	}
	public String getSend_weighting() {
		return send_weighting;
	}
	public void setSend_weighting(String send_weighting) {
		this.send_weighting = send_weighting;
	}
	public String getEffective() {
		return effective;
	}
	public void setEffective(String effective) {
		this.effective = effective;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	String login_id;
	String login_name;
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public Map<String, Object> getMsg() {
		return msg;
	}
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "save_user_base_info", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String save_user_base_info() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		
Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			com.cqqyd2014.hibernate.dao.SysUserDAO cmdao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			com.cqqyd2014.hibernate.entities.SysUser cm=cmdao.getUserFromUserid(session, user_id, com_id);
			cm.setUserLogin(login_id);
			cm.setName(login_name);
			if (effective.equals("true")){
				cm.setEffective(true);
			}
			else{
				cm.setEffective(false);
			}
			if (online.equals("true")){
				cm.setOnline(true);
				cm.setLastOnlineTime(new java.util.Date());
			}
			else{
				cm.setOnline(false);
			}
			cm.setTel(tell);
			cm.setPickupWeighting(new java.math.BigDecimal(pickup_weighting));
			cm.setSendWeighting(new java.math.BigDecimal(send_weighting));
			
			session.saveOrUpdate(cm);
				
					
				 sm.setSuccess(true);
				
			
			
			
			
			
			
tx.commit();
		
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		
		sm.setSuccess(false);
		sm.setBody(e.toString());
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