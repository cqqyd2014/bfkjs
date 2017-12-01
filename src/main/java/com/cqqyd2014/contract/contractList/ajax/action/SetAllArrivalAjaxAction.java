package com.cqqyd2014.contract.contractList.ajax.action;
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
@Namespace("/contract")
public class SetAllArrivalAjaxAction    extends UserLoginedAction {
	private Map<String, Object> msg;
	String contract_id;

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "set_all_arrival", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "set_all_arrival", privilege = "[00050003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.ContractMDAO cmdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
			com.cqqyd2014.hibernate.entities.ContractM cm=cmdao.getObjectByContractId(session, com_id, contract_id);
			if (cm.getArrival()){
				cm.setArrival(false);
			}
			else{
				cm.setArrival(true);
			}
			sm.setSuccess(true);			
				
			
			
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

}

