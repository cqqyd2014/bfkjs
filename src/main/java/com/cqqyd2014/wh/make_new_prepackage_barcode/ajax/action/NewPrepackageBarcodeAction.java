package com.cqqyd2014.wh.make_new_prepackage_barcode.ajax.action;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/wh")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class NewPrepackageBarcodeAction  extends ActionSupport {
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


	@Action(value = "make_new_prepackage_barcode", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String make_new_prepackage_barcode() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			
			
			com.cqqyd2014.wh.logic.PrePackageMLogic.makeNewPrepackageBarcode(session, com_id, c_goods_count, num,userid);
			
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