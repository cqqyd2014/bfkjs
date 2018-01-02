package com.cqqyd2014.usergroup.user_manage.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.entities.UserPrice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/usergroup") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
public class SaveUserPrice      extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String userid;
	String uuid;

	String start_time;
	String end_time;
	String goods_id;
	java.math.BigDecimal user_price;

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public java.math.BigDecimal getUser_price() {
		return user_price;
	}

	public void setUser_price(java.math.BigDecimal user_price) {
		this.user_price = user_price;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Action(value = "save_user_price", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String save_user_price() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		

Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			if (uuid.equals("")){
				//新增价格记录
				uuid=com.cqqyd2014.util.StringUtil.getUUID();
			}
			else{
				
			}
			//将起止时间变为日期
			java.util.Date start_dat=com.cqqyd2014.util.DateUtil.JDateToStartDate(com.cqqyd2014.util.DateUtil.ShortStringToJDate(start_time));
			java.util.Date end_dat=com.cqqyd2014.util.DateUtil.JDateToEndDate(com.cqqyd2014.util.DateUtil.ShortStringToJDate(end_time));
			
			//测算时间是否与其他价格记录重叠
			
			com.cqqyd2014.hibernate.dao.UserPriceDAO updao=new com.cqqyd2014.hibernate.dao.UserPriceDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> ups1=updao.getArrayEntityByUseridDatGoodsidNotUuid(session, com_id, userid, start_dat,uuid,goods_id);
			if (ups1.size()>0){
				
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("价格开始时间与其他价格记录重叠");
			}
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> ups2=updao.getArrayEntityByUseridDatGoodsidNotUuid(session, com_id, userid, end_dat,uuid,goods_id);
			if (ups2.size()>0){
				
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("价格结束时间与其他价格记录重叠");
			}
			
			com.cqqyd2014.hibernate.entities.UserPrice up=new com.cqqyd2014.hibernate.entities.UserPrice();
			com.cqqyd2014.hibernate.entities.UserPriceId upid=new com.cqqyd2014.hibernate.entities.UserPriceId();
			upid.setComId(com_id);
			upid.setUuid(uuid);
			up.setId(upid);
			up.setEffective(true);
			up.setEndTime(end_dat);
			up.setStartTime(start_dat);
			up.setGoodsId(goods_id);
			up.setUserPrice(user_price);
			up.setUserId(userid);
			session.saveOrUpdate(up);
			
				
				sm.setSuccess(true);
				
			
			
			
			
tx.commit();
		
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		
		sm.setSuccess(false);
		sm.setBody(e.getMessage());
	}
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			
			System.out.println(e.getMessage());
			sm.setSuccess(false);
			sm.setBody(e.toString());
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}