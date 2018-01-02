package com.cqqyd2014.wh.prepackage.ajax.action;


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
public class AddGoodsToTempAction   extends UserLoginedAction {
	String goods_barcode;
	String prepackage_barcode;
	public String getPrepackage_barcode() {
		return prepackage_barcode;
	}

	public void setPrepackage_barcode(String prepackage_barcode) {
		this.prepackage_barcode = prepackage_barcode;
	}

	public String getGoods_barcode() {
		return goods_barcode;
	}

	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "add_goods_to_temp", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_unprinted_prepackage_barcode", privilege = "[00020005]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			//测试商品sn是否在库
			//com.cqqyd2014.hibernate.dao.GoodsDAO swd=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
					.get("temp_add_prepackage_barcode");
			
			//本次打包不要重复录入
			for (int i=0;i<odis.size();i++){
				
				
				String old_sn=odis.get(i).getBarcode();
				if (old_sn.equals(goods_barcode)){
					
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("重复录入商品条码");
				}
			}

			com.cqqyd2014.hibernate.dao.VGoodsDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			
			
			com.cqqyd2014.hibernate.entities.VGoods g_h=gdao.getEntityViewByBarcode(session, com_id, goods_barcode);
			if (g_h==null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("找不到这个条码"+goods_barcode);
			}
			com.cqqyd2014.wh.model.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(g_h);
			
			
			odis.add(g);
			
			
			
			
			java.math.BigDecimal weight=com.cqqyd2014.util.ArrayListTools.sumFields(odis, "getPackage_weight");
			session_http.put("temp_add_prepackage_barcode", odis);
			sm.setO(odis);
			sm.setO2(weight);
			String c_num=prepackage_barcode.substring(0,1);
			java.math.BigDecimal b_num=new java.math.BigDecimal(c_num);
			java.math.BigDecimal odis_num=new java.math.BigDecimal(odis.size());
			if (b_num.compareTo(odis_num)==0) {
				sm.setO3("数量已满");
			}
			else {
				sm.setO3("数量未满");
			}
			sm.setSuccess(true);
			sm.setBody("准备打包商品"+g.getGoods_name());
			sm.setSound("picked_ok");
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
