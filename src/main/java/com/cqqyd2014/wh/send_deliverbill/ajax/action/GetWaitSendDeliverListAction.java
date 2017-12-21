package com.cqqyd2014.wh.send_deliverbill.ajax.action;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.Session;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.wh.deliverbill.common.ajax.action.DeliverListAction;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class GetWaitSendDeliverListAction extends DeliverListAction{
	
	@Action(value = "get_wait_send_deliver_list", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_wait_send_deliver_list", privilege = "[00010004]", error_url = "authority_ajax_error")
	@Override
	public String getJson() throws Exception {
		// TODO Auto-generated method stub
		setMsg(getList());
		return SUCCESS;
	}

	



	@Override
	public ArrayList<String> getDelivers(Session session, Date start_date, Date end_date, String goods_barcode,
			String deliverbill_status, String express_com, String express_no, String com_id, String rows,
			String receiver_name, String receiver_mobile, String page, String send_user, String create_userid,
			String order_no) {
		// TODO Auto-generated method stub
		java.util.ArrayList<String> bill_statuss=new java.util.ArrayList<>();
		if (deliverbill_status!=null) {
			bill_statuss.add(deliverbill_status);
		}
		bill_statuss=com.cqqyd2014.util.StringUtil.toArrayList("分派发货");
		com.cqqyd2014.hibernate.dao.VDeliverDDAO vomgbdao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
		java.util.ArrayList<String> deliver_no_list=vomgbdao.getDeliverNos(session, start_date,end_date,goods_barcode, bill_statuss,
				express_com, express_no, com_id, rows, receiver_name,
				 receiver_mobile,  page,  send_user,  create_userid,order_no);
		
		
		return deliver_no_list;
	}}
