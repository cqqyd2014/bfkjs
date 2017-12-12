package com.cqqyd2014.order.all_orders.ajax.action;


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
public class ChangeExpressAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}



	String logistics;
	String order_no;
	String seq;
String express_no;



	public String getExpress_no() {
	return express_no;
}

public void setExpress_no(String express_no) {
	this.express_no = express_no;
}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	@Action(value = "change_express", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "set_all_arrival", privilege = "[00010002]", error_url = "authority_ajax_error")
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
			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(dm);
			db.setExpress_com(logistics);
			db.setExpress_no(express_no);
			com.cqqyd2014.order.logic.DeliverMLogic.save(session, db);
			sm.setSuccess(true);
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

		finally {
			HibernateSessionFactory.closeSession();
		}
		
		msg=sm.toMap();
		return SUCCESS;
	}
	

}