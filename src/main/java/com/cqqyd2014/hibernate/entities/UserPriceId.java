package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserPriceId generated by hbm2java
 */
@Embeddable
public class UserPriceId implements java.io.Serializable {

	private String comId;
	private String uuid;

	public UserPriceId() {
	}

	public UserPriceId(String comId, String uuid) {
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
		if (!(other instanceof UserPriceId))
			return false;
		UserPriceId castOther = (UserPriceId) other;

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
