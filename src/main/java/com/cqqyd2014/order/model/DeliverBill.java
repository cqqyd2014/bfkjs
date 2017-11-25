package com.cqqyd2014.order.model;

public class DeliverBill {
	
	String original_id;
	String receive_mobile;
	String receive_tell;
	
	public String getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(String original_id) {
		this.original_id = original_id;
	}
	public String getReceive_mobile() {
		return receive_mobile;
	}
	public void setReceive_mobile(String receive_mobile) {
		this.receive_mobile = receive_mobile;
	}
	public String getReceive_tell() {
		return receive_tell;
	}
	public void setReceive_tell(String receive_tell) {
		this.receive_tell = receive_tell;
	}
	java.util.Date package_dat;
	public java.util.Date getPackage_dat() {
		return package_dat;
	}
	public void setPackage_dat(java.util.Date package_dat) {
		this.package_dat = package_dat;
	}
	String memo_barcodes;
	String memo_names;

	public String getMemo_barcodes() {
		return memo_barcodes;
	}
	public void setMemo_barcodes(String memo_barcodes) {
		this.memo_barcodes = memo_barcodes;
	}
	public String getMemo_names() {
		return memo_names;
	}
	public void setMemo_names(String memo_names) {
		this.memo_names = memo_names;
	}
	String receive_user;
	String receive_addr_full;
	String receive_province;
	String receive_city;
	String receive_district;
	String receive_addr;
	String receive_com;
	public String getReceive_user() {
		return receive_user;
	}
	public void setReceive_user(String receive_user) {
		this.receive_user = receive_user;
	}
	public String getReceive_addr_full() {
		return receive_addr_full;
	}
	public void setReceive_addr_full(String receive_addr_full) {
		this.receive_addr_full = receive_addr_full;
	}
	public String getReceive_province() {
		return receive_province;
	}
	public void setReceive_province(String receive_province) {
		this.receive_province = receive_province;
	}
	public String getReceive_city() {
		return receive_city;
	}
	public void setReceive_city(String receive_city) {
		this.receive_city = receive_city;
	}
	public String getReceive_district() {
		return receive_district;
	}
	public void setReceive_district(String receive_district) {
		this.receive_district = receive_district;
	}
	public String getReceive_addr() {
		return receive_addr;
	}
	public void setReceive_addr(String receive_addr) {
		this.receive_addr = receive_addr;
	}
	public String getReceive_com() {
		return receive_com;
	}
	public void setReceive_com(String receive_com) {
		this.receive_com = receive_com;
	}
	String create_userid;
	String create_username;
	public String getCreate_userid() {
		return create_userid;
	}
	public void setCreate_userid(String create_userid) {
		this.create_userid = create_userid;
	}
	public String getCreate_username() {
		return create_username;
	}
	public void setCreate_username(String create_username) {
		this.create_username = create_username;
	}
	String com_name;
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	java.math.BigDecimal num;
	public java.math.BigDecimal getNum() {
		return num;
	}
	public void setNum(java.math.BigDecimal num) {
		this.num = num;
	}
	java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds;
	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> getDbds() {
		return dbds;
	}
	public void setDbds(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds) {
		this.dbds = dbds;
	}
	String carton_type;
	java.math.BigDecimal carton_weight;
	public String getCarton_type() {
		return carton_type;
	}
	public void setCarton_type(String carton_type) {
		this.carton_type = carton_type;
	}

