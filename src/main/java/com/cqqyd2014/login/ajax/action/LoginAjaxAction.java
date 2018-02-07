package com.cqqyd2014.login.ajax.action;

import java.util.Map;

import javax.annotation.Resource;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import com.cqqyd2014.service.inter.SysUserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller("LoginAjaxAction")
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/login")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class LoginAjaxAction extends ActionSupport {
	private Map<String, Object> msg;
	String user_name;
	String password;
	@Resource(name="sysUserService")
	SysUserService sysUserService;
	
	public SysUserService getSysUserService() {
		return sysUserService;
	}
	
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Action(value = "login", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String login() {
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		
		try {

			if (user_name.trim().length() == 0 || user_name == null) {
				
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("用户名为空");

			}
			if (password.trim().length() == 0 || password == null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("密码为空");
			}

			sysUserService.checkLoginName(user_name, password, com_id);
			
			
			
				
			sm.setSuccess(true);
		

		}

		catch (com.cqqyd2014.util.exception.ServcieException se) {

			
			sm.setSuccess(false);
			sm.setBody(se.getMessage());

		} 
		
catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException se) {

			
			sm.setSuccess(false);
			sm.setBody(se.toString());

		} 
		
		finally {
			
		}
		msg=sm.toMap();
		return SUCCESS;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
}
