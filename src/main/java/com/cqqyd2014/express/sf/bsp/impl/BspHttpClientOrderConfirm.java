package com.cqqyd2014.express.sf.bsp.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Element;
import org.hibernate.Session;

import com.cqqyd2014.express.sf.bsp.BspException;
import com.cqqyd2014.express.sf.bsp.BspHttpClient;
import com.cqqyd2014.express.sf.dom4j.common.Request;
import com.cqqyd2014.express.sf.dom4j.impl.RequestOrderConfirmService;
import com.cqqyd2014.order.model.DeliverBill;
import com.cqqyd2014.order.model.Order;
import com.cqqyd2014.order.model.OrderFrom;

public class BspHttpClientOrderConfirm extends BspHttpClient {
	String mailno;
	String dealtype;
	public String getDealtype() {
		return dealtype;
	}


	@Override
	public void setDecodeURIXml(String xml) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		super.setDecodeURIXml(xml);
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}



	public String getMailno() {
		return mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}








	public BspHttpClientOrderConfirm(Session session, DeliverBill db) throws BspException{
		super(session, db);
		// TODO Auto-generated constructor stub
		setService_name("OrderConfirmService");
	}



	@Override
	public String initBill()  {
		// TODO Auto-generated method stub
		RequestOrderConfirmService r=new RequestOrderConfirmService();
		r.setDb(this.getDb());
		r.setOrder(this.getOrder());
		r.setService("OrderConfirmService");
		r.setDealtype(dealtype);
		r.setOf(this.getOf());
		return afterInitBill((Request)r);
	}

	@Override
	public Object afterProcessPostBack(Element body) throws BspException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stubOrderConfirmResponse
		
		Element orderconfirm_response=body.element("OrderConfirmResponse");
				//System.out.println("dfs");
		

		        //System.out.println(o.getBody().getOrderResponse().getMailno());
		        com.cqqyd2014.hibernate.entities.SfResponseOrderConfirmBack sb=new com.cqqyd2014.hibernate.entities.SfResponseOrderConfirmBack();
		        com.cqqyd2014.hibernate.entities.SfResponseOrderConfirmBackId sbid=new com.cqqyd2014.hibernate.entities.SfResponseOrderConfirmBackId();
		        sbid.setComId(getOrders().get(0).getCom_id());
		        sbid.setUuid(com.cqqyd2014.util.StringUtil.getUUID());
		        sb.setId(sbid);
		        sb.setCustId(getSfa().getSfMonthPayAccount());
		        
		        sb.setOpTime(getResponse_time());
		        sb.setOrderid(orderconfirm_response.attribute("orderid").getText());
		        sb.setMailno(orderconfirm_response.attribute("mailno").getText());
		        sb.setResStatus(orderconfirm_response.attribute("res_status").getText());
		        getSession().save(sb);
		        return null;
		        
	}


}
