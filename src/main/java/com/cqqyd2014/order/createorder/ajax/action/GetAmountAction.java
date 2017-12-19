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


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class GetAmountAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
  String logistics_com;
String vehicle;


public String getLogistics_com() {
	return logistics_com;
}

public void setLogistics_com(String logistics_com) {
	this.logistics_com = logistics_com;
}

public String getVehicle() {
	return vehicle;
}

public void setVehicle(String vehicle) {
	this.vehicle = vehicle;
}


	@Action(value = "get_amount", results = {
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
					
					@InterceptorRef("defaultStack"),
					@InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_amount", privilege = "[00010001]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);
		//java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice>();
		session = HibernateSessionFactory.getSession();
		//com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		try {
			//商品价值
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis = (java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>) session_http
					.get("temp_order_detail");
			if (odis.size()==0){
				sm.setSuccess(true);
				sm.setO(new java.math.BigDecimal(0));
				sm.setO2(new java.math.BigDecimal(0));
				sm.setO3(new java.math.BigDecimal(0));
			}
			else{
				java.math.BigDecimal amount=com.cqqyd2014.util.ArrayListTools.sumFields(odis, "getTotal1");
				
				java.math.BigDecimal num=com.cqqyd2014.util.ArrayListTools.sumFields(odis, "getNum");
				
				
				
				
				//计算运费
				java.math.BigDecimal fee=com.cqqyd2014.logistics.logic.LogisticsComLogic.getFeeByLogisticsVehicleNum(session, com_id, logistics_com, vehicle, num);
				
				sm.setSuccess(true);
				sm.setO(amount);
				sm.setO2(num);
				sm.setO3(fee);
			}
			
			
			
			
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
