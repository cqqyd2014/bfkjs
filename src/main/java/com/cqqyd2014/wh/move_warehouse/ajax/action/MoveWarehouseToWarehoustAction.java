package com.cqqyd2014.wh.move_warehouse.ajax.action;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/wh")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class MoveWarehouseToWarehoustAction   extends ActionSupport {

	String fromWh;
	String toWh;
	String move_date;
	String memo;


	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "move_warehouse_to_warehouse", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String move_warehouse_to_warehouse() throws Exception {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {


			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
					.get("temp_move_goods");
			java.util.Date now=com.cqqyd2014.util.DateUtil.FullStringToJDate(move_date);
			com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseM mgwm=new com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseM();
			com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseMId mgwmid=new com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseMId();
			mgwmid.setComId(com_id);
			mgwmid.setMoveDate(now);
			mgwmid.setUserId(userid);
			mgwm.setId(mgwmid);
			mgwm.setMemo(memo);
			mgwm.setWhFrom(fromWh);
			mgwm.setWhTo(toWh);
			session.saveOrUpdate(mgwm);
			for (int i =0;i<odis.size();i++){
				com.cqqyd2014.wh.model.Goods gb=odis.get(i);
				com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseD md=new com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseD();
				com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseDId mdid=new com.cqqyd2014.hibernate.entities.MoveGoodsWarehouseDId();
				mdid.setComId(com_id);
				mdid.setGoodsBarcode(gb.getBarcode());
				mdid.setMoveDate(now);
				mdid.setUserId(userid);
				md.setId(mdid);
				session.saveOrUpdate(md);
				
				//跟新库存
				com.cqqyd2014.wh.logic.Storage.MoveGoods(session, gb.getGoods_id(), fromWh, toWh, new java.math.BigDecimal(1), com_id);
				//跟新商品状态，移库
				com.cqqyd2014.wh.logic.GoodsLogic.MoveGoods(session, gb.getBarcode(), toWh, fromWh, now, com_id,userid);
			}
			
			
			
			
			odis.clear();
			session_http.put("temp_move_goods", odis);
			
			sm.setSuccess(true);
		
		//sm.setBody("条码"+goods_barcode+"的商品已经录入");
		
		
		
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


}