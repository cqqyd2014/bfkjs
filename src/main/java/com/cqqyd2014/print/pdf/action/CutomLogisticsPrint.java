package com.cqqyd2014.print.pdf.action;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.cqqyd2014.express.common.ExpressBillModel;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

public class CutomLogisticsPrint extends PrintLogisticsBilUsingPDF{
	String sender;
	String sender_com;
	String sender_addr;
	String sender_tel;
	String something;
	String receiver;
	String receiver_addr;
	String receiver_tel;
	String sign;



	public String getSender() {
		return sender;
	}



	public void setSender(String sender) {
		this.sender = sender;
	}



	public String getSender_com() {
		return sender_com;
	}



	public void setSender_com(String sender_com) {
		this.sender_com = sender_com;
	}



	public String getSender_addr() {
		return sender_addr;
	}



	public void setSender_addr(String sender_addr) {
		this.sender_addr = sender_addr;
	}



	public String getSender_tel() {
		return sender_tel;
	}



	public void setSender_tel(String sender_tel) {
		this.sender_tel = sender_tel;
	}



	public String getSomething() {
		return something;
	}



	public void setSomething(String something) {
		this.something = something;
	}



	public String getReceiver() {
		return receiver;
	}



	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}



	public String getReceiver_addr() {
		return receiver_addr;
	}



	public void setReceiver_addr(String receiver_addr) {
		this.receiver_addr = receiver_addr;
	}



	public String getReceiver_tel() {
		return receiver_tel;
	}



	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}



	public String getSign() {
		return sign;
	}



	public void setSign(String sign) {
		this.sign = sign;
	}



	public ExpressBillModel[] initializeBeanArray() {
		ExpressBillModel[] reportRows = new ExpressBillModel[1];
		
		this.file_name="自定义快递单";
		
			
			String print_time = com.cqqyd2014.util.DateUtil.JDateToSimpleString(new java.util.Date());
			/*
			reportRows[0] = new ExpressBill(receiver,
					receiver_addr,
					receiver_tel, sender, sender_com,
					sender_addr, sender_tel, something, print_time);
					*/
		
		return reportRows;
	}

}