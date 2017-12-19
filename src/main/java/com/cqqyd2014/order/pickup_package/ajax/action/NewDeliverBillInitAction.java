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
public class NewDeliverBillInitAction extends UserLoginedAction {
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

	@Action(value = "new_deliver_bill_init", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
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
			com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.OrderMainDAO();
			com.cqqyd2014.hibernate.entities.COrderMain om=omdao.getOrder(session, order_no, com_id);
			com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vomdao.getVOrderMain(session, com_id, order_no);
			
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getModelFromView(vom);
			//如果是已经申请取消的，确认取消
			if (com.cqqyd2014.order.logic.OrderLogic.check_if_cancel(session, order, user_id, "初始化发货单")){
				sm.setSuccess(false);
				sm.setBody("订单已经被客户申请取消");
				msg = sm.toMap();
				return "success";
				
			}
			if (om.getGtStatus().equals("等待拣货")){
				om.setGtStatus("拣货处理");
				session.saveOrUpdate(om);
			}
			
			tx.commit();
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}

		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis = new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();

		session_http.put("temp_deliver_picked_sn", odis);

		//com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		sm.setSuccess(true);
		msg = sm.toMap();
		return "success";
	}

}