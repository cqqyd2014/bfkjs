package com.cqqyd2014.logistics.model;

public class Vehicle {
	String logistics_code;
	String logistics_name;
	java.math.BigDecimal first_fee;
	java.math.BigDecimal next_fee;
	boolean effective;
	String com_id;
	public String getLogistics_code() {
		return logistics_code;
	}
	public void setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
	}
	public String getLogistics_name() {
		return logistics_name;
	}
	public void setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
	}
	public java.math.BigDecimal getFirst_fee() {
		return first_fee;
	}
	public void setFirst_fee(java.math.BigDecimal first_fee) {
		this.first_fee = first_fee;
	}
	public java.math.BigDecimal getNext_fee() {
		return next_fee;
	}
	public void setNext_fee(java.math.BigDecimal next_fee) {
		this.next_fee = next_fee;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	String vehicle_code;
	String vehicle_name;
	java.math.BigDecimal vechicle_order;

	public String getVehicle_code() {
		return vehicle_code;
	}
	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public java.math.BigDecimal getVechicle_order() {
		return vechicle_order;
	}
	public void setVechicle_order(java.math.BigDecimal vechicle_order) {
		this.vechicle_order = vechicle_order;
	}

	

}
