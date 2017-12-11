package com.cqqyd2014.system.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;


import com.cqqyd2014.common.action.UserLoginedAction;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/system")
public class GetSystemDatAction extends UserLoginedAction{
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_system_dat", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);
		String result = com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date());
		sm.setSuccess(true);
		sm.setBody(result);
		msg=sm.toMap();
		return ActionSupport.SUCCESS;
	}

}
