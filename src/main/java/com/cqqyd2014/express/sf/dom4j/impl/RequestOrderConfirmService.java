package com.cqqyd2014.express.sf.dom4j.impl;

import org.dom4j.Element;

import com.cqqyd2014.express.sf.dom4j.common.Request;

public class RequestOrderConfirmService  extends Request{

	String dealtype;
	public String getDealtype() {
		return dealtype;
	}
	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}
	@Override
	public void make_body() {
		// TODO Auto-generated method stub
		
		Element order_e=body.addElement("OrderConfirm");
		
		order_e.addAttribute("orderid", order.getOrder_no()+"_"+db.getSeq());
		order_e.addAttribute("mailno", db.getExpress_no());
		order_e.addAttribute("dealtype",dealtype);
	}

}
