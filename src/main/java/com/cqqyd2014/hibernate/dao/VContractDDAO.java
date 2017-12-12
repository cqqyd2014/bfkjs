package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VContractDDAO {
	public com.cqqyd2014.hibernate.entities.VContractD getViewByContractNoGoodsId(Session session,String contract_no,String goods_id,String com_id){
		String hql="from VContractD where id.comId=:com_id and id.contractNo=:contract_no and id.goodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("contract_no",contract_no);
		q.setParameter("com_id",com_id);
		q.setParameter("goods_id",goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> list=(java.util.ArrayList)q.list();
		if (list.size()>0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> getArrayListByContractNo(Session session,String com_id,String contract_no){
		String hql="from VContractD where id.comId=:com_id and id.contractNo=:contract_no";
		Query q = session.createQuery(hql);
		q.setParameter("contract_no",contract_no);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> list=(java.util.ArrayList)q.list();
		return list;
		
	}
	
	public java.math.BigDecimal getSumWaiteForArrival(Session session,String com_id,String contract_no){
		String hql=" from VContractD where id.comId=:com_id and id.contractNo=:contract_no";
		Query q = session.createQuery(hql);
		q.setParameter("contract_no",contract_no);
		q.setParameter("com_id",com_id);
		java.math.BigDecimal value = ((com.cqqyd2014.hibernate.entities.VContractD)q.list().get(0)).getId().getWaiteForArrival();
    		return value;
	}

}
