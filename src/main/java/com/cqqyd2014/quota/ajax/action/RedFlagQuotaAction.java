package com.cqqyd2014.quota.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.quota.logic.QuotaTransLogic;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/quota") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
public class RedFlagQuotaAction     extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String userid;
	
	String uuid;


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	@Action(value = "red_flag_quota", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String red_flag_quota() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		String op_user_id=(String)session_http.get("USER_ID");

Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			com.cqqyd2014.hibernate.dao.QuotaTransDAO qtdao=new com.cqqyd2014.hibernate.dao.QuotaTransDAO();
			com.cqqyd2014.hibernate.entities.QuotaTrans qt=qtdao.getObjectByUuid(session, com_id, uuid, userid);
			
			qt.setStatus("已经冲销");
			session.saveOrUpdate(qt);
			
			QuotaTransLogic.changeQuota(session, com_id, userid, op_user_id, "0007", qt.getTransAmount(), "", "", uuid);
			
				
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