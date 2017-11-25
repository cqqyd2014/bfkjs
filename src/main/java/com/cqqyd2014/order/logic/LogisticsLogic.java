package com.cqqyd2014.order.logic;

import java.math.BigDecimal;


import org.hibernate.Session;

import com.cqqyd2014.hibernate.dao.LogisticsFeeDAO;

public class LogisticsLogic {
	public static java.math.BigDecimal getFeeByLogisticsVehicleNum(Session session,String com_id,String logistics_code,String vehicle,java.math.BigDecimal num){
		LogisticsFeeDAO lfdao=new LogisticsFeeDAO();
		com.cqqyd2014.hibernate.entities.LogisticsFee lf=lfdao.getEntityByLogisticsVehicleNum(session, com_id, logistics_code, vehicle);
		if (num.compareTo(new java.math.BigDecimal("1"))==1){
			//大于1
			//System.out.println(lf.getFirstFee());
			java.math.BigDecimal bigger=num.setScale(0, BigDecimal.ROUND_HALF_UP).subtract(new java.math.BigDecimal("1"));
			return lf.getFirstFee().add(bigger.multiply(lf.getNextFee()));
		}
		else{
			//小于1
			//System.out.println(lf.getFirstFee());
			return lf.getFirstFee();
		}
	}
	
	

}
