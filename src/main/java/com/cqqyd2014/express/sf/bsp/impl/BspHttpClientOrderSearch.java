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

import com.cqqyd2014.express.sf.dom4j.impl.RequestOrderSearchService;

import com.cqqyd2014.order.model.DeliverBill;


public class BspHttpClientOrderSearch extends BspHttpClient {





	@Override
	public void setDecodeURIXml(String xml) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		super.setDecodeURIXml(xml);
	}


	@Override
	public String initBill()  {
		// TODO Auto-generated method stub
		RequestOrderSearchService r=new RequestOrderSearchService();
		r.setDb(this.getDb());
		r.setOrder(this.getOrder());
		r.setService("OrderSearchService");
		
		return afterInitBill((Request)r);
		
	}

	public BspHttpClientOrderSearch() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BspHttpClientOrderSearch(Session session,  DeliverBill db) throws  BspException {
		super(session, db );
		// TODO Auto-generated constructor stub
		setService_name("OrderSearchService");
	}



	@Override
	public Object afterProcessPostBack(Element body) throws BspException {
		// TODO Auto-generated method stub
		
				//System.out.println("dfs");
		Element ordersearch_response=body.element("OrderSearchService");

		        //System.out.println(o.getBody().getOrderResponse().getMailno());
		        com.cqqyd2014.hibernate.entities.SfReponseOrderSearch sb=new com.cqqyd2014.hibernate.entities.SfReponseOrderSearch();
		        com.cqqyd2014.hibernate.entities.SfReponseOrderSearchId sbid=new com.cqqyd2014.hibernate.entities.SfReponseOrderSearchId();
		        sbid.setComId(getOrders().get(0).getCom_id());
		        sbid.setUuid(getResponse_uuid());
		        sb.setId(sbid);
		        sb.setCustId(getSfa().getSfMonthPayAccount());
		        sb.setDestcode(ordersearch_response.attribute("destcode").getText());
		        sb.setFilterResult(ordersearch_response.attribute("filter_result").getText());
		        sb.setMailno(ordersearch_response.attribute("mailno").getText());
		        
		        sb.setOpTime(getResponse_time());
		        sb.setOrderid(ordersearch_response.attribute("orderid").getText());
		        sb.setOrigincode(ordersearch_response.attribute("origincode").getText());
		        this.getDb().setExpress_no(ordersearch_response.attribute("mailno").getText());
		        return this.getDb();
	}

}
