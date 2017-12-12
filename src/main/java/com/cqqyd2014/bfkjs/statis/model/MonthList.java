package com.cqqyd2014.bfkjs.statis.model;

public class MonthList {
	public String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public java.util.Date d_start1;
	public java.util.Date d_end1;
	public java.util.Date d_start2;
	public java.util.Date d_end2;
	//前一月
	public java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.MonthItem> monthList1;
	public double total1=0;
	//本月
	public java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.MonthItem> monthList2;
	public double total2=0;
	public java.util.Date getD_start1() {
		return d_start1;
	}
	public void setD_start1(java.util.Date d_start1) {
		this.d_start1 = d_start1;
	}
	public java.util.Date getD_end1() {
		return d_end1;
	}
	public void setD_end1(java.util.Date d_end1) {
		this.d_end1 = d_end1;
	}
	public java.util.Date getD_start2() {
		return d_start2;
	}
	public void setD_start2(java.util.Date d_start2) {
		this.d_start2 = d_start2;
	}
	public java.util.Date getD_end2() {
		return d_end2;
	}
	public void setD_end2(java.util.Date d_end2) {
		this.d_end2 = d_end2;
	}
	public java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.MonthItem> getMonthList1() {
		return monthList1;
	}
	public void setMonthList1(
			java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.MonthItem> monthList1) {
		this.monthList1 = monthList1;
	}
	public double getTotal1() {
		return total1;
	}
	public void setTotal1(double total1) {
		this.total1 = total1;
	}
	public java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.MonthItem> getMonthList2() {
		return monthList2;
	}
	public void setMonthList2(
			java.util.ArrayList<com.cqqyd2014.bfkjs.statis.model.MonthItem> monthList2) {
		this.monthList2 = monthList2;
	}
	public double getTotal2() {
		return total2;
	}
	public void setTotal2(double total2) {
		this.total2 = total2;
	}

}
