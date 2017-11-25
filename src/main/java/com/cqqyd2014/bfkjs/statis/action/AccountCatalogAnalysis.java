package com.cqqyd2014.bfkjs.statis.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class AccountCatalogAnalysis extends ActionSupport implements 
SessionAware {
private Map<String, Object> session;


public void setSession(Map<String, Object> session) {
	// TODO Auto-generated method stub
	this.session = session;
}

	String startDateS;
	String endDateS;
	java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.ProductPriceCount> ppcs;
	java.math.BigDecimal all;
	

	public java.math.BigDecimal getAll() {
		return all;
	}

	public void setAll(java.math.BigDecimal all) {
		this.all = all;
	}

	public java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.ProductPriceCount> getPpcs() {
		return ppcs;
	}

	public void setPpcs(
			java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.ProductPriceCount> ppcs) {
		this.ppcs = ppcs;
	}

	public String getStartDateS() {
		return startDateS;
	}

	public void setStartDateS(String startDateS) {
		this.startDateS = startDateS;
	}

	public String getEndDateS() {
		return endDateS;
	}

	public void setEndDateS(String endDateS) {
		this.endDateS = endDateS;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String com_id = (String) this.session.get("com_code");
		java.util.Date startDate_d = com.cqqyd2014.util.DateUtil
				.FullStringToJDate(startDateS + " 00:00:00");
		java.util.Date endDate_d = com.cqqyd2014.util.DateUtil
				.FullStringToJDate(endDateS + " 23:59:59");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.VOrdermainDetailDAO vodao=new com.cqqyd2014.hibernate.dao.VOrdermainDetailDAO();
			ppcs = vodao.getSaleSum(session,com_id,
					startDate_d, endDate_d);
			vodao=null;
			all=new java.math.BigDecimal(0);
			
			for (int i=0;i<ppcs.size();i++){
				all=all.add(ppcs.get(i).getSum());
			}
			tx.commit();
		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();//

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ERROR;
		} finally {

			HibernateSessionFactory.closeSession();
		}
		return super.execute();
	}

}