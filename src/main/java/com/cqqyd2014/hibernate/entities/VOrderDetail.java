package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VOrderDetail generated by hbm2java
 */
@Entity
@Table(name = "v_order_detail", schema = "public")
public class VOrderDetail implements java.io.Serializable {

	private VOrderDetailId id;

	public VOrderDetail() {
	}

	public VOrderDetail(VOrderDetailId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "CTime", column = @Column(name = "c_time", length = 35)),
			@AttributeOverride(name = "CTax2", column = @Column(name = "c_tax2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CRegTax2", column = @Column(name = "c_reg_tax2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CPrice2", column = @Column(name = "c_price2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "totall", column = @Column(name = "totall", precision = 131089, scale = 0)),
			@AttributeOverride(name = "total2", column = @Column(name = "total2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "COrderId", column = @Column(name = "c_order_id", length = 45)),
			@AttributeOverride(name = "CGoodsId", column = @Column(name = "c_goods_id", length = 45)),
			@AttributeOverride(name = "CCount", column = @Column(name = "c_count", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CPrice", column = @Column(name = "c_price", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CDetailId", column = @Column(name = "c_detail_id", length = 36)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "unit", column = @Column(name = "unit", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)) })
	public VOrderDetailId getId() {
		return this.id;
	}

	public void setId(VOrderDetailId id) {
		this.id = id;
	}

}
