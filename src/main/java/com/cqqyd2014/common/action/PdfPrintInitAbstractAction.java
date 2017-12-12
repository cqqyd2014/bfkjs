package com.cqqyd2014.common.action;

import org.hibernate.Session;

import com.cqqyd2014.common.print.action.PdfPrintAbstractAction;

public abstract class PdfPrintInitAbstractAction extends PdfPrintAbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String jquery_version;
	String easyi_version;
	String temp_save_time;
	public Session session;
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getTemp_save_time() {
		return temp_save_time;
	}

	public void setTemp_save_time(String temp_save_time) {
		this.temp_save_time = temp_save_time;
	}

	public String getEasyi_version() {
		return easyi_version;
	}

	public void setEasyi_version(String easyi_version) {
		this.easyi_version = easyi_version;
	}

	public String getJquery_ui_version() {
		return jquery_ui_version;
	}

	public void setJquery_ui_version(String jquery_ui_version) {
		this.jquery_ui_version = jquery_ui_version;
	}

	String jquery_ui_version;

	public String getJquery_version() {
		return jquery_version;
	}

	public void setJquery_version(String jquery_version) {
		this.jquery_version = jquery_version;
	}

	
	public void init_js_par(Session session) {
	com.cqqyd2014.hibernate.dao.SysParDAO spdao=new com.cqqyd2014.hibernate.dao.SysParDAO();
	com.cqqyd2014.hibernate.entities.SysPar sp_jquery_ver=spdao.getEntityByCode(session, "jquery_ver");
		jquery_version=sp_jquery_ver.getValue();
		com.cqqyd2014.hibernate.entities.SysPar sp_jquery_ui_ver=spdao.getEntityByCode(session, "jquery_ui_ver");
		jquery_ui_version=sp_jquery_ui_ver.getValue();
		com.cqqyd2014.hibernate.entities.SysPar sp_easyui_ver=spdao.getEntityByCode(session, "easyui_ver");
		easyi_version=sp_easyui_ver.getValue();
		com.cqqyd2014.hibernate.entities.SysPar sp_temp_save_time=spdao.getEntityByCode(session, "temp_save_time");
		
		temp_save_time=sp_temp_save_time.getValue();
	}
	

}