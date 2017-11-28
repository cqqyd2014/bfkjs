package com.cqqyd2014.wh.prepackage.ajax.action;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/wh")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class CheckPrepackageBarcodeAction   extends ActionSupport {
	String prepackage_barcode;

	public String getPrepackage_barcode() {
		return prepackage_barcode;
	}

	public void setPrepackage_barcode(String prepackage_barcode) {
		this.prepackage_barcode = prepackage_barcode;
	}
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "check_prepackage_barcode", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String check_prepackage_barcode() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
		//测试这个条码是否存在，是否为未打包过的条码
		com.cqqyd2014.hibernate.dao.PrePackageDAO gid=new com.cqqyd2014.hibernate.dao.PrePackageDAO();
		com.cqqyd2014.hibernate.entities.PrePackageM psw=gid.getEntityByPrepackageBarcode(session, com_id, prepackage_barcode);
		if (psw==null){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该预包装条码不存在");
			
		}
		
		if (!psw.getEffective()){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该预包装条码已经作废");
			
		}
		if (!psw.getPrinted()){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该预包装条码未打印");
			
		}
		if (psw.getPackaged()){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该预包装条码已经用于打包");
			
		}
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis=(java.util.ArrayList<com.cqqyd2014.wh.model.Goods>)session_http.get("temp_add_prepackage_barcode");
		sm.setO(odis);
		sm.setSuccess(true);
		sm.setBody("该预包装条码可用");
		sm.setSound("picked_ok");
		
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
			catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
				sm.setSuccess(false);
				sm.setBody(e.getMessageString());
				sm.setSound("error");
				if (null != tx) {
					tx.rollback();// 撤销事务

				}
			}
		msg=sm.toMap();
		return SUCCESS;
	}
}