package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VIntoWhContractByGoodsIdDAO {
	public com.cqqyd2014.hibernate.entities.VIntoWhContractByGoodsId getObject(Session session,String contract_no,String goods_id,String com_id){
		String hql="from VIntoWhContractByGoodsId where id.comId=:com_id and id.contractNo=:contract_no and id.goodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("contract_no", contract_no);
		q.setParameter("goods_id", goods_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VIntoWhContractByGoodsId> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VIntoWhContractByGoodsId>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
	}
	public java.math.BigDecimal getBuy(Session session,String contract_no,String goods_id,String com_id){
		com.cqqyd2014.hibernate.entities.VIntoWhContractByGoodsId o=getObject(session,contract_no,goods_id,com_id);
		if (o==null){
			return new java.math.BigDecimal(0);
		}
		else{
			return o.getId().getBuy();
		}

}
}
