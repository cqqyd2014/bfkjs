package com.cqqyd2014.goods.goodsList.ajax.action;

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
import com.cqqyd2014.hibernate.dao.VInventoryByGoodsIdAvailableDAO;
import com.cqqyd2014.util.message.IfMessage;
import com.cqqyd2014.util.taobao.OrderAutoAnalysisException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/goods")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetGoodsListAjaxAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_goods_list", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_goods_list() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
Session session = HibernateSessionFactory.getSession();
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> gis=gdao.getGoodsInfosAll(session, com_id);
			java.util.ArrayList<com.cqqyd2014.wh.model.GoodsInfo> gs=new java.util.ArrayList<>();
			
			for (int i=0;i<gis.size();i++){
				com.cqqyd2014.hibernate.entities.VGoodsInfo gi=gis.get(i);
				com.cqqyd2014.wh.model.GoodsInfo g=new com.cqqyd2014.wh.model.GoodsInfo();
				
				g=com.cqqyd2014.wh.logic.GoodsInfoLogic.getModelFromView(gi);
				
				gs.add(g);
			}
			
			
				
				sm.setSuccess(true);
				sm.setO(gs);
			
			
			
			

		
		
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

