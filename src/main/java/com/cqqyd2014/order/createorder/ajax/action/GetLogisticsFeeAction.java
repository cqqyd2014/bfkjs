package com.cqqyd2014.order.createorder.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.order.logic.LogisticsLogic;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")

public class GetLogisticsFeeAction extends UserLoginedAction {
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

	@Action(value = "get_logistics_fee", results = {
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
					
					@InterceptorRef("defaultStack"),
					@InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_goods_info", privilege = "[00010001]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);
		//java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice>();
		Session session = HibernateSessionFactory.getSession();
		//com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Transaction tx = session.beginTransaction();
		try {
			
			java.math.BigDecimal fee=LogisticsLogic.getFeeByLogisticsVehicleNum(session, com_id, logistics, vehicle, num);
			
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
