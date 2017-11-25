package com.cqqyd2014.express.sf.dom4j.common;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public abstract class Request {
	public com.cqqyd2014.order.model.OrderFrom getOf() {
		return of;
	}
	public void setOf(com.cqqyd2014.order.model.OrderFrom of) {
		this.of = of;
	}
	public com.cqqyd2014.order.model.Order getOrder() {
		return order;
	}
	public void setOrder(com.cqqyd2014.order.model.Order order) {
		this.order = order;
	}

	public com.cqqyd2014.order.model.DeliverBill getDb() {
		return db;
	}
	public void setDb(com.cqqyd2014.order.model.DeliverBill db) {
		this.db = db;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Element getBody() {
		return body;
	}
	public void setBody(Element body) {
		this.body = body;
	}
	public com.cqqyd2014.order.model.OrderFrom of;
	java.util.ArrayList<com.cqqyd2014.order.model.Order> orders;
	java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs;
	public java.util.ArrayList<com.cqqyd2014.order.model.Order> getOrders() {
		return orders;
	}
	public void setOrders(java.util.ArrayList<com.cqqyd2014.order.model.Order> orders) {
		this.orders = orders;
	}
	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getDbs() {
		return dbs;
	}
	public void setDbs(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs) {
		this.dbs = dbs;
	}
	public com.cqqyd2014.order.model.Order order;
	public com.cqqyd2014.order.model.DeliverBill db;
	String service;
	String head;
	public Element body;
	public String make_xml() {
		Document document = DocumentHelper.createDocument();
		
		Element request = document.addElement("Request");         
		request.addAttribute("service", service);
		request.addAttribute( "lang","zh-CN");
		Element head = request.addElement("Head");
		head.setText(getHead());
		body = request.addElement("Body");
		
		make_body();
		document.setXMLEncoding("UTF-8");
        
        String output = document.asXML();
        return output;
	}
	public abstract void make_body();

}
