package com.cqqyd2014.wh.move_warehouse.ajax.action;

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
@Namespace("/wh")
public class GetGoodsSumAction   extends UserLoginedAction {



	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_goods_sum", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_unprinted_prepackage_barcode", privilege = "[00020008]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {

			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
					.get("temp_move_goods");
			
			java.util.ArrayList<com.cqqyd2014.util.hashmap.ListItemStringN>  list=com.cqqyd2014.util.HashMapTools.conertToArrayListStringN( com.cqqyd2014.util.HashMapTools.convertArrayListToHashMapCount(odis, "getGoods_id"));
			
			
			
			sm.setO(list);
			
			
			sm.setSuccess(true);
			
			
			

		
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}


		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return SUCCESS;
	}


}
