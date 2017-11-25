package com.cqqyd2014.express.sf.bsp.manual.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.entities.ComInfo;
import com.cqqyd2014.util.HashMapTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/express/sf") //表示当前Action所在命名空间
@Results( { @Result(name = "success", location = "/WEB-INF/express/sf/manual.jsp"),
	 
    @Result(name = "error", location = "/error.jsp") }) 
public class ManualInitAction extends ActionSupport {
	java.util.HashMap<String, String> coms_map;


	@Action("manual_init") //或者写成  @Action(value = "login") 
	public String manual_init() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		
		
		Session session = HibernateSessionFactory.getSession();

		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			com.cqqyd2014.hibernate.dao.ComInfoDAO cidao=new com.cqqyd2014.hibernate.dao.ComInfoDAO();
			ArrayList<ComInfo> cis=cidao.getList(session);
			coms_map=HashMapTools.convertArrayToHashMap(cis.toArray(), "getCId", "getCName");
			
			
			
		} catch (HibernateException e) {

			System.out.println(e.getMessage());

		} finally {
			HibernateSessionFactory.closeSession();
		}
		return "success";
	}
	public java.util.HashMap<String, String> getComs_map() {
		return coms_map;
	}
	public void setComs_map(java.util.HashMap<String, String> coms_map) {
		this.coms_map = coms_map;
	}
	
}