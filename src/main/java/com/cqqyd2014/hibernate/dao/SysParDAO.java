package com.cqqyd2014.hibernate.dao;
import org.hibernate.Query;
import org.hibernate.Session;

public final class SysParDAO {
	
	public static String getStringValueByCode(Session session, String code){
		com.cqqyd2014.hibernate.entities.SysPar sp=getEntityByCode(session,code);
		return sp.getValue();
	}
	public static java.math.BigDecimal getDecValueByCode(Session session, String code){
		
		return new java.math.BigDecimal(getStringValueByCode(session,code));
	}
	
	
	private static com.cqqyd2014.hibernate.entities.SysPar getEntityByCode(Session session, String code) {
		String hql = "from SysPar where code=:code";

		Query q = session.createQuery(hql);
		q.setParameter("code", code);
		
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar> sws = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar>) q
				.list();
		if (sws.size() == 0) {
			System.out.println("不能得到SysPar数据，参数code:" + code);
			return null;
		} else {
			return sws.get(0);
		}
	}
	

	
	

	


}