package com.cqqyd2014.express.sf.bsp.manual.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientOrderSearch;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/express/sf")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class ManualOrderSearchAction   extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String com_id;
	String order_no;
	String seq;


	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Action(value = "manual_ordersearch", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String manual_ordersearch() throws Exception {
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {

com.cqqyd2014.order.model.DeliverBill db=new com.cqqyd2014.order.model.DeliverBill();
			db.setSeq(seq);
			
BspHttpClientOrderSearch bhco1=new BspHttpClientOrderSearch(session,db);
			
			
			sm.setSuccess(true);
			sm.setBody(bhco1.initBill());
			tx.commit();
			// session.close();
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
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}

		

}