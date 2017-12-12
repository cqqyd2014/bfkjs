package com.cqqyd2014.order.model;



public class Order {
	//以财务入账价值测算的实际支付金额
	java.math.BigDecimal actual_amount2;
	public java.math.BigDecimal getActual_amount2() {
		return actual_amount2;
	}
	public void setActual_amount2(java.math.BigDecimal actual_amount2) {
		this.actual_amount2 = actual_amount2;
	}
	
	public java.math.BigDecimal getTax2() {
		return tax2;
	}
	public void setTax2(java.math.BigDecimal tax2) {
		this.tax2 = tax2;
	}
	public java.math.BigDecimal getReg_tax2() {
		return reg_tax2;
	}
	public void setReg_tax2(java.math.BigDecimal reg_tax2) {
		this.reg_tax2 = reg_tax2;
	}
	//增值税
	java.math.BigDecimal tax2;
	//消费税
	java.math.BigDecimal reg_tax2;
	
	boolean effective;
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	String express_status;
	public String getExpress_status() {
		return express_status;
	}
	public void setExpress_status(String express_status) {
		this.express_status = express_status;
	}
	java.util.Date cancel_request_dat;
	java.util.Date cancel_confrim_dat;
	String cancel_status;
	String cancel_request_userid;
	String cancel_confirm_userid;
	String cancel_request_memo;
	String cancel_confirm_memo;
	public java.util.Date getCancel_request_dat() {
		return cancel_request_dat;
	}
	public void setCancel_request_dat(java.util.Date cancel_request_dat) {
		this.cancel_request_dat = cancel_request_dat;
	}
	public java.util.Date getCancel_confrim_dat() {
		return cancel_confrim_dat;
	}
	public void setCancel_confrim_dat(java.util.Date cancel_confrim_dat) {
		this.cancel_confrim_dat = cancel_confrim_dat;
	}
	public String getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(String cancel_status) {
		this.cancel_status = cancel_status;
	}
	public String getCancel_request_userid() {
		return cancel_request_userid;
	}
	public void setCancel_request_userid(String cancel_request_userid) {
		this.cancel_request_userid = cancel_request_userid;
	}
	public String getCancel_confirm_userid() {
		return cancel_confirm_userid;
	}
	public void setCancel_confirm_userid(String cancel_confirm_userid) {
		this.cancel_confirm_userid = cancel_confirm_userid;
	}
	public String getCancel_request_memo() {
		return cancel_request_memo;
	}
	public void setCancel_request_memo(String cancel_request_memo) {
		this.cancel_request_memo = cancel_request_memo;
	}
	public String getCancel_confirm_memo() {
		return cancel_confirm_memo;
	}
	public void setCancel_confirm_memo(String cancel_confirm_memo) {
		this.cancel_confirm_memo = cancel_confirm_memo;
	}
	java.math.BigDecimal qty;
	public java.math.BigDecimal getQty() {
		return qty;
	}
	public void setQty(java.math.BigDecimal qty) {
		this.qty = qty;
	}
	String order_status;
	String wh_status;
	
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getWh_status() {
		return wh_status;
	}
	public void setWh_status(String wh_status) {
		this.wh_status = wh_status;
	}
	String logistics;
	String logistics_name;
	String vehicle;
	String vehicle_name;
	public String getLogistics_name() {
		return logistics_name;
	}
	public void setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	java.util.Date paid_time;
	
	public java.util.Date getPaid_time() {
		return paid_time;
	}
	public void setPaid_time(java.util.Date paid_time) {
		this.paid_time = paid_time;
	}
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
	java.util.Date package_user_assign_time;
	String package_userid;
	String package_username;
	boolean paid;
	java.math.BigDecimal paid_money;
	
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public java.math.BigDecimal getPaid_money() {
		return paid_money;
	}
	public void setPaid_money(java.math.BigDecimal paid_money) {
		this.paid_money = paid_money;
	}
	public java.util.Date getPackage_user_assign_time() {
		return package_user_assign_time;
	}
	public void setPackage_user_assign_time(java.util.Date package_user_assign_time) {
		this.package_user_assign_time = package_user_assign_time;
	}
	public String getPackage_userid() {
		return package_userid;
	}
	public void setPackage_userid(String package_userid) {
		this.package_userid = package_userid;
	}
	public String getPackage_username() {
		return package_username;
	}
	public void setPackage_username(String package_username) {
		this.package_username = package_username;
	}
	String order_ebusiness_code;
	String order_ebusiness_name;
	String order_type_code;
	

