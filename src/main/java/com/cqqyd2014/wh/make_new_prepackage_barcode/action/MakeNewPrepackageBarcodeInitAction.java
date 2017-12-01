package com.cqqyd2014.wh.make_new_prepackage_barcode.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;


@SuppressWarnings("serial")
@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default")  
@Namespace(value="/wh") //表示当前Action所在命名空间  
public class MakeNewPrepackageBarcodeInitAction extends UserLoginedAction {

	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="make_new_prepackage_barcode_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/make_new_prepackage_barcode.jsp")},
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  





@Authority(module="mainframe", privilege="[00020004]",error_url="authority_error") 
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
		

		
		return "success";
	}
	



	
	

}
