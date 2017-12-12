package com.cqqyd2014.weixin.model;

public class ScanQrLog {
	boolean effective;
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	String ip;
	String barcode;
	java.util.Date scan_dat;
	String com_id;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public java.util.Date getScan_dat() {
		return scan_dat;
	}
	public void setScan_dat(java.util.Date scan_dat) {
		this.scan_dat = scan_dat;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

}
