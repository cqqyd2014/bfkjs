package com.cqqyd2014.finance.googds_price.action;

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
@Namespace(value="/finance") //表示当前Action所在命名空间 
public class FinanceGoodsPriceInitAction  extends ActionSupport {
	java.util.LinkedHashMap goods_id_map;  
	



	public java.util.LinkedHashMap getGoods_id_map() {
		return goods_id_map;
	}




	public void setGoods_id_map(java.util.LinkedHashMap goods_id_map) {
		this.goods_id_map = goods_id_map;
	}




	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="finance_goods_price_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/finance/finance_goods_price.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	

	public String finance_goods_price_init() throws Exception {
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();
		String com_id = (String) session_http.get("com_code");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			
					
			goods_id_map=gidao.getGoodsInfosMapInUse(session, com_id);
			

			
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