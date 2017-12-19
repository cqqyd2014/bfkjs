package com.cqqyd2014.express.sf.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientRoute;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")

@Namespace(value = "/express/sf") // 表示当前Action所在命名空间

public class GetRouteAction extends UserLoginedAction {
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

	String order_no;
	String seq;

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

	@Action(value = "get_route", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {

			@InterceptorRef("defaultStack"), @InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_route", privilege = "*", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);

		session = HibernateSessionFactory.getSession();
		tx = session.beginTransaction();

		try {

			// String order_no="QYDTB201610240001391";

			com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao = new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			com.cqqyd2014.hibernate.entities.VDeliverM vdM = vdmdao.getEntityViewByOrderNoSeq(session, order_no, seq,
					com_id);
			com.cqqyd2014.order.model.DeliverBill db = com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(vdM);
			if (com.cqqyd2014.order.logic.DeliverMLogic.ifSfElecBill(session, db)) {
				java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs = com.cqqyd2014.order.logic.DeliverMLogic
						.transOneToArray(db);

				// 生成顺丰订单报文对象

				BspHttpClientRoute bhco1 = new BspHttpClientRoute(session, dbs);

				bhco1.initBill();
				@SuppressWarnings("unchecked")
				java.util.ArrayList<com.cqqyd2014.order.model.Route> rs = (java.util.ArrayList<com.cqqyd2014.order.model.Route>) bhco1
						.post();
				session.flush();

				sm.setSuccess(true);
				sm.setO(rs);
			} else {
				sm.setSuccess(false);
				sm.setBody("不是顺丰电子运单，不能查询路由");
			}

			tx.commit();

		}

		catch (Exception e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());

		} finally {
			HibernateSessionFactory.closeSession();
		}
		msg = sm.toMap();
		return "success";
	}
}