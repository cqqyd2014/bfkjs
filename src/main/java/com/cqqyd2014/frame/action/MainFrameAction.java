package com.cqqyd2014.frame.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;

import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@SuppressWarnings("serial")
@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/mainframe") //表示当前Action所在命名空间  
public class MainFrameAction extends UserLoginedAction {
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="main_frame",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/mainframe/main.jsp"),  
		                    
		            }
		    )    
	   
	   })
	@Authority(module="mainframe", privilege="*",error_url="") 
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		 session = HibernateSessionFactory.getSession();
		 tx = session.beginTransaction();
		
		
		try {
			
			
			tx.commit();
		}

		catch (HibernateException e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		 finally {
			HibernateSessionFactory.closeSession();
		}
		
		end_execute();
		
		
		
		return "success";
	}

}
