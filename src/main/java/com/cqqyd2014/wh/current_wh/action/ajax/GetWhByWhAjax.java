package com.cqqyd2014.wh.current_wh.action.ajax;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class GetWhByWhAjax extends ActionSupport implements  SessionAware{
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	


	public String execute() throws Exception {
		// TODO Auto-generated method stub

		String user_name=(String)this.session.get("USER_NAME");
		String user_id=(String)this.session.get("USER_ID");
		String com_id=(String)this.session.get("com_code");

		// TODO Auto-generated method stub

		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> whs=new java.util.ArrayList<>();
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao=new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			whs=whdao.getUserCustomWareHouseModelByComId(session, com_id);
			sm.setSuccess(true);
			sm.setO(whs);
			
			
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
		JsonConfig jsonConfig = new JsonConfig();  
		
		JSONArray ja1 = JSONArray.fromObject(sm);
		//System.out.println(ja1.toString());
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ja1); 
		return null;
	}
}