package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WarehouseStorage generated by hbm2java
 */
@Entity
@Table(name = "warehouse_storage", schema = "public")
public class WarehouseStorage implements java.io.Serializable {

	private WarehouseStorageId id;
	private String SName;

	public WarehouseStorage() {
	}

	public WarehouseStorage(WarehouseStorageId id) {
		this.id = id;
	}

	public WarehouseStorage(WarehouseStorageId id, String SName) {
		this.id = id;
		this.SName = SName;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "whId", column = @Column(name = "wh_id", nullable = false, length = 6)),
			@AttributeOverride(name = "SId", column = @Column(name = "s_id", nullable = false, length = 6)) })
	public WarehouseStorageId getId() {
		return this.id;
	}

	public void setId(WarehouseStorageId id) {
		this.id = id;
	}

	@Column(name = "s_name", length = 45)
	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

}
