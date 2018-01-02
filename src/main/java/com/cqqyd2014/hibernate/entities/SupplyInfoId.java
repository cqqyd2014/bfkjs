package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SupplyInfoId generated by hbm2java
 */
@Embeddable
public class SupplyInfoId implements java.io.Serializable {

	private String supplyId;
	private String comId;

	public SupplyInfoId() {
	}

	public SupplyInfoId(String supplyId, String comId) {
		this.supplyId = supplyId;
		this.comId = comId;
	}

	@Column(name = "supply_id", nullable = false, length = 45)
	public String getSupplyId() {
		return this.supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	@Column(name = "com_id", nullable = false, length = 45)
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
		if (!(other instanceof SupplyInfoId))
			return false;
		SupplyInfoId castOther = (SupplyInfoId) other;

		return ((this.getSupplyId() == castOther.getSupplyId()) || (this.getSupplyId() != null
				&& castOther.getSupplyId() != null && this.getSupplyId().equals(castOther.getSupplyId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSupplyId() == null ? 0 : this.getSupplyId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
