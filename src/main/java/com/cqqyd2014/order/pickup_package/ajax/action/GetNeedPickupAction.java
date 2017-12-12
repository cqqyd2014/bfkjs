package com.cqqyd2014.order.pickup_package.ajax.action;


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
public class GetNeedPickupAction extends UserLoginedAction {
	String order_no;


	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "get_need_pickup", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "[00010003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			
			com.cqqyd2014.hibernate.dao.VPickupYueDAO dydao=new com.cqqyd2014.hibernate.dao.VPickupYueDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue> vpys=dydao.getArrayListViewByOrderNo(session, order_no, com_id);
			
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbs=com.cqqyd2014.order.logic.DeliverDLogic.getArrayListModelFromArrayListView2(vpys);
			
			
			
			sm.setO(dbs);
			tx.commit();
			//com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
			sm.setSuccess(true);
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		msg = sm.toMap();
		return "success";
	}
}