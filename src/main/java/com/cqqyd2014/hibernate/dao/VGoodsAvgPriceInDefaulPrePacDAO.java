package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.VGoodsAvgPriceInDefaulPrepac;

public class VGoodsAvgPriceInDefaulPrePacDAO {

	//默认仓库和预包装仓库的平均价格推算的“订单”库位商品价值
	public java.math.BigDecimal getAmountByGoodsIdWhId(Session session,String com_id,String whId,String goodsId){
		
		String hql="from VGoodsAvgPriceInDefaulPrepac where id.comId=:com_id and id.currentWh=:whId and id.goodsId=:goodsId";
				//System.out.println(hql);
				Query q = session.createQuery(hql);
				q.setParameter("com_id", com_id);
				q.setParameter("whId", whId);
				//q.setParameter("storageId", storageId);
				q.setParameter("goodsId", goodsId);
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsAvgPriceInDefaulPrepac> vgpds=(java.util.ArrayList<VGoodsAvgPriceInDefaulPrepac>)q.list();
				if (vgpds.size()==0){
					return new java.math.BigDecimal(0);
				}
				
				return vgpds.get(0).getId().getAmount();
	}
}
