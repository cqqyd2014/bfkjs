package com.cqqyd2014.hibernate.dao;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.entities.*;

public class ReturnTxtDAO {
	public static String getReturnTxt(Session session,String par,String com_id){
		String re=null;
		
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.WxReturnTxt> rs = null;
			String hql = "from WxReturnTxt where txtId=:txt and comId=:com_id";

			Query q = session.createQuery(hql);
			q.setParameter("txt", par);
			q.setParameter("com_id", com_id);
			rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.WxReturnTxt>) q
					.list();
			if (rs.size()==0){
				re=null;
			}
			else{
				re=rs.get(0).getTxtText();
			}

			//session.flush();
			//session.clear();
			//tx.commit();
		
			return re;
	}

}
