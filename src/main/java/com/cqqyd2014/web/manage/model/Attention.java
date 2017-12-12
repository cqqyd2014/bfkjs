package com.cqqyd2014.web.manage.model;

public class Attention {
	String m_id;
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public java.math.BigDecimal getCount() {
		return count;
	}
	public void setCount(java.math.BigDecimal count) {
		this.count = count;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	java.math.BigDecimal count;
	String user_id;
}
