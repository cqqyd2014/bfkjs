package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MoveGoodsWarehouseD generated by hbm2java
 */
@Entity
@Table(name = "move_goods_warehouse_d", schema = "public")
public class MoveGoodsWarehouseD implements java.io.Serializable {

	private MoveGoodsWarehouseDId id;

	public MoveGoodsWarehouseD() {
	}

	public MoveGoodsWarehouseD(MoveGoodsWarehouseDId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 6)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 36)),
			@AttributeOverride(name = "moveDate", column = @Column(name = "move_date", nullable = false, length = 35)),
			@AttributeOverride(name = "goodsBarcode", column = @Column(name = "goods_barcode", nullable = false, length = 22)) })
	public MoveGoodsWarehouseDId getId() {
		return this.id;
	}

	public void setId(MoveGoodsWarehouseDId id) {
		this.id = id;
	}

}
