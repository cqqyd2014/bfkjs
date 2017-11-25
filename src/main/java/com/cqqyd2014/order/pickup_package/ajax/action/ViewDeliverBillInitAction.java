package com.cqqyd2014.order.pickup_package.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class ViewDeliverBillInitAction extends ActionSupport {
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

	@Action(value = "view_deliver_bill_init", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String new_deliver_bill_init() throws Exception {

		Map session_http = ActionContext.getContext().getSession();

		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
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