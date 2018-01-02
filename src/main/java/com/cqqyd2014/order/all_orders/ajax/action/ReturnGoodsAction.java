package com.cqqyd2014.order.all_orders.ajax.action;


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
import com.cqqyd2014.hibernate.dao.VWeixinScanQrLogDAO;
import com.cqqyd2014.util.hashmap.HashMapToolsCompareResult;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class ReturnGoodsAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String wh_id;
	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	String goods_barcode;
	String order_no;
	String seq;
	String goods_id;
	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	String memo;
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getGoods_barcode() {
		return goods_barcode;
	}

	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	@Action(value = "return_goods", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "set_all_arrival", privilege = "[00010002]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.order.model.ReturnGoods rg=new com.cqqyd2014.order.model.ReturnGoods();
			rg.setCom_id(com_id);
			rg.setGoods_barcode(goods_barcode);
			rg.setGoods_id(goods_id);
			rg.setMemo(memo);
			rg.setOp_date(new java.util.Date());
			
			com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vomdao.getVOrderMain(session, com_id, order_no);
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getModelFromView(vom);
			com.cqqyd2014.hibernate.dao.VOrderDetailDAO voddao=new com.cqqyd2014.hibernate.dao.VOrderDetailDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods=voddao.getArrayListViewsByOrderNo(session, com_id, order_no);
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail > ods=com.cqqyd2014.order.logic.OrderDetailLogic.getArrayListModelFromArrayListView(vods);
			
			
			rg.setOrder_create_userid(order.getUser_id());
			rg.setOrder_no(order_no);
			//计算退回的金额
			java.math.BigDecimal return_value=com.cqqyd2014.order.logic.OrderLogic.getGoodsValue(session, order, ods, goods_barcode, com_id);
			rg.setReturn_pirce(return_value);
			rg.setReturn_userid(user_id);
			rg.setSeq(seq);
			rg.setUuid(com.cqqyd2014.util.StringUtil.getUUID());
			com.cqqyd2014.order.logic.ReturnGoodsLogic.save(session, rg);
			//退备用金
			com.cqqyd2014.quota.logic.QuotaTransLogic.changeQuota(session, com_id, order.getUser_id(), user_id, "0005", return_value, "退货退费", order.getOrder_no(), "");
			//处理发货单明细
			com.cqqyd2014.hibernate.dao.VDeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			com.cqqyd2014.hibernate.entities.VDeliverD dd=dddao.getViewByOrderNoSeqGoodsBarcode(session, order_no, seq, goods_barcode, com_id);
			com.cqqyd2014.order.model.DeliverBillDetail dbd=com.cqqyd2014.order.logic.DeliverDLogic.getModelFromEntity(dd);
			if (dbd.isReturned()) {
				//已经退货
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该商品已经退货");
			}
			com.cqqyd2014.hibernate.dao.VDeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			com.cqqyd2014.hibernate.entities.VDeliverM dm_h=dmdao.getEntityViewByOrderNoSeq(session, order_no, seq, com_id);
			com.cqqyd2014.order.model.DeliverBill  db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(dm_h);
			if (!db.getDeliver_bill_status().equals("发货完毕")) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("发货完毕的订单才能退货");
			}
			dbd.setReturned(true);
			java.util.Date return_dat=new java.util.Date();
			dbd.setReturned_dat(return_dat);
			dbd.setReturned_memo(memo);
			dbd.setReturned_userid(user_id);
			com.cqqyd2014.order.logic.DeliverDLogic.save(session, dbd);
			//清理微信查询记录
			com.cqqyd2014.hibernate.dao.VWeixinScanQrLogDAO logdao=new VWeixinScanQrLogDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWeixinScanQrLog> logs=logdao.getLogsByBarcode(session, com_id, goods_barcode);
			com.cqqyd2014.weixin.logic.ScanQrLogLogic.cleanLogs(session, com.cqqyd2014.weixin.logic.ScanQrLogLogic.getArrayListModelFromArrayListViews(logs));
			//生成日志
			com.cqqyd2014.hibernate.dao.OrderLogDAO oldao=new com.cqqyd2014.hibernate.dao.OrderLogDAO();
			oldao.addLog(session, order_no, "订单退货，商品id"+goods_id+"，条码"+goods_barcode, "0003", com_id, user_id);
			com.cqqyd2014.hibernate.dao.VGoodsDAO vgdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			com.cqqyd2014.hibernate.entities.VGoods vg=vgdao.getEntityViewByBarcode(session, com_id, goods_barcode);
			com.cqqyd2014.wh.model.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(vg);
			
			session.flush();
			//处理商品库位
			com.cqqyd2014.wh.logic.GoodsLogic.GoodsReturn(session, goods_barcode, wh_id, return_dat, com_id, user_id);
			com.cqqyd2014.wh.logic.Storage.ReturnGoods(session, goods_id, wh_id, new java.math.BigDecimal(1), com_id);
			com.cqqyd2014.wh.logic.IntoWh.numChange(session, com_id,g.getInto_wh_uuid() , goods_id, new java.math.BigDecimal(1), "0003", user_id);
			//处理订单标志
			java.util.LinkedHashMap<String, java.math.BigDecimal> order_detail_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(ods, "getGoods_id", "getNum");
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds_return=dddao.getArrayListViewByOrderNoReturned(session, com_id, order_no);
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> order_reurn=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(vdds_return);
			java.util.LinkedHashMap<String , java.math.BigDecimal> order_reurn_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMapCount(order_reurn, "getGoods_id");
			
			HashMapToolsCompareResult rs1=com.cqqyd2014.util.HashMapTools.compare(order_detail_map, order_reurn_map);
			if (rs1.isFlag()) {
				//所有商品都退货了
				
				order.setOrder_status("退货完成");
				com.cqqyd2014.order.logic.OrderLogic.save(session, order);
			}
			//处理运单标志
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> delivers=dddao.getArrayListViewByOrderNoSeq(session, com_id, order_no, seq) ;
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(delivers);
			java.util.LinkedHashMap<String, java.math.BigDecimal> deliver_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMapCount(dbds, "getGoods_id");
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> deliver_return_array=(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail>)com.cqqyd2014.util.ArrayListTools.getArrayListSearchBooleanField(dbds, "isReturned", true);
			java.util.LinkedHashMap<String, java.math.BigDecimal> deliver_return_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMapCount(deliver_return_array, "getGoods_id");
			HashMapToolsCompareResult rs2=com.cqqyd2014.util.HashMapTools.compare(deliver_map, deliver_return_map);
			if (rs2.isFlag()) {
				//所有商品都退货了
				
				db.setDeliver_bill_status("退货完成");
				com.cqqyd2014.order.logic.DeliverMLogic.save(session, db);
			}
			
			sm.setSuccess(true);
			
			
		
			
			tx.commit();
			// session.close();
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.toString());
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		
		msg=sm.toMap();
		return SUCCESS;
	}

}