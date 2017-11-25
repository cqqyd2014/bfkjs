package com.cqqyd2014.wh.logic;

import org.hibernate.Session;

public class WareHouseLogic {
	
	public static com.cqqyd2014.wh.model.WareHouse getModelFromEntity(com.cqqyd2014.hibernate.entities.Warehouse wh_h) {
		com.cqqyd2014.wh.model.WareHouse wh=new com.cqqyd2014.wh.model.WareHouse();
		wh.setWh_id(wh_h.getId().getWhId());
		wh.setWh_name(wh_h.getWhName());
		wh.setCom_id(wh_h.getId().getComId());
		return wh;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> getArrayListModelFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs_h){
		java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> whs=new java.util.ArrayList<>();
		for (int i=0;i<whs_h.size();i++) {
			com.cqqyd2014.wh.model.WareHouse wh=getModelFromEntity(whs_h.get(i));
			whs.add(wh);
		}
		
		return whs;
	}
	
	
	
	//不同仓库之间移动商品（订单锁定除外）,原理是移动库位，所以直接调用移动库位
		public static void moveBetweenWarehouse(Session session,String goodsId,String fromWhId,String toWhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			com.cqqyd2014.wh.logic.Storage.moveBetweenStorage(session, goodsId, fromWhId, toWhId, num, "DEFAUL", "DEFAUL", com_id);
			
		}
		//不同仓库之间移动预包装
		 //下订单，增加“订单锁定”仓库的数量,不具体是哪个仓库，因此多一个仓库用来处理订单锁定
		/*
		public static void orderLock(Session session,String goodsId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			com.cqqyd2014.wh.logic.Storage.addStorage(session, goodsId, "ORDER_", "DEFAUL", num, com_id);
			
		}
		
		//取消已经下单未打包的订单，减少"仓库锁定"的数量
		public static void orderUnLock(Session session,String goodsId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			com.cqqyd2014.wh.logic.Storage.addStorage(session, goodsId, "ORDER_", "DEFAUL", num.multiply(new java.math.BigDecimal("-1")), com_id);
			
		}
		*/
}
