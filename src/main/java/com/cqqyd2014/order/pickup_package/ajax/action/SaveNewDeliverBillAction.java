package com.cqqyd2014.order.pickup_package.ajax.action;



import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;



@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class SaveNewDeliverBillAction extends UserLoginedAction{
	String wh_id;
	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	String seq;
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	String vehicle;
	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	String order_no;
	String logistics;
	String express_no;
	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	private Map<String, Object> msg;



	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getExpress_no() {
		return express_no;
	}

	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "save_new_deliver_bill", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "[00010003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
Session session = HibernateSessionFactory.getSession();


Transaction tx = session.beginTransaction();
		try {
			
			
			com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vomdao.getVOrderMain(session, com_id, order_no);
			
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getOrderModelFromHiberanteEntities(vom);
			if (com.cqqyd2014.order.logic.OrderLogic.check_if_cancel(session, order, user_id, "保存发货单")){
				sm.setSuccess(false);
				sm.setBody("订单已经被客户申请取消");
				msg = sm.toMap();
				return "success";
				
			}
			
			
			
			seq=com.cqqyd2014.order.logic.OrderLogic.getSeq(session, seq, order_no, express_no, com_id);
			//生成deliverbill对象
			com.cqqyd2014.order.model.DeliverBill db=new com.cqqyd2014.order.model.DeliverBill();
			db.setCom_id(com_id);
			db.setOrder_no(order_no);
			db.setSeq(seq);
			
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=new java.util.ArrayList<>();
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis= (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
					.get("temp_deliver_picked_sn");
			com.cqqyd2014.hibernate.dao.GoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.GoodsInfoDAO();
			com.cqqyd2014.hibernate.entities.GoodsInfo gi=null;
			java.math.BigDecimal total_package_weight=new java.math.BigDecimal(0);
			int i=0;
			for ( ;i<odis.size();i++){
				
				com.cqqyd2014.wh.model.Goods g=odis.get(i);
				com.cqqyd2014.order.model.DeliverBillDetail dbd=new com.cqqyd2014.order.model.DeliverBillDetail();
				dbd.setCom_id(com_id);
				dbd.setGoods_barcode(g.getBarcode());
				dbd.setGoods_id(g.getGoods_id());
				gi=gidao.getEntityByGoodsId(session, g.getGoods_id(), com_id);
				dbd.setGross_weight(gi.getGrossWeight());
				dbd.setNet_weight(gi.getNetWeight());
				dbd.setPackage_weight(gi.getPackageWeight());
				dbd.setReturned(false);
				dbd.setReturned_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
				dbd.setReturned_memo("");
				dbd.setReturned_userid("");
				total_package_weight=total_package_weight.add(gi.getPackageWeight());
				//dbd.setNum(new java.math.BigDecimal(1));
				dbd.setOrder_no(order_no);
				dbd.setSeq(seq);
				dbd.setUuid(com.cqqyd2014.util.StringUtil.getUUID());
				dbds.add(dbd);
				
				com.cqqyd2014.wh.logic.GoodsLogic.LockItemsForPickup(session, g.getBarcode(), com_id,user_id);
				com.cqqyd2014.wh.logic.Storage.LockItemsForPickup(session,g.getGoods_id(), wh_id, new java.math.BigDecimal(1), com_id);
				
				com.cqqyd2014.wh.logic.Storage.addStorage(session, g.getGoods_id(), "ORDER_", "DEFAUL", new java.math.BigDecimal("-1"), com_id);
			}
			
			com.cqqyd2014.order.logic.DeliverDLogic.save(session, dbds);
			
			db.setNum(new java.math.BigDecimal(i));
			db.setPackage_weight(total_package_weight);
			
			db.setActual_weight(new java.math.BigDecimal(0));
			db.setCancel_confirm_memo("");
			db.setCancel_confirm_userid("");
			db.setCancel_confrim_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setCancel_request_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setCancel_request_memo("");
			db.setCancel_request_userid("");
			db.setCancel_status("");
			db.setCarton_type("0001");//初始为一号纸箱
			db.setCarton_weight(new java.math.BigDecimal(0));
			db.setCom_id(com_id);
			java.util.Date now=new java.util.Date();
			db.setPackage_dat(now);
			//db.setDbds(dbds);
			db.setDeliver_bill_status("拣货完成");
			
			String derliver_no=order_no+seq;
			db.setDeliver_no(derliver_no);
			db.setEffective(true);
			db.setExpress_com(logistics);
			db.setExpress_no(express_no);
			db.setLogistics_fb_code("");
			db.setLogistics_fb_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setLogistics_fb_memo("");
			db.setLogistics_fb_status("");
			db.setOrder_no(order_no);
			db.setPackage_userid(user_id);
			db.setPre_package_barcode("");
			db.setSend_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setSend_user_assgin_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
			db.setSended(false);
			db.setSeq(seq);
			db.setVehicle_id(vehicle);
			db.setWh_id(wh_id);
			db.setSend_userid("");
			db.setMemo_barcodes(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(odis, "getBarcode"));
			db.setMemo_names(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(odis, "getGoods_name"));

			com.cqqyd2014.order.logic.DeliverMLogic.save(session, db);
			
			
			
			
			
			
			
			session.flush();
			
			
			

			
			
			sm.setSuccess(true);
			sm.setBody(seq);
			tx.commit();
			

		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
		}

		finally {
			HibernateSessionFactory.closeSession();
		}

		
		msg = sm.toMap();
		return "success";
	}

}