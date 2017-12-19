package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VUserPickingId generated by hbm2java
 */
@Embeddable
public class VUserPickingId implements java.io.Serializable {

	private String comId;
	private Long pickingCount;
	private String id;
	private BigDecimal pickupWeighting;

	public VUserPickingId() {
	}

	public VUserPickingId(String comId, Long pickingCount, String id, BigDecimal pickupWeighting) {
		this.comId = comId;
		this.pickingCount = pickingCount;
		this.id = id;
		this.pickupWeighting = pickupWeighting;
	}

	@Column(name = "com_id")
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "picking_count")
	public Long getPickingCount() {
		return this.pickingCount;
	}

	public void setPickingCount(Long pickingCount) {
		this.pickingCount = pickingCount;
	}

	@Column(name = "id", length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "pickup_weighting", precision = 131089, scale = 0)
	public BigDecimal getPickupWeighting() {
		return this.pickupWeighting;
	}

	public void setPickupWeighting(BigDecimal pickupWeighting) {
		this.pickupWeighting = pickupWeighting;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUserPickingId))
			return false;
		VUserPickingId castOther = (VUserPickingId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getPickingCount() == castOther.getPickingCount())
						|| (this.getPickingCount() != null && castOther.getPickingCount() != null
								&& this.getPickingCount().equals(castOther.getPickingCount())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null
						&& this.getId().equals(castOther.getId())))
				&& ((this.getPickupWeighting() == castOther.getPickupWeighting())
						|| (this.getPickupWeighting() != null && castOther.getPickupWeighting() != null
								&& this.getPickupWeighting().equals(castOther.getPickupWeighting())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getPickingCount() == null ? 0 : this.getPickingCount().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getPickupWeighting() == null ? 0 : this.getPickupWeighting().hashCode());
		return result;
	}

}
