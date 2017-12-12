package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ExpressPrint generated by hbm2java
 */
@Entity
@Table(name = "express_print", schema = "public")
public class ExpressPrint implements java.io.Serializable {

	private ExpressPrintId id;
	private String EName;
	private String parName;
	private String parValue;

	public ExpressPrint() {
	}

	public ExpressPrint(ExpressPrintId id) {
		this.id = id;
	}

	public ExpressPrint(ExpressPrintId id, String EName, String parName, String parValue) {
		this.id = id;
		this.EName = EName;
		this.parName = parName;
		this.parValue = parValue;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 45)),
			@AttributeOverride(name = "parCode", column = @Column(name = "par_code", nullable = false, length = 45)) })
	public ExpressPrintId getId() {
		return this.id;
	}

	public void setId(ExpressPrintId id) {
		this.id = id;
	}

	@Column(name = "e_name", length = 45)
	public String getEName() {
		return this.EName;
	}

	public void setEName(String EName) {
		this.EName = EName;
	}

	@Column(name = "par_name", length = 45)
	public String getParName() {
		return this.parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

	@Column(name = "par_value", length = 45)
	public String getParValue() {
		return this.parValue;
	}

	public void setParValue(String parValue) {
		this.parValue = parValue;
	}

}