	public java.math.BigDecimal getCarton_weight() {
		return carton_weight;
	}
	public void setCarton_weight(java.math.BigDecimal carton_weight) {
		this.carton_weight = carton_weight;
	}
	String wh_id;
	String wh_name;
	public String getWh_id() {
		return wh_id;
	}
	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	public String getWh_name() {
		return wh_name;
	}
	public void setWh_name(String wh_name) {
		this.wh_name = wh_name;
	}
	java.util.Date send_user_assgin_dat;
	java.util.Date send_dat;
	public java.util.Date getSend_dat() {
		return send_dat;
	}
	public void setSend_dat(java.util.Date send_dat) {
		this.send_dat = send_dat;
	}
	public java.util.Date getSend_user_assgin_dat() {
		return send_user_assgin_dat;
	}
	public void setSend_user_assgin_dat(java.util.Date send_user_assgin_dat) {
		this.send_user_assgin_dat = send_user_assgin_dat;
	}
	String pre_package_barcode;
	public String getPre_package_barcode() {
		return pre_package_barcode;
	}
	public void setPre_package_barcode(String pre_package_barcode) {
		this.pre_package_barcode = pre_package_barcode;
	}
	String package_userid;
	public String getPackage_userid() {
		return package_userid;
	}
	public void setPackage_userid(String package_userid) {
		this.package_userid = package_userid;
	}
	java.util.Date order_dat;

	public java.util.Date getOrder_dat() {
		return order_dat;
	}
	public void setOrder_dat(java.util.Date order_dat) {
		this.order_dat = order_dat;
	}
	String vehicle_name;
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	String vehicle_id;

	String seq;
	String express_no;
	String express_com;
	String express_com_name;
	public String getExpress_com_name() {
		return express_com_name;
	}
	public void setExpress_com_name(String express_com_name) {
		this.express_com_name = express_com_name;
	}
	String order_no;
	String deliver_no;
	java.math.BigDecimal actual_weight;
	java.math.BigDecimal package_weight;
	public java.math.BigDecimal getActual_weight() {
		return actual_weight;
	}
	public void setActual_weight(java.math.BigDecimal actual_weight) {
		this.actual_weight = actual_weight;
	}

	public java.math.BigDecimal getPackage_weight() {
		return package_weight;
	}
	public void setPackage_weight(java.math.BigDecimal package_weight) {
		this.package_weight = package_weight;
	}
	boolean sended;
	String send_userid;
	public String getSend_userid() {
		return send_userid;
	}
	public void setSend_userid(String send_userid) {
		this.send_userid = send_userid;
	}
	String logistics_fb_status;
	String logistics_fb_memo;
	String logistics_fb_code;
	public String getLogistics_fb_code() {
		return logistics_fb_code;
	}
	public void setLogistics_fb_code(String logistics_fb_code) {
		this.logistics_fb_code = logistics_fb_code;
	}
	java.util.Date logistics_fb_dat;
	java.util.Date cancel_request_dat;
	java.util.Date cancel_confrim_dat;
	String cancel_status;
	String cancel_request_userid;
	String cancel_confirm_userid;
	String cancel_request_memo;
	String cancel_confirm_memo;
	public String getLogistics_fb_status() {
		return logistics_fb_status;
	}
	public void setLogistics_fb_status(String logistics_fb_status) {
		this.logistics_fb_status = logistics_fb_status;
	}
	public String getLogistics_fb_memo() {
		return logistics_fb_memo;
	}
	public void setLogistics_fb_memo(String logistics_fb_memo) {
		this.logistics_fb_memo = logistics_fb_memo;
	}
	public java.util.Date getLogistics_fb_dat() {
		return logistics_fb_dat;
	}
	public void setLogistics_fb_dat(java.util.Date logistics_fb_dat) {
		this.logistics_fb_dat = logistics_fb_dat;
	}
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
	String deliver_bill_status;
	public String getDeliver_bill_status() {
		return deliver_bill_status;
	}
	public void setDeliver_bill_status(String deliver_bill_status) {
		this.deliver_bill_status = deliver_bill_status;
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
	boolean effective;

	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}


	public boolean isSended() {
		return sended;
	}
	public void setSended(boolean sended) {
		this.sended = sended;
	}
	public String getDeliver_no() {
		return deliver_no;
	}
	public void setDeliver_no(String deliver_no) {
		this.deliver_no = deliver_no;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public String getExpress_com() {
		return express_com;
	}
	public void setExpress_com(String express_com) {
		this.express_com = express_com;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
}
