package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VUserPickingNoWorking generated by hbm2java
 */
@Entity
@Table(name = "v_user_picking_no_working", schema = "public")
public class VUserPickingNoWorking implements java.io.Serializable {

	private VUserPickingNoWorkingId id;

	public VUserPickingNoWorking() {
	}

	public VUserPickingNoWorking(VUserPickingNoWorkingId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "pickingCount", column = @Column(name = "picking_count")),
			@AttributeOverride(name = "id", column = @Column(name = "id", length = 36)),
			@AttributeOverride(name = "pickupWeighting", column = @Column(name = "pickup_weighting", precision = 131089, scale = 0)) })
	public VUserPickingNoWorkingId getId() {
		return this.id;
	}

	public void setId(VUserPickingNoWorkingId id) {
		this.id = id;
	}

}
