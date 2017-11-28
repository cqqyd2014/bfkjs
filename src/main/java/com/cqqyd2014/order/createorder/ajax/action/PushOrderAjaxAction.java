package com.cqqyd2014.order.createorder.ajax.action;

import java.math.RoundingMode;
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
import com.cqqyd2014.order.logic.LogisticsLogic;
import com.cqqyd2014.order.logic.OrderLogic;
import com.cqqyd2014.util.exception.AjaxSuccessMessageException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class PushOrderAjaxAction extends ActionSupport {
	
	public String original_no;
	public String OrderFrom;
	public String logistics;
	public String vehicle;

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String tell2;
	public String user_com;
	public String order_dat;
	public String userName;
	public String tell;
	public String province;
	public String city;
	public String district;
	public String userAddr;
	public java.math.BigDecimal original_amount;
	public java.math.BigDecimal discount;
	public java.math.BigDecimal card_pay;
	public java.math.BigDecimal ship_fee;
	public java.math.BigDecimal actual_amount;
	String memo;
	
	
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "push_order", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String push_order() {
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		//String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis = (java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>) session_http
				.get("temp_order_detail");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
		
		com.cqqyd2014.order.model.Order order=new com.cqqyd2014.order.model.Order();
		order.setCity(city);
		order.setTell(tell);
		order.setDetails(odis);
		order.setDistrict(district);
		order.setOrder_dat(com.cqqyd2014.util.DateUtil.FullStringToJDate(order_dat));
		order.setOrder_type_code(OrderFrom);
		order.setOriginal_id(original_no);
		order.setProvince(province);
		order.setUser_addr(userAddr);
		order.setUser_name(userName);
		
		
		order.setCard_pay(card_pay);
		order.setDiscount(discount);
		//系统自动统计
		//order.setOriginal_amount(original_amount);
		//系统自动统计
		//order.setActual_amount(actual_amount);
		order.setMemo(memo);
		order.setTell2(tell2);
		order.setCard_pay(card_pay);
		order.setCom_id(com_id);
		order.setUser_com(user_com);
		order.setUser_id(user_id);
		order.setPaid(false);
		order.setPaid_money(new java.math.BigDecimal(0));
		order.setPaid_time(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		order.setLogistics(logistics);
		order.setVehicle(vehicle);
		order.setPackage_user_assign_time(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		order.setPackage_userid("");
		order.setCancel_confirm_memo("");
		order.setCancel_confirm_userid("");
		order.setCancel_confrim_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		order.setCancel_request_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		order.setCancel_request_memo("");
		order.setCancel_request_userid("");
		order.setCancel_status("");
		order.setOrder_status("订单生成");
		order.setWh_status("");
		order.setExpress_status("");
		order.setPackage_userid("");
		order.setEffective(true);
		order.setCard_pay(new java.math.BigDecimal(0));
		//测试是否有not_air商品
		boolean not_air=false;
		com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao = new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
		com.cqqyd2014.hibernate.dao.EcsTaxRateDAO etrdao = new com.cqqyd2014.hibernate.dao.EcsTaxRateDAO();
		
		order.setDetail_memo("");
		//财务申报的增值税
		order.setTax2(new java.math.BigDecimal(0));
		//财务申报的消费税
		order.setReg_tax2(new java.math.BigDecimal(0));
		String detail_memo = "";
		order.setOriginal_amount(new java.math.BigDecimal("0"));
		//财务申报的价格
		order.setOriginal_amount2(new java.math.BigDecimal(0));
		//计算商品数量
		java.math.BigDecimal qty=new java.math.BigDecimal(0);
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=new java.util.ArrayList<>();
		
		com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO vupadao=new com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO();
		String order_no = com.cqqyd2014.order.logic.OrderNo.createNo(session,order.getOrder_type_code(),order.getCom_id());
		order.setOrder_no(order_no);
				for (int i=0;i<odis.size();i++){
					qty=qty.add(odis.get(i).getNum());
					com.cqqyd2014.order.model.OrderDetail od=odis.get(i);
					od.setOrder_no(order_no);
					if (od.isNot_air()){
						not_air=true;
					}
					//在订单锁定库房，锁定商品数量
					//下订单，增加“订单锁定”仓库的数量,不具体是哪个仓库，因此多一个仓库用来处理订单锁定
					
					//com.cqqyd2014.wh.logic.WareHouse.orderLock(session, odi.getGoods_id(), odi.getNum(), order.getCom_id()); 
					com.cqqyd2014.wh.logic.Storage.addStorage(session, odis.get(i).getGoods_id(), "ORDER_", "DEFAUL", odis.get(i).getNum(), order.getCom_id());
				
					
					
					order.setOriginal_amount(order.getOriginal_amount().add(od.getPrice().multiply(od.getNum())));
					
					
					detail_memo = detail_memo + "[序号：" + (i + 1) + ";型号:" + od.getGoods_name() + ";数量：" + od.getNum()
							+ "]";
					order.setDetail_memo(order.getDetail_memo()+detail_memo);
					order.setTax2(order.getTax2().add(od.getTax()));
					
					order.setReg_tax2(order.getReg_tax2().add(od.getReg_tax()));
					// 计算商品的入账价值，存货和成本
					order.setOriginal_amount2(order.getOriginal_amount2().add(od.getNum().multiply(od.getPrice2())));

					com.cqqyd2014.order.logic.OrderDetailLogic.save(session, od);
				
				}
				order.setNotAir(not_air);
				


				
				
		order.setQty(qty);
		java.math.BigDecimal ship_fee=LogisticsLogic.getFeeByLogisticsVehicleNum(session, order.getCom_id(), order.getLogistics(), order.getVehicle(), order.getQty());
		order.setShip_fee(ship_fee);
		com.cqqyd2014.hibernate.dao.OrderLogDAO oldao=new com.cqqyd2014.hibernate.dao.OrderLogDAO();
		
		
		
		oldao.addLog(session, order.getOrder_no(), "新增订单", "0001",order.getCom_id(),order.getUser_id());
		OrderLogic.save(session,order);
			sm.setSuccess(true);
			tx.commit();

			
		} catch (AjaxSuccessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
		}
		


	catch (HibernateException e) {

		if (null != tx) {
			tx.rollback();// 撤销事务

		}

		System.out.println(e.getMessage());
		e.printStackTrace();
		sm.setSuccess(false);
		sm.setBody(e.getMessage());

	}  finally {
		HibernateSessionFactory.closeSession();
	}
		//在订单锁定库房，锁定商品数量
		
		

		
		
		
		
		msg=sm.toMap();
		return SUCCESS;
	}

	public String getOriginal_no() {
		return original_no;
	}

	public void setOriginal_no(String original_no) {
		this.original_no = original_no;
	}

	public String getOrderFrom() {
		return OrderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		OrderFrom = orderFrom;
	}

	public String getTell2() {
		return tell2;
	}

	public void setTell2(String tell2) {
		this.tell2 = tell2;
	}

	public String getUser_com() {
		return user_com;
	}

	public void setUser_com(String user_com) {
		this.user_com = user_com;
	}

	public String getOrder_dat() {
		return order_dat;
	}

	public void setOrder_dat(String order_dat) {
		this.order_dat = order_dat;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public java.math.BigDecimal getOriginal_amount() {
		return original_amount;
	}

	public void setOriginal_amount(java.math.BigDecimal original_amount) {
		this.original_amount = original_amount;
	}

	public java.math.BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(java.math.BigDecimal discount) {
		this.discount = discount;
	}

	public java.math.BigDecimal getCard_pay() {
		return card_pay;
	}

	public void setCard_pay(java.math.BigDecimal card_pay) {
		this.card_pay = card_pay;
	}

	public java.math.BigDecimal getShip_fee() {
		return ship_fee;
	}

	public void setShip_fee(java.math.BigDecimal ship_fee) {
		this.ship_fee = ship_fee;
	}

	public java.math.BigDecimal getActual_amount() {
		return actual_amount;
	}

	public void setActual_amount(java.math.BigDecimal actual_amount) {
		this.actual_amount = actual_amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}