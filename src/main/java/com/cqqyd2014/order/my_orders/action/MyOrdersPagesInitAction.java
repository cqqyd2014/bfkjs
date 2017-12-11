package com.cqqyd2014.order.my_orders.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.order.common.OrderPagesInit;

@SuppressWarnings("serial")
@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default") 
@Namespace(value="/order") //表示当前Action所在命名空间 
public class MyOrdersPagesInitAction  extends OrderPagesInit{
	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="my_orders_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/order/my_orders.jsp")
		                    
		            },
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  
	@Authority(module="myorders", privilege="[00010000]",error_url="authority_error") 
	public String my_orders_init() throws Exception{
		execute();
		
		return "success";
	}

}

