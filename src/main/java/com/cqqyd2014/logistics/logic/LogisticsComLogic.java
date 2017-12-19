package com.cqqyd2014.logistics.logic;

import java.math.BigDecimal;

import org.hibernate.Session;

public class LogisticsComLogic {
	public static com.cqqyd2014.logistics.model.LogisticsCom getModelfromEntity(com.cqqyd2014.hibernate.entities.LogisticsCompany lc_h){
		com.cqqyd2014.logistics.model.LogisticsCom lc=new com.cqqyd2014.logistics.model.LogisticsCom();
		lc.setLogistics_code(lc_h.getLogisticsId());
		lc.setLogistics_name(lc_h.getLogisticsName());
		lc.setBill_no_len(lc_h.getLogisticsBillLength());
		lc.setElec_bill(lc_h.getLogisticsElectronicBill());
		
		return lc;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.logistics.model.LogisticsCom> getArrayListModelFromArrayListEntities(java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsCompany> lcs_h){
		java.util.ArrayList<com.cqqyd2014.logistics.model.LogisticsCom> lcs=new java.util.ArrayList<>();
		for (int i=0,j=lcs_h.size();i<j;i++){
			com.cqqyd2014.logistics.model.LogisticsCom lc=getModelfromEntity(lcs_h.get(i));
			lcs.add(lc);
		}
		return lcs;
	}
public static java.math.BigDecimal getFeeByLogisticsVehicleNum(Session session,String com_id,String logistics_code,String vehicle,java.math.BigDecimal num){
		
		com.cqqyd2014.logistics.model.Vehicle v=com.cqqyd2014.logistics.logic.VehicleLogic.getModelFromView(com.cqqyd2014.hibernate.dao.LogisticsVehicleFeeDAO.getViewByLogisticsVehicle(session, com_id, logistics_code, vehicle));
		if (num.compareTo(new java.math.BigDecimal("1"))==1){
			//大于1
			//System.out.println(lf.getFirstFee());
			java.math.BigDecimal bigger=num.setScale(0, BigDecimal.ROUND_HALF_UP).subtract(new java.math.BigDecimal("1"));
			return v.getFirst_fee().add(bigger.multiply(v.getNext_fee()));
		}
		else{
			//小于1
			//System.out.println(lf.getFirstFee());
			return v.getFirst_fee();
		}
	}
	
	
	public static java.util.LinkedHashMap<String, String> getHashMap(java.util.ArrayList<com.cqqyd2014.hibernate.entities.LogisticsCompany> lcs_h){
		java.util.LinkedHashMap<String, String> map=new java.util.LinkedHashMap<>();
		java.util.ArrayList<com.cqqyd2014.logistics.model.LogisticsCom> lcs=getArrayListModelFromArrayListEntities(lcs_h);
		for (int i=0,len=lcs_h.size();i<len;i++){
			com.cqqyd2014.logistics.model.LogisticsCom lc=lcs.get(i);
			map.put(lc.getLogistics_code(), lc.getLogistics_name());
		}
		return map;
	}

}
