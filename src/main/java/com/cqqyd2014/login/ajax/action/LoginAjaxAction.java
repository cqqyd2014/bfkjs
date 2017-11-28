package com.cqqyd2014.login.ajax.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.util.exception.AjaxSuccessMessageException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/login")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class LoginAjaxAction extends ActionSupport {
	private Map<String, Object> msg;
	String user_name;
	String password;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Action(value = "login", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String login() {
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Session session = HibernateSessionFactory.getSession();
		com.cqqyd2014.hibernate.entities.SysUser b = null;
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		Transaction tx = session.beginTransaction();
		try {

			if (user_name.trim().length() == 0 || user_name == null) {
				
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("用户名为空");

			}
			if (password.trim().length() == 0 || password == null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("密码为空");
			}

			com.cqqyd2014.hibernate.dao.SysUserDAO sudao = new com.cqqyd2014.hibernate.dao.SysUserDAO();
			b = sudao.getEntiyByLogin(session, user_name,  com_id);
			if (b==null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("用户不存在");
			}
			if (!b.getPwd().equals(com.cqqyd2014.util.StringUtil.md5(password))) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("密码错误");
			}
			
				// System.out.println("设置信息");
				session_http.put("user_name", b.getName());
				session_http.put("user_login", b.getUserLogin());
				session_http.put("user_id", b.getId());
				//设置权限
				com.cqqyd2014.hibernate.dao.MenuDDAO mddao=new com.cqqyd2014.hibernate.dao.MenuDDAO();
				java.util.ArrayList<com.cqqyd2014.system.model.MenuD> menuds=mddao.getMenuDAll(session, com_id,b.getId());
				java.util.ArrayList<String> menu_array=new java.util.ArrayList<String>();
				for (int i=0;i<menuds.size();i++) {
					menu_array.add(menuds.get(i).getM_id()+menuds.get(i).getM_d_id());
				}
				
				
				session_http.put("menu_array", menu_array);
				sudao.setOnline(session, b.getId(), com_id);

				HttpServletRequest request = ServletActionContext.getRequest();
				String ip = com.cqqyd2014.util.IPUtil.getIpAddr(request);
				com.cqqyd2014.hibernate.dao.SysLogDAO sldao = new com.cqqyd2014.hibernate.dao.SysLogDAO();
				sldao.saveLog(session, b.getId(), "登录系统,来自IP：" + ip, "1", com_id);
				sldao = null;
				sm.setSuccess(true);

			
			tx.commit();

		}

		catch (HibernateException e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			sm.setSuccess(false);
			sm.setBody(e.toString());

		} 
		
		catch (AjaxSuccessMessageException e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			sm.setSuccess(false);
			sm.setBody(e.getMessageString());

		} 
		
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return SUCCESS;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
}
