package com.cqqyd2014.order.pickup_package.ajax.action;


import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientOrderConfirm;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.dao.SfResponseOrderBackDAO;


import com.cqqyd2014.util.exception.AjaxSuccessMessageException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class CancelDeliverBillAction extends ActionSupport {
	String order_no;
	String seq;

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

	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "cancel_deliver_bill", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String cancel_deliver_bill() throws Exception {

		Map session_http = ActionContext.getContext().getSession();

		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			
			com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			com.cqqyd2014.hibernate.entities.VDeliverM vdm=vdmdao.getEntityViewByOrderNoSeq(session, order_no, seq, com_id);

			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(vdm);
			if (db.getDeliver_bill_status().equals("发货完毕")) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该运单已经发出，不能取消");
			}
			db.setDeliver_bill_status("运单取消");
			
			
			db.setCancel_confirm_memo("拣货阶段手动取消");
			db.setCancel_confirm_userid(userid);
			java.util.Date now=new java.util.Date();
			db.setCancel_confrim_dat(now);
			db.setCancel_request_dat(now);
			db.setCancel_request_memo("拣货阶段手动取消");
			db.setCancel_request_userid(userid);
			db.setCancel_status("取消成功");
			com.cqqyd2014.order.logic.DeliverMLogic.save(session, db);
			//如果快递是顺丰，查看是否电子面单，如果电子面单，做取消处理
			if (db.getExpress_com().equals("500528000A")) {
				SfResponseOrderBackDAO sfdao=new SfResponseOrderBackDAO();
				com.cqqyd2014.hibernate.entities.SfResponseOrderBack back=sfdao.getEntityByOrderSeq(session, order_no, seq);
				if (back!=null) {
					//电子面单，进行反确认处理
					
					BspHttpClientOrderConfirm bhco=new BspHttpClientOrderConfirm(session, db);
					
					bhco.setDealtype("2");
					bhco.initBill();
					bhco.post();
					 session.flush();
				}
			}
			//把锁定的商品解锁，库位恢复，对发货单明细做处理
			com.cqqyd2014.hibernate.dao.VDeliverDDAO vdddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=vdddao.getArrayListViewByOrderNoSeq(session, com_id, order_no, seq);
			
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(vdds);
			
			for (int i=0;i<dbds.size();i++) {
				com.cqqyd2014.order.model.DeliverBillDetail dbd=dbds.get(i);
				com.cqqyd2014.wh.logic.GoodsLogic.ReturnLockItemToDefault(session, dbd.getGoods_barcode(), com_id,userid);
				com.cqqyd2014.wh.logic.Storage.ReturnLockItemToDefault(session,dbd.getGoods_barcode(), db.getWh_id(), new java.math.BigDecimal(1), com_id);
				//订单打包，减少"仓库锁定"的数量
				//com.cqqyd2014.wh.logic.WareHouse.orderUnLock(session, g.getGoods_id(), new java.math.BigDecimal(1), com_id);
				com.cqqyd2014.wh.logic.Storage.addStorage(session, dbd.getGoods_barcode(), "ORDER_", "DEFAUL", new java.math.BigDecimal("1"), com_id);
			}
			
			
			
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
		catch (AjaxSuccessMessageException e) {
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
		}
		finally {
			HibernateSessionFactory.closeSession();
		}



		msg = sm.toMap();
		return "success";
	}

}