package com.cqqyd2014.print.pdf.action;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.cqqyd2014.express.common.ExpressBillModel;
import com.cqqyd2014.express.sf.BillSf;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@SuppressWarnings("serial")
public class SfLogisticsElecPrint extends PrintLogisticsBilUsingPDF{
	String order_no;
	String seq;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Override
	public ExpressBillModel[] initializeBeanArray() throws Exception {
		// TODO Auto-generated method stub
		BillSf[] reportRows = new BillSf[1];
		Session session = HibernateSessionFactory.getSession();
		this.file_name=order_no+"快递单";
		try {
			com.cqqyd2014.hibernate.dao.VOrderMainDAO omdao = new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain om = omdao.getVOrderMain(session, com_id, order_no);
			com.cqqyd2014.hibernate.dao.VDeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			com.cqqyd2014.hibernate.entities.VDeliverM dm=dmdao.getEntityViewByOrderNoSeq(session, order_no, seq, com_id);
			
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getModelFromView(om);
			
			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(dm);
			
			BillSf bs=new BillSf();
			bs.transOrderDeliverModelToExpressBillModel(session, order, db);
			
			reportRows[0] = bs;
			/*
			(om.getCUserName(),
					om.getAddrProvince() + ' ' + om.getAddrCity() + ' ' + om.getAddrDistrict() + ' '
							+ om.getCUserAddr(),
					om.getCTell(), spdao.getExpressSender(session), spdao.getExpressCom(session),
					spdao.getExpressAddr(session), spdao.getExpressTell(session), com.cqqyd2014.util.StringUtil.getLogisticsMemo(om.getDetailMemo()), print_time);
					*/
		} catch (HibernateException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {

			HibernateSessionFactory.closeSession();
		}
		return reportRows;
	}

}
