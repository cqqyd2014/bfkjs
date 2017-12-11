package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VDeliveredCount generated by hbm2java
 */
@Entity
@Table(name = "v_delivered_count", schema = "public")
public class VDeliveredCount implements java.io.Serializable {

	private VDeliveredCountId id;

	public VDeliveredCount() {
	}

	public VDeliveredCount(VDeliveredCountId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 20)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "num", column = @Column(name = "num")) })
	public VDeliveredCountId getId() {
		return this.id;
	}

	public void setId(VDeliveredCountId id) {
		this.id = id;
	}

}
