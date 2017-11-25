package com.cqqyd2014.wh.send_deliverbill.ajax.action;

import java.math.BigInteger;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;

import com.cqqyd2014.order.common.ajax.action.OrderCountAjaxAction;
import com.cqqyd2014.wh.deliverbill.common.ajax.action.DeliverCountAction;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/wh")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class GetWaitSendDeliverCountAction extends DeliverCountAction{
	

	@Action(value = "get_wait_send_deliver_count", results = { @Result(type = "json", params = { "root", "msg" }) })
	@Override
	public String getJson() throws Exception {
		// TODO Auto-generated method stub
		setMsg(getCountImpl());
		return SUCCESS;
	}



	@Override
	public BigInteger getCount(Session session,java.util.Date start_date,java.util.Date end_date, String goods_barcode, String deliverbill_status, String express_com,
			String express_no, String com_id, String rows, String receiver_name, String receiver_mobile,
			String reciever_addr, String page, String send_user, String create_userid,String order_no) {
		// TODO Auto-generated method stub
		com.cqqyd2014.hibernate.dao.VDeliverDDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
		java.util.ArrayList<String> deliverbill_statuss=new java.util.ArrayList<>();

		//分派发货
		deliverbill_statuss=com.cqqyd2014.util.StringUtil.toArrayList("分派发货");
		return dmdao.getDeliverNoCount(session, start_date, end_date, goods_barcode, deliverbill_statuss, express_com, express_no, com_id, rows, receiver_name, receiver_mobile, reciever_addr, page, send_user, create_userid,order_no);
	}

	

}
