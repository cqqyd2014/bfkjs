package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VGoodsInfoDAO {
	
	
	public java.util.LinkedHashMap<String, String> getGoodsInfosMapInUse(Session session,String com_id) throws Exception{
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> vgis=getViewByInUse(session, com_id);
		
		java.util.ArrayList<com.cqqyd2014.wh.model.GoodsInfo> gs=com.cqqyd2014.wh.logic.GoodsInfoLogic.getArrayModelFromArrayEntityV(vgis);
		return com.cqqyd2014.util.HashMapTools.convertArrayToHashMap(gs.toArray(), "getGoods_id", "getGoods_name");
	}
	
	public com.cqqyd2014.hibernate.entities.VGoodsInfo getGoodsInfoByBarcode(Session session,String com_id,String barcode){
		
		java.util.ArrayList<String> sn_array=com.cqqyd2014.wh.logic.GoodsLogic.decodeBarcode(barcode);
		return getGoodsInfoBySnCode(session,sn_array.get(0),com_id);
	}
	

	
	//查找在售商品
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> getViewByInUse(
			Session session,String com_id) {
		java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> oms = null;
		String hqlString = "from VGoodsInfo where id.comId=:com_id and id.inUse=true order by id.CId";

		Query query = session.createQuery(hqlString);
		
		query.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo>)query.list();
		return gis;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> getGoodsInfosAll(
			Session session,String com_id) {
		java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> oms = null;
		String hqlString = "from VGoodsInfo where id.comId=:com_id order by id.CId";

		Query query = session.createQuery(hqlString);
		
		query.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo>)query.list();
		return gis;
	}
	
	
	//查找商品2
	public com.cqqyd2014.hibernate.entities.VGoodsInfo getGoodsInfo(
			String goods_id, Session session,String com_id) {
		java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> oms = null;
		String hqlString = "from VGoodsInfo where id.CId=:CId and id.comId=:com_id";

		Query query = session.createQuery(hqlString);
		query.setParameter("CId", goods_id);
		query.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo>)query.list();
		if (gis.size()==0){
			return null;
		}
		else{
			return gis.get(0);
		}
		
	}
	//模糊查找商品2
		public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> getGoodsInfosLike(
				String goods_id, Session session,String com_id) {
			java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> oms = null;
			String hqlString = "from VGoodsInfo where id.CId like :CId and id.comId=:com_id";

			Query query = session.createQuery(hqlString);
			query.setParameter("CId", "%"+goods_id+"%");
			query.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo>)query.list();
			return gis;
		}
	
	

	
	/*
	//更新总库存
	public void updateNumFromOdoo(String c_id, Session session,float num){
		com.cqqyd2014.hibernate.entities.GoodsInfo gi=getGoodsInfo(c_id,session);
		if (gi!=null){
			gi.setNum(new BigDecimal(num));
			session.saveOrUpdate(gi);
		}
		
	}
	
	
	//根据c_id返回odoo_id
	public int getOdooId(Session session ,String c_id,String com_id){
		com.cqqyd2014.hibernate.entities.GoodsInfo gi=getGoodsInfo(c_id,session,com_id);
		return gi.getOdooId().intValue();
	}
	
	*/
		//根据goods_id得到sn编码
	
	public String getSnCode(Session session,String goods_id,String com_id){
		com.cqqyd2014.hibernate.entities.VGoodsInfo cgi=this.getGoodsInfo(goods_id, session, com_id);
		return cgi.getId().getSnCode();
	}
	public java.math.BigDecimal getGrossWeight(Session session,String goods_id,String com_id){
		com.cqqyd2014.hibernate.entities.VGoodsInfo cgi=this.getGoodsInfo(goods_id, session, com_id);
		return cgi.getId().getGrossWeight();
	}
	public java.math.BigDecimal getNetWeight(Session session,String goods_id,String com_id){
		com.cqqyd2014.hibernate.entities.VGoodsInfo cgi=this.getGoodsInfo(goods_id, session, com_id);
		return cgi.getId().getNetWeight();
	}
	
	public com.cqqyd2014.hibernate.entities.VGoodsInfo getGoodsInfoBySnCode(Session session,String sn_code,String com_id){
		String hqlString="from VGoodsInfo where id.comId=:com_id and id.snCode=:sn_code";
		Query query = session.createQuery(hqlString);
		query.setParameter("com_id", com_id);
		query.setParameter("sn_code", sn_code);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo>)query.list();
		if (gis.size()>0){
		return gis.get(0);
		}else{
			return null;
		}
	}
}
