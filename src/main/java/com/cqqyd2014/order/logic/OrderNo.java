package com.cqqyd2014.order.logic;

import org.hibernate.Session;

public class OrderNo {
	public static  String createNo(Session session, String channel,String com_id) {
		
		String orderNo;
		com.cqqyd2014.hibernate.dao.ComInfoDAO cidao=new com.cqqyd2014.hibernate.dao.ComInfoDAO();
		String orderHead=cidao.getOrderHead(session, com_id);
		cidao=null;
		do {
			orderNo = getNo(session, channel,orderHead,com_id);
		} while (orderNo == null);

		return orderNo;
	
	

}

private static String getNo(Session session, String channel,String orderHead,String com_id) {
	String no = "";
	java.util.Date today = com.cqqyd2014.util.DateUtil.JDateToStartDate(new java.util.Date());
	no = com.cqqyd2014.util.DateUtil.JDateToCompatString(today);
	 
	long end=com.cqqyd2014.hibernate.dao.SnMaxDAO.getNew(session, 1,"ORDER_",com_id,today);
	
	
	
	String randstr = com.cqqyd2014.util.NumberUtil.numToStr0(end, 6);
	no = no + randstr;
	//checkNo为我的检验码，以判断是否是我家的订单
	no = no + checkNo(no);
	com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.OrderMainDAO();
	if (omdao.checkOrderIdIfExist(session,
			orderHead + channel + no)) {
		// 如果重号，返回null
		omdao=null;
		return null;
	}
	omdao=null;
	return orderHead + channel + no;
}

/*
 * 生成校验码，确认识勤驿达进出口有限责任公司的订单
 * 
 * 假设现有的订单码为“20150425704325”，一共14位长，
 * 每一位乘以1,6,3,8,3,1,6,9,7,5,3,2,1,5,得数相加，与9取余数， 作为最后一位
 */
public static String checkNo(String no) {
	String cn = "";
	int all = Integer.parseInt(no.substring(0, 1)) * 1
			+ Integer.parseInt(no.substring(1, 2)) * 6
			+ Integer.parseInt(no.substring(2, 3)) * 3
			+ Integer.parseInt(no.substring(3, 4)) * 8
			+ Integer.parseInt(no.substring(4, 5)) * 3
			+ Integer.parseInt(no.substring(5, 6)) * 1
			+ Integer.parseInt(no.substring(6, 7)) * 6
			+ Integer.parseInt(no.substring(7, 8)) * 9
			+ Integer.parseInt(no.substring(8, 9)) * 7
			+ Integer.parseInt(no.substring(9, 10)) * 5
			+ Integer.parseInt(no.substring(10, 11)) * 3
			+ Integer.parseInt(no.substring(11, 12)) * 2
			+ Integer.parseInt(no.substring(12, 13)) * 1
			+ Integer.parseInt(no.substring(13, 14)) * 5;
	cn = String.valueOf(all % 9);
	return cn;
}
}
