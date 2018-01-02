package com.cqqyd2014.order.createorder.ajax.action;

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
public class GetTempOrderDetailAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_temp_order_detail", results = { 
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
					
					@InterceptorRef("defaultStack"),
					@InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_temp_order_detail", privilege = "[00010001]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);

		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis=(java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>)session_http.get("temp_order_detail");
		if(odis==null){
			odis=new java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>();
			session_http.put("temp_order_detail", odis);
		}
		
		msg=new java.util.HashMap<>();
		msg.put("total", String.valueOf(odis.size()));
		msg.put("rows", odis);
		return SUCCESS;
		
	}
}