package com.cqqyd2014.wh.make_new_prepackage_barcode.ajax.action;

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
public class NewPrepackageBarcodeAction  extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	java.math.BigDecimal num;
	public java.math.BigDecimal getC_goods_count() {
		return c_goods_count;
	}

	public void setC_goods_count(java.math.BigDecimal c_goods_count) {
		this.c_goods_count = c_goods_count;
	}

	public void setNum(java.math.BigDecimal num) {
		this.num = num;
	}
	java.math.BigDecimal c_goods_count;


	@Action(value = "make_new_prepackage_barcode", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_unprinted_prepackage_barcode", privilege = "[00020004]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			
			
			com.cqqyd2014.wh.logic.PrePackageMLogic.makeNewPrepackageBarcode(session, com_id, c_goods_count, num,user_id);
			
			sm.setSuccess(true);
			
			

		tx.commit();
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		sm.setSuccess(false);
		sm.setBody(e.toString());
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
		finally {
			HibernateSessionFactory.closeSession();
		}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}