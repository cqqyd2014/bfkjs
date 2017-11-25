package com.cqqyd2014.express.sf;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.express.common.ExpressBillModel;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.order.model.Order;

public class BillSf extends ExpressBillModel {
	public BillSf() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	String deliver_dat;

	public String getDeliver_dat() {
		return deliver_dat;
	}

	public void setDeliver_dat(String deliver_dat) {
		this.deliver_dat = deliver_dat;
	}

	String month_pay_account;
	//com.cqqyd2014.order.model.Order order;
	String e_flag;
	String express_no;
	String destination_code;
	String reciever_full;
	String sender_full;
	String package_detial;

	
	String business_type;
	


	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getE_flag() {
		return e_flag;
	}

	public void setE_flag(String e_flag) {
		this.e_flag = e_flag;
	}

	public String getExpress_no() {
		return express_no;
	}

	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}

	public String getDestination_code() {
		return destination_code;
	}

	public void setDestination_code(String destination_code) {
		this.destination_code = destination_code;
	}

	public String getReciever_full() {
		return reciever_full;
	}

	public void setReciever_full(String reciever_full) {
		this.reciever_full = reciever_full;
	}

	public String getSender_full() {
		return sender_full;
	}

	public void setSender_full(String sender_full) {
		this.sender_full = sender_full;
	}

	public String getPackage_detial() {
		return package_detial;
	}

	public void setPackage_detial(String package_detial) {
		this.package_detial = package_detial;
	}




	public String getMonth_pay_account() {
		return month_pay_account;
	}

	public void setMonth_pay_account(String month_pay_account) {
		this.month_pay_account = month_pay_account;
	}

	@Override
	public void CustomInformation(Session session) {
		// TODO Auto-generated method stub
		/*
		 * business_type
		1：表示“标准快递”
		 2：表示“顺丰特惠”
		 5：表示“顺丰次晨”
		 6：表示“即日件”
		*/
		if (db.getVehicle_id().equals("CAR_")||db.getVehicle_id().equals("SHIP")){
			setBusiness_type("顺丰特惠");
			setE_flag("E");
		}
		else{
			setBusiness_type("标准快递");
			setE_flag("");
		}
		com.cqqyd2014.hibernate.dao.SfResponseOrderBackDAO sdao=new com.cqqyd2014.hibernate.dao.SfResponseOrderBackDAO();
		com.cqqyd2014.hibernate.entities.SfResponseOrderBack sfrob=sdao.getEntityByOrderSeq(session, db.getOrder_no(), db.getSeq());
		setDeliver_dat(com.cqqyd2014.util.DateUtil.JDateToSimpleString(new java.util.Date()));
		setDestination_code(sfrob.getDestcode());
		setExpress_no(sfrob.getMailno());
		setPackage_memo(order.getDetail_memo());
		setPackage_detial(order.getDetail_memo());
		setMonth_pay_account(sfrob.getCustId());

		
		
	}



}
