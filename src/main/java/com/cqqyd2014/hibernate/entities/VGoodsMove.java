package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VGoodsMove generated by hbm2java
 */
@Entity
@Table(name = "v_goods_move", schema = "public")
public class VGoodsMove implements java.io.Serializable {

	private VGoodsMoveId id;

	public VGoodsMove() {
	}

	public VGoodsMove(VGoodsMoveId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "goodsBarcode", column = @Column(name = "goods_barcode", length = 22)),
			@AttributeOverride(name = "toWh", column = @Column(name = "to_wh", length = 6)),
			@AttributeOverride(name = "dat", column = @Column(name = "dat", length = 35)),
			@AttributeOverride(name = "fromWh", column = @Column(name = "from_wh", length = 6)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "moveType", column = @Column(name = "move_type", length = 4)),
			@AttributeOverride(name = "fromStorage", column = @Column(name = "from_storage", length = 6)),
			@AttributeOverride(name = "toStorage", column = @Column(name = "to_storage", length = 6)),
			@AttributeOverride(name = "moveId", column = @Column(name = "move_id", length = 36)),
			@AttributeOverride(name = "toWhName", column = @Column(name = "to_wh_name", length = 45)),
			@AttributeOverride(name = "fromWhName", column = @Column(name = "from_wh_name", length = 45)),
			@AttributeOverride(name = "moveTypeName", column = @Column(name = "move_type_name", length = 90)),
			@AttributeOverride(name = "toStorageName", column = @Column(name = "to_storage_name", length = 90)),
			@AttributeOverride(name = "fromStorageName", column = @Column(name = "from_storage_name", length = 90)) })
	public VGoodsMoveId getId() {
		return this.id;
	}

	public void setId(VGoodsMoveId id) {
		this.id = id;
	}

}
