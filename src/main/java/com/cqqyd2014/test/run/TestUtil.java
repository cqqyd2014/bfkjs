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
import com.cqqyd2014.system.run.night.SystemClean;

public class TestUtil {

	public static void main(String[] args) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.DeliverMDAO vmdao=new com.cqqyd2014.hibernate.dao.DeliverMDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> vdms=vmdao.getArrayListEntity(session, "CQQY");
			com.cqqyd2014.hibernate.dao.VDeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			
			for (int i=0;i<vdms.size();i++) {
				System.out.println(i);
				com.cqqyd2014.hibernate.entities.DeliverM vdm=vdms.get(i);
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=dddao.getArrayListViewByOrderNo(session, "CQQY",vdm.getId().getOrderNo());
				java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(vdds);
				vdm.setMemoBarcodes(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(dbds, "getGoods_barcode"));
				vdm.setMemoNames(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(dbds, "getGoods_name"));
				session.saveOrUpdate(vdm);
			}

			
			
			
			tx.commit();
		}

		catch (Exception e) {
			if (null != tx) {
				tx.rollback();//

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {

			HibernateSessionFactory.closeSession();
		}
		
		
		
		
		
		
	}

	

}