	public String getOrder_ebusiness_code() {
		return order_ebusiness_code;
	}
	public void setOrder_ebusiness_code(String order_ebusiness_code) {
		this.order_ebusiness_code = order_ebusiness_code;
	}
	public String getOrder_ebusiness_name() {
		return order_ebusiness_name;
	}
	public void setOrder_ebusiness_name(String order_ebusiness_name) {
		this.order_ebusiness_name = order_ebusiness_name;
	}
	public String getOrder_type_code() {
		return order_type_code;
	}
	public void setOrder_type_code(String order_type_code) {
		this.order_type_code = order_type_code;
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
	public String getSender_tell() {
		return sender_tell;
	}
	public void setSender_tell(String sender_tell) {
		this.sender_tell = sender_tell;
	}
	public String getSender_full_addr() {
		return sender_full_addr;
	}
	public void setSender_full_addr(String sender_full_addr) {
		this.sender_full_addr = sender_full_addr;
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
	public String getSender_district() {
		return sender_district;
	}
	public void setSender_district(String sender_district) {
		this.sender_district = sender_district;
	}
	public String getSender_addr() {
		return sender_addr;
	}
	public void setSender_addr(String sender_addr) {
		this.sender_addr = sender_addr;
	}
	String sender;
	String sender_com;
	String sender_tell;
	String sender_full_addr;
	String sender_province;
	String sender_city;
	String sender_district;
	String sender_addr;
	String detail_memo;
	public String getDetail_memo() {
		return detail_memo;
	}
	public void setDetail_memo(String detail_memo) {
		this.detail_memo = detail_memo;
	}
	java.util.ArrayList<OrderDetail> details;

	java.math.BigDecimal actual_amount;
	java.math.BigDecimal original_amount;
	java.math.BigDecimal original_amount2;
	
	public java.math.BigDecimal getOriginal_amount2() {
		return original_amount2;
	}
	public void setOriginal_amount2(java.math.BigDecimal original_amount2) {
		this.original_amount2 = original_amount2;
	}
	public java.math.BigDecimal getActual_amount() {
		return actual_amount;
	}
	public void setActual_amount(java.math.BigDecimal actual_amount) {
		this.actual_amount = actual_amount;
	}
	public java.math.BigDecimal getOriginal_amount() {
		return original_amount;
	}
	public void setOriginal_amount(java.math.BigDecimal original_amount) {
		this.original_amount = original_amount;
	}

	String original_id;
	public String getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(String original_id) {
		this.original_id = original_id;
	}
	String sys_user_name;
	public String getSys_user_name() {
		return sys_user_name;
	}
	public void setSys_user_name(String sys_user_name) {
		this.sys_user_name = sys_user_name;
	}
	String user_name;
	String province;
	String city;
	String district;
	String user_addr;
	java.util.Date order_dat;
	String order_no;
	java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> deliver_bills;
	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getDeliver_bills() {
		return deliver_bills;
	}
	public void setDeliver_bills(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> deliver_bills) {
		this.deliver_bills = deliver_bills;
	}
	public java.util.ArrayList<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(java.util.ArrayList<OrderDetail> details) {
		this.details = details;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public java.util.Date getOrder_dat() {
		return order_dat;
	}
	public void setOrder_dat(java.util.Date order_dat) {
		this.order_dat = order_dat;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	String memo;
	String user_com;
	
	public String getUser_com() {
		return user_com;
	}
	public void setUser_com(String user_com) {
		this.user_com = user_com;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public java.math.BigDecimal card_pay;
	public String tell2;
	public java.math.BigDecimal getCard_pay() {
		return card_pay;
	}
	public void setCard_pay(java.math.BigDecimal card_pay) {
		this.card_pay = card_pay;
	}
	public String getTell2() {
		return tell2;
	}
	public void setTell2(String tell2) {
		this.tell2 = tell2;
	}
	public String tell;
	
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public java.math.BigDecimal discount;
	
	public java.math.BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(java.math.BigDecimal discount) {
		this.discount = discount;
	}
	 java.math.BigDecimal ship_fee;
	String user_id;
	
	public java.math.BigDecimal getShip_fee() {
		return ship_fee;
	}
	public void setShip_fee(java.math.BigDecimal ship_fee) {
		this.ship_fee = ship_fee;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean notAir;
	public boolean isNotAir() {
		return notAir;
	}
	public void setNotAir(boolean notAir) {
		this.notAir = notAir;
	}
	public String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public com.cqqyd2014.order.model.DeliverBill getDeliverBillBySeq(String seq){
		
		for (int i=0;i<getDeliver_bills().size();i++){
			com.cqqyd2014.order.model.DeliverBill deliver_bill=getDeliver_bills().get(i);
			if (deliver_bill.getSeq().equals(seq)){
				return deliver_bill;
			}
			
		}
		return null;
	}

	
	


}
