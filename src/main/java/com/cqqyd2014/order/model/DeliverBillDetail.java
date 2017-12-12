package com.cqqyd2014.order.model;

public class DeliverBillDetail {
	
	String receiver;
	String original_id;

	String receiver_province;
	String receiver_city;
	String receiver_district;
	String receiver_addr;
	String receiver_full_addr;
	public String getReceiver_province() {
		return receiver_province;
	}
	public void setReceiver_province(String receiver_province) {
		this.receiver_province = receiver_province;
	}
	public String getReceiver_city() {
		return receiver_city;
	}
	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}
	public String getReceiver_district() {
		return receiver_district;
	}
	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}
	public String getReceiver_addr() {
		return receiver_addr;
	}
	public void setReceiver_addr(String receiver_addr) {
		this.receiver_addr = receiver_addr;
	}
	public String getReceiver_full_addr() {
		return receiver_full_addr;
	}
	public void setReceiver_full_addr(String receiver_full_addr) {
		this.receiver_full_addr = receiver_full_addr;
	}

	java.util.Date order_dat;
	public java.util.Date getOrder_dat() {
		return order_dat;
	}
	public void setOrder_dat(java.util.Date order_dat) {
		this.order_dat = order_dat;
	}

	String mobile;
	String tell;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(String original_id) {
		this.original_id = original_id;
	}

	String express_com_name;
	public String getExpress_com_name() {
		return express_com_name;
	}
	public void setExpress_com_name(String express_com_name) {
		this.express_com_name = express_com_name;
	}

	String express_no;
	String express_com;
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

	String wh_name;
	public String getWh_name() {
		return wh_name;
	}
	public void setWh_name(String wh_name) {
		this.wh_name = wh_name;
	}

	String create_user_name;
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	String return_dat_chinese;
	public String getReturn_dat_chinese() {
		return return_dat_chinese;
	}
	public void setReturn_dat_chinese(String return_dat_chinese) {
		this.return_dat_chinese = return_dat_chinese;
	}

	java.math.BigDecimal num;
	public java.math.BigDecimal getNum() {
		return num;
	}
	public void setNum(java.math.BigDecimal num) {
		this.num = num;
	}

	boolean returned;
	String returned_memo;
	java.util.Date returned_dat;
	String returned_userid;
	
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	public String getReturned_memo() {
		return returned_memo;
	}
	public void setReturned_memo(String returned_memo) {
		this.returned_memo = returned_memo;
	}
	public java.util.Date getReturned_dat() {
		return returned_dat;
	}
	public void setReturned_dat(java.util.Date returned_dat) {
		this.returned_dat = returned_dat;
	}
	public String getReturned_userid() {
		return returned_userid;
	}
	public void setReturned_userid(String returned_userid) {
		this.returned_userid = returned_userid;
	}

	java.math.BigDecimal order_count;
	public java.math.BigDecimal getOrder_count() {
		return order_count;
	}
	public void setOrder_count(java.math.BigDecimal order_count) {
		this.order_count = order_count;
	}

	String unit;
	java.math.BigDecimal sended_count;
	java.math.BigDecimal yue;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public java.math.BigDecimal getSended_count() {
		return sended_count;
	}
	public void setSended_count(java.math.BigDecimal sended_count) {
		this.sended_count = sended_count;
	}
	public java.math.BigDecimal getYue() {
		return yue;
	}
	public void setYue(java.math.BigDecimal yue) {
		this.yue = yue;
	}

	String order_no;
	String uuid;
	String seq;
	String com_id;
	public java.math.BigDecimal getNet_weight() {
		return net_weight;
	}
	public void setNet_weight(java.math.BigDecimal net_weight) {
		this.net_weight = net_weight;
	}
	public java.math.BigDecimal getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(java.math.BigDecimal gross_weight) {
		this.gross_weight = gross_weight;
	}
	public java.math.BigDecimal getPackage_weight() {
		return package_weight;
	}
	public void setPackage_weight(java.math.BigDecimal package_weight) {
		this.package_weight = package_weight;
	}
	String goods_barcode;
	String goods_id;
	String goods_name;
	java.math.BigDecimal net_weight;
	java.math.BigDecimal gross_weight;
	java.math.BigDecimal package_weight;
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

}
