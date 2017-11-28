package com.cqqyd2014.wh.move_warehouse.ajax.action;

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
public class AddGoodsToMoveAction   extends ActionSupport {
	String goods_barcode;
	String wh_id_from;


	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "add_goods_to_move", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String add_goods_to_move() throws Exception {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		//String user = (String) session_http.get("USER");
		//String user_name = (String) session_http.get("USER_NAME");
		//String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {

			
			com.cqqyd2014.util.message.IfMessage ir=com.cqqyd2014.wh.logic.GoodsLogic.if_available_checksn(goods_barcode);
			if (ir.isIf_ok()==false) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(ir.getMessage());
				
			}
			
			com.cqqyd2014.hibernate.dao.VGoodsDAO omdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			com.cqqyd2014.hibernate.entities.VGoods vg=omdao.getEntityViewByBarcode(session, com_id, goods_barcode) ;
			com.cqqyd2014.wh.model.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(vg);
			//需要仓库库房，普通库位的记录
			java.util.ArrayList<String> whs=com.cqqyd2014.util.StringUtil.toArrayList(wh_id_from);
			java.util.ArrayList<String> storages=com.cqqyd2014.util.StringUtil.toArrayList("DEFAUL");
			ir=com.cqqyd2014.wh.logic.GoodsLogic.if_available_in_wh_storage(session, g, whs,null, storages,null);
			
			if (!ir.isIf_ok()){
				//库位和库房不合要求
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(ir.getMessage());
				
			}
			
			
			
			
			
			
				
				java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
						.get("temp_move_goods");
				
				//4、本次入库不要重复录入
				for (int i=0;i<odis.size();i++){
					
					
					String old_sn=odis.get(i).getBarcode();
					if (old_sn.equals(goods_barcode)){
						throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("不要重复录入该条码");
						
					}
				}

				

				
				
				
				
				
				
				odis.add(g);
				
				session_http.put("temp_move_goods", odis);
				sm.setO(odis);
				sm.setO2(odis.size());
				
				sm.setSuccess(true);
			
			sm.setBody("条码"+goods_barcode+"的商品已经录入");
			
			
			

		
		
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
			sm.setBody(e.getMessageString());
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

	public String getGoods_barcode() {
		return goods_barcode;
	}

	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}

	public String getWh_id_from() {
		return wh_id_from;
	}

	public void setWh_id_from(String wh_id_from) {
		this.wh_id_from = wh_id_from;
	}

}
