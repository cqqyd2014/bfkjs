package com.cqqyd2014.bfkjs.portal;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "portal_cqw", urlPatterns = "/portal/cqw")
public class Cqw extends AbstractPortalFilter {
	
	public Cqw() {
		super();
		// TODO Auto-generated constructor stub
		com_name = "勤驿达测试";
		com_code = "CQQW";
		
	}

}
