package com.cqqyd2014.wh.common.ajax.action;

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


public class GetGoodsInfoAjaxAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	String goods_id;



	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}


	@Action(value = "get_goods_info", results = {
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
					
					@InterceptorRef("defaultStack"),
					@InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_goods_info", privilege = "*", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);

		java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups = new java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice>();
		session = HibernateSessionFactory.getSession();
		
		
		try {

			
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> gis = com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO.getGoodsInfosLike(session, goods_id, com_id, user_id, new java.util.Date());
			
			
			ups = com.cqqyd2014.usergroup.logic.UserPriceLogic.getArrayListModelFromArrayListView(gis, new java.util.Date());

			sm.setSuccess(true);
			sm.setO(ups);

			
		}

		catch (HibernateException e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);
			sm.setBody(e.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.toString());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		msg = sm.toMap();
		return "success";
	}
}
