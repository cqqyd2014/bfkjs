package com.cqqyd2014.order.common.ajax.action;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class OrderCountAjaxAction extends ActionSupport{
	
	
	public  abstract java.math.BigInteger getCount(Session session, Date start_date, Date end_date, String com_id, String rows,
			String page, String order_status, String user_name, String user_tell, String goods_name, String original_id,
			String barcode, String express_no, String package_user, String send_user,String user_id);
	java.math.BigInteger msg;
	public java.math.BigInteger getMsg() {
		return msg;
	}
	public void setMsg(java.math.BigInteger msg) {
		this.msg = msg;
	}
	String page;
	String rows;
	String order_status;
	String user_name;
	String user_tell;
	String goods_name;
	String original_id;
	String barcode;
	String express_no;
	String package_user;
	String send_user;
	String start_dat;

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_tell() {
		return user_tell;
	}
	public void setUser_tell(String user_tell) {
		this.user_tell = user_tell;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(String original_id) {
		this.original_id = original_id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public String getPackage_user() {
		return package_user;
	}
	public void setPackage_user(String package_user) {
		this.package_user = package_user;
	}
	public String getSend_user() {
		return send_user;
	}
	public void setSend_user(String send_user) {
		this.send_user = send_user;
	}
	public String getStart_dat() {
		return start_dat;
	}
	public void setStart_dat(String start_dat) {
		this.start_dat = start_dat;
	}
	String end_dat;
	
	public String getEnd_dat() {
		return end_dat;
	}
	public void setEnd_dat(String end_dat) {
		this.end_dat = end_dat;
	}
	public abstract String getJson() throws Exception;
	
	public java.math.BigInteger getCountImpl() throws Exception {
		
		java.math.BigInteger order_count=new java.math.BigInteger("0");

		// TODO Auto-generated method stub


		//String user_name = (String) this.session.get("USER_NAME");
		Map session_http = ActionContext.getContext().getSession();
		String user = (String) session_http.get("USER");
		//String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();

		//java.util.ArrayList<com.cqqyd2014.order.model.Order> items=new java.util.ArrayList<>();
		Transaction tx = session.beginTransaction();
		try {
			java.util.Date start_date=null;
			java.util.Date end_date=null;
			if (start_dat==null||end_dat==null){
				//没有指定，只列出最近180天的数据
				start_date=com.cqqyd2014.util.DateUtil.JDateToStartDate(com.cqqyd2014.util.DateUtil.getNearDays(new java.util.Date(), -180));
				end_date=com.cqqyd2014.util.DateUtil.JDateToEndDate(new java.util.Date());
				
			}
			else{
				start_date=com.cqqyd2014.util.DateUtil.FullStringToJDate(start_dat);
				end_date=com.cqqyd2014.util.DateUtil.FullStringToJDate(end_dat);
			}

			order_count=getCount(session, start_date,end_date, com_id, rows, page,
					order_status, user_name, user_tell, goods_name, original_id,
					barcode,express_no, package_user, send_user, user_id);
			
			
			
			
			
			tx.commit();
			
		}

		catch (Exception e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			

			
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return order_count;
		
		
	}
	


}