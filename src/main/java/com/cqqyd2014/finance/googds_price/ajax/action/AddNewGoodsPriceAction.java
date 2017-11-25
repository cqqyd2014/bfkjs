package com.cqqyd2014.finance.googds_price.ajax.action;

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
@Namespace("/finance")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class AddNewGoodsPriceAction  extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String new_goods_id;
	String new_start_dat;
	String new_end_dat;
	java.math.BigDecimal new_price;
	

	public String getNew_goods_id() {
		return new_goods_id;
	}

	public void setNew_goods_id(String new_goods_id) {
		this.new_goods_id = new_goods_id;
	}



	public String getNew_start_dat() {
		return new_start_dat;
	}

	public void setNew_start_dat(String new_start_dat) {
		this.new_start_dat = new_start_dat;
	}

	public String getNew_end_dat() {
		return new_end_dat;
	}

	public void setNew_end_dat(String new_end_dat) {
		this.new_end_dat = new_end_dat;
	}

	String uuid;
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public java.math.BigDecimal getNew_price() {
		return new_price;
	}

	public void setNew_price(java.math.BigDecimal new_price) {
		this.new_price = new_price;
	}

	@Action(value = "add_new_goods_price", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String add_new_goods_price() {
		Map session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
Session session = HibernateSessionFactory.getSession();
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Transaction tx = session.beginTransaction();
		try {
			if (uuid.equals("")){
				//新增价格记录
				uuid=com.cqqyd2014.util.StringUtil.getUUID();
			}
			else{
				
			}
			
			
			//将起止时间变为日期
			java.util.Date start_dat=com.cqqyd2014.util.DateUtil.JDateToStartDate(com.cqqyd2014.util.DateUtil.ShortStringToJDate(new_start_dat));
			java.util.Date end_dat=com.cqqyd2014.util.DateUtil.JDateToEndDate(com.cqqyd2014.util.DateUtil.ShortStringToJDate(new_end_dat));
			
			//测算时间是否与其他价格记录重叠
			
			com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO updao=new com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO();
			com.cqqyd2014.hibernate.entities.FinanceGoodsPrice ups1=updao.getEntityByGoodsIdDateNotUuid(session, com_id, new_goods_id, start_dat,uuid);
			if (ups1==null){
				
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("价格开始时间与其他价格记录重叠");
			}
			com.cqqyd2014.hibernate.entities.FinanceGoodsPrice ups2=updao.getEntityByGoodsIdDateNotUuid(session, com_id, new_goods_id, end_dat,uuid);
			if (ups2==null){
				
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("价格结束时间与其他价格记录重叠");
			}
			
		com.cqqyd2014.finance.model.FinanceGoodsPrice fgp=new com.cqqyd2014.finance.model.FinanceGoodsPrice();
		fgp.setCom_id(com_id);
		fgp.setEnd_dat(end_dat);
		fgp.setStart_dat(start_dat);
		fgp.setGoods_id(new_goods_id);
		fgp.setPrice(new_price);

		com.cqqyd2014.finance.logic.FinanceGoodsPriceLogic fgpl=new com.cqqyd2014.finance.logic.FinanceGoodsPriceLogic();
		fgpl.save(session, fgp);
		
			sm.setSuccess(true);
			tx.commit();
		
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		
		System.out.println(e.getMessage());
		sm.setSuccess(false);
		sm.setBody(e.toString());
		
		
	}
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			
			System.out.println(e.getMessageString());
			sm.setSuccess(false);
			sm.setBody(e.toString());
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}
