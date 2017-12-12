package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.util.message.IfMessage;


public class GoodsDAO {
	
	
	
	
	
	
	
	
	//订单，采用平均值，DEFAUL和PREPAC库位
	private java.math.BigDecimal getValueOrder_(Session session,String com_id,String whId,String goodsId){
		VGoodsAvgPriceInDefaulPrePacDAO vgapdao=new VGoodsAvgPriceInDefaulPrePacDAO();
		java.math.BigDecimal value=vgapdao.getAmountByGoodsIdWhId(session, com_id, whId, goodsId);
		return value;
	}
	//通常情况为该商品在该库位的情况
	private java.math.BigDecimal getValueStorage(Session session,String com_id,String whId,String storageId,String goodsId){
		String storage_in="\'"+storageId+"\'";
		
		
		String hql="select sum(contractPrice) from Goods where id.comId=:com_id and printed=true and currentWh=:whId and currentStorage in ("
		+storage_in+") and goodsId=:goodsId";
		//System.out.println(hql);
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("whId", whId);
		//q.setParameter("storageId", storageId);
		q.setParameter("goodsId", goodsId);
		java.math.BigDecimal value = (java.math.BigDecimal) q.iterate().next();
		if (value==null){
			value=new java.math.BigDecimal(0);
		}
		return value;
	}
	
	
	//分库位存货价值，按照实际商品价值累计，下单库位因为没有制定商品，所以采用平均价格计算
		public java.math.BigDecimal getValueByWhStorageGoodsId(Session session,String com_id,String whId,String storageId,String goodsId){
			//System.out.println(whId);
			//System.out.println(storageId);
			//System.out.println(goodsId);
			
			if (storageId.equals("ORDER_")){
				return getValueOrder_(session,com_id,whId,goodsId);
				
				
				
			}
			if (storageId.equals("DEFAUL")){
				//默认库位的总价值，应该是该库位价值减去“订单库位价值”
				java.math.BigDecimal defaul=getValueStorage(session,com_id,whId,"DEFAUL",goodsId);
				//System.out.println(defaul);
				java.math.BigDecimal order_=getValueOrder_(session,com_id,whId,goodsId);
				//System.out.println(order_);
				return defaul.subtract(order_);
			}
			
			return getValueStorage(session,com_id,whId,storageId,goodsId);
			
			
		}
	
	
	
