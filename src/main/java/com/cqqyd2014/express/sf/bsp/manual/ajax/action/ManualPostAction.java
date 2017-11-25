package com.cqqyd2014.express.sf.bsp.manual.ajax.action;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.order.model.Order;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/express/sf")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class ManualPostAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String post_text;
	String order_no;
	String com_id;
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	String seq;
	String method;


	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getPost_text() {
		return post_text;
	}

	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}

	@Action(value = "manual_post", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String manual_order() throws Exception {
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.VOrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain om=omdao.getVOrderMain(session, com_id, order_no);
			com.cqqyd2014.hibernate.dao.OrderFromDAO ofdao=new com.cqqyd2014.hibernate.dao.OrderFromDAO();
			com.cqqyd2014.hibernate.entities.OrderFrom of=ofdao.getObjectByOrderNo(session,  order_no, com_id);
			
			
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getOrderModelFromHiberanteEntities(om,of);
			java.util.ArrayList<Order> orders=com.cqqyd2014.order.logic.OrderLogic.transOneToArray(order);
			Class clazz=Class.forName("com.cqqyd2014.express.sf.bsp.impl."+method);
//	        Person p=(Person)clazz.newInstance();    //通过无参构造创建对象
//	        p.say();
	        Constructor c=clazz.getConstructor(org.hibernate.Session.class,java.util.ArrayList.class);//获取有参构造
	         
			
			
	        Object bhco1=c.newInstance(session,orders);  
			
			System.out.println(post_text);
			sm.setSuccess(true);
			Method m1 = clazz.getDeclaredMethod("setDecodeURIXml",new Class[]{String.class});
			m1.invoke(bhco1,new Object[] {post_text}); 
			Method m2 = clazz.getDeclaredMethod("setSeqs",new Class[]{java.util.ArrayList.class});
			m2.invoke(bhco1,new Object[] {com.cqqyd2014.util.StringUtil.toArrayList(seq)}); 
			Method m3 = clazz.getDeclaredMethod("post");
			String post_str=(String)m3.invoke(bhco1); 
			
			sm.setBody(post_str);
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
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}

		

}