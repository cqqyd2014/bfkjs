package com.cqqyd2014.order.common.ajax.action;




import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.order.logic.OrderLogic;



@SuppressWarnings("serial")
public abstract class OrderListAjaxAction extends UserLoginedAction{
	java.util.ArrayList<com.cqqyd2014.order.model.Order> msg;
	public java.util.ArrayList<com.cqqyd2014.order.model.Order> getMsg() {
		return msg;
	}
	public void setMsg(java.util.ArrayList<com.cqqyd2014.order.model.Order> msg) {
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
	public abstract java.util.ArrayList<String> getOrders(Session session, java.util.Date start_date,java.util.Date end_date,String com_id,String rows,String page,String order_status,String user_name,String user_tell,String goods_name,String original_id,String barcode,String express_no,String package_user,String send_user,String user_id); 
	public java.util.ArrayList<com.cqqyd2014.order.model.Order> getList() throws Exception {
		
		
		//查询条件预处理
		
		if(order_status.equals("0")) {
			//全部订单
			order_status=null;
		}

		// TODO Auto-generated method stub


		super.execute();
		
		Session session = HibernateSessionFactory.getSession();

		java.util.ArrayList<com.cqqyd2014.order.model.Order> items=new java.util.ArrayList<>();
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

			
			
			//com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			
			//com.cqqyd2014.hibernate.dao.DeliverMDAO dmdao=new DeliverMDAO();
			if (rows==null){
				rows="10";
			}
			if (page==null){
				page="1";
			}
			
			
			//java.util.ArrayList<String> order_no_list=vomgbdao.getOrderNos(session, start_date, end_date, com_id, rows, page, order_status, user_name, user_tell, goods_name, original_id, barcode, express_no,package_user,send_user);
			
			
			java.util.ArrayList<String> order_nos=getOrders(session, start_date, end_date, com_id, rows, page, order_status, user_name, user_tell, goods_name, original_id, barcode, express_no,package_user,send_user,user_id);
			//java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=vomdao.getListByOrderNoArray(session, order_no_list, com_id);
			
			
			if (order_nos.size()==0) {
				items=new java.util.ArrayList<>();
			}
			else {
				
				
				
				items=OrderLogic.getArrayListModelWithDetailDeliverM(session, order_nos, com_id);
			}
			
			
			
			
			
			
			
			
			
			
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
		return items;
		
		
	}
	


}
