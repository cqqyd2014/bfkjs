package com.cqqyd2014.tools.big_event.action.ajax;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class GetBigEventItem extends ActionSupport implements  SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		
		//String user_name=(String)this.session.get("USER_NAME");
		String com_id=(String)this.session.get("com_code");
		Session session = HibernateSessionFactory.getSession();
		
		//String user_id=(String)this.session.get("USER_ID");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			com.cqqyd2014.hibernate.dao.BigEventDAO bedao=new com.cqqyd2014.hibernate.dao.BigEventDAO();
			com.cqqyd2014.tools.big_event.model.BigEventItem bei=bedao.getModelByUuid(session, com_id, uuid);
		sm.setSuccess(true);
		sm.setO(bei);
		}

		catch (HibernateException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);
			sm.setBody(e.toString());
			
		} 
		catch (Exception e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);
			sm.setBody(e.toString());
			
		} 
		
		finally {
			HibernateSessionFactory.closeSession();
		}
			JSONArray ja1 = JSONArray.fromObject(sm);
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ja1); 
		return null;
	}

}