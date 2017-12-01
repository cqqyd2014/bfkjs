package com.cqqyd2014.wh.make_new_barcode.ajax.action;
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
@Namespace("/wh")
public class GetNewBarcodeUnPrintedAjaxAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_new_barcode_un_printed", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "set_all_arrival", privilege = "[00020002]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.VGoodsDAO vsd=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods> sws=vsd.getUnPrintGoodsBarcode(session, com_id,user_id);
		
			sm.setO(sws);
			sm.setSuccess(true);
			
			tx.commit();
			// session.close();
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.toString());
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		 finally {
				HibernateSessionFactory.closeSession();
			}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}