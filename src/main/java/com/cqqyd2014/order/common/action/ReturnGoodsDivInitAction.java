package com.cqqyd2014.order.common.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
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
import com.cqqyd2014.order.model.OrderFromUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("prototype") // 支持多例


@ParentPackage("bfkjs-default")
@Namespace(value="/order/common") //表示当前Action所在命名空间  
public class ReturnGoodsDivInitAction extends UserLoginedAction {

	java.util.LinkedHashMap<String, String> logisticsList;
	String default_logistics;
	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="return_goods_div_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/order/common/return_goods.jsp") }, interceptorRefs = {
									@InterceptorRef("authorityInterceptor") })

	})

	@Authority(module = "return_goods_div_init", privilege = "[00010001]", error_url = "authority_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO lcdao=new com.cqqyd2014.hibernate.dao.LogisticsCompanyDAO();
			
			
			logisticsList=lcdao.getNameMap(session);
			
			

			com.cqqyd2014.hibernate.dao.UserParDAO updao=new com.cqqyd2014.hibernate.dao.UserParDAO();
			default_logistics=updao.getValue(session, user_id, com_id, "default_logistics_com");

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


	
}