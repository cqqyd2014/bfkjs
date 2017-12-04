package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SupplyInfo generated by hbm2java
 */
@Entity
@Table(name = "supply_info", schema = "public")
public class SupplyInfo implements java.io.Serializable {

	private SupplyInfoId id;
	private String supplyName;
	private String effective;

	public SupplyInfo() {
	}

	public SupplyInfo(SupplyInfoId id) {
		this.id = id;
	}

	public SupplyInfo(SupplyInfoId id, String supplyName, String effective) {
		this.id = id;
		this.supplyName = supplyName;
		this.effective = effective;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "supplyId", column = @Column(name = "supply_id", nullable = false, length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)) })
	public SupplyInfoId getId() {
		return this.id;
	}

	public void setId(SupplyInfoId id) {
		this.id = id;
	}

	@Column(name = "supply_name", length = 45)
	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "effective", length = 45)
	public String getEffective() {
		return this.effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

}
