package com.cqqyd2014.order.pickup_package.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetPickedBarcodeAction extends ActionSupport {


	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "get_picked_barcode", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_picked_barcode() throws Exception {

		Map<String,Object> session_http = ActionContext.getContext().getSession();
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
				.get("temp_deliver_picked_sn");
		

		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		sm.setSuccess(true);
		sm.setO(odis);
		msg = sm.toMap();
		return "success";
	}
}
