package com.cqqyd2014.usergroup.add_user.action;

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
public class AddUserInitAction  extends ActionSupport {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPar> ups;
public java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPar> getUps() {
		return ups;
	}

	public void setUps(java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPar> ups) {
		this.ups = ups;
	}

//java.util.LinkedHashMap<String, String> com_map;
java.util.LinkedHashMap<String, String> role_map;
java.util.LinkedHashMap<String, String> e_map;

	public java.util.LinkedHashMap<String, String> getE_map() {
	return e_map;
}

public void setE_map(java.util.LinkedHashMap<String, String> e_map) {
	this.e_map = e_map;
}

	public java.util.LinkedHashMap<String, String> getRole_map() {
	return role_map;
}

public void setRole_map(java.util.LinkedHashMap<String, String> role_map) {
	this.role_map = role_map;
}

	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="add_user_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/usergroup/add_user.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	

	public String add_user_init() throws Exception {
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		
		String com_id = (String) session_http.get("com_code");

		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			com.cqqyd2014.hibernate.dao.SysroleDAO srdao=new com.cqqyd2014.hibernate.dao.SysroleDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysRole> srs=srdao.getArrayListEntityByCom(session, com_id);
			com.cqqyd2014.usergroup.logic.RoleLogic rl=new com.cqqyd2014.usergroup.logic.RoleLogic();
			role_map=rl.getMapFromArrayListEntity(srs);
			
			com.cqqyd2014.hibernate.dao.EBusinessDAO edao=new com.cqqyd2014.hibernate.dao.EBusinessDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Ebusiness> es_h=edao.getArrayListEntity(session);
			
			e_map=com.cqqyd2014.order.logic.EBusinessLogic.getMapFromArrayListEntiy(es_h);
			//goods_id_map=gil.getHashMap(vgis);
			
			
			//参数,以王安敏参数为准，"107932b8-c3d4-47e4-a0d5-b724db108785"
			
			com.cqqyd2014.hibernate.dao.UserParDAO updao=new com.cqqyd2014.hibernate.dao.UserParDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPar> ups_h=updao.getArrayListEntityByUserId(session, com_id, "107932b8-c3d4-47e4-a0d5-b724db108785");
			com.cqqyd2014.usergroup.logic.UserParLogic upl=new com.cqqyd2014.usergroup.logic.UserParLogic();
			ups=upl.getArrayListModelEntityFromArrayListEntity(ups_h);
			
			
			
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