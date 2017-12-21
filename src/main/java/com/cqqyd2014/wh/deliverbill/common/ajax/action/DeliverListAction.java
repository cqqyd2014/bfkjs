package com.cqqyd2014.wh.deliverbill.common.ajax.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public abstract class DeliverListAction extends UserLoginedAction {
	java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> msg;

	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getMsg() {
		return msg;
	}

	public void setMsg(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> msg) {
		this.msg = msg;
	}

	String order_no;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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

	String page;

	String rows;
	String barcode;
	String deliverbill_status;
	String start_dat;
	String end_dat;
	String express_com;
	String express_no;



	String send_user;
	String receiver_name;
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

	String receiver_mobile;


	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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




	public String getSend_user() {
		return send_user;
	}

	public void setSend_user(String send_user) {
		this.send_user = send_user;
	}

	String goods_barcode;

	public String getGoods_barcode() {
		return goods_barcode;
	}

	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}

	public abstract String getJson() throws Exception;

	public abstract java.util.ArrayList<String> getDelivers(Session session, Date start_date, Date end_date,
			String goods_barcode, String deliverbill_status, String express_com, String express_no, String com_id,
			String rows, String receiver_name, String receiver_mobile,  String page,
			String send_user, String create_userid, String order_no);

	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getList() throws Exception {

		super.execute();
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs = null;
		session = HibernateSessionFactory.getSession();

		try {
			java.util.Date start_date = null;
			java.util.Date end_date = null;
			if (start_dat == null || end_dat == null) {
				// 没有指定，只列出最近180天的数据
				start_date = com.cqqyd2014.util.DateUtil
						.JDateToStartDate(com.cqqyd2014.util.DateUtil.getNearDays(new java.util.Date(), -180));
				end_date = com.cqqyd2014.util.DateUtil.JDateToEndDate(new java.util.Date());

			} else {
				start_date = com.cqqyd2014.util.DateUtil.FullStringToJDate(start_dat);
				end_date = com.cqqyd2014.util.DateUtil.FullStringToJDate(end_dat);
			}

			// com.cqqyd2014.hibernate.dao.DeliverMDAO dmdao=new DeliverMDAO();
			if (rows == null) {
				rows = "10";
			}
			if (page == null) {
				page = "1";
			}

			// java.util.ArrayList<String>
			// order_no_list=vomgbdao.getOrderNos(session, start_date, end_date,
			// com_id, rows, page, order_status, user_name, user_tell,
			// goods_name, original_id, barcode,
			// express_no,package_user,send_user);

			java.util.ArrayList<String> arraylist_delivers = getDelivers(session, start_date, end_date, goods_barcode,
					deliverbill_status, express_com, express_no, com_id, rows, receiver_name, receiver_mobile,
					 page, send_user, create_userid, order_no);

			if (arraylist_delivers.size() == 0) {
				dbs = new java.util.ArrayList<>();
			} else {
				// java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM>
				// voms=dmdao.getArrayListByDeliverNoArray(session,
				// arraylist_delivers, com_id);

				dbs = com.cqqyd2014.order.logic.DeliverMLogic.getArrayListModelWithDetail(session, arraylist_delivers,
						com_id);
			}

		}

		catch (Exception e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
		return dbs;

	}

}
