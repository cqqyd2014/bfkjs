package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ContractPrintHistory generated by hbm2java
 */
@Entity
@Table(name = "contract_print_history", schema = "public")
public class ContractPrintHistory implements java.io.Serializable {

	private ContractPrintHistoryId id;
	private BigDecimal printNum;

	public ContractPrintHistory() {
	}

	public ContractPrintHistory(ContractPrintHistoryId id) {
		this.id = id;
	}

	public ContractPrintHistory(ContractPrintHistoryId id, BigDecimal printNum) {
		this.id = id;
		this.printNum = printNum;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "contractId", column = @Column(name = "contract_id", nullable = false, length = 45)),
			@AttributeOverride(name = "printDat", column = @Column(name = "print_dat", nullable = false, length = 35)) })
	public ContractPrintHistoryId getId() {
		return this.id;
	}

	public void setId(ContractPrintHistoryId id) {
		this.id = id;
	}

	@Column(name = "print_num", precision = 131089, scale = 0)
	public BigDecimal getPrintNum() {
		return this.printNum;
	}

	public void setPrintNum(BigDecimal printNum) {
		this.printNum = printNum;
	}

}
