package com.cqqyd2014.order.pickup_package.ajax.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;

import com.cqqyd2014.order.common.ajax.action.OrderCountAjaxAction;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")

public class GetWaitPackageCountAction extends OrderCountAjaxAction{
	

	@Action(value = "get_wait_package_count", results = { @Result(type = "json", params = { "root", "msg" }) })
	@Override
	public String getJson() throws Exception {
		// TODO Auto-generated method stub
		setMsg(getCountImpl());
		return SUCCESS;
	}

	@Override
	public java.math.BigInteger getCount(Session session, Date start_date, Date end_date, String com_id, String rows, String page,
			String order_status, String user_name, String user_tell, String goods_name, String original_id,
			String barcode, String express_no, String package_user, String send_user, String user_id) {
		// TODO Auto-generated method stub
		com.cqqyd2014.hibernate.dao.VOrderMainGoodsBarcodeDAO vomd=new com.cqqyd2014.hibernate.dao.VOrderMainGoodsBarcodeDAO();
		String[] gt_array= {"等待拣货","拣货处理"};
		java.util.ArrayList<String> gts=com.cqqyd2014.util.StringUtil.ArrayToArrayList(gt_array);
		java.util.ArrayList<String> emss=new java.util.ArrayList<>();
		java.util.ArrayList<String> orders=com.cqqyd2014.util.StringUtil.toArrayList("订单处理");
		return vomd.getOrderNoCount(session, start_date, end_date, com_id, orders, user_name, user_tell, goods_name, original_id, barcode, express_no,user_id,null,gts,emss);

	}

	

}
