package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class FinanceAccountDAO {
	
	
	//科目列表
	
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceAccount> getAccountsAll(Session session,String com_id,String book_id){
		String hql="from FinanceAccount where id.comId=:com_id and id.bookId=:book_id";
		Query query = session.createQuery(hql);
		query.setParameter("book_id", book_id);
		query.setParameter("com_id", com_id);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.FinanceAccount>)query.list();
	}

}
