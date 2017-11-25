package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VUserPicking generated by hbm2java
 */
@Entity
@Table(name = "v_user_picking", schema = "public")
public class VUserPicking implements java.io.Serializable {

	private VUserPickingId id;

	public VUserPicking() {
	}

	public VUserPicking(VUserPickingId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "comId", column = @Column(name = "com_id")),
			@AttributeOverride(name = "pickingCount", column = @Column(name = "picking_count")),
			@AttributeOverride(name = "id", column = @Column(name = "id", length = 36)),
			@AttributeOverride(name = "pickupWeighting", column = @Column(name = "pickup_weighting", precision = 131089, scale = 0)) })
	public VUserPickingId getId() {
		return this.id;
	}

	public void setId(VUserPickingId id) {
		this.id = id;
	}

}
