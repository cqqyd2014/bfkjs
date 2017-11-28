package com.cqqyd2014.system.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/system") //表示当前Action所在命名空间 
public class Exit extends ActionSupport {

	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="exit",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    
		                    @Result(name = "success",  params={"actionName","login_init","namespace","/login","method","login_init"}, type = "chain")
		                    
		            }
		    )    
	   
	   })  
	public String exit() throws Exception {

		Map<String,Object> session_http = ActionContext.getContext().getSession();
		
		
		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			com.cqqyd2014.hibernate.entities.SysUser su=sudao.getUserFromUserid(session, userid, com_id);
			su.setLastOnlineTime(new java.util.Date());
			su.setOnline(false);
			session.saveOrUpdate(su);
			
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

		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis = new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();

		session_http.put("temp_deliver_picked_sn", odis);

		//com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		sm.setSuccess(true);
		
		session_http.put("USER_ID", "");
		session_http.put("USER_NAME", "");
		
		return "success";
	}

}
