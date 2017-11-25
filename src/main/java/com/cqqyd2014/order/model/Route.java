package com.cqqyd2014.order.model;

public class Route {
	String remark;
	String mail_no;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMail_no() {
		return mail_no;
	}
	public void setMail_no(String mail_no) {
		this.mail_no = mail_no;
	}
	java.util.Date accept_time;
	String express_no;
	String accept_addr;
	public java.util.Date getAccept_time() {
		return accept_time;
	}
	public void setAccept_time(java.util.Date accept_time) {
		this.accept_time = accept_time;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public String getAccept_addr() {
		return accept_addr;
	}
	public void setAccept_addr(String accept_addr) {
		this.accept_addr = accept_addr;
	}

}
