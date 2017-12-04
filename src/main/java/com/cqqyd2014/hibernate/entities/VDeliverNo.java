package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VDeliverNo generated by hbm2java
 */
@Entity
@Table(name = "v_deliver_no", schema = "public")
public class VDeliverNo implements java.io.Serializable {

	private VDeliverNoId id;

	public VDeliverNo() {
	}

	public VDeliverNo(VDeliverNoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "deliverNo", column = @Column(name = "deliver_no")),
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 20)),
			@AttributeOverride(name = "seq", column = @Column(name = "seq", length = 4)) })
	public VDeliverNoId getId() {
		return this.id;
	}

	public void setId(VDeliverNoId id) {
		this.id = id;
	}

}
