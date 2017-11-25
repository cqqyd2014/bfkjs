package com.cqqyd2014.wh.move_warehouse.action;

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
@Namespace(value="/wh") //表示当前Action所在命名空间  
public class MoveWareHouseInitAction extends ActionSupport {
	java.util.LinkedHashMap<String,String> wh_map;
	String wh_id;
	java.util.Date move_date;
	public java.util.Date getMove_date() {
		return move_date;
	}

	public void setMove_date(java.util.Date move_date) {
		this.move_date = move_date;
	}

	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}

	public java.util.LinkedHashMap<String, String> getWh_map() {
		return wh_map;
	}

	public void setWh_map(java.util.LinkedHashMap<String, String> wh_map) {
		this.wh_map = wh_map;
	}

	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="move_warehouse_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/move_warehouse.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	

	public String move_warehouse_init() throws Exception {
		Map session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis =  new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();
			session_http.put("temp_move_goods", odis);
			
			com.cqqyd2014.hibernate.dao.UserParDAO cpcdao = new com.cqqyd2014.hibernate.dao.UserParDAO();
			
			wh_id = cpcdao.getValue(session, userid, com_id, "default_warehouse");
			
			
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao = new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs_h = whdao.getAllByComId(session, com_id);
			
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> whs=com.cqqyd2014.wh.logic.WareHouseLogic.getArrayListModelFromArrayListEntity(whs_h);
			
			wh_map=com.cqqyd2014.util.HashMapTools.convertArrayToHashMap(whs.toArray(), "getWh_id", "getWh_name");
			
			move_date=new java.util.Date();
			
			
			
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
