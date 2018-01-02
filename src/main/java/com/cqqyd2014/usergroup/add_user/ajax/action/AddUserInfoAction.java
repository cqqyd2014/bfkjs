package com.cqqyd2014.usergroup.add_user.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.util.exception.AjaxSuccessMessageException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/usergroup") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
public class AddUserInfoAction      extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String login_id;
	String login_name;
	String passwd;
	String email;
	String tell;
	java.math.BigDecimal pickup_weighting;
	java.math.BigDecimal send_weighting;
	String role_id;
	String sender_name;
	String sender_addr;
	String sender_province;
	String sender_city;
	String pars;
	public String getPars() {
		return pars;
	}

	public void setPars(String pars) {
		this.pars = pars;
	}

	public String getSender_tell() {
		return sender_tell;
	}

	public void setSender_tell(String sender_tell) {
		this.sender_tell = sender_tell;
	}
	String sender_district;
	String sender_com;
	String sender_tell;



	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

	public String getSender_addr() {
		return sender_addr;
	}

	public void setSender_addr(String sender_addr) {
		this.sender_addr = sender_addr;
	}

	public String getSender_province() {
		return sender_province;
	}

	public void setSender_province(String sender_province) {
		this.sender_province = sender_province;
	}

	public String getSender_city() {
		return sender_city;
	}

	public void setSender_city(String sender_city) {
		this.sender_city = sender_city;
	}

	public String getSender_district() {
		return sender_district;
	}

	public void setSender_district(String sender_district) {
		this.sender_district = sender_district;
	}

	public String getSender_com() {
		return sender_com;
	}

	public void setSender_com(String sender_com) {
		this.sender_com = sender_com;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public java.math.BigDecimal getPickup_weighting() {
		return pickup_weighting;
	}

	public void setPickup_weighting(java.math.BigDecimal pickup_weighting) {
		this.pickup_weighting = pickup_weighting;
	}

	public java.math.BigDecimal getSend_weighting() {
		return send_weighting;
	}

	public void setSend_weighting(java.math.BigDecimal send_weighting) {
		this.send_weighting = send_weighting;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	String order_from_id;
	String order_from_name;
	String e_id;



	public String getOrder_from_id() {
		return order_from_id;
	}

	public void setOrder_from_id(String order_from_id) {
		this.order_from_id = order_from_id;
	}

	public String getOrder_from_name() {
		return order_from_name;
	}

	public void setOrder_from_name(String order_from_name) {
		this.order_from_name = order_from_name;
	}

	public String getE_id() {
		return e_id;
	}

	public void setE_id(String e_id) {
		this.e_id = e_id;
	}

	@Action(value = "add_user_info", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String del_user_price() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		

Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			
			//登录名是否重复
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
			com.cqqyd2014.hibernate.entities.SysUser su=sudao.getEntiyByLogin(session, login_id, com_id);
			if (su!=null) {
				throw new
				 com.cqqyd2014.util.exception.AjaxSuccessMessageException("登录名重复");
			}
			//发货渠道代码是否重复
			
			com.cqqyd2014.hibernate.dao.OrderFromDAO ofdao=new com.cqqyd2014.hibernate.dao.OrderFromDAO();
			com.cqqyd2014.hibernate.entities.OrderFrom of=ofdao.getEntityByTypeCode(session,order_from_id,com_id);
			if (of!=null) {
				throw new
				 com.cqqyd2014.util.exception.AjaxSuccessMessageException("发货渠道代码重复");
			}
			
			
			com.cqqyd2014.usergroup.model.SysUser su_new=new com.cqqyd2014.usergroup.model.SysUser();
			su_new.setCom_id(com_id);
			String user_uuid=com.cqqyd2014.util.StringUtil.getUUID();
			su_new.setUuid(user_uuid);
			su_new.setCreate_date(new java.util.Date());
			su_new.setDisplay_name(login_name);
			su_new.setEffective(true);
			su_new.setEmail(email);
			su_new.setLast_online_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			su_new.setLogin_user(login_id);
			su_new.setOn_line(false);
			su_new.setPassword(passwd);
			su_new.setPassword_md5(com.cqqyd2014.util.StringUtil.md5(passwd));
			su_new.setPickup_weighting(pickup_weighting);
			su_new.setQuota_add(new java.math.BigDecimal(0));
			su_new.setQuota_freez(new java.math.BigDecimal(0));
			su_new.setQuota_current(new java.math.BigDecimal(0));
			su_new.setQuota_substract(new java.math.BigDecimal(0));
			su_new.setSend_weighting(send_weighting);
			su_new.setTell(tell);
			
			
			com.cqqyd2014.usergroup.logic.SysUserLogic sul=new com.cqqyd2014.usergroup.logic.SysUserLogic();
			sul.save(session, su_new);
			//更新用户组
			com.cqqyd2014.usergroup.model.UserRole ur=new com.cqqyd2014.usergroup.model.UserRole();
			com.cqqyd2014.usergroup.logic.UserRoleLogic url=new com.cqqyd2014.usergroup.logic.UserRoleLogic();
			ur.setCom_id(com_id);
			ur.setRole_id(role_id);
			ur.setUser_uuid(user_uuid);
			url.save(session, ur);
			//设置发货渠道
			com.cqqyd2014.order.model.OrderFrom ofm=new com.cqqyd2014.order.model.OrderFrom(order_from_id, order_from_name);
			ofm.setCom_id(com_id);
			ofm.setDesc(order_from_name);
			ofm.setE_id(e_id);
			ofm.setSender(sender_name);
			ofm.setSender_addr(sender_addr);
			ofm.setSender_city(sender_city);
			ofm.setSender_com(sender_com);
			ofm.setSender_district(sender_district);
			ofm.setSender_full_addr(sender_province+" "+sender_city+" "+sender_district+" "+sender_addr);
			ofm.setSender_province(sender_province);
			ofm.setSender_tell(sender_tell);
			ofm.setSeq("0001");
			
			com.cqqyd2014.order.logic.OrderFromLogic.save(session, ofm);
			//发货渠道与人员关联
			com.cqqyd2014.order.model.OrderFromUser ofu=new com.cqqyd2014.order.model.OrderFromUser();
			ofu.setCom_id(com_id);
			ofu.setType_code(order_from_id);
			ofu.setUserid(user_uuid);
			
			com.cqqyd2014.order.logic.OrderFromUserLogic.save(session, ofu);
			
			//设置默认参数
			String[] par_array=pars.split(",");
			int par_count=(par_array.length+1)/3;
			com.cqqyd2014.usergroup.logic.UserParLogic upl=new com.cqqyd2014.usergroup.logic.UserParLogic();
			for (int i=0;i<par_count;i++) {
				com.cqqyd2014.usergroup.model.UserPar up=new com.cqqyd2014.usergroup.model.UserPar();
				up.setCom_id(com_id);
				up.setParam(par_array[i*3]);

				up.setParam_desc(par_array[i*3+2]);
				up.setUserid(user_uuid);
				up.setValue(par_array[i*3+1]);

				upl.save(session, up);
				
			}
			
				
				sm.setSuccess(true);
				
			
			
			
			
tx.commit();
		
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		sm.setSuccess(false);
		sm.setBody(e.toString());
		System.out.println(e.getMessage());
		
		
	}
		catch(AjaxSuccessMessageException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}