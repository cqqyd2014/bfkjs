package com.cqqyd2014.order.logic;



import java.util.Map;


import org.hibernate.Session;


import com.cqqyd2014.express.sf.bsp.BspException;

import com.cqqyd2014.hibernate.dao.VDeliverYueDAO;
import com.cqqyd2014.hibernate.entities.VDeliverM;
import com.cqqyd2014.order.model.Order;
import com.cqqyd2014.quota.logic.QuotaTransLogic;

import com.cqqyd2014.util.hashmap.HashMapToolsCompareResult;


public class OrderLogic {
	
	
	//计算退货金额
	public static java.math.BigDecimal getGoodsValue(Session session,com.cqqyd2014.order.model.Order order,java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods,String goods_barcode,String com_id) {
		//订单总商品金额
		java.math.BigDecimal goods_total=order.getActual_amount().subtract(order.getShip_fee());
		//明细总金额
		java.math.BigDecimal goods_detail_total=com.cqqyd2014.util.ArrayListTools.sumFields(ods, "getTotal1");
		//该商品的金额
		java.util.LinkedHashMap<String, java.math.BigDecimal> detail_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(ods, "getGoods_id", "getPrice");
		String goods_id=com.cqqyd2014.wh.logic.GoodsLogic.getGoodsIdByBarcode(session, goods_barcode, com_id);
		java.math.BigDecimal value=detail_map.get(goods_id);
		//分摊的金额
		java.math.BigDecimal goods_value=goods_total.multiply(value.divide(goods_detail_total));
		return goods_value;
	}
	public static java.math.BigDecimal getGoodsValue(Session session,com.cqqyd2014.order.model.Order order,java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods,String goods_id,java.math.BigDecimal num) {
		//订单总商品金额
		java.math.BigDecimal goods_total=order.getActual_amount().subtract(order.getShip_fee());
		//明细总金额
		java.math.BigDecimal goods_detail_total=com.cqqyd2014.util.ArrayListTools.sumFields(ods, "getTotal1");
		//该商品的金额
		java.util.LinkedHashMap<String, java.math.BigDecimal> detail_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(ods, "getGoods_id", "getPrice");
		
		java.math.BigDecimal value=detail_map.get(goods_id);
		//分摊的金额
		java.math.BigDecimal goods_value=goods_total.multiply(value.multiply(num).divide(goods_detail_total));
		return goods_value;
	}
	
	//如果本来有seq返回本来的seq，如果没有，先查询是否有同样的运单号，如果有同样的运单号，合并运单，也就是返回之前的运单号
	public static String getSeq(Session session,String seq,String order_no,String express_no,String com_id) {
		if (seq==null) {
			return getSeqSub(session,order_no,express_no,com_id);
		}
		if (seq.equals("")){
			return getSeqSub(session,order_no,express_no,com_id);
		}
		else {
			return seq;
		}
		
	}
	private static String getSeqSub(Session session,String order_no,String express_no,String com_id) {
		//如果有单号相同，而且状态为‘分配单号’
		com.cqqyd2014.hibernate.dao.VDeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
		com.cqqyd2014.hibernate.entities.VDeliverM dm=dmdao.getEntityByExpressNoAssign(session, order_no, express_no, com_id);
		if (dm==null) {
			//没有匹配的运单
			com.cqqyd2014.hibernate.dao.VDeliverMMaxDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMMaxDAO();
			String seq=vdmdao.getNewOrderSeq(session, order_no, com_id);
			return seq;
		}
		else {
			return dm.getId().getSeq();
		}
	}
	
