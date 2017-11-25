package com.cqqyd2014.wh.model;

public class GoodsInfo {
	
	String sn_code;//代码，用在商品条码上
	String spec;
	String barcode;
	String com_id;
String short_name;
	public String getShort_name() {
	return short_name;
}
public void setShort_name(String short_name) {
	this.short_name = short_name;
}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSn_code() {
		return sn_code;
	}
	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	String memo;
	boolean not_air;
	String country_id;
	String country_name;

	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public boolean isNot_air() {
		return not_air;
	}
	public void setNot_air(boolean not_air) {
		this.not_air = not_air;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	boolean in_use;
	public boolean isIn_use() {
		return in_use;
	}
	public void setIn_use(boolean in_use) {
		this.in_use = in_use;
	}
	String goods_id;
	String goods_name;
	String hs_code;
	java.math.BigDecimal net_weight;
	java.math.BigDecimal gross_weight;
	java.math.BigDecimal package_weight;
	public java.math.BigDecimal getPackage_weight() {
		return package_weight;
	}
	public void setPackage_weight(java.math.BigDecimal package_weight) {
		this.package_weight = package_weight;
	}
	String unit_name;
	String unit_code;
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
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
	public String getHs_code() {
		return hs_code;
	}
	public void setHs_code(String hs_code) {
		this.hs_code = hs_code;
	}
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


	
	
}
