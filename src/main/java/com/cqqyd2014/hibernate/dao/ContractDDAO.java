package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.util.exception.AjaxSuccessMessageException;


public class ContractDDAO {
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD> getListByContractId(Session session,String com_id,String contract_id){
		String hql="from ContractD where id.comId=:com_id and id.contractNo=:contract_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("contract_id", contract_id);

		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD>) q
				.list();
		return rs;
		
	}
	
	public com.cqqyd2014.hibernate.entities.ContractD getObjectByGoodsId(Session session,String com_id
			,String contract_no,String goods_id){
		String hql="from ContractD where id.comId=:com_id and id.contractNo=:contract_no and id.goodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("contract_no", contract_no);
		q.setParameter("goods_id", goods_id);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD>) q
				.list();
		if (rs.size()>0){
			return rs.get(0);
		}
		else{
			return null;
		}
		
		
	}
	/*
	public void clearDetail(Session session,String com_id,String contract_no){
		String hql="from ContractD where id.comId=:com_id and id.contractNo=:contract_no";
		Query q = session.createQuery(hql);
		q.setParameter("contract_no", contract_no);

		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD> list=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD>)q.list();
		for (int i=0;i<list.size();i++){
			com.cqqyd2014.hibernate.entities.ContractD cd=list.get(i);
			session.delete(cd);
		}
		session.flush();
	}
	*/
	public java.math.BigDecimal addOrUpdateDetailUsingArray(Session session,String com_id,String contract_no,java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds) throws Exception{
		VIntoWhContractDByContractGoodsDAO iwbgdao=new VIntoWhContractDByContractGoodsDAO();
		java.math.BigDecimal amount=new java.math.BigDecimal("0");
		
		for (int i=0;i<cds.size();i++){
			com.cqqyd2014.hibernate.entities.ContractD cd=getObjectByGoodsId(session,com_id
			,contract_no,cds.get(i).getGoods_id());
			if (cd==null){
				cd=new com.cqqyd2014.hibernate.entities.ContractD();
				com.cqqyd2014.hibernate.entities.ContractDId cdid=new com.cqqyd2014.hibernate.entities.ContractDId();
				cdid.setComId(com_id);
				cdid.setContractNo(contract_no);
				cdid.setGoodsId(cds.get(i).getGoods_id());
				cd.setId(cdid);
			}
			
			
			if (cds.get(i).getNum().compareTo(cds.get(i).getNum_in())==-1){
				throw new Exception("合同数量不能少于已经入库数量");
			}
			cd.setNum(cds.get(i).getNum());
			cd.setPrice(cds.get(i).getPrice());
			cd.setNumIn(new java.math.BigDecimal(0));
			cd.setNumOut(new java.math.BigDecimal(0));
			com.cqqyd2014.hibernate.entities.VIntoWhContractDByContractGoods iwdbcg=iwbgdao.getObject(session, contract_no, cds.get(i).getGoods_id());
			//in和out 数据是自动更新，不能更改
			//cd.setNumIn(iwdbcg.getId().getSumBuy());
			//cd.setNumOut(iwdbcg.getId().getSumOut());

			amount=amount.add(cds.get(i).getNum().multiply(cds.get(i).getPrice()));
			session.saveOrUpdate(cd);
		}
		return amount;
		
	}
	

	

}
