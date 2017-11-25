package com.cqqyd2014.wh.search_goods_barcode.ajax.action;

import java.lang.reflect.InvocationTargetException;
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
public class SearchGoodsBarcodeAction   extends ActionSupport {
	String goods_barcode;

	
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
	@Action(value = "search_goods_barcode", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String search_goods_barcode() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		String com_id = (String) session_http.get("com_code");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			sm=com.cqqyd2014.wh.logic.GoodsLogic.getAjaxMessageModelByBarcode(sm, session, goods_barcode, com_id);
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
			
		}
			catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException ex){
				if (null != tx) {
					tx.rollback();// 撤销事务

				}
				
				
				sm.setSuccess(false);
				sm.setBody(ex.getMessageString());
			}

			finally {
				HibernateSessionFactory.closeSession();
			}
	
		msg=sm.toMap();
		return SUCCESS;
	}
}
