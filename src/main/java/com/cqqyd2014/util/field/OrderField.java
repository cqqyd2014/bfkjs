package com.cqqyd2014.util.field;

public class OrderField {
	private static String original_id="电商平台原始单号";
	private static String user_name="收件人姓名";
	private static String user_addr="收件人详细地址";
	private static String tell="联系电话1（手机）";
	
	public static String getName(String field){
		switch(field){
		case "original_id":
			return original_id;
		case "user_name":
			return user_name;
		case "user_addr":
			return user_addr;
		case "tell":
			return tell;
		default:
			return "";
		}
	}
	

}
