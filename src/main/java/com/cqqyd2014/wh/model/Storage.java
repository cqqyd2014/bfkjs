package com.cqqyd2014.wh.model;

public class Storage {
	String wh_id;
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
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	String s_id;
	String s_name;
	java.util.ArrayList<com.cqqyd2014.wh.model.StorageDetail> sds;
	public java.util.ArrayList<com.cqqyd2014.wh.model.StorageDetail> getSds() {
		return sds;
	}
	public void setSds(java.util.ArrayList<com.cqqyd2014.wh.model.StorageDetail> sds) {
		this.sds = sds;
	}

}
