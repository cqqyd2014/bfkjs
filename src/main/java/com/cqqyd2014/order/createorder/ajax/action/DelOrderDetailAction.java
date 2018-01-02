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
public class DelOrderDetailAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	String detail_uuid;

	@Action(value = "del_order_detail", results = {
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {

					@InterceptorRef("defaultStack"), @InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_goods_info", privilege = "[00010001]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);

		session = HibernateSessionFactory.getSession();
		tx = session.beginTransaction();
		try {
			// com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new
			// com.cqqyd2014.hibernate.dao.OrderMainDAO();

			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis = (java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>) session_http
					.get("temp_order_detail");
			if (odis == null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("不能获取用户Session中的订单明细对象");

			}

			for (int i = 0; i < odis.size(); i++) {
				com.cqqyd2014.order.model.OrderDetail odi = odis.get(i);
				if (odi.getDetail_id().equals(detail_uuid)) {
					odis.remove(i);
				}

			}

			session_http.put("temp_order_detail", odis);
			sm.setSuccess(true);

			tx.commit();

		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);

		} catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e) {
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
		}

		finally {
			HibernateSessionFactory.closeSession();
		}

		msg = sm.toMap();
		return SUCCESS;
	}

	public String getDetail_uuid() {
		return detail_uuid;
	}

	public void setDetail_uuid(String detail_uuid) {
		this.detail_uuid = detail_uuid;
	}

}
