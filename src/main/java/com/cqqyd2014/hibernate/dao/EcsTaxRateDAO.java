package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public final class EcsTaxRateDAO {
	public static java.util.ArrayList<com.cqqyd2014.hibernate.entities.EcsTaxRate> getEcs(Session session,String hs_code){
		String hql="from EcsTaxRate where hsCode=:hs_code";
		Query q = session.createQuery(hql);
		q.setParameter("hs_code", hs_code);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.EcsTaxRate> etrs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.EcsTaxRate>)q.list();
		return etrs;
	}
	//得到一个商品的增值税税率，消费税税率，和净值率
	public static java.util.ArrayList<java.math.BigDecimal> getRegTax(Session session,String hs_code){
		String hql="from EcsTaxRate where hsCode=:hs_code";
		Query q = session.createQuery(hql);
		q.setParameter("hs_code", hs_code);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.EcsTaxRate> etrs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.EcsTaxRate>)q.list();
		com.cqqyd2014.hibernate.entities.EcsTaxRate etr=etrs.get(0);
		
		java.math.BigDecimal reg_rate=etr.getRegRate();
		java.math.BigDecimal tax_rate=etr.getTaxRate();
		java.math.BigDecimal reg_rate_out=new java.math.BigDecimal(1).divide(new java.math.BigDecimal(1).add(tax_rate),4).multiply(reg_rate);
		java.math.BigDecimal tax_rate_out=new java.math.BigDecimal(1).divide(new java.math.BigDecimal(1).add(tax_rate),4).multiply(tax_rate);
		java.math.BigDecimal goods_out=new java.math.BigDecimal(1).subtract(reg_rate_out).subtract(tax_rate_out);
		java.util.ArrayList<java.math.BigDecimal> bds=new java.util.ArrayList<>();
		bds.add(tax_rate_out);
		bds.add(reg_rate_out);
		bds.add(goods_out);
		return bds;
	}

}
