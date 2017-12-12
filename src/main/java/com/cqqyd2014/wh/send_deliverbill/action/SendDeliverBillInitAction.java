package com.cqqyd2014.wh.send_deliverbill.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.wh.deliverbill.common.action.DeliverBillPagesInitAction;


@SuppressWarnings("serial")
@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default")  
@Namespace(value="/wh") //表示当前Action所在命名空间  
public class SendDeliverBillInitAction extends DeliverBillPagesInitAction {
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="send_deliverbill_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/send_deliverbill.jsp")
		                    
		            },
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  
	@Authority(module="pickup_package", privilege="[00010004]",error_url="login") 
	

	public String send_deliverbill_init()  {
		super.init();
		
		
		return "success";
	}
	



	
	

}