	public static void afterSendedPackage(Session session,String order_no,String com_id,String op_userid)  {

		//更新订单状态，如果全部发货，更新订单状态为，发货完毕
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao=new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		java.util.LinkedHashMap<String, java.math.BigDecimal> order_detail_map=oddao.getGoodsIdMapByOrder(session, order_no, com_id);
		
		com.cqqyd2014.hibernate.dao.VDeliverDDAO vddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
		java.util.LinkedHashMap<String, java.math.BigDecimal> deliver_detail_map=vddao.getGoodsIdMapByOrderSended(session, order_no, com_id);
		
		HashMapToolsCompareResult rs=com.cqqyd2014.util.HashMapTools.compare(order_detail_map, deliver_detail_map);
		com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.OrderMainDAO();
		com.cqqyd2014.hibernate.entities.COrderMain om=omdao.getOrder(session, order_no, com_id);
		
		if (rs.isFlag()) {
			//清单完全一致
			om.setCStatus("发货完毕");
			om.setEmsStatus("发货完毕");
			session.saveOrUpdate(om);
		}
		//如果已经全部发货，从锁定财务中转出到
		session.flush();
		VDeliverYueDAO vdydao=new VDeliverYueDAO();
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverYue> vdys=vdydao.getViewByOrderNo(session, order_no);
		boolean flag=true;
		for (int i=0;i<vdys.size();i++) {
			if (vdys.get(i).getId().getOrderNum().compareTo(new java.math.BigDecimal(vdys.get(i).getId().getSendedNum()))==1) {
				//有未发完的
				flag=false;
			}
		}
		if (flag) {
			//全部发送完
			QuotaTransLogic.changeQuota(session, com_id, om.getUserId(), op_userid, "0004", om.getActualAmount(), "发货解冻", om.getId().getOrderNo(), "");
			
		}
	}
	
	
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.Order> getArrayListModelWithDetailDeliverM(Session session,java.util.ArrayList<String> order_nos,String com_id){
		com.cqqyd2014.hibernate.dao.VOrderDetailDAO voddao=new com.cqqyd2014.hibernate.dao.VOrderDetailDAO();
		com.cqqyd2014.hibernate.dao.VDeliverMDAO vdmdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
		com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
		java.util.ArrayList<com.cqqyd2014.order.model.Order> items=new java.util.ArrayList<>();
		for (int i=0;i<order_nos.size();i++){
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vomdao.getVOrderMain(session, com_id, order_nos.get(i));
			
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods=voddao.getArrayListViewsByOrderNo(session, com_id, vom.getId().getOrderNo());
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> vdms=vdmdao.getArrayEntityViewByOrderNo(session, order_nos.get(i), com_id);
			
			com.cqqyd2014.order.model.Order item=getOrderModelFromHiberanteEntities(vom, vods,vdms);

			
			
			
			items.add(item);
			
		}
		return items;
	}
	
	
	
