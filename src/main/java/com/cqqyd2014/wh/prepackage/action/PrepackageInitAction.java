package com.cqqyd2014.wh.prepackage.action;



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


@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default")  
@Namespace(value="/wh") //表示当前Action所在命名空间   
public class PrepackageInitAction extends UserLoginedAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.util.LinkedHashMap<String,String> wh_map;
	String wh_id;
	
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
		            value="prepackage_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/prepackage.jsp")},
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  





@Authority(module="prepackage_init", privilege="[00020005]",error_url="authority_error") 
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis = new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();
		session_http.put("temp_add_prepackage_barcode", odis);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			
			com.cqqyd2014.hibernate.dao.UserParDAO cpcdao = new com.cqqyd2014.hibernate.dao.UserParDAO();
			
			wh_id = cpcdao.getValue(session, user_id, com_id, "default_warehouse");
			
			
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao = new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs_h = whdao.getAllByComId(session, com_id);
			
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> whs=com.cqqyd2014.wh.logic.WareHouseLogic.getArrayListModelFromArrayListEntity(whs_h);
			
			wh_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMap(whs, "getWh_id", "getWh_name");
			
			
			
			
			
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
