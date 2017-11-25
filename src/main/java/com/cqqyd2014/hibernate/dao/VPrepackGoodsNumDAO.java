package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VPrepackGoodsNumDAO {
	//得到预包装的商品列表
		public java.util.LinkedHashMap<String,java.math.BigDecimal> getPrePackageMap(Session session,String com_id,String pickup_barcode){
			String hql="from VPrepackGoodsNum where id.comId=:com_id and id.PSn=:pickup_barcode";
			
			Query q = session.createQuery(hql);
			q.setParameter("com_id", com_id);
			q.setParameter("pickup_barcode", pickup_barcode);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackGoodsNum> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPrepackGoodsNum>)q.list();
			java.util.LinkedHashMap<String,java.math.BigDecimal> odis=new java.util.LinkedHashMap<>();
			for (int i=0;i<sws.size();i++){
				com.cqqyd2014.hibernate.entities.VPrepackGoodsNum ppd=sws.get(i);
				//com.cqqyd2014.bfkjs.order.model.OrderDetailItem odi=new com.cqqyd2014.bfkjs.order.model.OrderDetailItem();
				odis.put(ppd.getId().getGoodsId(), new java.math.BigDecimal(ppd.getId().getNum()));
			}
			return odis;
		}

}
