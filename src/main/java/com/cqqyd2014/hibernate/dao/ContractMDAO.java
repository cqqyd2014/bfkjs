package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class ContractMDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> getArrayListEntitesByComId(Session session,String com_id){
		String hql="from ContractM where id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);


		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM>) q
				.list();
		return rs;
		
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> getArrayListEntitesByComIdUnArrial(Session session,String com_id){
		String hql="from ContractM where id.comId=:com_id and arrival=false";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);


		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM>) q
				.list();
		return rs;
		
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> getListWaiteForArrival(Session session,String com_id){
		String hql="from ContractM where id.comId=:com_id and arrival=false";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);


		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM>) q
				.list();
		return rs;
		
	}

	public com.cqqyd2014.hibernate.entities.ContractM getObjectByContractId(Session session,String com_id,String contract_no){
		String hql="from ContractM where id.comId=:com_id and id.contractNo=:contract_no";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("contract_no", contract_no);

		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
	}
	public void updateContractM(Session session,String com_id,String contract_no,String supply_id,java.util.Date paperDat,java.math.BigDecimal amount,String user_id){
		com.cqqyd2014.hibernate.entities.ContractM cm=getObjectByContractId(session,com_id,contract_no);
		if (cm==null){
			cm=new com.cqqyd2014.hibernate.entities.ContractM();
			cm.setLastPrintDat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-01-01"));
			cm.setPrintCount(new java.math.BigDecimal(0));
			cm.setUserId(user_id);
			cm.setEffective(true);
		}
		cm.setId(new com.cqqyd2014.hibernate.entities.ContractMId(contract_no,com_id));
		cm.setOpDat(new java.util.Date());
		cm.setPaperDat(paperDat);
		cm.setSupply(supply_id);
		cm.setAmount(amount);
		cm.setArrival(false);
		cm.setUneffectiveDat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
		cm.setUneffectiveUserId("");
		
		session.saveOrUpdate(cm);
		
	}
	//更新最后打印信息
	public void updateLastPrintBarcode(Session session,String com_id,String contract_id,java.util.Date date){
		com.cqqyd2014.hibernate.entities.ContractM cm=getObjectByContractId(session,com_id,contract_id);
		cm.setLastPrintDat(date);
		cm.setPrintCount(cm.getPrintCount().add(new java.math.BigDecimal(1)));
		session.saveOrUpdate(cm);
	}
}
