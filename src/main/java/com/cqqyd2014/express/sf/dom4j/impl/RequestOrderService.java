package com.cqqyd2014.express.sf.dom4j.impl;

import org.dom4j.Element;

import com.cqqyd2014.express.sf.dom4j.common.Request;

public class RequestOrderService extends Request{

	@Override
	public void make_body() {
		// TODO Auto-generated method stub
		Element order_e=body.addElement("Order");
		
		order_e.addAttribute("orderid", order.getOrder_no()+"_"+db.getSeq());
		order_e.addAttribute("j_company", of.getSender_com());
		order_e.addAttribute("j_contact", of.getSender());
		order_e.addAttribute("j_tel", of.getSender_tell());
		order_e.addAttribute("j_mobile", of.getSender_tell());
		order_e.addAttribute("j_province", of.getSender_province());
		order_e.addAttribute("j_city", of.getSender_city());
		order_e.addAttribute("j_county", of.getSender_district());
		order_e.addAttribute("j_address", of.getSender_addr());
		order_e.addAttribute("d_company", order.getUser_com());
		order_e.addAttribute("d_contact", order.getUser_name());
		order_e.addAttribute("d_tel", order.getTell());
		order_e.addAttribute("d_mobile", order.getTell2());
		order_e.addAttribute("d_address", order.getUser_addr());
		order_e.addAttribute("d_province ", order.getProvince());
		order_e.addAttribute("d_city", order.getCity());
		order_e.addAttribute("d_county", order.getDistrict());
		 
		if (order.getVehicle().equals("AIR_")) {
			order_e.addAttribute("express_type", "1");
		}
		else {
			order_e.addAttribute("express_type", "2");
		}
		order_e.addAttribute("pay_method", "1");
		order_e.addAttribute("parcel_quantity", "1");
		order_e.addAttribute("remark", order.getDetail_memo());
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=order.getDetails();
		for (int i=0;i<ods.size();i++) {
			com.cqqyd2014.order.model.OrderDetail od=ods.get(i);
			Element cargo=order_e.addElement("Cargo");
			cargo.addAttribute("name", od.getGoods_name());
			cargo.addAttribute("count", od.getNum().toString());
			cargo.addAttribute("unit", od.getUnit());
			cargo.addAttribute("amount", od.getTotal1().toString());
			
		}
		
	}

}
