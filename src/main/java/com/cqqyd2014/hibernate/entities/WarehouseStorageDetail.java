package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WarehouseStorageDetail generated by hbm2java
 */
@Entity
@Table(name = "warehouse_storage_detail", schema = "public")
public class WarehouseStorageDetail implements java.io.Serializable {

	private WarehouseStorageDetailId id;
	private BigDecimal num;

	public WarehouseStorageDetail() {
	}

	public WarehouseStorageDetail(WarehouseStorageDetailId id) {
		this.id = id;
	}

	public WarehouseStorageDetail(WarehouseStorageDetailId id, BigDecimal num) {
		this.id = id;
		this.num = num;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)),
			@AttributeOverride(name = "whId", column = @Column(name = "wh_id", nullable = false, length = 6)),
			@AttributeOverride(name = "storageId", column = @Column(name = "storage_id", nullable = false, length = 6)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", nullable = false, length = 45)) })
	public WarehouseStorageDetailId getId() {
		return this.id;
	}

	public void setId(WarehouseStorageDetailId id) {
		this.id = id;
	}

	@Column(name = "num", precision = 131089, scale = 0)
	public BigDecimal getNum() {
		return this.num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

}