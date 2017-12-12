package com.cqqyd2014.web.manage.main.ajax.action;


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

@Namespace("/web/manage")

public class GetAttentionAction   extends UserLoginedAction {
	private Map<String, Object> msg;


	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_attention", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "*", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.MenuDDAO mddao=new com.cqqyd2014.hibernate.dao.MenuDDAO();
			java.util.ArrayList<com.cqqyd2014.system.model.MenuD> mds=com.cqqyd2014.system.logic.MenuDLogic.getArrayListModelFromView1(mddao.getWebAttentionMenuD(session, user_id, com_id));
			java.util.ArrayList<com.cqqyd2014.web.manage.model.Attention> atts=com.cqqyd2014.web.manage.logic.AttentionLogic.getArrayListModelFromArrayListView(session, mds, user_id, com_id);
			
			sm.setO(atts);
			sm.setSuccess(true);
			tx.commit();
			
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);
			sm.setBody(e.toString());
			
		}


			finally {
				HibernateSessionFactory.closeSession();
			}
	
		msg=sm.toMap();
		return SUCCESS;
	}
}