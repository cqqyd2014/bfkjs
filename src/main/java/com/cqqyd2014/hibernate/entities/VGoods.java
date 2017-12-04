package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VGoods generated by hbm2java
 */
@Entity
@Table(name = "v_goods", schema = "public")
public class VGoods implements java.io.Serializable {

	private VGoodsId id;

	public VGoods() {
	}

	public VGoods(VGoodsId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "intoWhUuid", column = @Column(name = "into_wh_uuid", length = 36)),
			@AttributeOverride(name = "createDat", column = @Column(name = "create_dat", length = 35)),
			@AttributeOverride(name = "uneffectiveUserid", column = @Column(name = "uneffective_userid", length = 36)),
			@AttributeOverride(name = "grossWeight", column = @Column(name = "gross_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "maker", column = @Column(name = "maker", length = 36)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "CSpec", column = @Column(name = "c_spec", length = 45)),
			@AttributeOverride(name = "goodsBarcode", column = @Column(name = "goods_barcode", length = 22)),
			@AttributeOverride(name = "printed", column = @Column(name = "printed")),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id")),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "currentWh", column = @Column(name = "current_wh", length = 6)),
			@AttributeOverride(name = "whName", column = @Column(name = "wh_name", length = 45)),
			@AttributeOverride(name = "currentStorage", column = @Column(name = "current_storage", length = 6)),
			@AttributeOverride(name = "storageName", column = @Column(name = "storage_name", length = 90)),
			@AttributeOverride(name = "effective", column = @Column(name = "effective")),
			@AttributeOverride(name = "origin", column = @Column(name = "origin", length = 45)),
			@AttributeOverride(name = "CHs", column = @Column(name = "c_hs", length = 45)),
			@AttributeOverride(name = "notAir", column = @Column(name = "not_air")),
			@AttributeOverride(name = "snCode", column = @Column(name = "sn_code", length = 4)),
			@AttributeOverride(name = "snSpec", column = @Column(name = "sn_spec", length = 45)),
			@AttributeOverride(name = "unit", column = @Column(name = "unit", length = 90)),
			@AttributeOverride(name = "netWeight", column = @Column(name = "net_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "packageWeight", column = @Column(name = "package_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "uneffectiveDat", column = @Column(name = "uneffective_dat", length = 35)),
			@AttributeOverride(name = "inDat", column = @Column(name = "in_dat", length = 35)),
			@AttributeOverride(name = "contractId", column = @Column(name = "contract_id")),
			@AttributeOverride(name = "contractPrice", column = @Column(name = "contract_price", precision = 131089, scale = 0)),
			@AttributeOverride(name = "paperDat", column = @Column(name = "paper_dat", length = 35)),
			@AttributeOverride(name = "supplyName", column = @Column(name = "supply_name")),
			@AttributeOverride(name = "supplyId", column = @Column(name = "supply_id")),
			@AttributeOverride(name = "CCountryName", column = @Column(name = "c_country_name", length = 90)) })
	public VGoodsId getId() {
		return this.id;
	}

	public void setId(VGoodsId id) {
		this.id = id;
	}

}
