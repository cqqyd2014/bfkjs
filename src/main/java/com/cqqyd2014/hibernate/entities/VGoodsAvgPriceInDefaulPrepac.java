package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VGoodsAvgPriceInDefaulPrepac generated by hbm2java
 */
@Entity
@Table(name = "v_goods_avg_price_in_defaul_prepac", schema = "public")
public class VGoodsAvgPriceInDefaulPrepac implements java.io.Serializable {

	private VGoodsAvgPriceInDefaulPrepacId id;

	public VGoodsAvgPriceInDefaulPrepac() {
	}

	public VGoodsAvgPriceInDefaulPrepac(VGoodsAvgPriceInDefaulPrepacId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "currentWh", column = @Column(name = "current_wh", length = 6)),
			@AttributeOverride(name = "num", column = @Column(name = "num", precision = 131089, scale = 0)),
			@AttributeOverride(name = "avgPrice", column = @Column(name = "avg_price", precision = 131089, scale = 0)),
			@AttributeOverride(name = "amount", column = @Column(name = "amount", precision = 131089, scale = 0)) })
	public VGoodsAvgPriceInDefaulPrepacId getId() {
		return this.id;
	}

	public void setId(VGoodsAvgPriceInDefaulPrepacId id) {
		this.id = id;
	}

}
