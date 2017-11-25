package com.cqqyd2014.wh.logic;

import org.hibernate.Session;



public class IntoWh {
	public synchronized static void numChange(Session session,String com_id,String intoUuid,String goods_id,java.math.BigDecimal num,String type,String user_id){
		com.cqqyd2014.hibernate.dao.IntoWhDDAO iwddao=new com.cqqyd2014.hibernate.dao.IntoWhDDAO();
		com.cqqyd2014.hibernate.dao.IntoWhMDAO iwmdao=new com.cqqyd2014.hibernate.dao.IntoWhMDAO();
		com.cqqyd2014.hibernate.dao.VContractDDAO vcddao=new com.cqqyd2014.hibernate.dao.VContractDDAO();
		
		com.cqqyd2014.hibernate.entities.IntoWhM iwm=iwmdao.getObjectByUuid(session, com_id, intoUuid);
		com.cqqyd2014.hibernate.entities.IntoWhD iwd=iwddao.getIntoWhD(session, com_id, intoUuid, goods_id);
		if (iwd==null){
			iwd=new com.cqqyd2014.hibernate.entities.IntoWhD();
			com.cqqyd2014.hibernate.entities.IntoWhDId iwdid=new com.cqqyd2014.hibernate.entities.IntoWhDId();
			iwdid.setComId(com_id);
			iwdid.setGoodsId(goods_id);
			iwdid.setUuid(intoUuid);
			iwd.setId(iwdid);
			com.cqqyd2014.hibernate.dao.VIntoWhContractDDAO viwcddao=new com.cqqyd2014.hibernate.dao.VIntoWhContractDDAO();
			com.cqqyd2014.hibernate.entities.VIntoWhContractD viwcd=viwcddao.getObjectByUuidGoodsId(session, com_id, intoUuid, goods_id);
			
			iwd.setPrice(viwcd.getId().getPrice());
			iwd.setBuy(new java.math.BigDecimal("0"));
			iwd.setOut(new java.math.BigDecimal("0"));
			iwd.setYue(new java.math.BigDecimal("0"));
			
		}
		if (type.equals("0001")){
			//采购入库，参数为正数
			iwd.setBuy(iwd.getBuy().add(num));

			//测试商品是否入库完毕，更新合同表主表
			
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> vcds=vcddao.getArrayListByContractNo(session, com_id,iwm.getContractId());
			
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds=com.cqqyd2014.contract.logic.ContractDLogic.getArrayListModelFromArrayListView(vcds);
			boolean flag=true;
			for (int i=0;i<cds.size();i++) {
				if (cds.get(i).getWait_for_arrival().compareTo(new java.math.BigDecimal(0))==1) {
					flag=false;
				}
			}
			if (flag) {
				com.cqqyd2014.hibernate.dao.ContractMDAO cdmdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
				com.cqqyd2014.hibernate.entities.ContractM cdm=cdmdao.getObjectByContractId(session, com_id, iwm.getContractId());
				cdm.setArrival(true);
				session.saveOrUpdate(cdm);
			}
			//级联更新合同表入库数量
			com.cqqyd2014.hibernate.entities.VContractD vcd=vcddao.getViewByContractNoGoodsId(session, iwm.getContractId(), goods_id, com_id);
			com.cqqyd2014.contract.model.ContractD cd=com.cqqyd2014.contract.logic.ContractDLogic.getModelFromView(vcd);
			cd.setNum_in(cd.getNum_in().add(num));
			com.cqqyd2014.contract.logic.ContractDLogic.save(session, cd);
		}
		if (type.equals("0002")){
			//销售出库，参数为负数
			iwd.setOut(iwd.getOut().add(num));
			//级联更新合同表入库数量
			com.cqqyd2014.hibernate.entities.VContractD vcd=vcddao.getViewByContractNoGoodsId(session, iwm.getContractId(), goods_id, com_id);
			com.cqqyd2014.contract.model.ContractD cd=com.cqqyd2014.contract.logic.ContractDLogic.getModelFromView(vcd);
			cd.setNum_out(cd.getNum_out().add(num));
			
			com.cqqyd2014.contract.logic.ContractDLogic.save(session, cd);
		}
		if (type.equals("0003")){
			//退货入库，退货一个，以正数为参数，出库减少
			iwd.setOut(iwd.getOut().subtract(num));
			//级联更新合同表入库数量
			com.cqqyd2014.hibernate.entities.VContractD vcd=vcddao.getViewByContractNoGoodsId(session, iwm.getContractId(), goods_id, com_id);
			com.cqqyd2014.contract.model.ContractD cd=com.cqqyd2014.contract.logic.ContractDLogic.getModelFromView(vcd);
			cd.setNum_out(cd.getNum_out().subtract(num));
			com.cqqyd2014.contract.logic.ContractDLogic.save(session, cd);
		}
		if (type.equals("0004")){
			//条码作废，以出库计算
			iwd.setOut(iwd.getOut().add(num));
		}
		if (type.equals("0005")){
			//商品作废，以出库计算
			iwd.setOut(iwd.getOut().add(num));
		}
		iwd.setYue(iwd.getYue().add(num));
		session.merge(iwd);
		//生成日志
		com.cqqyd2014.hibernate.entities.IntoWhLog log=new com.cqqyd2014.hibernate.entities.IntoWhLog();
		com.cqqyd2014.hibernate.entities.IntoWhLogId logid=new com.cqqyd2014.hibernate.entities.IntoWhLogId();
		logid.setComId(com_id);
		logid.setOpDate(new java.util.Date());
		logid.setUuid(intoUuid);
		log.setId(logid);
		log.setNum(num);
		log.setGoodsId(goods_id);
		log.setType(type);
		log.setUserId(user_id);
		session.save(log);
	}

}
