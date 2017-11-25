package com.cqqyd2014.contract.logic;

import org.hibernate.Session;

public class ContractDLogic {
	
	
	public static void save(Session session,com.cqqyd2014.contract.model.ContractD cd) {
		com.cqqyd2014.hibernate.entities.ContractD cd_h=new com.cqqyd2014.hibernate.entities.ContractD();
		cd_h.setId(new com.cqqyd2014.hibernate.entities.ContractDId(cd.getCom_id(), cd.getContract_no(), cd.getGoods_id()));
		cd_h.setNum(cd.getNum());
		cd_h.setNumIn(cd.getNum_in());
		cd_h.setNumOut(cd.getNum_out());
		cd_h.setPrice(cd.getPrice());
		session.merge(cd_h);
		
	}
	public static com.cqqyd2014.contract.model.ContractD getModelFromView(com.cqqyd2014.hibernate.entities.VContractD cd_h){
		com.cqqyd2014.contract.model.ContractD cd=new com.cqqyd2014.contract.model.ContractD();
		cd.setAmount(cd_h.getId().getPrice().multiply(cd_h.getId().getNum()));
		cd.setC_unit(cd_h.getId().getCUnit());
		cd.setGoods_id(cd_h.getId().getGoodsId());
		cd.setGoods_name(cd_h.getId().getCName());
		cd.setNum(cd_h.getId().getNum());
		cd.setNum_in(cd_h.getId().getNumIn());
		cd.setNum_out(cd_h.getId().getNumOut());
		cd.setPrice(cd_h.getId().getPrice());
		cd.setUnit(cd_h.getId().getUnit());
		cd.setWait_for_arrival(cd_h.getId().getNum().subtract(cd_h.getId().getNumIn()));
		cd.setCom_id(cd_h.getId().getComId());
		cd.setContract_no(cd_h.getId().getContractNo());
		return cd;
	}
	public static java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> vcds){
		java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds=new java.util.ArrayList<>();
		for (int i=0;i<vcds.size();i++) {
			com.cqqyd2014.contract.model.ContractD cd=getModelFromView(vcds.get(i));
			cds.add(cd);
		}
		return cds;
	}

}
