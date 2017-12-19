package com.cqqyd2014.print.pdf.action;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.cqqyd2014.express.common.ExpressBillModel;
import com.cqqyd2014.express.sf.BillSf;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.print.pdf.NormalBill;

public class OrderLogisticsPrint extends PrintLogisticsBilUsingPDF{
	String order_no;





	public NormalBill[] initializeBeanArray() throws Exception {
		NormalBill[] reportRows = new NormalBill[1];
		Session session = HibernateSessionFactory.getSession();
		this.file_name=order_no+"快递单";
		try {
			com.cqqyd2014.hibernate.dao.VOrderMainDAO omdao = new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain om = omdao.getVOrderMain(session, com_id, order_no);
			com.cqqyd2014.hibernate.dao.VDeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();

			
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getModelFromView(om);
			NormalBill bs=new NormalBill();
			bs.transOrderDeliverModelToExpressBillModel(session, order, null);
			reportRows[0] = bs;
		} catch (HibernateException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {

			HibernateSessionFactory.closeSession();
		}
		return reportRows;
	}





	public String getOrder_no() {
		return order_no;
	}





	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

}