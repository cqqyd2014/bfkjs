package com.cqqyd2014.bfkjs.statis.model;


public class ProductPriceCount {
	

	String c_id;
	String c_name;
	
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	
	java.math.BigDecimal price;
	java.math.BigDecimal count;
	java.math.BigDecimal sum;
	
	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	public java.math.BigDecimal getCount() {
		return count;
	}
	public void setCount(java.math.BigDecimal count) {
		this.count = count;
	}
	public java.math.BigDecimal getSum() {
		return sum;
	}
	public void setSum(java.math.BigDecimal sum) {
		this.sum = sum;
	}
	

}