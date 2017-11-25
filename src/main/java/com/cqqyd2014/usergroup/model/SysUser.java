package com.cqqyd2014.usergroup.model;

public class SysUser {
	String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	boolean on_line;
	public boolean isOn_line() {
		return on_line;
	}
	public void setOn_line(boolean on_line) {
		this.on_line = on_line;
	}
	String display_name;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	String uuid;
	String login_user;
	String password;
	String password_md5;
	String com_id;
	java.util.Date create_date;
	java.util.Date last_online_dat;
	String tell;
	boolean effective;
	java.math.BigDecimal pickup_weighting;
	java.math.BigDecimal send_weighting;
	java.math.BigDecimal quota_current;
	java.math.BigDecimal quota_add;
	java.math.BigDecimal quota_substract;
	java.math.BigDecimal quota_freez;
	
	public java.util.Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(java.util.Date create_date) {
		this.create_date = create_date;
	}
	public java.util.Date getLast_online_dat() {
		return last_online_dat;
	}
	public void setLast_online_dat(java.util.Date last_online_dat) {
		this.last_online_dat = last_online_dat;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public java.math.BigDecimal getPickup_weighting() {
		return pickup_weighting;
	}
	public void setPickup_weighting(java.math.BigDecimal pickup_weighting) {
		this.pickup_weighting = pickup_weighting;
	}
	public java.math.BigDecimal getSend_weighting() {
		return send_weighting;
	}
	public void setSend_weighting(java.math.BigDecimal send_weighting) {
		this.send_weighting = send_weighting;
	}
	public java.math.BigDecimal getQuota_current() {
		return quota_current;
	}
	public void setQuota_current(java.math.BigDecimal quota_current) {
		this.quota_current = quota_current;
	}
	public java.math.BigDecimal getQuota_add() {
		return quota_add;
	}
	public void setQuota_add(java.math.BigDecimal quota_add) {
		this.quota_add = quota_add;
	}
	public java.math.BigDecimal getQuota_substract() {
		return quota_substract;
	}
	public void setQuota_substract(java.math.BigDecimal quota_substract) {
		this.quota_substract = quota_substract;
	}
	public java.math.BigDecimal getQuota_freez() {
		return quota_freez;
	}
	public void setQuota_freez(java.math.BigDecimal quota_freez) {
		this.quota_freez = quota_freez;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getLogin_user() {
		return login_user;
	}
	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword_md5() {
		return password_md5;
	}
	public void setPassword_md5(String password_md5) {
		this.password_md5 = password_md5;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

}
