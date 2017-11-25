package com.cqqyd2014.contract.model;

public class ContractM {
	String contract_no;
	String supply_name;
	java.util.Date paper_dat;
	java.util.Date op_dat;
	
	public java.util.Date getPaper_dat() {
		return paper_dat;
	}

	public void setPaper_dat(java.util.Date paper_dat) {
		this.paper_dat = paper_dat;
	}

	public java.util.Date getOp_dat() {
		return op_dat;
	}

	public void setOp_dat(java.util.Date op_dat) {
		this.op_dat = op_dat;
	}

	java.math.BigDecimal amount;
	java.math.BigDecimal print_count;
	java.util.Date last_print_dat;
	boolean arrival;

	public java.math.BigDecimal getPrint_count() {
		return print_count;
	}

	public void setPrint_count(java.math.BigDecimal print_count) {
		this.print_count = print_count;
	}



	public java.util.Date getLast_print_dat() {
		return last_print_dat;
	}

	public void setLast_print_dat(java.util.Date last_print_dat) {
		this.last_print_dat = last_print_dat;
	}

	public boolean isArrival() {
		return arrival;
	}

	public void setArrival(boolean arrival) {
		this.arrival = arrival;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getSupply_name() {
		return supply_name;
	}

	public void setSupply_name(String supply_name) {
		this.supply_name = supply_name;
	}



	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}
}
