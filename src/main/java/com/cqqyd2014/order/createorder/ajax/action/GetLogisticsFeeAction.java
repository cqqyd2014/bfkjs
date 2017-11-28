package com.cqqyd2014.order.createorder.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.order.logic.LogisticsLogic;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetLogisticsFeeAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
  String logistics;
String vehicle;
java.math.BigDecimal num;

	public String getLogistics() {
	return logistics;
}

public void setLogistics(String logistics) {
	this.logistics = logistics;
}

public String getVehicle() {
	return vehicle;
}

public void setVehicle(String vehicle) {
	this.vehicle = vehicle;
}

public java.math.BigDecimal getNum() {
	return num;
}

public void setNum(java.math.BigDecimal num) {
	this.num = num;
}

	@Action(value = "get_logistics_fee", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_logistics_fee() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");


		//java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice>();
		Session session = HibernateSessionFactory.getSession();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Transaction tx = session.beginTransaction();
		try {
			LogisticsLogic ll=new LogisticsLogic();
			java.math.BigDecimal fee=ll.getFeeByLogisticsVehicleNum(session, com_id, logistics, vehicle, num);
			
			sm.setSuccess(true);
			sm.setO(fee);
			
			tx.commit();
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		sm.setSuccess(false);
		sm.setBody(e.toString());
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
		return SUCCESS;
	}
}
