package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VInventoryByGoodsId generated by hbm2java
 */
@Entity
@Table(name = "v_inventory_by_goods_id", schema = "public")
public class VInventoryByGoodsId implements java.io.Serializable {

	private VInventoryByGoodsIdId id;

	public VInventoryByGoodsId() {
	}

	public VInventoryByGoodsId(VInventoryByGoodsIdId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "priority", column = @Column(name = "priority")),
			@AttributeOverride(name = "storageId", column = @Column(name = "storage_id", length = 6)),
			@AttributeOverride(name = "whId", column = @Column(name = "wh_id", length = 6)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "num", column = @Column(name = "num", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "unit", column = @Column(name = "unit", length = 90)) })
	public VInventoryByGoodsIdId getId() {
		return this.id;
	}

	public void setId(VInventoryByGoodsIdId id) {
		this.id = id;
	}

}
