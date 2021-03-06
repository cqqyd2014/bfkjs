package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserParId generated by hbm2java
 */
@Embeddable
public class UserParId implements java.io.Serializable {

	private String userId;
	private String comId;
	private String parCode;

	public UserParId() {
	}

	public UserParId(String userId, String comId, String parCode) {
		this.userId = userId;
		this.comId = comId;
		this.parCode = parCode;
	}

	@Column(name = "user_id", nullable = false, length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "com_id", nullable = false, length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "par_code", nullable = false, length = 45)
	public String getParCode() {
		return this.parCode;
	}

	public void setParCode(String parCode) {
		this.parCode = parCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserParId))
			return false;
		UserParId castOther = (UserParId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getParCode() == castOther.getParCode()) || (this.getParCode() != null
						&& castOther.getParCode() != null && this.getParCode().equals(castOther.getParCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getParCode() == null ? 0 : this.getParCode().hashCode());
		return result;
	}

}
