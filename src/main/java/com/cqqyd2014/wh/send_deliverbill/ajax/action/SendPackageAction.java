package com.cqqyd2014.wh.send_deliverbill.ajax.action;


import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.wh.logic.IntoWh;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/wh")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class SendPackageAction   extends ActionSupport {
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
	@Action(value = "send_package", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String send_package() throws Exception {
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
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
				com.cqqyd2014.wh.logic.GoodsLogic.LockItemToSaleItem(session,dds.get(i).getGoodsBarcode(), com_id,userid);
				com.cqqyd2014.wh.logic.Storage.LockItemToSaleItem(session,dds.get(i).getGoodsId(), wh_id, new java.math.BigDecimal(1), com_id);
				com.cqqyd2014.hibernate.entities.VGoods vg=vgdao.getEntityViewByBarcode(session, com_id, dds.get(i).getGoodsBarcode());
				IntoWh.numChange(session,com_id,vg.getId().getIntoWhUuid(),vg.getId().getGoodsId(),new java.math.BigDecimal(-1),"0002",userid);
			}
			
			
			com.cqqyd2014.order.logic.OrderLogic.afterSendedPackage(session, order_no, com_id,userid);
			
			
			
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
			sm.setBody(e.getMessageString());
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
