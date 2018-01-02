package com.cqqyd2014.wh.move_warehouse.ajax.action;

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
@Namespace("/wh")
public class MoveWarehouseToWarehoustAction   extends UserLoginedAction {

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
	@Action(value = "move_warehouse_to_warehouse", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_unprinted_prepackage_barcode", privilege = "[00020008]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
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
			mgwmid.setUserId(user_id);
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
				mdid.setUserId(user_id);
				md.setId(mdid);
				session.saveOrUpdate(md);
				
				//跟新库存
				com.cqqyd2014.wh.logic.Storage.MoveGoods(session, gb.getGoods_id(), fromWh, toWh, new java.math.BigDecimal(1), com_id);
				//跟新商品状态，移库
				com.cqqyd2014.wh.logic.GoodsLogic.MoveGoods(session, gb.getBarcode(), toWh, fromWh, now, com_id,user_id);
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


}