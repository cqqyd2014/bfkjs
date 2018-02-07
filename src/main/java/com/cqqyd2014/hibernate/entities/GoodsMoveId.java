package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GoodsMoveId generated by hbm2java
 */
@Embeddable
public class GoodsMoveId implements java.io.Serializable {

	private String moveId;
	private String comId;

	public GoodsMoveId() {
	}

	public GoodsMoveId(String moveId, String comId) {
		this.moveId = moveId;
		this.comId = comId;
	}

	@Column(name = "move_id", nullable = false, length = 36)
	public String getMoveId() {
		return this.moveId;
	}

	public void setMoveId(String moveId) {
		this.moveId = moveId;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GoodsMoveId))
			return false;
		GoodsMoveId castOther = (GoodsMoveId) other;

		return ((this.getMoveId() == castOther.getMoveId()) || (this.getMoveId() != null
				&& castOther.getMoveId() != null && this.getMoveId().equals(castOther.getMoveId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMoveId() == null ? 0 : this.getMoveId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
