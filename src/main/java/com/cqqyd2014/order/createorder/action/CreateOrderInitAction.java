package com.cqqyd2014.order.createorder.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.order.model.OrderFromUser;

@Scope("prototype") // 支持多例

@Namespace(value = "/order") // 表示当前Action所在命名空间
@ParentPackage("bfkjs-default")
public class CreateOrderInitAction extends UserLoginedAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	public String order_time;

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	String discount;
	String ship_fee;
	String card_pay;
	java.util.ArrayList<OrderFromUser> ofus;
	

	public java.util.ArrayList<OrderFromUser> getOfus() {
		return ofus;
	}

	public void setOfus(java.util.ArrayList<OrderFromUser> ofus) {
		this.ofus = ofus;
	}



	@Actions({

			@Action( // 表示请求的Action及处理方法
					value = "create_order_init", // 表示action的请求名称
					results = { // 表示结果跳转
							@Result(name = "success", location = "/WEB-INF/order/createOrderMain.jsp") }, interceptorRefs = {
									@InterceptorRef("authorityInterceptor") })

	})

	@Authority(module = "mainframe", privilege = "[00010001]", error_url = "authority_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		order_time = com.cqqyd2014.util.DateUtil.JDateToFullString(new java.util.Date());

		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis = (java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>) session_http
				.get("temp_order_detail");
		if (odis == null) {
			odis = new java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>();

		}
		odis.clear();
		session_http.put("temp_order_detail", odis);

		session = HibernateSessionFactory.getSession();
		
		try {
			// com.cqqyd2014.hibernate.dao.UserParDAO cpcdao=new
			// com.cqqyd2014.hibernate.dao.UserParDAO();

			

			discount = "0";
			ship_fee = "0";
			card_pay = "0";

			com.cqqyd2014.hibernate.dao.VOrderFromUserDAO vfdao = new com.cqqyd2014.hibernate.dao.VOrderFromUserDAO();

			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> vofus = vfdao
					.getArrayViewByUserID(session, com_id, user_id);
			ofus = com.cqqyd2014.order.logic.OrderFromUserLogic.getArrayModelFromArrayEntityView(vofus);
			com.cqqyd2014.hibernate.dao.VOrderFromUserLenDAO vfldao = new com.cqqyd2014.hibernate.dao.VOrderFromUserLenDAO();

			




			// com.cqqyd2014.hibernate.dao.SysCodeDAO scdao=new
			// com.cqqyd2014.hibernate.dao.SysCodeDAO();

		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {

			HibernateSessionFactory.closeSession();
		}
		return "success";
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getShip_fee() {
		return ship_fee;
	}

	public void setShip_fee(String ship_fee) {
		this.ship_fee = ship_fee;
	}

	public String getCard_pay() {
		return card_pay;
	}

	public void setCard_pay(String card_pay) {
		this.card_pay = card_pay;
	}



	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	String orderFrom;

}
