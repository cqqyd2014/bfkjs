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
import com.cqqyd2014.express.sf.dom4j.impl.RequestOrderService;
import com.cqqyd2014.express.sf.dom4j.impl.RequestRouteService;
import com.cqqyd2014.order.model.DeliverBill;
import com.cqqyd2014.order.model.Order;
import com.cqqyd2014.order.model.OrderFrom;

public class BspHttpClientRoute extends BspHttpClient {
	



	@Override
	public void setDecodeURIXml(String xml) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		super.setDecodeURIXml(xml);
	}

	public BspHttpClientRoute() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BspHttpClientRoute(Session session,  ArrayList<DeliverBill> dbs)
			throws Exception {
		super(session,  dbs);
		// TODO Auto-generated constructor stub
		setService_name("RouteService");
	}


	java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs;

	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getDbs() {
		return dbs;
	}

	public void setDbs(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs) {
		this.dbs = dbs;
	}

	@Override
	public String initBill() {
		// TODO Auto-generated method stub
		RequestRouteService r=new RequestRouteService();
		r.setDbs(this.getDbs());
		
		r.setService("RouteService");
		 return afterInitBill((Request)r);
		
	}

	@Override
	public Object afterProcessPostBack(Element body)  throws BspException{
		// TODO Auto-generated method stub
		if (body.content().size()==0) {
			return null;
		}
		Element route_response=body.element("RouteResponse");
		String mail_no=route_response.attribute("mailno").getText();
		
		java.util.ArrayList<Element> rs=(java.util.ArrayList<Element>)route_response.elements();
		for (int i=0;i<rs.size();i++) {
			Element r=rs.get(i);
			com.cqqyd2014.hibernate.entities.SfResponseRouteBack rb=new com.cqqyd2014.hibernate.entities.SfResponseRouteBack();
	        com.cqqyd2014.hibernate.entities.SfResponseRouteBackId rbid=new com.cqqyd2014.hibernate.entities.SfResponseRouteBackId();
	        rbid.setComId(getOrders().get(0).getCom_id());
	        rbid.setUuid(getResponse_uuid());
	        rbid.setSubUuid(com.cqqyd2014.util.StringUtil.getUUID());
	        
	        rb.setId(rbid);
	        rb.setOpTime(getResponse_time());
	        rb.setCustId(getSfa().getSfMonthPayAccount());
	        rb.setAcceptAddress(r.attribute("accept_address").getText());
	        rb.setAcceptTime(r.attribute("accept_time").getText());
	        rb.setAcceptTimeDat(com.cqqyd2014.util.DateUtil.FullStringToJDate(r.attribute("accept_time").getText()));
	        rb.setMailno(mail_no);
	        rb.setOpcode(r.attribute("opcode").getText());
	        rb.setRemark(r.attribute("remark").getText());
	        rb.setPush(false);
	        getSession().save(rb);
		}
		

		
        
       
        return rs;
		
	}


}
