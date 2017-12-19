package com.cqqyd2014.order.common.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class ViewDeliverBillInitAction extends UserLoginedAction {
	String order_no;

	public String getOrder_no() {
		return order_no;
	}
	String seq;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	@Action(value = "view_deliver_bill_init", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "view_deliver_bill_init", privilege = "*", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			
			
			
			com.cqqyd2014.hibernate.dao.VDeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			com.cqqyd2014.hibernate.entities.VDeliverM dm=dmdao.getEntityViewByOrderNoSeq(session, order_no, seq, com_id);
			
			com.cqqyd2014.hibernate.dao.VDeliverSeqDAO vdsdao=new com.cqqyd2014.hibernate.dao.VDeliverSeqDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverSeq> vdss=vdsdao.getDeliverListSeq(session, order_no, seq);
			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV2(dm,vdss);
			
			sm.setO(db);
			
			com.cqqyd2014.hibernate.dao.VDeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> dds=dddao.getArrayListViewByOrderNoSeq(session, com_id, order_no, seq);
			
			//com.cqqyd2014.hibernate.dao.VDeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> gbs=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(dds);
			sm.setO2(gbs);
			
			
			
			
			sm.setSuccess(true);
			
			
		}

		catch (Exception e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			sm.setSuccess(false);
			
		}
		finally {
			HibernateSessionFactory.closeSession();
		}

		msg = sm.toMap();
		return "success";
	}

}