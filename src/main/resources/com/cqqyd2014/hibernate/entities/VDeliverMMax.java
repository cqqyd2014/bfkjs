package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-6 13:56:00 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VDeliverMMax generated by hbm2java
 */
@Entity
@Table(name = "v_deliver_m_max", schema = "public")
public class VDeliverMMax implements java.io.Serializable {

	private VDeliverMMaxId id;

	public VDeliverMMax() {
	}

	public VDeliverMMax(VDeliverMMaxId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 20)),
			@AttributeOverride(name = "max", column = @Column(name = "max")) })
	public VDeliverMMaxId getId() {
		return this.id;
	}

	public void setId(VDeliverMMaxId id) {
		this.id = id;
	}

}
