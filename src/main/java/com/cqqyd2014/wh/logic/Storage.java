package com.cqqyd2014.wh.logic;

import org.hibernate.Session;



public class Storage {
	//单一库位增加，特殊用途
	
	
	public synchronized static void addStorage(Session session,String goodsId,String WhId,String storage,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
		
		com.cqqyd2014.hibernate.dao.WarehouseStorageDetailDAO wsddao=new com.cqqyd2014.hibernate.dao.WarehouseStorageDetailDAO();
		com.cqqyd2014.hibernate.entities.WarehouseStorageDetail wsd_from=wsddao.findById(session, goodsId,com_id, WhId, storage);
		if (wsd_from==null){
			//之前没有存在的条目
			wsd_from=new com.cqqyd2014.hibernate.entities.WarehouseStorageDetail();
			com.cqqyd2014.hibernate.entities.WarehouseStorageDetailId wsd_id_from=new com.cqqyd2014.hibernate.entities.WarehouseStorageDetailId();
			wsd_id_from.setComId(com_id);
			wsd_id_from.setGoodsId(goodsId);
			wsd_id_from.setStorageId(storage);
			wsd_id_from.setWhId(WhId);
			wsd_from.setId(wsd_id_from);
			wsd_from.setNum(new java.math.BigDecimal("0"));
			
		}
		java.math.BigDecimal r=wsd_from.getNum().add(num);
		if (r.compareTo(new java.math.BigDecimal(0))==-1&&(!WhId.equals("SUPPLY"))){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("原始仓库"+WhId+"的商品"+goodsId+"余额不足，当前余额为"+wsd_from.getNum().toString()+"，需要出库"+num.toString()+"!");
			
		}
		wsd_from.setNum(r);
		session.saveOrUpdate(wsd_from);
	}
	/*
	 * 锁定需要发出的库位数字
	 * 库位数量变动的级联更新
	 * 
	 * 
	 */
		public synchronized static void moveBetweenStorage(Session session,String goodsId,String fromWhId,String toWhId,java.math.BigDecimal num,String fromStorage,String toStorage,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			
			//2、库位商品数量变化
			//2.1、原始仓库库存变化
			addStorage (session, goodsId, fromWhId,fromStorage,num.multiply(new java.math.BigDecimal("-1")), com_id);
			
			//2.2、目标仓库库位变化
			addStorage (session, goodsId, toWhId,toStorage,num.multiply(new java.math.BigDecimal("1")), com_id);
			
		}
		
		//商品被锁定-订单开始处理
		public static void LockItemsForPickup(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"DEFAUL","LOCKED",com_id);
			
		}
		
		/*
		//下订单
		public static void OrderItemsInWh(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws SuccessMessageException{
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"DEFAUL","ORDER_",com_id);
			
		}
		*/
		
		//锁定商品销售
		public static void LockItemToSaleItem(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,WhId,"CUSTOM",num,"LOCKED","DEFAUL",com_id);
		}
		/*
		//未拣货订单取消商品退库
		public static void OrderItemBackToWh(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws SuccessMessageException{
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"ORDER_","DEFAUL",com_id);
		}
		*/
		//处理中订单取消商品退库
		public static void ReturnLockItemToDefault(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"LOCKED","DEFAUL",com_id);
		}
		
		//新商品入库
		public static void NewItemToWh(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,"SUPPLY",WhId,num,"DEFAUL","DEFAUL",com_id);
		}
		//商品移库
		public static void MoveGoods(Session session,String goodsId,String fromWh,String toWh,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,fromWh,toWh,num,"DEFAUL","DEFAUL",com_id);
		}
		//商品退库
		public static void ReturnGoods(Session session,String goodsId,String toWh,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,"CUSTOM",toWh,num,"DEFAUL","DEFAUL",com_id);
		}
		
		
		
		
		//商品被预包装
		//预包装影响默认库位
		public static void PrePackItemsInWh(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			
			//PrePackChangeInWh(session,WhId,com_id,goodsId,num);
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"DEFAUL","PREPAC",com_id);
			
		}
		//预包装商品拣货
		//预包装库位减少，
		public static void PrePackItemToLockItem(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//PrePackChangeInWh(session,WhId,com_id,goodsId,num.multiply(new java.math.BigDecimal(-1)));
			//moveBetweenStorage(session,goodsId,WhId,"CUSTOM",num,"DEFAUL","DEFAUL",com_id);
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"PREPAC","LOCKED",com_id);
		}
		//预包装解包
		//预包装库位减少
		public static void PrePackItemUnPack(Session session,String goodsId,String WhId,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//PrePackChangeInWh(session,WhId,com_id,goodsId,num.multiply(new java.math.BigDecimal(-1)));
			//moveBetweenStorage(session,goodsId,WhId,"CUSTOM",num,"DEFAUL","DEFAUL",com_id);
			moveBetweenStorage(session,goodsId,WhId,WhId,num,"PREPAC","DEFAUL",com_id);
		}
		
		
		//商品损毁，移动库存到BROKEN库位，需要是"DEFAUL"库位才能进入破损库位
		public static void BrokenGoods(Session session,String goodsId,String fromWh,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,fromWh,fromWh,num,"DEFAUL","BROKEN",com_id);
		}
		//标签作废，移库到LABLE_库位
		public static void BrokenBarcode(Session session,String goodsId,String fromWh,java.math.BigDecimal num,String com_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			moveBetweenStorage(session,goodsId,fromWh,fromWh,num,"DEFAUL","LABEL_",com_id);
		}
}
