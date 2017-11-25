package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VFinanceGoodsPrice generated by hbm2java
 */
@Entity
@Table(name = "v_finance_goods_price", schema = "public")
public class VFinanceGoodsPrice implements java.io.Serializable {

	private VFinanceGoodsPriceId id;

	public VFinanceGoodsPrice() {
	}

	public VFinanceGoodsPrice(VFinanceGoodsPriceId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "CId", column = @Column(name = "c_id")),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "unit", column = @Column(name = "unit", length = 90)),
			@AttributeOverride(name = "startDat", column = @Column(name = "start_dat", length = 35)),
			@AttributeOverride(name = "endDat", column = @Column(name = "end_dat", length = 35)),
			@AttributeOverride(name = "price", column = @Column(name = "price", precision = 131089, scale = 0)) })
	public VFinanceGoodsPriceId getId() {
		return this.id;
	}

	public void setId(VFinanceGoodsPriceId id) {
		this.id = id;
	}

}
