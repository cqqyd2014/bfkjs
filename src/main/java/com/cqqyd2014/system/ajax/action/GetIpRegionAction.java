package com.cqqyd2014.system.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.util.exception.HttpExcpetion;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/system") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetIpRegionAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}
	String ip;
	
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_ip_region", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_ip_region() {
		String url="http://int.dpool.sina.com.cn/iplookup/iplookup.php";
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		
		
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		String par="format=json&ip"+ip;
		try{
			String r=com.cqqyd2014.util.HttpRequest.sendGet(url, par);
			sm.setSuccess(true);
			sm.setBody(r);
			
			 
		}

		catch (HttpExcpetion e) {
			
			
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setBody(e.getMessage());
			sm.setSuccess(false);
			
		}
		msg=sm.toMap();
		return ActionSupport.SUCCESS;
	}

}