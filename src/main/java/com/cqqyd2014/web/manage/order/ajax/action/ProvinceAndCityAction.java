package com.cqqyd2014.web.manage.order.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.cqqyd2014.annotation.Authority;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/web/manage/order")
public class ProvinceAndCityAction extends com.cqqyd2014.order.createorder.ajax.action.ProvinceAndCityAjaxAction {

	@Action(value = "province_and_city", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "province_and_city", privilege = "[00010001]", error_url = "authority_ajax_error")
@Override
	public String execute() {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
