package com.cqqyd2014.system.action.ajax;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetGoodsInfoBySnAjax extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	String sn;
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		Integer I_type = ((Integer) this.session.get("TYPE"));
		int type = I_type.intValue();
		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		String com_id=(String) this.session.get("com_code");
		result="";
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			

			java.util.ArrayList<String> split=com.cqqyd2014.wh.logic.GoodsLogic.decodeBarcode(sn);
			String sn_code=split.get(0);
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gid=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			com.cqqyd2014.hibernate.entities.VGoodsInfo gi=gid.getGoodsInfoBySnCode(session, sn_code, com_id);
			result=gi.getId().getCName()+"/"+gi.getId().getCSpec();
			tx.commit();
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		HttpServletRequest request = (HttpServletRequest)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);  
		HttpServletResponse response = (HttpServletResponse)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);  
		request.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");  
	        ServletActionContext.getResponse().getWriter().print(result);  
	        return null;  
	}
}
