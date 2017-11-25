package com.cqqyd2014.finance.model;

public class FinanceGoodsPrice {
	boolean effective_now;
	boolean in_the_futurn;
	boolean out_date;
	public boolean isEffective_now() {
		return effective_now;
	}
	public void setEffective_now(boolean effective_now) {
		this.effective_now = effective_now;
	}
	public boolean isIn_the_futurn() {
		return in_the_futurn;
	}
	public void setIn_the_futurn(boolean in_the_futurn) {
		this.in_the_futurn = in_the_futurn;
	}
	public boolean isOut_date() {
		return out_date;
	}
	public void setOut_date(boolean out_date) {
		this.out_date = out_date;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	String goods_id;
	String goods_name;
	String unit;
	java.util.Date start_dat;
	java.util.Date end_dat;
	java.math.BigDecimal price;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public java.util.Date getStart_dat() {
		return start_dat;
	}
	public void setStart_dat(java.util.Date start_dat) {
		this.start_dat = start_dat;
	}
	public java.util.Date getEnd_dat() {
		return end_dat;
	}
	public void setEnd_dat(java.util.Date end_dat) {
		this.end_dat = end_dat;
	}
	public java.math.BigDecimal getPrice() {
		return price;
	}
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}


}
