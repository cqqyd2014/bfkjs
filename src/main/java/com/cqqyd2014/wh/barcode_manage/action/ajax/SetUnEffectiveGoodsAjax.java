package com.cqqyd2014.wh.barcode_manage.action.ajax;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class SetUnEffectiveGoodsAjax extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	String barcode;
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
		
		
		
		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			
			
			//作废的条码必须是入库，且为库房的，不是在supply和custom，
			
			com.cqqyd2014.hibernate.entities.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.GoodsBroken(session, barcode, new java.util.Date(), com_id,user_id);
			//修改入库单
			String goods_id=g.getGoodsId();
			
			
			
			
			//StorageDetail更新，库位也要减少 
			com.cqqyd2014.wh.logic.Storage.BrokenGoods(session, goods_id, g.getCurrentWh(), new java.math.BigDecimal(1), com_id);
			tx.commit();
			sm.setSuccess(true);
			sm.setSound("picked_ok");
			
			
		}
		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} 
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			if (null != tx) {
				tx.rollback();// 撤销事务
				sm.setSuccess(false);
				sm.setSound("error");
				sm.setBody(e.getMessage());

			}
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