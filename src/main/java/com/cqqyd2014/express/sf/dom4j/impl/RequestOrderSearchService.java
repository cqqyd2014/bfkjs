package com.cqqyd2014.express.sf.dom4j.impl;

import org.dom4j.Element;

import com.cqqyd2014.express.sf.dom4j.common.Request;

public class RequestOrderSearchService  extends Request{

	@Override
	public void make_body() {
		// TODO Auto-generated method stub
		
		Element order_e=body.addElement("OrderSearchService");
		
		order_e.addAttribute("orderid", order.getOrder_no()+"_"+db.getSeq());
		
	}

}
