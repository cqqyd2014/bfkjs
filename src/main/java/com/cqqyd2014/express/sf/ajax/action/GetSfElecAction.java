package com.cqqyd2014.express.sf.ajax.action;

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

import com.cqqyd2014.express.sf.bsp.impl.BspHttpClientOrder;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.order.model.DeliverBill;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/express/sf") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
public class GetSfElecAction      extends ActionSupport {
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
	String wh_id;
	String userid;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	String order_no;
	String logistics_vehicle;
	public String getLogistics_vehicle() {
		return logistics_vehicle;
	}

	public void setLogistics_vehicle(String logistics_vehicle) {
		this.logistics_vehicle = logistics_vehicle;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	@Action(value = "get_sf_elec_bill", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String get_sf_elec_bill() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		
		String com_id = (String) session_http.get("com_code");
		

Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			
			//String order_no="QYDTB201610240001391";
			

			
			//生成顺丰订单报文对象
			
			com.cqqyd2014.hibernate.dao.VDeliverMMaxDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverMMaxDAO();
			String seq=dmdao.getNewOrderSeq(session, order_no, com_id);
			com.cqqyd2014.order.model.DeliverBill db=new com.cqqyd2014.order.model.DeliverBill();
			db.setActual_weight(new java.math.BigDecimal(0));
			db.setCancel_confirm_memo("");
			db.setCancel_confirm_userid("");
			db.setCancel_confrim_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setCancel_request_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setCancel_request_memo("");
			db.setCancel_request_userid("");
			db.setCancel_status("");
			db.setCarton_type("0001");
			db.setCarton_weight(new java.math.BigDecimal(0));
			db.setCom_id(com_id);
			db.setCreate_userid(userid);
			db.setDbds(new java.util.ArrayList<>());
			db.setDeliver_bill_status("分配单号");
			db.setDeliver_no(order_no+seq);
			db.setEffective(true);
			db.setExpress_com("500528000A");
			db.setExpress_no("");
			db.setLogistics_fb_code("");
			db.setLogistics_fb_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setLogistics_fb_memo("");
			db.setLogistics_fb_status("");
			db.setMemo_barcodes("");
			db.setMemo_names("");
			db.setNum(new java.math.BigDecimal(0));
			db.setOrder_no(order_no);
			db.setPackage_weight(new java.math.BigDecimal(0));
			db.setPre_package_barcode("");
			db.setPackage_userid(userid);
			db.setPackage_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setPackage_weight(new java.math.BigDecimal(0));
			db.setSend_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setSend_user_assgin_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setSend_userid("");
			db.setSended(false);
			db.setSeq(seq);
			db.setVehicle_id(logistics_vehicle);
			db.setWh_id(wh_id);
			
			BspHttpClientOrder bhco1=new BspHttpClientOrder(session, db);
			
			bhco1.initBill();
			DeliverBill dm=(DeliverBill)bhco1.post();
			session.flush();
			
				
				sm.setSuccess(true);
				sm.setO(dm);
			
			
			
			
tx.commit();
		
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		
		
	}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}