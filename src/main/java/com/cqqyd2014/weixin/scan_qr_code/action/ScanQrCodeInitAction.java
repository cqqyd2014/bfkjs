package com.cqqyd2014.weixin.scan_qr_code.action;

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
@Namespace(value="/weixin") //表示当前Action所在命名空间  
public class ScanQrCodeInitAction extends ActionSupport {
	String com_id;
	String barcode;
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
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
		            value="ScanQrCodeInit",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/weixin/scanqrcode.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
 
	


	public String ScanQrCodeInit() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		
		
		
		return "success";
	}

}
