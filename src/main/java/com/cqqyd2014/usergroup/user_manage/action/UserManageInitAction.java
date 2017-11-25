package com.cqqyd2014.usergroup.user_manage.action;

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
@Namespace(value="/usergroup") //表示当前Action所在命名空间  

public class UserManageInitAction  extends ActionSupport {
	
	java.util.HashMap<String, String> goods_id_map;
	
public java.util.HashMap<String, String> getGoods_id_map() {
		return goods_id_map;
	}
	public void setGoods_id_map(java.util.HashMap<String, String> goods_id_map) {
		this.goods_id_map = goods_id_map;
	}
java.util.HashMap<String, String> user_map;
	public java.util.HashMap<String, String> getUser_map() {
	return user_map;
}
public void setUser_map(java.util.HashMap<String, String> user_map) {
	this.user_map = user_map;
}
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="user_manage_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/usergroup/user_manage.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	

	public String login_init() throws Exception {
		
		Map session_http = ActionContext.getContext().getSession();


		
		String com_id = (String) session_http.get("com_code");

		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysUser> users=sudao.getUsersAllByCom(session, com_id);
			user_map=com.cqqyd2014.util.HashMapTools.convertArrayToHashMap(users.toArray(), "getId", "getName");
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO vgidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> vgis=vgidao.getGoodsInfosAll(session, com_id);
			
			
			goods_id_map=com.cqqyd2014.wh.logic.GoodsInfoLogic.getHashMap(vgis);
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
