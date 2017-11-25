package com.cqqyd2014.order.model;

public class ReturnGoods {
	String seq;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	String order_no;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	String com_id;
	String uuid;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	String goods_id;
	String goods_name;
	String return_userid;
	String return_username;
	String order_create_userid;
	String order_create_username;
	java.util.Date op_date;
	String op_date_chinese;
	String goods_barcode;
	java.math.BigDecimal return_pirce;
	String memo;
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
	public String getReturn_userid() {
		return return_userid;
	}
	public void setReturn_userid(String return_userid) {
		this.return_userid = return_userid;
	}
	public String getReturn_username() {
		return return_username;
	}
	public void setReturn_username(String return_username) {
		this.return_username = return_username;
	}
	public String getOrder_create_userid() {
		return order_create_userid;
	}
	public void setOrder_create_userid(String order_create_userid) {
		this.order_create_userid = order_create_userid;
	}
	public String getOrder_create_username() {
		return order_create_username;
	}
	public void setOrder_create_username(String order_create_username) {
		this.order_create_username = order_create_username;
	}
	public java.util.Date getOp_date() {
		return op_date;
	}
	public void setOp_date(java.util.Date op_date) {
		this.op_date = op_date;
	}
	public String getOp_date_chinese() {
		return op_date_chinese;
	}
	public void setOp_date_chinese(String op_date_chinese) {
		this.op_date_chinese = op_date_chinese;
	}
	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	public java.math.BigDecimal getReturn_pirce() {
		return return_pirce;
	}
	public void setReturn_pirce(java.math.BigDecimal return_pirce) {
		this.return_pirce = return_pirce;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
