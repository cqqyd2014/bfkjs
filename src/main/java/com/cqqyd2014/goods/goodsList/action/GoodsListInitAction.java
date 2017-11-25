package com.cqqyd2014.goods.goodsList.action;

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
@Namespace(value="/goods") //表示当前Action所在命名空间  
public class GoodsListInitAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	java.util.LinkedHashMap<String, String > unitList=new java.util.LinkedHashMap<>();
    java.util.LinkedHashMap<String, String> countryList=new java.util.LinkedHashMap<>();
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="goods_list_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/goods/GoodsList.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
  
	


	public String create_order_init() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.SysCodeDAO scdao=new com.cqqyd2014.hibernate.dao.SysCodeDAO();
            unitList=scdao.getValuesMap(session, "unit");
            countryList=scdao.getValuesMap(session, "country");


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

	public java.util.LinkedHashMap<String, String> getUnitList() {
		return unitList;
	}

	public void setUnitList(java.util.LinkedHashMap<String, String> unitList) {
		this.unitList = unitList;
	}

	public java.util.LinkedHashMap<String, String> getCountryList() {
		return countryList;
	}

	public void setCountryList(java.util.LinkedHashMap<String, String> countryList) {
		this.countryList = countryList;
	}

	
}
