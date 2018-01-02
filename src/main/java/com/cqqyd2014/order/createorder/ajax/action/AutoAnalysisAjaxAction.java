package com.cqqyd2014.order.createorder.ajax.action;
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

import com.cqqyd2014.util.taobao.OrderAutoAnalysisException;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class AutoAnalysisAjaxAction  extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String memo;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Action(value = "auto_analysis", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "[00010001]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
		if (memo.length()==0){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("输入为空");
		}
		com.cqqyd2014.hibernate.entities.COrderMain om=new com.cqqyd2014.hibernate.entities.COrderMain();
		com.cqqyd2014.hibernate.entities.COrderMainId omid=new com.cqqyd2014.hibernate.entities.COrderMainId();
		String s=memo;
		com.cqqyd2014.util.taobao.OrderAutoAnalysis oaa=new com.cqqyd2014.util.taobao.OrderAutoAnalysis(s);
		omid.setOrderNo(oaa.getOrder_no());
		om.setId(omid);
		om.setCUserName(oaa.getUser_name());
		om.setAddrCity(oaa.getCity());
		om.setAddrDistrict(oaa.getDistrict());
		om.setAddrProvince(oaa.getProvince());
		om.setCTell(oaa.getTell());
		om.setTell2(oaa.getTell2());
		om.setCUserAddr(oaa.getUser_addr());
		om.setOriginalId(oaa.getOrder_no());

		
		
			
			
			

			
			
			tx.commit();

			sm.setSuccess(true);
			sm.setO(om);
		}

		catch (OrderAutoAnalysisException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
		}
		catch (HibernateException e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);

		}catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		msg=sm.toMap();
		return SUCCESS;
	}
}
