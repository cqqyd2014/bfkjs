package com.cqqyd2014.frame.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/mainframe") //表示当前Action所在命名空间  

public class CenterFrameAction extends ActionSupport  {
	String user_name;
	String user_id;
	String com_id;
	String user;
	
	   
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getCom_id() {
		return com_id;
	}


	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

java.util.ArrayList<com.cqqyd2014.system.model.MenuM> menu;
	
	public java.util.ArrayList<com.cqqyd2014.system.model.MenuM> getMenu() {
		return menu;
	}

	public void setMenu(java.util.ArrayList<com.cqqyd2014.system.model.MenuM> menu) {
		this.menu = menu;
	}

	java.util.ArrayList<com.cqqyd2014.system.model.MenuD> javascrpits_method;

	public java.util.ArrayList<com.cqqyd2014.system.model.MenuD> getJavascrpits_method() {
		return javascrpits_method;
	}

	public void setJavascrpits_method(java.util.ArrayList<com.cqqyd2014.system.model.MenuD> javascrpits_method) {
		this.javascrpits_method = javascrpits_method;
	}
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="center_frame",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/mainframe/center.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
   
	


	public String center_frame() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		user = (String) session_http.get("USER");
		user_name = (String) session_http.get("USER_NAME");
		user_id = (String) session_http.get("USER_ID");
		com_id = (String) session_http.get("com_code");
		
		if (user==null){
			return "loginError";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
		com.cqqyd2014.hibernate.dao.MenuDDAO mddao=new com.cqqyd2014.hibernate.dao.MenuDDAO();
		javascrpits_method=mddao.getMenuDAll(session, com_id,user_id);
		com.cqqyd2014.hibernate.dao.MenuMDAO mmdao=new com.cqqyd2014.hibernate.dao.MenuMDAO();
		menu=mmdao.getMenu(session, com_id,user_id);
		
		
		
		
		
		tx.commit();
		}
		catch (HibernateException e) {
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