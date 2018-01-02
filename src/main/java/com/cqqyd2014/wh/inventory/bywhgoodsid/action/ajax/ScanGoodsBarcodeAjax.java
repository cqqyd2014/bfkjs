package com.cqqyd2014.wh.inventory.bywhgoodsid.action.ajax;

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

public class ScanGoodsBarcodeAjax extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	String barcode;
	String wh_id;
	
	
	public String getWh_id() {
		return wh_id;
	}
	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
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
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, barcode, com_id);
			if (g==null){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该条码不在系统登记中！");
			}
			String currentWh=g.getCurrentWh();
			if (!currentWh.equals(wh_id)){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该条码不在仓库"+wh_id+"中，而在仓库"+currentWh+"中！");
			}
			String currentStorage=g.getCurrentStorage();
			if (currentStorage.equals("LOCKED")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该条码锁定中！");
			}
			
			
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods> gbs=(java.util.ArrayList<com.cqqyd2014.wh.model.Goods>)this.session.get("temp_inventory_by_wh_goods_id");
			
			for (int i = 0 , len= gbs.size();i<len;++i){
				com.cqqyd2014.wh.model.Goods gb=gbs.get(i);
				if (gb.getBarcode().equals(barcode)){
					gbs.remove(i);
					--len;
					--i;
				}
			}
			//System.out.println(gbs.size());
			
			sm.setSuccess(true);
			sm.setBody("商品在库房中");
			sm.setO(gbs);
			
			this.session.put("temp_inventory_by_wh_goods_id", gbs);
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
			}
			catch(Exception e){
				System.out.println(e.toString());
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