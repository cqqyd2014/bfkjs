package com.cqqyd2014.wh.inventory.bywhgoodsid.action.ajax;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;


import com.cqqyd2014.wh.logic.IntoWh;
import com.cqqyd2014.wh.logic.Storage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class GetWhGoodsIdBarcodeAjax extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	String wh_id;
	
	String goods_id;
	






	

	

	public String getWh_id() {
		return wh_id;
	}
	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		

		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		com.cqqyd2014.hibernate.dao.VGoodsDAO cmdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
		
		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods> gbs=cmdao.getBarcodeByWhGoodsIdInDefaultAndPrepackage(session, com_id, wh_id, goods_id);
			
			

			
			sm.setSuccess(true);
			
			sm.setO(gbs);
			
			this.session.put("temp_inventory_by_wh_goods_id", gbs);
		}
		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} 

		finally {
			HibernateSessionFactory.closeSession();
		}
		
		
		
		JsonConfig jsonConfig = new JsonConfig();  
		
		JSONArray ja1 = JSONArray.fromObject(sm);

		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ja1); 
		return null;
	}

}