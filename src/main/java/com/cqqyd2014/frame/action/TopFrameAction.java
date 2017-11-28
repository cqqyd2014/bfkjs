package com.cqqyd2014.frame.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/mainframe") //表示当前Action所在命名空间  
public class TopFrameAction  extends UserLoginedAction {
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