	public static void cancel_confirm(Session session,com.cqqyd2014.order.model.Order order,String userid,String memo){
		
			order.setCancel_confirm_memo(memo);
			order.setCancel_confirm_userid(userid);
			order.setCancel_confrim_dat(new java.util.Date());
			order.setCancel_status("取消成功");
			session.save(order);
	}
	//检测是否是客户申请取消的订单,如果是，处理取消
	public static boolean check_if_cancel(Session session,com.cqqyd2014.order.model.Order order,String userid,String memo){
		if (order.getCancel_status().equals("申请取消")){
			
			cancel_confirm(session,order,userid,"处理中检测取消，环节："+memo);
			
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public  static com.cqqyd2014.util.message.IfMessage CompareNeedSendMapWithGoodsMap(Session session,String com_id,java.util.HashMap<String, java.math.BigDecimal> needSendMap,java.util.HashMap<String, java.math.BigDecimal> pickedMap) 
	{
com.cqqyd2014.util.message.IfMessage if_msg=new com.cqqyd2014.util.message.IfMessage();
com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();

//对比两个map
		//拣货需要是需要发货的商品之一，从已经拣货的map开始遍历，如果有不是需要发货的goods_id就是问题

HashMapToolsCompareResult rs=com.cqqyd2014.util.HashMapTools.compare(needSendMap, pickedMap);
if(!rs.isFlag()){
	String msg="";
	for(Map.Entry<String, java.math.BigDecimal> entry1:rs.getABCompare().entrySet()){
		String  id=entry1.getKey();
		java.math.BigDecimal value=entry1.getValue();
		if (value.compareTo(new java.math.BigDecimal("0"))==1){
			String goods_name=gdao.getGoodsInfo(id, session, com_id).getId().getCName();
			msg=msg+"商品"+goods_name+"还需要捡货"+value.toString();
		}
		if (value.compareTo(new java.math.BigDecimal("0"))==-1){
			String goods_name=gdao.getGoodsInfo(id, session, com_id).getId().getCName();
			msg=msg+"商品"+goods_name+"多捡货"+value.multiply(new java.math.BigDecimal("-1")).toString();
		}
		
     }
	//不是完全相同的
	if_msg.setIf_ok(false);
	if_msg.setMessage(msg);
}
else{
	if_msg.setIf_ok(true);
}

		
		
		
		
		return if_msg;
}




	
	
	public static java.util.ArrayList<com.cqqyd2014.order.model.Order> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> vorders){
		java.util.ArrayList<com.cqqyd2014.order.model.Order > orders=new java.util.ArrayList<>();
		for (int i=0;i<vorders.size();i++){
			com.cqqyd2014.order.model.Order order=getModelFromView(vorders.get(i));
			orders.add(order);
		}
		return orders;
	}
	

	
	//设定发货人信息
	
	private static com.cqqyd2014.order.model.Order getSenderFormHibernateEntities(com.cqqyd2014.order.model.Order order,com.cqqyd2014.hibernate.entities.OrderFrom of){
		
		//order.setOrder_ebusiness_name(of.getOrderTypeName());
		order.setOrder_type_code(of.getId().getOrderTypeCode());
		order.setOrder_ebusiness_code(of.getId().getEId());
		order.setSender(of.getSender());
		order.setSender_addr(of.getSenderAddr());
		order.setSender_city(of.getSenderCity());
		order.setSender_com(of.getSenderCom());
		order.setSender_district(of.getSenderDistrict());
		order.setSender_full_addr(of.getSenderFullAddr());
		order.setSender_province(of.getSenderProvince());
		order.setSender_tell(of.getSenderTell());
		return order;
	}
	
	
	//将数据库发货单明细转换为数组订单对象
	
	private static java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getDeliverBillsModelFormHibernateEntities(java.util.ArrayList<VDeliverM> vds){
		
		
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=new java.util.ArrayList<>();
		for (int i=0;i<vds.size();i++){
			com.cqqyd2014.hibernate.entities.VDeliverM vdm=vds.get(i);
			com.cqqyd2014.order.model.DeliverBill db=com.cqqyd2014.order.logic.DeliverMLogic.getModelFromEntityV(vdm);
			
			
			dbs.add(db);
		}
		return dbs;
	}
	
	
	
	//将数据库数组订单明细转换为数组订单明细对象
	
	private static java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> getOrderDetailsModelFromHiberanteEntities(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods){
		
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=new java.util.ArrayList<>();
if (vods==null) {
		return ods;	
		}
		for (int i=0;i<vods.size();i++){
			com.cqqyd2014.hibernate.entities.VOrderDetail vod=vods.get(i);
			com.cqqyd2014.order.model.OrderDetail od=new com.cqqyd2014.order.model.OrderDetail();
			
			
			od.setDetail_id(vod.getId().getCDetailId());
			od.setGoods_id(vod.getId().getCGoodsId());
			od.setGoods_name(vod.getId().getCName());
			od.setNum(vod.getId().getCCount());
			od.setOrder_no(vod.getId().getCOrderId());
			od.setPrice(vod.getId().getCPrice());
			od.setTotal1(vod.getId().getTotall());
			od.setTotal2(vod.getId().getTotal2());
			ods.add(od);
		}
		
		return ods;
	}
	
	//将数据库对象装换为订单模型队形_类型1,订单
	
	
	public static com.cqqyd2014.order.model.Order getModelFromView(com.cqqyd2014.hibernate.entities.VOrderMain om){
		com.cqqyd2014.order.model.Order order=new com.cqqyd2014.order.model.Order();
		order.setCancel_status(om.getId().getCancelStatus());
		order.setCancel_confirm_memo(om.getId().getCancelConfirmMemo());
		order.setCancel_confirm_userid(om.getId().getCancelConfirmUserid());
		order.setCancel_confrim_dat(om.getId().getCancelConfirmDat());
		order.setCancel_request_dat(om.getId().getCancelRequestDat());
		order.setCancel_request_memo(om.getId().getCancelRequestMemo());
		order.setCancel_request_userid(om.getId().getCancelRequestUserid());
		order.setLogistics(om.getId().getLogisticsId());
		order.setLogistics_name(om.getId().getLogisticsName());
		order.setVehicle(om.getId().getVehicleId());
		order.setVehicle_name(om.getId().getVehicleName());
		order.setActual_amount(om.getId().getActualAmount());
		order.setCard_pay(om.getId().getCardPay());
		order.setCity(om.getId().getAddrCity());
		order.setCom_id(om.getId().getComId());
		order.setDetail_memo(om.getId().getDetailMemo());
		order.setDiscount(om.getId().getDiscount());
		order.setDistrict(om.getId().getAddrDistrict());
		order.setMemo(om.getId().getCMemo());
		order.setNotAir(om.getId().getNotAir());
		order.setOrder_dat(om.getId().getOrderDat());
		order.setOriginal_amount(om.getId().getCAmount());
		order.setOrder_type_code(om.getId().getOrderNo().substring(3,5));
		order.setOrder_ebusiness_code(om.getId().getOrderTypeName());
		order.setOrder_ebusiness_name(om.getId().getOrderTypeName());
		order.setOrder_no(om.getId().getOrderNo());
		order.setOriginal_id(om.getId().getOriginalId());
		order.setProvince(om.getId().getAddrProvince());
		order.setShip_fee(om.getId().getShipFee());
		order.setTell(om.getId().getCTell());
		order.setTell2(om.getId().getTell2());
		order.setUser_addr(om.getId().getCUserAddr());
		order.setUser_com(om.getId().getUserCom());
		order.setUser_id(om.getId().getUserId());
		order.setSys_user_name(om.getId().getSysUserName());
		order.setUser_name(om.getId().getCUserName());
		order.setPackage_user_assign_time(om.getId().getPackageUserAssignTime());
		order.setPackage_userid(om.getId().getPackageUser());
		order.setPackage_username(om.getId().getPackageUserName());
		order.setOrder_status(om.getId().getCStatus());
		order.setWh_status(om.getId().getGtStatus());
		order.setActual_amount2(om.getId().getActualAmount2());
		order.setOriginal_amount2(om.getId().getOriginalAmount2());
		order.setTax2(om.getId().getCTax2());
		order.setReg_tax2(om.getId().getCRegTax2());
		order.setQty(om.getId().getCQty());
		order.setPaid(om.getId().getPaid());
		order.setPaid_money(om.getId().getPaidMoney());
		order.setPaid_time(om.getId().getPaidTime());
		order.setEffective(om.getId().getEffective());
		order.setExpress_status(om.getId().getEmsStatus());
		return order;
	}
	
	
	
	
	//将数据库对象订单转换为订单模型对象_类型2，订单与订单明细
	public static com.cqqyd2014.order.model.Order getOrderModelFromHiberanteEntities(com.cqqyd2014.hibernate.entities.VOrderMain om,java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods){
		com.cqqyd2014.order.model.Order order=getModelFromView(om);
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=getOrderDetailsModelFromHiberanteEntities(vods);
		
		order.setDetails(ods);
		
		
		return order;
	}
	
	//将数据库对象订单转换为订单模型对象_类型3，订单、订单明细余发货单
	public static com.cqqyd2014.order.model.Order getOrderModelFromHiberanteEntities(com.cqqyd2014.hibernate.entities.VOrderMain om,java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods,java.util.ArrayList<VDeliverM> vdms){
		com.cqqyd2014.order.model.Order order=getOrderModelFromHiberanteEntities(om,vods);
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=getDeliverBillsModelFormHibernateEntities(vdms);
		
		order.setDeliver_bills(dbs);
		
		return order;
	}
	///将数据库对象订单转换为订单模型对象_类型4，订单、订单明细、发货单、发货人信息
	public static com.cqqyd2014.order.model.Order getOrderModelFromHiberanteEntities(com.cqqyd2014.hibernate.entities.VOrderMain om,java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods,java.util.ArrayList<VDeliverM> vdms,com.cqqyd2014.hibernate.entities.OrderFrom of){
		com.cqqyd2014.order.model.Order order=getOrderModelFromHiberanteEntities(om, vods, vdms);
		order=getSenderFormHibernateEntities(order,of);
		
		return order;
	}
	//将数据库对象订单转换为订单模型对象_类型5，订单、订单明细、发货人信息
	public static com.cqqyd2014.order.model.Order getOrderModelFromHiberanteEntities(com.cqqyd2014.hibernate.entities.VOrderMain om,java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods,com.cqqyd2014.hibernate.entities.OrderFrom of){
		com.cqqyd2014.order.model.Order order=getOrderModelFromHiberanteEntities(om, vods);
		order=getSenderFormHibernateEntities(order,of);
		
		return order;
	}
	//将数据库对象订单转换为订单模型对象_类型6，订单、发货人信息
	public static com.cqqyd2014.order.model.Order getOrderModelFromHiberanteEntities(com.cqqyd2014.hibernate.entities.VOrderMain om,com.cqqyd2014.hibernate.entities.OrderFrom of){
		com.cqqyd2014.order.model.Order order=getModelFromView(om);
		order=getSenderFormHibernateEntities(order,of);
		
		return order;
	}
	/*
	//将数据库对象订单转换为订单模型对象_类型7，订单、运单、发货人信息
		public com.cqqyd2014.order.model.Order getOrderModelFromHiberanteEntities(com.cqqyd2014.hibernate.entities.VOrderMain om,java.util.ArrayList<VDeliverM> vdms,com.cqqyd2014.hibernate.entities.OrderFrom of){
			com.cqqyd2014.order.model.Order order=getOrderModelFromHiberanteEntities(om);
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=getDeliverBillsModelFormHibernateEntities(vdms);
			
			order.setDeliver_bills(dbs);
			order=getSenderFormHibernateEntities(order,of);
			
			return order;
		}
		*/
	public static void save(Session session,Order order) {

			
			
			
			com.cqqyd2014.hibernate.entities.COrderMain om = new com.cqqyd2014.hibernate.entities.COrderMain();
			
				om.setCMemo(order.getMemo());
			om.setDefaultExpressCom(order.getLogistics());
			om.setDefaultExpressVehicle(order.getVehicle());
			com.cqqyd2014.hibernate.entities.COrderMainId omid=new com.cqqyd2014.hibernate.entities.COrderMainId();
			omid.setComId(order.getCom_id());
			omid.setOrderNo(order.getOrder_no());
			om.setId(omid);
			om.setCancelConfirmDat(order.getCancel_confrim_dat());
			om.setCancelConfirmMemo(order.getCancel_confirm_memo());
			om.setCancelConfirmUserid(order.getCancel_confirm_userid());
			om.setCancelRequestDat(order.getCancel_request_dat());
			om.setCancelRequestMemo(order.getCancel_request_memo());
			om.setCancelRequestUserid(order.getCancel_request_userid());
			om.setCancelStatus(order.getCancel_status());
			
			om.setAddrCity(order.getCity());
			om.setAddrDistrict(order.getDistrict());
			om.setAddrProvince(order.getProvince());
			
			
			
			om.setCardPay(order.getCard_pay());
			om.setEffective(order.isEffective());
			om.setNotAir(order.isNotAir());
			om.setPackageUser(order.getPackage_userid());
			om.setPackageUserAssignTime(order.getPackage_user_assign_time());
			om.setTell2(order.getTell2());
			om.setUserCom(order.getUser_com());
			om.setCStatus(order.getOrder_status());
			om.setGtStatus(order.getWh_status());
			om.setEmsStatus(order.getExpress_status());
			om.setPaid(order.isPaid());
			om.setPaidMoney(order.getPaid_money());
			om.setPaidTime(order.getPaid_time());
			om.setCTell(order.getTell());
			java.util.Date now = new java.util.Date();
			om.setCTime(now);
			om.setCUserAddr(order.getUser_addr());
			om.setCUserName(order.getUser_name());
			om.setCQty(order.getQty());

			
			om.setShipFee(order.getShip_fee());
			om.setOrderDat(order.getOrder_dat());
			om.setOriginalId(order.getOriginal_id());
			
			om.setUserId(order.getUser_id());

			om.setCAmount(order.getOriginal_amount());
			//order.setDiscount(order.getOriginal_amount().subtract(order.getActual_amount()).add(order.getShip_fee()).subtract(order.getCard_pay()));
			om.setDiscount(order.getDiscount());
			om.setCardPay(order.getCard_pay());

			om.setActualAmount(order.getOriginal_amount().subtract(order.getCard_pay()).subtract(order.getDiscount()).add(order.getShip_fee()));
			
			
			om.setDetailMemo(order.getDetail_memo());
			om.setCAmount2(order.getOriginal_amount2());
			om.setCTax2(order.getTax2());
			om.setCRegTax2(order.getReg_tax2());
			om.setActualAmount2(order.getOriginal_amount2().subtract(order.getCard_pay()).subtract(order.getDiscount()).add(order.getShip_fee()));
			
			
			

			


			session.saveOrUpdate(om);
		
		
	}
	//将单个order对象转换为数组
	public static java.util.ArrayList<Order> transOneToArray(Order order) throws BspException{
		java.util.ArrayList<Order> orders=new java.util.ArrayList<>();
		if (order==null){
			throw new BspException("","单个order对象为空，不能转换为List");
		}
		orders.add(order);
		return orders;
	}
	

}
