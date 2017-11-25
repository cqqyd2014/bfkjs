package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SfResponseOrderBackId generated by hbm2java
 */
@Embeddable
public class SfResponseOrderBackId implements java.io.Serializable {

	private String comId;
	private String uuid;

	public SfResponseOrderBackId() {
	}

	public SfResponseOrderBackId(String comId, String uuid) {
		this.comId = comId;
		this.uuid = uuid;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "uuid", nullable = false, length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SfResponseOrderBackId))
			return false;
		SfResponseOrderBackId castOther = (SfResponseOrderBackId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getUuid() == castOther.getUuid()) || (this.getUuid() != null && castOther.getUuid() != null
						&& this.getUuid().equals(castOther.getUuid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getUuid() == null ? 0 : this.getUuid().hashCode());
		return result;
	}

}
