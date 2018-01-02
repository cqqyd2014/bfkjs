package com.cqqyd2014.wh.send_deliverbill.ajax.action;


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

import com.cqqyd2014.wh.logic.IntoWh;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class SendPackageAction   extends UserLoginedAction {
	String deliver_no;
	String wh_id;
	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}

	java.math.BigDecimal weight;

	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "send_package", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_wait_send_deliver_list", privilege = "[00010004]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
	// TODO Auto-generated method stub
	super.execute();
	sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.DeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.DeliverMDAO();
			com.cqqyd2014.hibernate.entities.DeliverM dm=dmdao.getDeliverM(session, deliver_no, com_id);

			if (dm==null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("找不到这个发货单号："+deliver_no);
			}
			String order_no=dm.getId().getOrderNo();
			String seq=dm.getId().getSeq();
			dm.setSendDat(new java.util.Date());
			dm.setSended(true);
			dm.setActualWeight(weight);
			dm.setDeliverBillStatus("发货完毕");
			session.saveOrUpdate(dm);
			session.flush();
			//发货单中的商品更新状态
			com.cqqyd2014.hibernate.dao.DeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.DeliverDDAO();
			com.cqqyd2014.hibernate.dao.VGoodsDAO vgdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> dds=dddao.getEntityByOrderNoSeq(session, order_no, seq, com_id);
			for (int i=0;i<dds.size();i++) {
				com.cqqyd2014.wh.logic.GoodsLogic.LockItemToSaleItem(session,dds.get(i).getGoodsBarcode(), com_id,user_id);
				com.cqqyd2014.wh.logic.Storage.LockItemToSaleItem(session,dds.get(i).getGoodsId(), wh_id, new java.math.BigDecimal(1), com_id);
				com.cqqyd2014.hibernate.entities.VGoods vg=vgdao.getEntityViewByBarcode(session, com_id, dds.get(i).getGoodsBarcode());
				IntoWh.numChange(session,com_id,vg.getId().getIntoWhUuid(),vg.getId().getGoodsId(),new java.math.BigDecimal(-1),"0002",user_id);
			}
			
			
			com.cqqyd2014.order.logic.OrderLogic.afterSendedPackage(session, order_no, com_id,user_id);
			
			
			
			sm.setSuccess(true);
			sm.setBody("发货成功");
			
			

		tx.commit();
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSound("error");
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return SUCCESS;
	}

	public String getDeliver_no() {
		return deliver_no;
	}

	public void setDeliver_no(String deliver_no) {
		this.deliver_no = deliver_no;
	}

	public java.math.BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(java.math.BigDecimal weight) {
		this.weight = weight;
	}
}
