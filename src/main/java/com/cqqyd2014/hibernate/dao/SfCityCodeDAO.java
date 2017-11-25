package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.order.logic.CityLogic;
import com.cqqyd2014.order.logic.result.CityLogicResult;

public class SfCityCodeDAO {
	
	
	
	
	private String getCodeByProvinceCity(Session session,String province,String city) throws Exception{
		String hql="from SfCityCode where provinceName=:province and cityName=:city";
		Query query = session.createQuery(hql);
		query.setString("province", province);
		query.setString("city", city);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfCityCode> cis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SfCityCode>)query.list();
		if (cis.size()==0){
			throw new Exception("找不到这个省市"+province+city+"的顺丰编号");
		}
		else{
			return cis.get(0).getCityCode();
		}
	}
	
	
	//根据省，市，返回顺丰地区码
	public String getCodeByProvniceCityDistrict(Session session,String province,String city,String district) throws Exception{

		CityLogicResult clr=CityLogic.ProvinceCityDistrict(province, city, district);
		
		
		
		
		return getCodeByProvinceCity(session,clr.getProvince(),clr.getCity());
		
		
	}


}
