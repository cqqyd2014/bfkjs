package com.cqqyd2014.system.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.springframework.context.annotation.Scope;


import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/system") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })

public class GetSystemDatAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_system_dat", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_system_dat() {

		
		
		
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		String result = com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date());
		sm.setSuccess(true);
		sm.setBody(result);
		msg=sm.toMap();
		return ActionSupport.SUCCESS;
	}

}
