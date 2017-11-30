package com.cqqyd2014.order.pickup_package.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;


import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;



@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class GetPickedBarcodeAction extends UserLoginedAction {


	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "get_picked_barcode", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "[00010003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
				.get("temp_deliver_picked_sn");
		

		
		sm.setSuccess(true);
		sm.setO(odis);
		msg = sm.toMap();
		return "success";
	}
}
