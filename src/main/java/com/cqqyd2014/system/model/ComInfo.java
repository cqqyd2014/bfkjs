package com.cqqyd2014.system.model;

public class ComInfo {
	String com_code;
	String com_name;
	boolean effective;
	String contract_name;
	String order_head;
	String message_mail;
	public String getCom_code() {
		return com_code;
	}
	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public String getContract_name() {
		return contract_name;
	}
	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}
	public String getOrder_head() {
		return order_head;
	}
	public void setOrder_head(String order_head) {
		this.order_head = order_head;
	}
	public String getMessage_mail() {
		return message_mail;
	}
	public void setMessage_mail(String message_mail) {
		this.message_mail = message_mail;
	}
	public String getWarning_mail() {
		return warning_mail;
	}
	public void setWarning_mail(String warning_mail) {
		this.warning_mail = warning_mail;
	}
	public String getService_tell() {
		return service_tell;
	}
	public void setService_tell(String service_tell) {
		this.service_tell = service_tell;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	String warning_mail;
	String service_tell;
	String seq;
	

}
