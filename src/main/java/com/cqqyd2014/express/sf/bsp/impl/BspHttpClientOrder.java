package com.cqqyd2014.express.sf.bsp.impl;



import java.io.UnsupportedEncodingException;


import org.dom4j.Element;
import org.hibernate.Session;

import com.cqqyd2014.express.sf.bsp.BspException;
import com.cqqyd2014.express.sf.bsp.BspHttpClient;
import com.cqqyd2014.express.sf.dom4j.common.Request;
import com.cqqyd2014.express.sf.dom4j.impl.RequestOrderService;

import com.cqqyd2014.order.model.DeliverBill;


public class BspHttpClientOrder extends BspHttpClient{






	@Override
	public void setDecodeURIXml(String xml) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		super.setDecodeURIXml(xml);
	}


	






	public BspHttpClientOrder(Session session,  DeliverBill db) throws BspException {
		super(session, db);
		setService_name("OrderService");
		// TODO Auto-generated constructor stub
	}



	public BspHttpClientOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String initBill() {
		// TODO Auto-generated method stub
		RequestOrderService r=new RequestOrderService();
		r.setOf(this.getOf());
		r.setOrder(this.getOrder());
		r.setDb(this.getDb());
		
		r.setService("OrderService");
		 return afterInitBill((Request)r);
	}

	@Override
	public Object afterProcessPostBack(Element body) throws BspException {
		// TODO Auto-generated method stub
		
		
		Element order_response=body.element("OrderResponse");
        //System.out.println(o.getBody().getOrderResponse().getMailno());
        com.cqqyd2014.hibernate.entities.SfResponseOrderBack sb=new com.cqqyd2014.hibernate.entities.SfResponseOrderBack();
        com.cqqyd2014.hibernate.entities.SfResponseOrderBackId sbid=new com.cqqyd2014.hibernate.entities.SfResponseOrderBackId();
        sbid.setComId(getOrder().getCom_id());
        sbid.setUuid(getResponse_uuid());
        sb.setId(sbid);
        sb.setCustId(getSfa().getSfMonthPayAccount());
        sb.setDestcode(order_response.attribute("destcode").getText());
        
        sb.setFilterResult(order_response.attribute("filter_result").getText());
        sb.setMailno(order_response.attribute("mailno").getText());

        sb.setOpTime(getResponse_time());
        sb.setOrderid(order_response.attribute("orderid").getText());
        sb.setOrigincode(order_response.attribute("origincode").getText());
        getSession().save(sb);
        
        this.getDb().setExpress_no(order_response.attribute("mailno").getText());
        com.cqqyd2014.order.logic.DeliverMLogic.save(this.getSession(), this.getDb());
        return this.getDb();
	}




}
