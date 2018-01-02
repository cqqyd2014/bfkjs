package com.cqqyd2014.system.region.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/system")
public class GetRegionAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	public java.math.BigDecimal getPid() {
		return pid;
	}

	public void setPid(java.math.BigDecimal pid) {
		this.pid = pid;
	}

	java.math.BigDecimal pid;
	@Action(value = "get_regions", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_regions", privilege = "*", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
		session = HibernateSessionFactory.getSession();
		tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.RegionDAO rdao=new com.cqqyd2014.hibernate.dao.RegionDAO();
			 java.util.ArrayList<com.cqqyd2014.system.model.Region> rs=rdao.getSubRegion(session, pid);
			
			sm.setSuccess(true);
			sm.setO(rs);
			
			
			tx.commit();
			
		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);

		}
		
		
		finally {
			HibernateSessionFactory.closeSession();
		}

		msg=sm.toMap();
		return SUCCESS;
	}
	
}