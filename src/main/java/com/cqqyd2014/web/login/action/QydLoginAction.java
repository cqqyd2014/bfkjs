package com.cqqyd2014.web.login.action;

import javax.servlet.annotation.WebFilter;

import com.cqqyd2014.web.common.LoginFilter;

@WebFilter(filterName = "web_qyd", urlPatterns = "/web/login/qyd")
public class QydLoginAction extends LoginFilter{

	public QydLoginAction() {
		super();
		// TODO Auto-generated constructor stub
		setCom_name("重庆勤驿达进出口有限责任公司");
		setCom_code("CQQY");
	}
}
