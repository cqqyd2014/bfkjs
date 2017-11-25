package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ComParCodeId generated by hbm2java
 */
@Embeddable
public class ComParCodeId implements java.io.Serializable {

	private String comId;
	private String parCode;

	public ComParCodeId() {
	}

	public ComParCodeId(String comId, String parCode) {
		this.comId = comId;
		this.parCode = parCode;
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
		if (!(other instanceof ComParCodeId))
			return false;
		ComParCodeId castOther = (ComParCodeId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getParCode() == castOther.getParCode()) || (this.getParCode() != null
						&& castOther.getParCode() != null && this.getParCode().equals(castOther.getParCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getParCode() == null ? 0 : this.getParCode().hashCode());
		return result;
	}

}
