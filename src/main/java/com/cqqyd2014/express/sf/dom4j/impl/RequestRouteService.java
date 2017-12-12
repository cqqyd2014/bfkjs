package com.cqqyd2014.express.sf.dom4j.impl;

import java.lang.reflect.InvocationTargetException;

import org.dom4j.Element;

import com.cqqyd2014.express.sf.dom4j.common.Request;

public class RequestRouteService  extends Request{

	@Override
	public void make_body() {
		// TODO Auto-generated method stub
		
		Element order_e=body.addElement("RouteRequest");
		
		order_e.addAttribute("tracking_type", "1");
		order_e.addAttribute("method_type", "1");
		String mail_nos="";
		
			mail_nos=com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(this.getDbs(), "getExpress_no");
		
		mail_nos=mail_nos.substring(1, mail_nos.length()-1);
		
		order_e.addAttribute("tracking_number",mail_nos);
	}

}
