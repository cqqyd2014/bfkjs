package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-6 13:56:00 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VWarehouseStorageDetail generated by hbm2java
 */
@Entity
@Table(name = "v_warehouse_storage_detail", schema = "public")
public class VWarehouseStorageDetail implements java.io.Serializable {

	private VWarehouseStorageDetailId id;

	public VWarehouseStorageDetail() {
	}

	public VWarehouseStorageDetail(VWarehouseStorageDetailId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "num", column = @Column(name = "num", precision = 131089, scale = 0)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "whId", column = @Column(name = "wh_id", length = 6)),
			@AttributeOverride(name = "storageId", column = @Column(name = "storage_id", length = 6)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)) })
	public VWarehouseStorageDetailId getId() {
		return this.id;
	}

	public void setId(VWarehouseStorageDetailId id) {
		this.id = id;
	}

}
