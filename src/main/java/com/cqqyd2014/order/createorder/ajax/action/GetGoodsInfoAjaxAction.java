package com.cqqyd2014.order.createorder.ajax.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.util.message.IfMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetGoodsInfoAjaxAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
public  String goods_id;
	
	
	public boolean fuzzy;

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public boolean isFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(boolean fuzzy) {
		this.fuzzy = fuzzy;
	}
	@Action(value = "get_goods_info", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_goods_info() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");


		java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice>();
		Session session = HibernateSessionFactory.getSession();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Transaction tx = session.beginTransaction();
		try {
			
			com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO gidao=new com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> gis=new java.util.ArrayList<>();
			if (fuzzy){
				gis=gidao.getGoodsInfosLike(session, goods_id, com_id,user_id,new java.util.Date());
			}
			else{
				gis.add(gidao.getGoodsInfos(session, goods_id, com_id,user_id,new java.util.Date()));
				
			}
			com.cqqyd2014.usergroup.logic.UserPriceLogic upl=new com.cqqyd2014.usergroup.logic.UserPriceLogic();
			ups=upl.getArrayModelFromArrayEntities(gis,new java.util.Date());
			
			sm.setSuccess(true);
			sm.setO(ups);
			
			tx.commit();
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
	}
		finally {
			HibernateSessionFactory.closeSession();
		}

		msg=sm.toMap();
		return SUCCESS;
	}
}
