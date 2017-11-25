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
public class TopFrameAction  extends ActionSupport  {
	int interval_time;
	String chineseDate;
	public int getInterval_time() {
		return interval_time;
	}
	public void setInterval_time(int interval_time) {
		this.interval_time = interval_time;
	}
	public String getChineseDate() {
		return chineseDate;
	}
	public void setChineseDate(String chineseDate) {
		this.chineseDate = chineseDate;
	}
	String user;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	String user_name;
	String user_id;
	String com_id;
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
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="top_frame",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/mainframe/top.jsp"),  
		                    
		            }
		    )    
	   
	   }) 
	public String top_frame() throws Exception {
		Map session_http = ActionContext.getContext().getSession();

		user = (String) session_http.get("USER");
		user_name = (String) session_http.get("USER_NAME");
		user_id = (String) session_http.get("USER_ID");
		com_id = (String) session_http.get("com_code");
		if (user==null){
			return "loginError";
		}
		chineseDate=com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date());
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			com.cqqyd2014.hibernate.dao.SysParDAO spdao=new com.cqqyd2014.hibernate.dao.SysParDAO();
			interval_time=spdao.getIntervalTime(session);
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
		
		
		
		
		
		return "success";
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
