package com.cqqyd2014.order.all_orders.ajax.action;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.Session;

import com.cqqyd2014.annotation.Authority;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
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
		
		
		java.util.ArrayList<String> order_no_list=com.cqqyd2014.hibernate.dao.VOrderMainGoodsBarcodeDAO.getOrderNos(session, start_date, end_date, com_id, rows, page, orders, user_name, user_tell, goods_name, original_id, barcode, express_no,null,null,gts,emss);
		
		
		return order_no_list;
	}

	@Action(value = "get_all_orders_pages", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_all_orders_pages", privilege = "[00010002]", error_url = "authority_ajax_error")
	public String getJson()  {
		// TODO Auto-generated method stub
		setMsg(getList());
		return SUCCESS;
	}
	
	
}
