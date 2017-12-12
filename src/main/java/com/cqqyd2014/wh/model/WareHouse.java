package com.cqqyd2014.wh.model;

public class WareHouse {
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
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
	String wh_id;
	String wh_name;
	java.util.ArrayList<com.cqqyd2014.wh.model.Storage>  ss;
	public java.util.ArrayList<com.cqqyd2014.wh.model.Storage> getSs() {
		return ss;
	}
	public void setSs(java.util.ArrayList<com.cqqyd2014.wh.model.Storage> ss) {
		this.ss = ss;
	}

}
