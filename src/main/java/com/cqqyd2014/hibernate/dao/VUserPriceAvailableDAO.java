package com.cqqyd2014.hibernate.dao;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.cqqyd2014.common.hibernate.GetModelFromEntityViewDAO;



public final class VUserPriceAvailableDAO  extends GetModelFromEntityViewDAO{

	
	//模糊查询
	@SuppressWarnings("unchecked")
	public java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> getGoodsInfosLike(Session session,String goods_id,String com_id,String user_id,java.util.Date date){
		String hql="from VUserPriceAvailable where id.comId=:com_id and id.userId=:user_id and id.startTime<=:t1 and id.endTime>=:t2 and id.goodsId like :goods_id";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("goods_id", "%"+goods_id+"%");
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		q.setParameter("com_id", com_id);
		
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable>) q
				.list();
		return (java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice>)getArrayListModelFromArrayListViewEntity(rs);
	}
	public com.cqqyd2014.usergroup.model.UserPrice getGoodsInfo(Session session,String goods_id,String com_id,String user_id,java.util.Date date) {
		String hql="from VUserPriceAvailable where id.comId=:com_id and id.userId=:user_id and id.startTime<=:t1 and id.endTime>=:t2 and id.goodsId=:goods_id";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql);
		q.setParameter("user_id", user_id);
		q.setParameter("goods_id",goods_id);
		q.setParameter("t1", date);
		q.setParameter("t2", date);
		q.setParameter("com_id", com_id);
		
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable>) q
				.list();
		if (rs.size()>0){
			return (com.cqqyd2014.usergroup.model.UserPrice)getModelFromViewEntity(rs.get(0));
		}
		else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getModelFromViewEntity(S s) {
		// TODO Auto-generated method stub
		com.cqqyd2014.hibernate.entities.VUserPriceAvailable vupa=(com.cqqyd2014.hibernate.entities.VUserPriceAvailable) s;
		java.util.Date now=new java.util.Date();
		com.cqqyd2014.usergroup.model.UserPrice up=new com.cqqyd2014.usergroup.model.UserPrice();
		up.setYue(vupa.getId().getSumAvailable());
		up.setCom_id(vupa.getId().getComId());
		up.setEffective(vupa.getId().getEffective());
		up.setEnd_time(vupa.getId().getEndTime());
		up.setGoods_id(vupa.getId().getGoodsId());
		up.setPrice(vupa.getId().getUserPrice());
		up.setStart_time(vupa.getId().getStartTime());
		up.setUuid(vupa.getId().getUserpriceUuid());
		up.setGoods_name(vupa.getId().getCName());
		up.setUserid(vupa.getId().getUserId());
		up.setUnit(vupa.getId().getUnit());
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(now, vupa.getId().getEndTime())>0){
			//过期时间比当前时间更早，价格已经过期
			up.setEffective_now(false);
			up.setIn_the_futurn(false);
			up.setOut_date(true);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(vupa.getId().getEndTime(), now)>0&&
				com.cqqyd2014.util.DateUtil.getDistanceSecends(now, vupa.getId().getStartTime())>0){
			
			//当前有效的价格
			up.setEffective_now(true);
			up.setIn_the_futurn(false);
			up.setOut_date(false);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(vupa.getId().getStartTime(), now)>0){
			//未来的价格
			up.setEffective_now(false);
			up.setIn_the_futurn(true);
			up.setOut_date(false);
		}
		return (T)up;
	}
	@Override
	public <T> void save(Session session, T t) {
		// TODO Auto-generated method stub
		
	}
}