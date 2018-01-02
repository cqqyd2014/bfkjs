package com.cqqyd2014.weixin.scan_qr_code.ajax.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import com.cqqyd2014.order.model.DeliverBillDetail;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/weixin")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetBarcodeAction   extends ActionSupport {
	private Map<String, Object> msg;

	String barcode;
	String com_id;
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_barcode", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String get_barcode() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			//先生成查询记录
			HttpServletRequest request = ServletActionContext.getRequest();
			String ip=com.cqqyd2014.util.ServletUtil.getIpAddr(request);
			java.util.Date now=new java.util.Date();
			com.cqqyd2014.weixin.model.ScanQrLog sq=new com.cqqyd2014.weixin.model.ScanQrLog();
			sq.setBarcode(barcode);
			sq.setCom_id(com_id);
			sq.setEffective(true);
			sq.setIp(ip);
			sq.setScan_dat(now);
			
			com.cqqyd2014.weixin.logic.ScanQrLogLogic.save(session, sq);
			
			session.flush();
			sm=com.cqqyd2014.wh.logic.GoodsLogic.getAjaxMessageModelByBarcode(sm, session, barcode, com_id);
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbs=(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail>)sm.getO5();
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds_array=(ArrayList<DeliverBillDetail>)com.cqqyd2014.util.ArrayListTools.getArrayListSearchBooleanField(dbs, "isReturned", false);

			if (dbds_array.size()>0) {
				sm.setO5(dbds_array.get(0));
			}
			else {
				sm.setO5(null);
			}
			
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> sqls=(java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog>)sm.getO4();
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> sqls_array=(java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog>)com.cqqyd2014.util.ArrayListTools.getArrayListSearchBooleanField(sqls, "isEffective", true);
			
			
			sm.setO4(sqls_array);
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
			catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException ex){
				if (null != tx) {
					tx.rollback();// 撤销事务

				}
				
				
				sm.setSuccess(false);
				sm.setBody(ex.getMessage());
			}

			finally {
				HibernateSessionFactory.closeSession();
			}
	
		msg=sm.toMap();
		return SUCCESS;
	}
}