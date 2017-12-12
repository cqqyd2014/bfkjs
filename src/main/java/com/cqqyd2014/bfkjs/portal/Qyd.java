package com.cqqyd2014.bfkjs.portal;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "portal_qyd", urlPatterns = "/portal/qyd")
public class Qyd extends AbstractPortalFilter {

	public Qyd() {
		super();
		// TODO Auto-generated constructor stub
		com_name = "重庆勤驿达进出口有限责任公司";
		com_code = "CQQY";
	}

}