package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VGoodsInfo generated by hbm2java
 */
@Entity
@Table(name = "v_goods_info", schema = "public")
public class VGoodsInfo implements java.io.Serializable {

	private VGoodsInfoId id;

	public VGoodsInfo() {
	}

	public VGoodsInfo(VGoodsInfoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "grossWeight", column = @Column(name = "gross_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id")),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "CSpec", column = @Column(name = "c_spec")),
			@AttributeOverride(name = "CHs", column = @Column(name = "c_hs", length = 45)),
			@AttributeOverride(name = "CMemo", column = @Column(name = "c_memo")),
			@AttributeOverride(name = "barcode", column = @Column(name = "barcode", length = 45)),
			@AttributeOverride(name = "unit", column = @Column(name = "unit", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "origin", column = @Column(name = "origin", length = 45)),
			@AttributeOverride(name = "snCode", column = @Column(name = "sn_code", length = 4)),
			@AttributeOverride(name = "shortName", column = @Column(name = "short_name", length = 45)),
			@AttributeOverride(name = "inUse", column = @Column(name = "in_use")),
			@AttributeOverride(name = "notAir", column = @Column(name = "not_air")),
			@AttributeOverride(name = "CUnit", column = @Column(name = "c_unit", length = 90)),
			@AttributeOverride(name = "CCountry", column = @Column(name = "c_country", length = 90)),
			@AttributeOverride(name = "netWeight", column = @Column(name = "net_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "packageWeight", column = @Column(name = "package_weight", precision = 131089, scale = 0)) })
	public VGoodsInfoId getId() {
		return this.id;
	}

	public void setId(VGoodsInfoId id) {
		this.id = id;
	}

}
