package com.cqqyd2014.finance.my_price.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;


@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default") 
@Namespace(value="/finance") //表示当前Action所在命名空间 
public class MyPriceInitAction  extends UserLoginedAction {


	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="my_price_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/finance/my_price.jsp"),  
		                    
		            },
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  
	

	@Authority(module="mainframe", privilege="[00010001]",error_url="login") 
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		
		
		


		return "success";
	}

}