package com.cqqyd2014.web.manage.main.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/web/manage") //表示当前Action所在命名空间  
public class MainInitAction extends ActionSupport {
	String com_name;
	
	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}


	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="main_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/web/manage/main.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
 
	


	public String main_init() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();
		com_name=(String) session_http.get("com_name");
		
		
		
		return "success";
	}

}
