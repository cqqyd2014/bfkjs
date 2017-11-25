package com.cqqyd2014.order.pickup_package.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.order.common.OrderPagesInit;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/order") //表示当前Action所在命名空间 
public class OrderPackagePagesInitAction  extends OrderPagesInit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="order_package_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/order/package.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	public String order_package_init() throws Exception{
		init();
		return "success";
	}

}

