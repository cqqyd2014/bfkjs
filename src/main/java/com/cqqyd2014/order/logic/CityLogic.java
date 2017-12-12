package com.cqqyd2014.order.logic;

import com.cqqyd2014.order.logic.result.CityLogicResult;

public class CityLogic {
	public static  CityLogicResult ProvinceCityDistrict(String province,String city,String district){
		
		CityLogicResult clr=new CityLogicResult();
		//初始化
		clr.setProvince(province);
		clr.setCity(city);
		clr.setDistrict(district);
		//如果是直辖市，直接返回
				if (province.substring(province.length()-1, province.length()).equals("市")){
					clr.setCity(clr.getProvince());
				}
				// 湖北和海南特殊处理-"省直辖县级行政区划"
				if (city.equals("省直辖县级行政区划")){
					clr.setCity(district);
				}
				return clr;
	}

}
