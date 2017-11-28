package com.cqqyd2014.order.taobao_import.action;

import java.util.Map;
import org.apache.struts2.convention.annotation.Action;     

import org.apache.struts2.convention.annotation.Actions;  
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/order") //表示当前Action所在命名空间  

public class TaobaoImportInitAction extends ActionSupport  {
	
	
	   
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="taobao_import_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/order/taobao_import.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
   
	


	public String taobao_import_init() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		 
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			
			tx.commit();
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return "success";
	}




}
