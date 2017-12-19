package com.cqqyd2014.frame.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;


import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;



@SuppressWarnings("serial")
@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default")  //表示继承的父包  
@Namespace(value="/mainframe") //表示当前Action所在命名空间  
public class TopFrameAction  extends UserLoginedAction {
	
	String chineseDate;
	
	public String getChineseDate() {
		return chineseDate;
	}
	public void setChineseDate(String chineseDate) {
		this.chineseDate = chineseDate;
	}
	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="top_frame",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/mainframe/top.jsp"),  
		                    
		            }
		    )    
	   
	   }) 
	@Authority(module="topframe", privilege="*",error_url="") 
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		chineseDate=com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date());

		
		
		
		
		
		return "success";
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
