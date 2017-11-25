package com.cqqyd2014.test.run;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

public class TestDb {

	public TestDb() {
		super();
		// TODO Auto-generated constructor stub
		
		String str=com.cqqyd2014.util.DateUtil.getDistanceChinese(new java.util.Date(), com.cqqyd2014.util.DateUtil.ShortStringToJDate("2017-1-1"));
		System.out.println(str);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestDb();
	}

}
