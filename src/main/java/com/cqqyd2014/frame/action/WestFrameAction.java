package com.cqqyd2014.frame.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.dao.VUserMenuDDAO;
import com.cqqyd2014.hibernate.dao.VUserMenuMDAO;
import com.cqqyd2014.hibernate.entities.VUserMenuD;
import com.cqqyd2014.system.logic.MenuDLogic;
import com.cqqyd2014.system.logic.MenuMLogic;
import com.cqqyd2014.system.model.MenuD;
import com.cqqyd2014.system.model.MenuM;



@SuppressWarnings("serial")
@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default")
@Namespace(value="/mainframe") //表示当前Action所在命名空间  
public class WestFrameAction extends UserLoginedAction  {
	

java.util.ArrayList<MenuM> menu;
	
	
	java.util.ArrayList<MenuD> javascrpits_method;

	
	
	public java.util.ArrayList<MenuM> getMenu() {
		return menu;
	}



	public void setMenu(java.util.ArrayList<MenuM> menu) {
		this.menu = menu;
	}



	public java.util.ArrayList<MenuD> getJavascrpits_method() {
		return javascrpits_method;
	}



	public void setJavascrpits_method(java.util.ArrayList<MenuD> javascrpits_method) {
		this.javascrpits_method = javascrpits_method;
	}



	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="west_frame",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/mainframe/west.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
   
	@Authority(module="west_frame", privilege="*",error_url="authority_error") 
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		
		

		super.execute();
		
		 session = HibernateSessionFactory.getSession();
		 
		
		
		try {
			
			
			javascrpits_method=MenuDLogic.getArrayListModelFromArrayListView(
					VUserMenuDDAO.getArrayListEntityByUserId(session, user_id));
			
		
		
		
		menu=MenuMLogic.getArrayListModelFromArrayListView(
				VUserMenuMDAO.getArrayListViewByUserId(session, user_id));
		for (int i=0;i<menu.size();i++) {
			MenuM mm=menu.get(i);
			java.util.ArrayList<VUserMenuD> vumds=VUserMenuDDAO.getArrayListEntityByIdUserId(session, mm.getM_id(), user_id);
			mm.setMds(MenuDLogic.getArrayListModelFromArrayListView(vumds));
		}
		
		
		
		
		
		
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