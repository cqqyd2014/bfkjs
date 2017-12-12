package com.cqqyd2014.bfkjs.statis.model;

public class MonthItem {
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public java.math.BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}
	public java.math.BigDecimal getQty() {
		return qty;
	}
	public void setQty(java.math.BigDecimal qty) {
		this.qty = qty;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public java.math.BigDecimal getTotal() {
		return total;
	}
	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}
	int no;
	String orderNo;
	java.math.BigDecimal amount;
	java.math.BigDecimal qty;
	java.util.Date time;
	java.math.BigDecimal total;
	String orderMemo;
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	
}
