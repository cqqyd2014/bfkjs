package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * InvertoryAjustDetailId generated by hbm2java
 */
@Embeddable
public class InvertoryAjustDetailId implements java.io.Serializable {

	private String IUuid;
	private String IHwCId;

	public InvertoryAjustDetailId() {
	}

	public InvertoryAjustDetailId(String IUuid, String IHwCId) {
		this.IUuid = IUuid;
		this.IHwCId = IHwCId;
	}

	@Column(name = "i_uuid", nullable = false, length = 45)
	public String getIUuid() {
		return this.IUuid;
	}

	public void setIUuid(String IUuid) {
		this.IUuid = IUuid;
	}

	@Column(name = "i_hw_c_id", nullable = false, length = 45)
	public String getIHwCId() {
		return this.IHwCId;
	}

	public void setIHwCId(String IHwCId) {
		this.IHwCId = IHwCId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InvertoryAjustDetailId))
			return false;
		InvertoryAjustDetailId castOther = (InvertoryAjustDetailId) other;

		return ((this.getIUuid() == castOther.getIUuid()) || (this.getIUuid() != null && castOther.getIUuid() != null
				&& this.getIUuid().equals(castOther.getIUuid())))
				&& ((this.getIHwCId() == castOther.getIHwCId()) || (this.getIHwCId() != null
						&& castOther.getIHwCId() != null && this.getIHwCId().equals(castOther.getIHwCId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIUuid() == null ? 0 : this.getIUuid().hashCode());
		result = 37 * result + (getIHwCId() == null ? 0 : this.getIHwCId().hashCode());
		return result;
	}

}
