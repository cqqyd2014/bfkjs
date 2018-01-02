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
public class ReturnGoodsInitAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String goods_barcode;
	String order_no;
	String seq;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getGoods_barcode() {
		return goods_barcode;
	}

	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	@Action(value = "return_goods_init", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
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
			com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			com.cqqyd2014.hibernate.entities.VDeliverM vdm=vdmdao.getEntityViewByOrderNoSeq(session, order_no, seq, com_id);
			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(vdm);
			if (db.equals("发货完毕")) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("发货完毕的订单才能退货");
			}
			com.cqqyd2014.hibernate.dao.VDeliverDDAO vomdao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			com.cqqyd2014.hibernate.entities.VDeliverD vom=vomdao.getViewByOrderNoSeqGoodsBarcode(session, order_no,seq, goods_barcode,com_id);
			com.cqqyd2014.order.model.DeliverBillDetail dbd=com.cqqyd2014.order.logic.DeliverDLogic.getModelFromEntity(vom);
			if (dbd.isReturned()) {
				//已经退货
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该商品已经退货");
			}
			sm.setSuccess(true);
			sm.setO(dbd);
			
		
			
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
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		
		msg=sm.toMap();
		return SUCCESS;
	}

}