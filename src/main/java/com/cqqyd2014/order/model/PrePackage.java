package com.cqqyd2014.order.model;

public class PrePackage {

	String com_id;
	String pakcage_barcode;
	java.util.Date create_dat;
	boolean effective;
	boolean packageed;
	java.util.Date package_dat;
	boolean sended;
	java.util.Date send_dat;
	java.util.Date unpackage_dat;
	String create_userid;
	String package_userid;
	String unpackage_userid;
	boolean printed;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getPakcage_barcode() {
		return pakcage_barcode;
	}
	public void setPakcage_barcode(String pakcage_barcode) {
		this.pakcage_barcode = pakcage_barcode;
	}
	public java.util.Date getCreate_dat() {
		return create_dat;
	}
	public void setCreate_dat(java.util.Date create_dat) {
		this.create_dat = create_dat;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public boolean isPackageed() {
		return packageed;
	}
	public void setPackageed(boolean packageed) {
		this.packageed = packageed;
	}
	public java.util.Date getPackage_dat() {
		return package_dat;
	}
	public void setPackage_dat(java.util.Date package_dat) {
		this.package_dat = package_dat;
	}
	public boolean isSended() {
		return sended;
	}
	public void setSended(boolean sended) {
		this.sended = sended;
	}
	public java.util.Date getSend_dat() {
		return send_dat;
	}
	public void setSend_dat(java.util.Date send_dat) {
		this.send_dat = send_dat;
	}
	public java.util.Date getUnpackage_dat() {
		return unpackage_dat;
	}
	public void setUnpackage_dat(java.util.Date unpackage_dat) {
		this.unpackage_dat = unpackage_dat;
	}
	public String getCreate_userid() {
		return create_userid;
	}
	public void setCreate_userid(String create_userid) {
		this.create_userid = create_userid;
	}
	public String getPackage_userid() {
		return package_userid;
	}
	public void setPackage_userid(String package_userid) {
		this.package_userid = package_userid;
	}
	public String getUnpackage_userid() {
		return unpackage_userid;
	}
	public void setUnpackage_userid(String unpackage_userid) {
		this.unpackage_userid = unpackage_userid;
	}
	public boolean isPrinted() {
		return printed;
	}
	public void setPrinted(boolean printed) {
		this.printed = printed;
	}
}
