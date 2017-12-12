package com.cqqyd2014.system.action.ajax;

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
import net.sf.json.JsonConfig;

public class FindHsAjax extends ActionSupport implements  SessionAware{
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String hs_code;
	public String getHs_code() {
		return hs_code;
	}
	public void setHs_code(String hs_code) {
		this.hs_code = hs_code;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Integer I_type=((Integer)this.session.get("TYPE"));
		int type=I_type.intValue();
		String user_name=(String)this.session.get("USER_NAME");
		String user_id=(String)this.session.get("USER_ID");
		String com_id=(String)this.session.get("com_code");

		// TODO Auto-generated method stub

		
		
		Session session = HibernateSessionFactory.getSession();
		//Transaction tx = session.beginTransaction();
		
		try {
			
			com.cqqyd2014.hibernate.dao.EcsTaxRateDAO sdao=new com.cqqyd2014.hibernate.dao.EcsTaxRateDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.EcsTaxRate> etrs=sdao.getEcs(session, hs_code);
			
			
			JsonConfig jsonConfig = new JsonConfig();  
			
		JSONArray ja1 = JSONArray.fromObject(etrs);
		/*
		java.util.ArrayList<String> rs=new java.util.ArrayList<String>();
		for (int i=0;i<oms.size();i++){
			String userName=oms.get(i).getCUserName();
			String userCardId=oms.get(i).getCUserCardid();
			

			//if (com.cqqyd2014.util.StringUtil.getLevenshteinDistance(id, originalID)<=Env.getORDER_COMPARE_RATE()){

				rs.add("<div><a onclick=\"replaceOrderFromTip(\'"+userName+"\',\'"+userCardId+"\')\">"+userName+"："+userCardId+"</a></div>");
				
			//}
		}
		inputStream=new ByteArrayInputStream(rs.toString().getBytes("UTF-8"));
		*/
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ja1); 

		//tx.commit();
		// session.close();
	}

	catch (HibernateException e) {
		/*
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		*/
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
		return null;
	}

}
