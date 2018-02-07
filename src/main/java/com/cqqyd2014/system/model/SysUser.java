package com.cqqyd2014.system.model;

public class SysUser {
	String login_name;
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	String tell;
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	java.util.Date last_modify_dat;
	String last_modify_uuid;
	public java.util.Date getLast_modify_dat() {
		return last_modify_dat;
	}
	public void setLast_modify_dat(java.util.Date last_modify_dat) {
		this.last_modify_dat = last_modify_dat;
	}
	public String getLast_modify_uuid() {
		return last_modify_uuid;
	}
	public void setLast_modify_uuid(String last_modify_uuid) {
		this.last_modify_uuid = last_modify_uuid;
	}
	public java.util.Date getCreate_dat() {
		return create_dat;
	}
	public void setCreate_dat(java.util.Date create_dat) {
		this.create_dat = create_dat;
	}
	public String getCreate_dat_print() {
		return create_dat_print;
	}
	public void setCreate_dat_print(String create_dat_print) {
		this.create_dat_print = create_dat_print;
	}
	public String getCreate_dat_chinese_print() {
		return create_dat_chinese_print;
	}
	public void setCreate_dat_chinese_print(String create_dat_chinese_print) {
		this.create_dat_chinese_print = create_dat_chinese_print;
	}
	public String getPassword_md5() {
		return password_md5;
	}
	public void setPassword_md5(String password_md5) {
		this.password_md5 = password_md5;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public String getEffective_chinese_print() {
		return effective_chinese_print;
	}
	public void setEffective_chinese_print(String effective_chinese_print) {
		this.effective_chinese_print = effective_chinese_print;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public java.util.Date getLast_online_dat() {
		return last_online_dat;
	}
	public void setLast_online_dat(java.util.Date last_online_dat) {
		this.last_online_dat = last_online_dat;
	}
	public String getLast_online_dat_print() {
		return last_online_dat_print;
	}
	public void setLast_online_dat_print(String last_online_dat_print) {
		this.last_online_dat_print = last_online_dat_print;
	}
	public String getLast_online_dat_chinese_print() {
		return last_online_dat_chinese_print;
	}
	public void setLast_online_dat_chinese_print(String last_online_dat_chinese_print) {
		this.last_online_dat_chinese_print = last_online_dat_chinese_print;
	}
	public java.math.BigDecimal getPickup_weighting() {
		return pickup_weighting;
	}
	public void setPickup_weighting(java.math.BigDecimal pickup_weighting) {
		this.pickup_weighting = pickup_weighting;
	}
	public java.math.BigDecimal getSend_wetighting() {
		return send_wetighting;
	}
	public void setSend_wetighting(java.math.BigDecimal send_wetighting) {
		this.send_wetighting = send_wetighting;
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
	public java.math.BigDecimal getQuota_subtract() {
		return quota_subtract;
	}
	public void setQuota_subtract(java.math.BigDecimal quota_subtract) {
		this.quota_subtract = quota_subtract;
	}
	public java.math.BigDecimal getQuota_freez() {
		return quota_freez;
	}
	public void setQuota_freez(java.math.BigDecimal quota_freez) {
		this.quota_freez = quota_freez;
	}
	java.util.Date create_dat;
	String create_dat_print;
	String create_dat_chinese_print;
	String password_md5;
	boolean effective;
	String effective_chinese_print;
	boolean online;
	java.util.Date last_online_dat;
	String last_online_dat_print;
	String last_online_dat_chinese_print;
	java.math.BigDecimal pickup_weighting;
	java.math.BigDecimal send_wetighting;
	java.math.BigDecimal quota_current;
	java.math.BigDecimal quota_add;
	java.math.BigDecimal quota_subtract;
	java.math.BigDecimal quota_freez;
	
	String user_id;
	String user_name;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	

}
