package com.cqqyd2014.express.common;

import org.hibernate.Session;

public abstract class ExpressBillModel {
	public com.cqqyd2014.order.model.Order order;
	public com.cqqyd2014.order.model.DeliverBill db;
	
	
	
	public void transOrderDeliverModelToExpressBillModel(Session session,com.cqqyd2014.order.model.Order order,com.cqqyd2014.order.model.DeliverBill db) throws Exception{
		this.order=order;
		this.db=db;
		this.print_dat=com.cqqyd2014.util.DateUtil.JDateToSimpleString(new java.util.Date());
		SenderInformation(session);
		RecieverInformation(session);
		FullAddr();
		CustomInformation(session);
		
	}
	
	public  abstract void CustomInformation(Session session);
	private void SenderInformation(Session session) throws Exception{
		//得到订单来源
		com.cqqyd2014.hibernate.dao.OrderFromDAO ofdao=new com.cqqyd2014.hibernate.dao.OrderFromDAO();
		com.cqqyd2014.hibernate.entities.OrderFrom of=ofdao.getObjectByOrderNo(session, order.getOrder_no(),order.getCom_id());
		if (of==null){
			throw new Exception("订单类型不能识别");
		}
		this.sender=of.getSender();
		this.sender_addr=of.getSenderAddr();
		this.sender_city=of.getSenderCity();
		this.sender_com=of.getSenderCom();
		this.sender_province=of.getSenderProvince();
		this.sender_tell=of.getSenderTell();
		this.sender_mobile=of.getSenderTell();
		this.sender_district=of.getSenderDistrict();
		
	}
	
	private void RecieverInformation(Session session){
		this.receiver=order.getUser_name();
		this.receiver_tell=order.getTell2();
		this.reciever_addr=order.getUser_addr();
		this.reciever_city=order.getCity();
		this.reciever_com=order.getUser_com();
		this.reciever_mobile=order.getTell();
		this.reciever_province=order.getProvince();
		this.package_memo=order.getDetail_memo();
		this.memo=order.getDetail_memo();
		this.reciever_district=order.getDistrict();
		
	}
	
	
	private void FullAddr(){
		reciever_full_address=reciever_province+" "+reciever_city+" "+reciever_district+" "+reciever_addr;
		sender_full_address=sender_province+" "+sender_city+" "+sender_district+" "+sender_addr;
		
	}
	String print_dat;
	public String getPrint_dat() {
		return print_dat;
	}

	public void setPrint_dat(String print_dat) {
		this.print_dat = print_dat;
	}
	String receiver;
	String receiver_tell;
	String reciever_mobile;
	String reciever_province;
	String reciever_city;
	String reciever_district;
	String reciever_full_address;
	String reciever_addr;
	String reciever_com;
	String reciever_post;
	String sender;
	String sender_com;
	String sender_mobile;
	String sender_province;
	String sender_city;
	String sender_district;
	String sender_full_address;
	String sender_addr;
	String package_memo;
	String memo;

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPackage_memo() {
		return package_memo;
	}
	public void setPackage_memo(String package_memo) {
		this.package_memo = package_memo;
	}
	public String getReciever_addr() {
		return reciever_addr;
	}
	public void setReciever_addr(String reciever_addr) {
		this.reciever_addr = reciever_addr;
	}
	public String getSender_addr() {
		return sender_addr;
	}
	public void setSender_addr(String sender_addr) {
		this.sender_addr = sender_addr;
	}

	String sender_tell;

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiver_tell() {
		return receiver_tell;
	}

	public void setReceiver_tell(String receiver_tell) {
		this.receiver_tell = receiver_tell;
	}

	public String getReciever_mobile() {
		return reciever_mobile;
	}

	public void setReciever_mobile(String reciever_mobile) {
		this.reciever_mobile = reciever_mobile;
	}

	public String getReciever_province() {
		return reciever_province;
	}

	public void setReciever_province(String reciever_province) {
		this.reciever_province = reciever_province;
	}

	public String getReciever_city() {
		return reciever_city;
	}

	public void setReciever_city(String reciever_city) {
		this.reciever_city = reciever_city;
	}

	public String getReciever_full_address() {
		return reciever_full_address;
	}

	public void setReciever_full_address(String reciever_full_address) {
		this.reciever_full_address = reciever_full_address;
	}

	public String getReciever_com() {
		return reciever_com;
	}

	public void setReciever_com(String reciever_com) {
		this.reciever_com = reciever_com;
	}

	public String getReciever_post() {
		return reciever_post;
	}

	public void setReciever_post(String reciever_post) {
		this.reciever_post = reciever_post;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender_com() {
		return sender_com;
	}

	public void setSender_com(String sender_com) {
		this.sender_com = sender_com;
	}

	public String getSender_mobile() {
		return sender_mobile;
	}

	public void setSender_mobile(String sender_mobile) {
		this.sender_mobile = sender_mobile;
	}

	public String getSender_province() {
		return sender_province;
	}

	public void setSender_province(String sender_province) {
		this.sender_province = sender_province;
	}

	public String getSender_city() {
		return sender_city;
	}

	public void setSender_city(String sender_city) {
		this.sender_city = sender_city;
	}

	public String getSender_full_address() {
		return sender_full_address;
	}

	public void setSender_full_address(String sender_full_address) {
		this.sender_full_address = sender_full_address;
	}

	public String getSender_tell() {
		return sender_tell;
	}

	public void setSender_tell(String sender_tell) {
		this.sender_tell = sender_tell;
	}

	public String getSender_post() {
		return sender_post;
	}

	public void setSender_post(String sender_post) {
		this.sender_post = sender_post;
	}

	String sender_post;

}
