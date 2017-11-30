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
import com.cqqyd2014.util.exception.AjaxSuccessMessageException;
import com.cqqyd2014.util.hashmap.HashMapToolsCompareResult;



@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class PickupGoodsBarcodeAction extends UserLoginedAction{
	String pickup_barcode;
	String order_no;
	String seq;
	String wh_id;
	String logistics;
	String express_no;
	String vehicle;
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

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	private Map<String, Object> msg;



	public String getPickup_barcode() {
		return pickup_barcode;
	}

	public void setPickup_barcode(String pickup_barcode) {
		this.pickup_barcode = pickup_barcode;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "pickup_goodsbarcode", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
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

		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis= (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
				.get("temp_deliver_picked_sn");
		try {
			//2、需要发出的商品列表
			com.cqqyd2014.hibernate.dao.VPickupYueDAO dydao=new com.cqqyd2014.hibernate.dao.VPickupYueDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue> vpys=dydao.getArrayListViewByOrderNo(session, order_no, com_id);
			
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbs=com.cqqyd2014.order.logic.DeliverDLogic.getArrayListModelFromArrayListView2(vpys);
			java.util.LinkedHashMap<String,java.math.BigDecimal> order_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(dbs, "getGoods_id", "getYue");
			
			
			//商品条码只能是14位或者22位，预包装是18位
			int len=pickup_barcode.length();
			if (len==18) {
				//预包装发货，只能是单一包裹，因此之前的odis为空
				if (odis.size()>0) {
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("预包装只能用于单一发货");
				}
				
				com.cqqyd2014.hibernate.dao.PrePackageDAO ppdao=new com.cqqyd2014.hibernate.dao.PrePackageDAO();
				com.cqqyd2014.hibernate.entities.PrePackageM ppm=ppdao.getEntityByPrepackageBarcode(session, com_id, pickup_barcode);
				if (ppm==null) {
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("找不到这个预包装"+pickup_barcode);
					
				}
				if (!ppm.getWhId().equals(wh_id)) {
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("这个预包装不在库房"+wh_id);
				}
				com.cqqyd2014.hibernate.dao.VPrepackageDDAO ppddao=new com.cqqyd2014.hibernate.dao.VPrepackageDDAO();
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackageD> ppds_h=ppddao.getViewByPrepackageBarcode(session, pickup_barcode, com_id);
				
				java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageD> ppds=com.cqqyd2014.wh.logic.PrePackageDLogic.getArrayListModelFromArrayListView(ppds_h);
				java.util.LinkedHashMap<String, java.math.BigDecimal> prepackage_map=com.cqqyd2014.util.ArrayListTools.getKeyCount(ppds, "getGoods_id");
				
				//订单剩余发货，是否大于等于包裹
				if (com.cqqyd2014.util.HashMapTools.check_if_contains(order_map,prepackage_map)) {
					//开始处理发货，不再单独调用发货的save
					//ppm.setActualWeight(com.cqqyd2014.util.ArrayListTools.sumFields(ppds.toArray(), "getPackage_weight"));
					ppm.setSendDat(new java.util.Date());
					ppm.setSended(true);
					ppm.setWhId(wh_id);
					
					session.saveOrUpdate(ppm);
					//更新处理deliver
					
					seq=com.cqqyd2014.order.logic.OrderLogic.getSeq(session, seq, order_no, express_no, com_id);
					java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=new java.util.ArrayList<>();
					for (int i=0;i<ppds.size();i++) {
						com.cqqyd2014.order.model.DeliverBillDetail dbd=new com.cqqyd2014.order.model.DeliverBillDetail();
						dbd.setCom_id(com_id);
						dbd.setGoods_barcode(ppds.get(i).getGoods_barcode());
						dbd.setGoods_id(ppds.get(i).getGoods_id());
						dbd.setOrder_no(order_no);
						dbd.setUuid(com.cqqyd2014.util.StringUtil.getUUID());
						dbd.setGross_weight(ppds.get(i).getGross_weight());
						dbd.setNet_weight(ppds.get(i).getNet_weight());
						dbd.setPackage_weight(ppds.get(i).getPackage_weight());
						dbd.setSeq(seq);
						dbd.setSended_count(new java.math.BigDecimal(1));
						dbds.add(dbd);
						
						//商品移动
						
						com.cqqyd2014.wh.logic.GoodsLogic.PrePacItemsToLockItem(session, ppds.get(i).getGoods_barcode(),  com_id,user_id);
						//com.cqqyd2014.wh.logic.IntoWh.numChange(session, com_id, g.getIntoHwUuid(), g.getGoodsId(), new java.math.BigDecimal(-1));
						
						//com.cqqyd2014.wh.logic.Storage.LockItemToSaleItem(session, g.getGoodsId(), currentWh, new java.math.BigDecimal(1), com_id);
						com.cqqyd2014.wh.logic.Storage.PrePackItemToLockItem(session, ppds.get(i).getGoods_id(), wh_id, new java.math.BigDecimal(1), com_id);
					}
					com.cqqyd2014.order.model.DeliverBill db=new com.cqqyd2014.order.model.DeliverBill();
					db.setActual_weight(new java.math.BigDecimal(0));
					db.setCancel_confirm_memo("");
					db.setCancel_confirm_userid("");
					db.setCancel_confrim_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
					db.setCancel_request_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
					db.setCancel_request_memo("");
					db.setCancel_request_userid("");
					db.setCancel_status("");
					db.setCarton_type("0001");
					db.setCarton_weight(new java.math.BigDecimal(0));
					db.setCom_id(com_id);
					db.setCreate_userid(user_id);
					db.setDbds(dbds);
					db.setDeliver_bill_status("拣货完成");
					db.setDeliver_no(order_no+seq);
					db.setEffective(true);
					db.setExpress_com(logistics);
					db.setExpress_no(express_no);
					db.setLogistics_fb_code("");
					db.setLogistics_fb_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
					db.setLogistics_fb_memo("");
					db.setLogistics_fb_status("");
					db.setMemo_barcodes(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(ppds, "getGoods_barcode"));
					db.setMemo_names(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(ppds, "getGoods_name"));
					db.setNum(new java.math.BigDecimal(ppds.size()));
					db.setOrder_no(order_no);
					db.setPackage_dat(new java.util.Date());
					db.setPackage_userid(user_id);
					db.setPackage_weight(com.cqqyd2014.util.ArrayListTools.sumFields(ppds, "getPackage_weight"));
					db.setPre_package_barcode(pickup_barcode);
					db.setSend_dat(new java.util.Date());
					db.setSend_user_assgin_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
					db.setSend_userid("");
					db.setSended(false);
					db.setSeq(seq);
					db.setVehicle_id(vehicle);
					db.setWh_id(wh_id);
					
					com.cqqyd2014.order.logic.DeliverMLogic.save(session, db);
					
					
				}
				else {
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("预包装的商品与需要发货的不一致:"+pickup_barcode);
				}
				
				sm.setSuccess(true);
				sm.setBody("预包装发货");
				sm.setSound("picked_ok");
				
				
			}
			else {
				//商品拣货
				com.cqqyd2014.hibernate.dao.VGoodsDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
				com.cqqyd2014.hibernate.entities.VGoods vg=gdao.getEntityViewByBarcode(session, com_id, pickup_barcode);
				if (vg==null) {
					//条码不存在
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("条码不存在，请确认商品条码输入是否正确");
				}
				
				
				com.cqqyd2014.wh.model.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(vg);
				
					//0、条码有效性
					String wh_not_id_array[]= new String[2];
					wh_not_id_array[0]="SUPPLY";
					wh_not_id_array[1]="CUSTOM";
					java.util.ArrayList<String> wh_not_ids=com.cqqyd2014.util.StringUtil.ArrayToArrayList(wh_not_id_array);
					java.util.ArrayList<String > wh_ids=null;
					java.util.ArrayList<String> storage_ids=com.cqqyd2014.util.StringUtil.toArrayList("DEFAUL");
					java.util.ArrayList<String > storage_not_ids=null;
					
					com.cqqyd2014.util.message.IfMessage ir=com.cqqyd2014.wh.logic.GoodsLogic.if_available_in_wh_storage(session, g, wh_ids,wh_not_ids, storage_ids,storage_not_ids);
					if (!ir.isIf_ok()) {
						throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(ir.getMessage());
					}
					

					
					
					
					
					//1.在现有的暂存发货单中重复
					for (int i=0;i<odis.size();i++){
						com.cqqyd2014.wh.model.Goods odi=odis.get(i);
						if (odi.getBarcode().equals(pickup_barcode)){
							throw new AjaxSuccessMessageException("该条码重复拣货，请核实");
							
						}
					}
					//com.cqqyd2014.wh.logic.GoodsLogic gl=new com.cqqyd2014.wh.logic.GoodsLogic();
					//2.是否是待发商品
					if (order_map.get(g.getGoods_id())==null) {
						throw new AjaxSuccessMessageException("该商品不是应发商品，请核实");
					}
					
					
					
					odis.add(g);
					
					session_http.put("temp_deliver_picked_sn",odis);
					
					
					//对比已经发货和待发的
					
					java.util.LinkedHashMap<String, java.math.BigDecimal> odis_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMapCount(odis, "getGoods_id");
					HashMapToolsCompareResult rs=com.cqqyd2014.util.HashMapTools.compare(order_map, odis_map);
					
					sm.setSuccess(true);
					sm.setBody("条码发货，已拣货："+g.getGoods_name());
					
					if (rs.isFlag()){
						//拣货完毕
						
						sm.setSound("item_picked_complet");
						
					}
					else{
						
						sm.setBody(rs.toCompareString("应发","实发"));
						sm.setSound("picked_ok");
					}	
					
				
			}

		
			
			

			
			
			tx.commit();
			

		}
		catch(HibernateException e) {
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
			sm.setSound("error");
					
					if (null != tx) {
						tx.rollback();// 撤销事务

					}
		}

catch (AjaxSuccessMessageException e) {
	sm.setSuccess(false);
	sm.setBody(e.getMessageString());
	sm.setSound("error");
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			
			
			
			


		} finally {
			HibernateSessionFactory.closeSession();
		}

		
		msg = sm.toMap();
		return "success";
	}

}