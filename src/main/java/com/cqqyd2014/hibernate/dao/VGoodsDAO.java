package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VGoodsDAO {
	//没有打印的条码
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> getEntityVUnPrinted(Session session,String com_id,String userid){
		String hql="from VGoods where id.printed=false and id.maker=:userid and id.comId=:com_id";
	
	Query q = session.createQuery(hql);
	q.setParameter("userid", userid);
	q.setParameter("com_id", com_id);
	java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods>)q.list();
	return sws;
}
	
	

	
	
	public com.cqqyd2014.wh.model.Goods getFullModelByBarcode(Session session,String com_id,String barcode){
		com.cqqyd2014.hibernate.entities.VGoods obj=getEntityViewByBarcode(session,com_id,barcode);
		
		com.cqqyd2014.wh.model.Goods gb=com.cqqyd2014.wh.logic.GoodsLogic.getModelGoodBarcode(session, com_id, barcode);




		return gb;
	}
	public com.cqqyd2014.wh.model.Goods getSimpleModelByBarcode(Session session,String com_id,String barcode){
		com.cqqyd2014.hibernate.entities.VGoods obj=getEntityViewByBarcode(session,com_id,barcode);
		
		com.cqqyd2014.wh.model.Goods gb=com.cqqyd2014.wh.logic.GoodsLogic.getModelGoodBarcode(session, com_id, barcode);
		
		
		return gb;
	}
	
	public com.cqqyd2014.hibernate.entities.VGoods getEntityViewByBarcode(Session session,String com_id,String barcode){
		String hql="from VGoods where id.comId=:com_id and id.goodsBarcode=:barcode";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("barcode", barcode);
		
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods>)q.list();
		if (list.size()==1){
			return list.get(0);
		}
		else{
			return null;
		}
	}
	
	public com.cqqyd2014.hibernate.entities.Goods getObjectAll(Session session,String barcode,String com_id){
		String hql="from VGoods where id.printed=true and id.goodsBarcode=:barcode and id.comId=:com_id";
		
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
	
	public java.util.ArrayList<com.cqqyd2014.wh.model.Goods> getUnPrintGoodsBarcode(Session session,String com_id,String user_id){
		
		String hql="from VGoods where id.comId=:com_id and id.printed=false and id.effective=true and id.maker=:user_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("user_id", user_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods>)q.list();
		
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> sns=com.cqqyd2014.wh.logic.GoodsLogic.getArrayModelFromArrayEntityV(list);
		return sns;
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.wh.model.Goods> getBarcodeByWhGoodsIdInDefaultAndPrepackage(Session session,String com_id,String wh_id,String goods_id){
		String hql="from VGoods where id.printed=true and id.goodsId=:goods_id and id.effective=true and id.comId=:com_id and id.currentWh=:wh_id and id.currentStorage in (\'DEFAUL\',\'PREPAC\')";
				
				Query q = session.createQuery(hql);
				q.setParameter("com_id", com_id);
				q.setParameter("wh_id", wh_id);
				q.setParameter("goods_id", goods_id);
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods>)q.list();
				
				
				java.util.ArrayList<com.cqqyd2014.wh.model.Goods> gbs=com.cqqyd2014.wh.logic.GoodsLogic.getArrayModelFromArrayEntityV(sws);

				
				
				return gbs;
			}
}
