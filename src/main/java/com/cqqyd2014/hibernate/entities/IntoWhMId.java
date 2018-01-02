package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IntoWhMId generated by hbm2java
 */
@Embeddable
public class IntoWhMId implements java.io.Serializable {

	private String uuid;
	private String comId;

	public IntoWhMId() {
	}

	public IntoWhMId(String uuid, String comId) {
		this.uuid = uuid;
		this.comId = comId;
	}

	@Column(name = "uuid", nullable = false, length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
		if (!(other instanceof IntoWhMId))
			return false;
		IntoWhMId castOther = (IntoWhMId) other;

		return ((this.getUuid() == castOther.getUuid()) || (this.getUuid() != null && castOther.getUuid() != null
				&& this.getUuid().equals(castOther.getUuid())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUuid() == null ? 0 : this.getUuid().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
