package com.cqqyd2014.wh.deliverbill.common.ajax.action;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class DeliverCountAction extends ActionSupport{
	
	
	public  abstract java.math.BigInteger getCount(Session session,java.util.Date start_dat,java.util.Date end_dat,String goods_barcode,String deliverbill_status ,String express_com,String express_no,String com_id,String rows,String receiver_name,String receiver_mobile,String reciever_addr,String page,String send_user,String create_userid,String order_no);
	java.math.BigInteger msg;
	public java.math.BigInteger getMsg() {
		return msg;
	}
	public void setMsg(java.math.BigInteger msg) {
		this.msg = msg;
	}
	String original_id;
	public String getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(String original_id) {
		this.original_id = original_id;
	}
	String create_userid;
	public String getCreate_userid() {
		return create_userid;
	}
	public void setCreate_userid(String create_userid) {
		this.create_userid = create_userid;
	}
	String order_no;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	String goods_barcode;
	String page;
	String rows;
	String deliverbill_status;
	String start_dat;
	String end_dat;
	String express_com;
	String express_no;
	String receiver_name;
	String receiver_mobile;
	String reciever_addr;
	String send_user;

	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
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
	public String getDeliverbill_status() {
		return deliverbill_status;
	}
	public void setDeliverbill_status(String deliverbill_status) {
		this.deliverbill_status = deliverbill_status;
	}
	public String getStart_dat() {
		return start_dat;
	}
	public void setStart_dat(String start_dat) {
		this.start_dat = start_dat;
	}
	public String getEnd_dat() {
		return end_dat;
	}
	public void setEnd_dat(String end_dat) {
		this.end_dat = end_dat;
	}
	public String getExpress_com() {
		return express_com;
	}
	public void setExpress_com(String express_com) {
		this.express_com = express_com;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public String getReciever_addr() {
		return reciever_addr;
	}
	public void setReciever_addr(String reciever_addr) {
		this.reciever_addr = reciever_addr;
	}
	public String getSend_user() {
		return send_user;
	}
	public void setSend_user(String send_user) {
		this.send_user = send_user;
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

			order_count=getCount(session,start_date,end_date,goods_barcode,deliverbill_status ,express_com,express_no,com_id,rows,receiver_name,receiver_mobile,reciever_addr,page,send_user,create_userid,order_no);
			
			
			
			
			
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