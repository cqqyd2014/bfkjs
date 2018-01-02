package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VInventoryByGoodsIdDefaul generated by hbm2java
 */
@Entity
@Table(name = "v_inventory_by_goods_id_defaul", schema = "public")
public class VInventoryByGoodsIdDefaul implements java.io.Serializable {

	private VInventoryByGoodsIdDefaulId id;

	public VInventoryByGoodsIdDefaul() {
	}

	public VInventoryByGoodsIdDefaul(VInventoryByGoodsIdDefaulId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "whId", column = @Column(name = "wh_id", length = 6)),
			@AttributeOverride(name = "priority", column = @Column(name = "priority")),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "unit", column = @Column(name = "unit", length = 90)),
			@AttributeOverride(name = "sum", column = @Column(name = "sum", precision = 131089, scale = 0)) })
	public VInventoryByGoodsIdDefaulId getId() {
		return this.id;
	}

	public void setId(VInventoryByGoodsIdDefaulId id) {
		this.id = id;
	}

}
