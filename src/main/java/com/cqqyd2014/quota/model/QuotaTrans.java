package com.cqqyd2014.quota.model;

public class QuotaTrans {
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getOp_user_name() {
		return op_user_name;
	}
	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}
	String uuid;
	String status;
	String com_id;
	java.util.Date op_time;
	java.math.BigDecimal begin_amount;
	java.math.BigDecimal trans_amount;
	java.math.BigDecimal end_amount;
	String trans_type;
	String trans_type_name;
	String memo;
	String order_no;
	java.math.BigDecimal begin_freez_amount;
	java.math.BigDecimal trans_freez_aount;
	java.math.BigDecimal end_freez_amount;
	String wrong_uuid;
	String op_user_id;
	String op_user_name;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public java.util.Date getOp_time() {
		return op_time;
	}
	public void setOp_time(java.util.Date op_time) {
		this.op_time = op_time;
	}
	public java.math.BigDecimal getBegin_amount() {
		return begin_amount;
	}
	public void setBegin_amount(java.math.BigDecimal begin_amount) {
		this.begin_amount = begin_amount;
	}
	public java.math.BigDecimal getTrans_amount() {
		return trans_amount;
	}
	public void setTrans_amount(java.math.BigDecimal trans_amount) {
		this.trans_amount = trans_amount;
	}
	public java.math.BigDecimal getEnd_amount() {
		return end_amount;
	}
	public void setEnd_amount(java.math.BigDecimal end_amount) {
		this.end_amount = end_amount;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public String getTrans_type_name() {
		return trans_type_name;
	}
	public void setTrans_type_name(String trans_type_name) {
		this.trans_type_name = trans_type_name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public java.math.BigDecimal getBegin_freez_amount() {
		return begin_freez_amount;
	}
	public void setBegin_freez_amount(java.math.BigDecimal begin_freez_amount) {
		this.begin_freez_amount = begin_freez_amount;
	}
	public java.math.BigDecimal getTrans_freez_aount() {
		return trans_freez_aount;
	}
	public void setTrans_freez_aount(java.math.BigDecimal trans_freez_aount) {
		this.trans_freez_aount = trans_freez_aount;
	}
	public java.math.BigDecimal getEnd_freez_amount() {
		return end_freez_amount;
	}
	public void setEnd_freez_amount(java.math.BigDecimal end_freez_amount) {
		this.end_freez_amount = end_freez_amount;
	}
	public String getWrong_uuid() {
		return wrong_uuid;
	}
	public void setWrong_uuid(String wrong_uuid) {
		this.wrong_uuid = wrong_uuid;
	}

}
