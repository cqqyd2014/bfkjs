package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrdermainDetailDAO {
	//产品分分品种，价格销售统计
	public java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.ProductPriceCount> getSaleSum(Session session,String com_id,java.util.Date startTime,java.util.Date endTime){
		java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.ProductPriceCount> ppcs=new java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.ProductPriceCount>();
		String hqlString = "select sum(id.CCount),sum(id.CPrice*id.CCount),id.CPrice,id.CName,id.CGoodsId from VOrdermainDetail where id.comId=:com_id and id.hwEndTime between :startTime and :endTime group by id.CPrice,id.CName,id.CGoodsId order by id.CGoodsId,id.CName,id.CPrice";

		Query query = session.createQuery(hqlString);
		query.setParameter("com_id", com_id);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		java.util.ArrayList<Object[]> rs = (java.util.ArrayList<Object[]>)query.list();
		
		if (rs.size()>0){
			for (int i=0;i<rs.size();i++){
				Object[] o=rs.get(i);
				com.cqqyd2014.bfkjs.statis.model.ProductPriceCount ppc=new com.cqqyd2014.bfkjs.statis.model.ProductPriceCount();
				ppc.setCount(new java.math.BigDecimal(o[0].toString()));
				ppc.setSum(new java.math.BigDecimal(o[1].toString()));
				ppc.setPrice(new java.math.BigDecimal(o[2].toString()));
				
				ppc.setC_name(o[3].toString());
				ppc.setC_id(o[4].toString());
				
				
				ppcs.add(ppc);
			}
			
			
			
		}
		
		
		return ppcs;
	}
	
	//得到仓库数量
	public java.util.ArrayList<Integer> getodooHwIds(Session session,java.util.Date startTime,java.util.Date endTime){
		java.util.ArrayList<Integer> odooHwIds=new java.util.ArrayList<Integer>();
		String hqlString="select id.odooHwId from VOrdermainDetail where id.hwEndTime between :startTime and :endTime group by id.odooHwId";
		Query query = session.createQuery(hqlString);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		
		odooHwIds=(java.util.ArrayList<Integer>)query.list();
		return odooHwIds;
	}
	
	//得到该仓库的不同分销渠道
	public java.util.ArrayList<Integer> getodooCustomerIds(Session session,java.util.Date startTime,java.util.Date endTime,Integer odooHwId){
		java.util.ArrayList<Integer> odooCustomerIds=new java.util.ArrayList<Integer>();
		String hqlString="select id.odooCustomerId from VOrdermainDetail where id.odooHwId=:odooHwId and id.hwEndTime between :startTime and :endTime group by id.odooCustomerId";
		Query query = session.createQuery(hqlString);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("odooHwId", odooHwId);
		odooCustomerIds=(java.util.ArrayList<Integer>)query.list();
		return odooCustomerIds;
	}
	

}
