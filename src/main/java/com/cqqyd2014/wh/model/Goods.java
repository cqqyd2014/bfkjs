package com.cqqyd2014.wh.model;

public class Goods {
	String contract_no;
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	String maker;
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	String into_wh_uuid;
	public String getInto_wh_uuid() {
		return into_wh_uuid;
	}
	public void setInto_wh_uuid(String into_wh_uuid) {
		this.into_wh_uuid = into_wh_uuid;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	java.util.Date create_dat;
	
	public java.util.Date getCreate_dat() {
		return create_dat;
	}
	public void setCreate_dat(java.util.Date create_dat) {
		this.create_dat = create_dat;
	}
	boolean printed;
	public boolean isPrinted() {
		return printed;
	}
	public void setPrinted(boolean printed) {
		this.printed = printed;
	}
	String uneffective_userid;
	java.util.Date uneffective_dat;
	
	public String getUneffective_userid() {
		return uneffective_userid;
	}
	public void setUneffective_userid(String uneffective_userid) {
		this.uneffective_userid = uneffective_userid;
	}
	public java.util.Date getUneffective_dat() {
		return uneffective_dat;
	}
	public void setUneffective_dat(java.util.Date uneffective_dat) {
		this.uneffective_dat = uneffective_dat;
	}
	String supply_id;
	String supply_name;
	
	public String getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}
	public String getSupply_name() {
		return supply_name;
	}
	public void setSupply_name(String supply_name) {
		this.supply_name = supply_name;
	}
	java.util.Date contract_paper_dat;
	
	public java.util.Date getContract_paper_dat() {
		return contract_paper_dat;
	}
	public void setContract_paper_dat(java.util.Date contract_paper_dat) {
		this.contract_paper_dat = contract_paper_dat;
	}
	java.util.Date in_dat;
	
	
	public java.util.Date getIn_dat() {
		return in_dat;
	}
	public void setIn_dat(java.util.Date in_dat) {
		this.in_dat = in_dat;
	}
	java.math.BigDecimal contract_price;
	
	
	public java.math.BigDecimal getContract_price() {
		return contract_price;
	}
	public void setContract_price(java.math.BigDecimal contract_price) {
		this.contract_price = contract_price;
	}
	String storage_id;
	String storage_name;
	
	public String getStorage_id() {
		return storage_id;
	}
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}
	public String getStorage_name() {
		return storage_name;
	}
	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
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
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
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
	java.math.BigDecimal gross_weight;
	public java.math.BigDecimal getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(java.math.BigDecimal gross_weight) {
		this.gross_weight = gross_weight;
	}
	
	java.math.BigDecimal net_weight;
	java.math.BigDecimal package_weight;
	public java.math.BigDecimal getNet_weight() {
		return net_weight;
	}
	public void setNet_weight(java.math.BigDecimal net_weight) {
		this.net_weight = net_weight;
	}
	public java.math.BigDecimal getPackage_weight() {
		return package_weight;
	}
	public void setPackage_weight(java.math.BigDecimal package_weight) {
		this.package_weight = package_weight;
	}
	boolean effective;
	boolean not_air;
	public boolean isNot_air() {
		return not_air;
	}
	public void setNot_air(boolean not_air) {
		this.not_air = not_air;
	}
	String barcode;

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


	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	String country_id;
	String country_name;
	String unit;
	String unit_code;
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}

	String memo;
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	String spec;
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	String sn_code;
	public String getSn_code() {
		return sn_code;
	}
	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}
	

}
