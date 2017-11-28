package com.cqqyd2014.login.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/login") //表示当前Action所在命名空间  

public class LoginInitActon  extends ActionSupport {
	

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String com_code;
	   String com_name;
	public String getCom_code() {
		return com_code;
	}
	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="login_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/login/loginQYD.jsp"), 
		                    
		                    
		            }
		    )    
	   
	   })  
	

	public String login_init() throws Exception {
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		com_name = (String) session_http.get("com_name");
		com_code = (String) session_http.get("com_code");
		return "success";
	}

}
