package com.cqqyd2014.finance.my_price.action;

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
@Namespace(value="/finance") //表示当前Action所在命名空间 
public class MyPriceInitAction  extends ActionSupport {
	String userid;
	

	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="my_price_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/finance/my_price.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	

	public String my_price_init() throws Exception {
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		
		String com_id = (String) session_http.get("com_code");
		userid = (String) session_http.get("USER_ID");
		
		


		return "success";
	}

}