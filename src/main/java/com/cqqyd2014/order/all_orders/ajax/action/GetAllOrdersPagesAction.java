package com.cqqyd2014.order.all_orders.ajax.action;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetAllOrdersPagesAction extends com.cqqyd2014.order.common.ajax.action.OrderListAjaxAction{

	@Override
	public ArrayList<String> getOrders(Session session, Date start_date, Date end_date, String com_id, String rows,
			String page, String order_status, String user_name, String user_tell, String goods_name, String original_id,
			String barcode, String express_no, String package_user, String send_user,String user_id) {
		// TODO Auto-generated method stub
		
		java.util.ArrayList<String> gts=new java.util.ArrayList<>();
		java.util.ArrayList<String> emss=new java.util.ArrayList<>();
		java.util.ArrayList<String> orders=new java.util.ArrayList<>();
		if (order_status!=null) {
			orders.add(order_status);
		}
		
		com.cqqyd2014.hibernate.dao.VOrderMainGoodsBarcodeDAO vomgbdao=new com.cqqyd2014.hibernate.dao.VOrderMainGoodsBarcodeDAO();
		java.util.ArrayList<String> order_no_list=vomgbdao.getOrderNos(session, start_date, end_date, com_id, rows, page, orders, user_name, user_tell, goods_name, original_id, barcode, express_no,null,null,gts,emss);
		
		
		return order_no_list;
	}

	@Action(value = "get_all_orders_pages", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String getJson() throws Exception {
		// TODO Auto-generated method stub
		setMsg(getList());
		return SUCCESS;
	}
	
	
}
