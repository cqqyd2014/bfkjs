package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class AddGoodsInit extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
	
	java.util.HashMap unitList=new java.util.LinkedHashMap();
	
	public java.util.HashMap getUnitList() {
		return unitList;
	}

	public void setUnitList(java.util.HashMap unitList) {
		this.unitList = unitList;
	}


	String defaultUnit;
	public String getDefaultUnit() {
		return defaultUnit;
	}

	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	String c_declare_unit;
	String c_law_unit;
	String c_bonded_count_unit;
	int c_law_convert;
	int c_bonded_convert;
	@Override
	public String execute() throws Exception {

				String user_name = (String) this.session.get("USER_NAME");
				String user_id = (String) this.session.get("USER_ID");
				String com_id=(String) this.session.get("com_code");
				
				Session session = HibernateSessionFactory.getSession();
				Transaction tx = session.beginTransaction();
				try {
					
					com.cqqyd2014.hibernate.dao.ComParCodeDAO cpcdao=new com.cqqyd2014.hibernate.dao.ComParCodeDAO();
					defaultUnit=cpcdao.getValue(session, com_id, "default_unit");
					//System.out.println(defaultUnit);
					c_declare_unit=defaultUnit;
					c_law_unit=defaultUnit;
					c_bonded_count_unit=defaultUnit;
					c_law_convert=1;
					c_bonded_convert=1;
					com.cqqyd2014.hibernate.dao.SysCodeDAO scdao=new com.cqqyd2014.hibernate.dao.SysCodeDAO();
					unitList=scdao.getValuesMap(session, "unit");

					tx.commit();
				}
				catch (HibernateException e) {
					if (null != tx) {
						tx.rollback();// 撤销事务

					}
					System.out.println(e.getMessage());
					e.printStackTrace();
					
					
				} finally {
					HibernateSessionFactory.closeSession();
				}
		
		
		
		return super.execute();
	}

	public String getC_law_unit() {
		return c_law_unit;
	}

	public void setC_law_unit(String c_law_unit) {
		this.c_law_unit = c_law_unit;
	}

	public String getC_bonded_count_unit() {
		return c_bonded_count_unit;
	}

	public void setC_bonded_count_unit(String c_bonded_count_unit) {
		this.c_bonded_count_unit = c_bonded_count_unit;
	}

	public int getC_law_convert() {
		return c_law_convert;
	}

	public void setC_law_convert(int c_law_convert) {
		this.c_law_convert = c_law_convert;
	}

	public int getC_bonded_convert() {
		return c_bonded_convert;
	}

	public void setC_bonded_convert(int c_bonded_convert) {
		this.c_bonded_convert = c_bonded_convert;
	}

	public String getC_declare_unit() {
		return c_declare_unit;
	}

	public void setC_declare_unit(String c_declare_unit) {
		this.c_declare_unit = c_declare_unit;
	}

}
