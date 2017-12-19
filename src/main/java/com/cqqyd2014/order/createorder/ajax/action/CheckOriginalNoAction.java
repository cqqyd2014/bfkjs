package com.cqqyd2014.order.createorder.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class CheckOriginalNoAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String order_from;
	String original_no;
	String order_from_name;

	public String getOrder_from_name() {
		return order_from_name;
	}

	public void setOrder_from_name(String order_from_name) {
		this.order_from_name = order_from_name;
	}

	public String getOriginal_no() {
		return original_no;
	}

	public void setOriginal_no(String original_no) {
		this.original_no = original_no;
	}

	public String getOrder_from() {
		return order_from;
	}

	public void setOrder_from(String order_from) {
		this.order_from = order_from;
	}

	@Action(value = "check_original_no", results = {@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "check_original_no", privilege = "[00010001]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
		session = HibernateSessionFactory.getSession();
		
		try {
			//是否有单号重复
			
			
			
			//单号长度是否满足电商平台需要
			java.util.ArrayList<com.cqqyd2014.order.model.OrderTypeLen> otls=com.cqqyd2014.order.logic.OrderTypeLenLogic.getArrayModelArraryEntityView(com.cqqyd2014.hibernate.dao.VOrderFromUserLenDAO
					.getArrayListViewByUserIDOrderFrom(session, com_id, user_id, order_from));
			
			java.math.BigDecimal[] len_array=(java.math.BigDecimal[])com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(otls, "getLen", java.math.BigDecimal.class);
			@SuppressWarnings("unchecked")
			java.util.ArrayList<java.math.BigDecimal> len_arraylist=(java.util.ArrayList<java.math.BigDecimal>)com.cqqyd2014.util.ArrayTools.convertArrayToArrayList(len_array);
			//单号长度错误
			java.math.BigDecimal order_len=new java.math.BigDecimal(original_no.length());
			int indexOf=len_arraylist.indexOf(order_len);
			if (indexOf==-1){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("单号长度错误","当前单号长度"+order_len+",但"+order_from_name+"可能存在的单号长度应该为："+com.cqqyd2014.util.ArrayTools.toString(len_array));
				
			}
			
			sm.setSuccess(true);
			
			
			

			
		}

		catch (HibernateException e) {
			

			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);

		}
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e) {
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
			sm.setO(e.getO1());
		}
		
		
		finally {
			HibernateSessionFactory.closeSession();
		}

		msg=sm.toMap();
		return SUCCESS;
	}


}