	public  String getSnByCom(String goods_id,Session session,String com_id){
		com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
		String snCode=gidao.getSnCode(session, goods_id, com_id);
		//从数据库中随机取出
		String hql="from Goods where product=:snCode and ifEffective=false and ifPrint=true";
		Query q = session.createQuery(hql);
		q.setParameter("snCode", snCode);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>) q
				.list();
		if (rs.size()==0){
			//条码库中已经用完，新申请条码,这个在条码申请模块
			return "";
		}
		else{
			com.cqqyd2014.hibernate.entities.Goods sw=rs.get(0);
			sw.setEffective(false);
			session.saveOrUpdate(sw);
			return sw.getId().getGoodsBarcode();
		}
		
	}
	
	
	//商品是否属于某一个仓库的某一库位
	
	

	//商品是否可用，用于下单
	
	public IfMessage if_available(Session session,String com_id,String sn){
		IfMessage ir=new IfMessage();
		
		//1、测试编码是否存在
		String hql="from Goods where ifPrint=true and id.goodsBarcode=:sn and id.comId=:com_id and ifEffective=true";
		
		Query q = session.createQuery(hql);
		q.setParameter("sn", sn);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>)q.list();
		if (sws.size()==0){
			ir.setIf_ok(false);
			ir.setMessage("该商品条码"+sn+"未打印、或处于不可用状态，或非我公司条码");
			return ir;
		}
		//2、测试仓库
		com.cqqyd2014.hibernate.entities.Goods g=sws.get(0);
		String warehouse=g.getCurrentWh();
		if (warehouse.equals("CUSTOM")){
			ir.setIf_ok(false);
			ir.setMessage("当前条码"+sn+"在客户仓库");
			return ir;
		}
		if (warehouse.equals("SUPPLY")){
			ir.setIf_ok(false);
			ir.setMessage("当前条码"+sn+"在供应商仓库");
			return ir;
		}
		//3、测试库位
		String storage=g.getCurrentStorage();
		if (!storage.equals("DEFAUL")){
			ir.setIf_ok(false);
			ir.setMessage("当前条码"+sn+"不在DEFAUL默认库位");
			return ir;
		}
		ir.setIf_ok(true);
		
		
		
		
		
		return ir;
	}
	
	//商品是否在某个仓库可用，用于下单
	public IfMessage if_available(Session session,String com_id,String sn,String currentWh){
		IfMessage ir=new IfMessage();
		
		//1、测试编码是否存在
		String hql="from Goods where ifPrint=true and id.goodsBarcode=:sn and id.comId=:com_id and ifEffective=true and currentWh=:currentWh";
		
		Query q = session.createQuery(hql);
		q.setParameter("sn", sn);
		q.setParameter("com_id", com_id);
		q.setParameter("currentWh", currentWh);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>)q.list();
		if (sws.size()==0){
			ir.setIf_ok(false);
			ir.setMessage("该商品条码"+sn+"未打印、或处于不可用状态，或非我公司条码");
			return ir;
		}
		
		com.cqqyd2014.hibernate.entities.Goods g=sws.get(0);
		
		//2、测试库位
		String storage=g.getCurrentStorage();
		if (!storage.equals("DEFAUL")){
			ir.setIf_ok(false);
			ir.setMessage("当前条码"+sn+"不在DEFAUL默认库位");
			return ir;
		}
		ir.setIf_ok(true);
		
		
		
		
		
		return ir;
	}
	
	
	
	/*
	public boolean checkSn(Session session,String sn){
		String hql="from Goods where ifPrint=true and id.goodsBarcode=:sn and ifEffective=true";
		
		Query q = session.createQuery(hql);
		q.setParameter("sn", sn);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>)q.list();
		if (sws.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkSnWh(Session session,String sn,String currentWh){
		String hql="from Goods where ifPrint=true and id.goodsBarcode=:sn and ifEffective=true and currentWh=:currentWh";
		
		Query q = session.createQuery(hql);
		q.setParameter("sn", sn);
		q.setParameter("currentWh", currentWh);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>)q.list();
		if (sws.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
	*/
	public com.cqqyd2014.hibernate.entities.Goods getEntity(Session session,String barcode,String com_id){
String hql="from Goods where id.goodsBarcode=:barcode and id.comId=:com_id";
		
		Query q = session.createQuery(hql);
		q.setParameter("barcode", barcode);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>)q.list();
		if (sws.size()>0){
			return sws.get(0);
		}
		else{
			return null;
		}
	}
	
		



	

	//测试sn是否在库，不是供应商或者客户状态,库位是DEFAUL
	public boolean checkSnIfInWhDefaul(Session session,String com_id,String sn){
		
		
		com.cqqyd2014.hibernate.entities.Goods g=getEntity(session,sn,com_id);
		if (g==null){
			return false;
		}
		String currentWh=g.getCurrentWh();
		
			if (currentWh.equals("SUPPLY")||currentWh.equals("CUSTOM")){
				return false;
			}
			else{
				//测试库位,应该为DEFAUL
				
				String currentStorage=g.getCurrentStorage();
				if (currentStorage.equals("DEFAUL")){
					return true;
				}
				else{
					return false;
				}
			}
			
		
		
	}
	public IfMessage if_prepackaged(Session session,String com_id,String goods_barcode){
		IfMessage ir=new IfMessage();
		com.cqqyd2014.hibernate.entities.Goods g=getEntity(session,goods_barcode,com_id);
		String currentWh=g.getCurrentWh();
		
			if (currentWh.equals("SUPPLY")){
				ir.setIf_ok(false);
				ir.setMessage("预包装商品只能在普通仓库，当前仓库为供应商");
				return ir;
			}
			if (currentWh.equals("CUSTOM")){
				ir.setIf_ok(false);
				ir.setMessage("预包装商品只能在普通仓库，当前仓库为客户");
				return ir;
			}
				//测试库位
				
				String currentStorage=g.getCurrentWh();
				if (currentStorage.equals("PREPAC")){
					ir.setIf_ok(true);
					
					return ir;
				}
				else{
					ir.setIf_ok(false);
					ir.setMessage("商品库位不是预包装，而是"+currentStorage);
					return ir;
				}
			
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> getUnPrintGoodsBarcode(Session session,String com_id,String user_id){
		String hql="from Goods where printed=false and  effective=true and id.comId=:com_id and maker=:user_id";
		
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.Goods>)q.list();
		return sws;
	}
	
	
	
}
