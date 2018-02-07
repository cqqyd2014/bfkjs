package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GoodsInfoId generated by hbm2java
 */
@Embeddable
public class GoodsInfoId implements java.io.Serializable {

	private String CId;
	private String comId;

	public GoodsInfoId() {
	}

	public GoodsInfoId(String CId, String comId) {
		this.CId = CId;
		this.comId = comId;
	}

	@Column(name = "c_id", nullable = false)
	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
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
		if (!(other instanceof GoodsInfoId))
			return false;
		GoodsInfoId castOther = (GoodsInfoId) other;

		return ((this.getCId() == castOther.getCId())
				|| (this.getCId() != null && castOther.getCId() != null && this.getCId().equals(castOther.getCId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCId() == null ? 0 : this.getCId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
