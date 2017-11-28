package com.cqqyd2014.finance.googds_price.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/finance")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetGoodsIdPirceAction  extends ActionSupport {
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

	@Action(value = "get_goods_price", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_goods_price() throws Exception {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
Session session = HibernateSessionFactory.getSession();
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		try {
			com.cqqyd2014.hibernate.dao.VFinanceGoodsPriceDAO vfgpdao=new com.cqqyd2014.hibernate.dao.VFinanceGoodsPriceDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice> vfgps_h=vfgpdao.getArrayListViewByGoodsId(session, com_id,goods_id);
			com.cqqyd2014.finance.logic.FinanceGoodsPriceLogic fgpl=new com.cqqyd2014.finance.logic.FinanceGoodsPriceLogic();
			java.util.ArrayList<com.cqqyd2014.finance.model.FinanceGoodsPrice> fgps=fgpl.getArrayListModelFromArrayListView(vfgps_h,new java.util.Date());
				sm.setSuccess(true);
				sm.setO(fgps);
			
			
			
			

		
		
	}

	catch (HibernateException e) {
		
		
		
		System.out.println(e.getMessage());
		
		
	}

		finally {
			HibernateSessionFactory.closeSession();
		}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}