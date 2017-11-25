package com.cqqyd2014.order.model;

public class OrderDetail {
	
	java.util.Date order_detail_dat;

	public java.util.Date getOrder_detail_dat() {
		return order_detail_dat;
	}
	public void setOrder_detail_dat(java.util.Date order_detail_dat) {
		this.order_detail_dat = order_detail_dat;
	}
	//增值税
	java.math.BigDecimal tax;
	//消费税
	java.math.BigDecimal reg_tax;
	
	public java.math.BigDecimal getTax() {
		return tax;
	}
	public void setTax(java.math.BigDecimal tax) {
		this.tax = tax;
	}
	public java.math.BigDecimal getReg_tax() {
		return reg_tax;
	}
	public void setReg_tax(java.math.BigDecimal reg_tax) {
		this.reg_tax = reg_tax;
	}
	java.math.BigDecimal price2;
	public java.math.BigDecimal getPrice2() {
		return price2;
	}
	public void setPrice2(java.math.BigDecimal price2) {
		this.price2 = price2;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	String order_no;
	String goods_id;
	String goods_name;
	java.math.BigDecimal num;
	java.math.BigDecimal price;
	
	//原始成交金额
	java.math.BigDecimal total1;
	//记账成交金额，计入存货和成本的金额
	java.math.BigDecimal total2;
	boolean not_air;
	public boolean isNot_air() {
		return not_air;
	}
	public void setNot_air(boolean not_air) {
		this.not_air = not_air;
	}
	public java.math.BigDecimal getTotal1() {
		return total1;
	}
	public void setTotal1(java.math.BigDecimal total1) {
		this.total1 = total1;
	}
	public java.math.BigDecimal getTotal2() {
		return total2;
	}
	public void setTotal2(java.math.BigDecimal total2) {
		this.total2 = total2;
	}
	String memo;
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	String detail_id;
	String unit;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public java.math.BigDecimal getNum() {
		return num;
	}
	public void setNum(java.math.BigDecimal num) {
		this.num = num;
	}
	public java.math.BigDecimal getPrice() {
		return price;
	}
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}

}
