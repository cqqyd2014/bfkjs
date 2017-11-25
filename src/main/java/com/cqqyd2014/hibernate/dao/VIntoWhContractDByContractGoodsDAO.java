package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.entities.VIntoWhContractDByContractGoods;

public class VIntoWhContractDByContractGoodsDAO {
com.cqqyd2014.hibernate.entities.VIntoWhContractDByContractGoods getObject(Session session,String contract_id,String goods_id){
	String hql="from VIntoWhContractDByContractGoods where id.contractId=:contract_id and id.goodsId=:goods_id";
	Query q = session.createQuery(hql);
	q.setParameter("contract_id", contract_id);
	q.setParameter("goods_id", goods_id);
	java.util.ArrayList<VIntoWhContractDByContractGoods>  list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VIntoWhContractDByContractGoods>)q.list();
	if (list.size()==0){
		return null;
	}
	else{
		return list.get(0);
	}
}
/*
public java.math.BigDecimal getBuy(Session session,String contract_id,String goods_id){
	com.cqqyd2014.hibernate.entities.VIntoWhContractDByContractGoods o=getObject( session, contract_id, goods_id);
	if (o==null){
		return new java.math.BigDecimal(0);
	}
	else{
		return o.getId().getSumBuy();
	}
	
}
*/

}
