package com.cqqyd2014.test.run;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cqqyd2014.express.sf.BillSf;
import com.cqqyd2014.express.sf.bsp.BspHttpClient;
import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientOrder;
import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientOrderConfirm;
import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientRoute;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.order.logic.OrderLogic;
import com.cqqyd2014.order.model.Order;


public class TestUtil {

	public static void main(String[] args) throws Exception {
		java.math.BigDecimal a=new java.math.BigDecimal(0);
		Class<? extends Object> clazz = a.getClass();
		System.out.println(clazz.getName());
		
		
		
		
		
	}

	

}