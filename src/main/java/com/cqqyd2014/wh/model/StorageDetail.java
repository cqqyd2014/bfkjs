package com.cqqyd2014.wh.model;

public class StorageDetail {
	String wh_id;
	String s_id;
	String goods_id;
	java.math.BigDecimal avg;
	public java.math.BigDecimal getAvg() {
		return avg;
	}
	public void setAvg(java.math.BigDecimal avg) {
		this.avg = avg;
	}
	public String getWh_id() {
		return wh_id;
	}
	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public java.math.BigDecimal getNum() {
		return num;
	}
	
	public void setNum(java.math.BigDecimal num) {
		this.num = num;
	}
	java.math.BigDecimal num;
	java.math.BigDecimal value;
	
	public java.math.BigDecimal getValue() {
		return value;
	}
	public void setValue(java.math.BigDecimal value) {
		this.value = value;
	}
	String goods_name;
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

}
