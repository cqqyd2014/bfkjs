package com.cqqyd2014.hibernate.entities;
// Generated 2017-10-27 7:29:43 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * HwServLocation generated by hbm2java
 */
@Entity
@Table(name = "hw_serv_location", schema = "public")
public class HwServLocation implements java.io.Serializable {

	private HwServLocationId id;
	private String hw;
	private String provinceName;

	public HwServLocation() {
	}

	public HwServLocation(HwServLocationId id) {
		this.id = id;
	}

	public HwServLocation(HwServLocationId id, String hw, String provinceName) {
		this.id = id;
		this.hw = hw;
		this.provinceName = provinceName;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "provinceCode", column = @Column(name = "province_code", nullable = false)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)) })
	public HwServLocationId getId() {
		return this.id;
	}

	public void setId(HwServLocationId id) {
		this.id = id;
	}

	@Column(name = "hw", length = 45)
	public String getHw() {
		return this.hw;
	}

	public void setHw(String hw) {
		this.hw = hw;
	}

	@Column(name = "province_name", length = 45)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}