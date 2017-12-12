package com.cqqyd2014.hibernate.dao;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;

public class VWareHouseStorageDetailDAO {
	//所有记录，以单位，仓库号，库位号
		public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWarehouseStorageDetail> getByComWhStorage(Session session,String com_id,String wh_id,String s_id){
			String hql = "from VWarehouseStorageDetail where id.comId=:com_id and id.storageId=:s_id and id.whId=:wh_id order by id.goodsId";

			Query q = session.createQuery(hql);
			q.setParameter("wh_id", wh_id);
			q.setParameter("s_id", s_id);
			q.setParameter("com_id", com_id);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWarehouseStorageDetail> hbds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWarehouseStorageDetail>)q.list();
			return hbds;
			
			
		}
		public java.util.ArrayList<com.cqqyd2014.wh.model.StorageDetail> getModelByComWhStorage(Session session,String com_id,String wh_id,String s_id){
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWarehouseStorageDetail> wsds=getByComWhStorage(session,com_id,wh_id,s_id);
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			java.util.ArrayList<com.cqqyd2014.wh.model.StorageDetail> sds_model=new java.util.ArrayList<>();
			for (int i=0;i<wsds.size();i++){
				com.cqqyd2014.wh.model.StorageDetail sd_model=new com.cqqyd2014.wh.model.StorageDetail();
				com.cqqyd2014.hibernate.entities.VWarehouseStorageDetail wsd=wsds.get(i);
				sd_model.setGoods_id(wsd.getId().getGoodsId());
				//System.out.println(wsd.getId().getGoodsId());
				//System.out.println(wsd.getId().getStorageId());
				java.math.BigDecimal num=wsd.getId().getNum();
				sd_model.setNum(num);
				sd_model.setS_id(wsd.getId().getStorageId());
				sd_model.setWh_id(wsd.getId().getWhId());
				sd_model.setGoods_name(wsd.getId().getCName());

				//System.out.println(wsd.getId().getWhId());
				java.math.BigDecimal amount_all=gdao.getValueByWhStorageGoodsId(session, com_id, wsd.getId().getWhId(), wsd.getId().getStorageId(), wsd.getId().getGoodsId());
				if (amount_all==null){
					continue;
				}
				sd_model.setValue(amount_all);
				//System.out.println(amount_all);
				
				/*
				if(wsd.getId().getStorageId().equals("LOCKED")){
					sd_model.setAvg(new java.math.BigDecimal(0));
				}
				else{
*/
				if (num.compareTo(new java.math.BigDecimal(0))==0){
					sd_model.setAvg(new java.math.BigDecimal(0));
				}else{

					java.math.BigDecimal avg=amount_all.divide(num,2, BigDecimal.ROUND_DOWN);

					sd_model.setAvg(avg);
					//sd_model.setAvg(new java.math.BigDecimal(0));
				
				}
				sds_model.add(sd_model);

				
			}
			wsds.clear();
			wsds=null;

			return sds_model;
		}

}
