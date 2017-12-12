package com.cqqyd2014.contract.logic;

public class ContractMLogic {
	
	
	
	
	public static com.cqqyd2014.contract.model.ContractM getModelFromEntiy(com.cqqyd2014.hibernate.entities.ContractM cm_h){
		com.cqqyd2014.contract.model.ContractM cm=new com.cqqyd2014.contract.model.ContractM();
		cm.setAmount(cm_h.getAmount());
		cm.setArrival(cm_h.getArrival());
		cm.setContract_no(cm_h.getId().getContractNo());
		cm.setPaper_dat(cm_h.getPaperDat());
		cm.setLast_print_dat(cm_h.getLastPrintDat());
		cm.setOp_dat(cm_h.getOpDat());
		cm.setPrint_count(cm_h.getPrintCount());
		cm.setSupply_name(cm_h.getSupply());
		return cm;
	}
	
	
	public static java.util.ArrayList<com.cqqyd2014.contract.model.ContractM> getArrayListModelFromArrayListEntity(java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> cms_h){
		java.util.ArrayList<com.cqqyd2014.contract.model.ContractM> cms=new java.util.ArrayList<>();
		
		for (int i =0;i<cms_h.size();i++) {
			com.cqqyd2014.contract.model.ContractM cm=getModelFromEntiy(cms_h.get(i));
			cms.add(cm);
		}
		return cms;
		
	}

}
