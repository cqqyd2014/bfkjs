package com.cqqyd2014.system.model;

public class MenuM {
	String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	String m_id;
	String m_name;
	String m_desc;
	java.util.ArrayList<MenuD> menu_d;
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_desc() {
		return m_desc;
	}
	public void setM_desc(String m_desc) {
		this.m_desc = m_desc;
	}
	public java.util.ArrayList<MenuD> getMenu_d() {
		return menu_d;
	}
	public void setMenu_d(java.util.ArrayList<MenuD> menu_d) {
		this.menu_d = menu_d;
	} 
	java.util.ArrayList<MenuD> mds;
	public java.util.ArrayList<MenuD> getMds() {
		return mds;
	}
	public void setMds(java.util.ArrayList<MenuD> mds) {
		this.mds = mds;
	}
}

