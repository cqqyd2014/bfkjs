package com.cqqyd2014.wh.current_warehouse_detail.action;

import java.util.Map;

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
@Namespace(value="/wh") //表示当前Action所在命名空间 
public class CurrentWarehouseDetailInitAction extends OrderPagesInit {

	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="current_warehouse_detail_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/current_warehouse_detail.jsp")
		                    
		            },
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  
	@Authority(module="pickup_package", privilege="[00020001]",error_url="login") 
	public String pickup_package_init() throws Exception{
	execute();
		
		return "success";
	}

}
