package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VContractM generated by hbm2java
 */
@Entity
@Table(name = "v_contract_m", schema = "public")
public class VContractM implements java.io.Serializable {

	private VContractMId id;

	public VContractM() {
	}

	public VContractM(VContractMId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "contractNo", column = @Column(name = "contract_no", length = 45)),
			@AttributeOverride(name = "supply", column = @Column(name = "supply", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "opDat", column = @Column(name = "op_dat", length = 35)),
			@AttributeOverride(name = "paperDat", column = @Column(name = "paper_dat", length = 35)),
			@AttributeOverride(name = "arrival", column = @Column(name = "arrival")),
			@AttributeOverride(name = "amount", column = @Column(name = "amount", precision = 131089, scale = 0)),
			@AttributeOverride(name = "supplyName", column = @Column(name = "supply_name", length = 45)),
			@AttributeOverride(name = "printCount", column = @Column(name = "print_count", precision = 131089, scale = 0)),
			@AttributeOverride(name = "lastPrintDat", column = @Column(name = "last_print_dat", length = 35)) })
	public VContractMId getId() {
		return this.id;
	}

	public void setId(VContractMId id) {
		this.id = id;
	}

}
