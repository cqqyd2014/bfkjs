package com.cqqyd2014.web.manage.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;

@Scope("prototype")//支持多例  

@Namespace(value="/web/manage/order") //表示当前Action所在命名空间  
@ParentPackage("bfkjs-default") 
@SuppressWarnings("serial")
public class CreateOrderInitAction extends com.cqqyd2014.order.createorder.action.CreateOrderInitAction{
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="create_order_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/web/manage/order/create_order_init.jsp")  },
		            interceptorRefs={  
		                            @InterceptorRef("authorityInterceptor")  
		            }
		    )    
	   
	   })  
	
 
	


	@Authority(module="mainframe", privilege="[00010001]",error_url="authority_error") 
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
