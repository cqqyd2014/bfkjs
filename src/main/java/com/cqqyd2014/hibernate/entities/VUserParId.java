package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VUserParId generated by hbm2java
 */
@Embeddable
public class VUserParId implements java.io.Serializable {

	private String userId;
	private String comId;
	private String parCode;
	private String parDesc;
	private String parValue;

	public VUserParId() {
	}

	public VUserParId(String userId, String comId, String parCode, String parDesc, String parValue) {
		this.userId = userId;
		this.comId = comId;
		this.parCode = parCode;
		this.parDesc = parDesc;
		this.parValue = parValue;
	}

	@Column(name = "user_id", length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "par_code", length = 45)
	public String getParCode() {
		return this.parCode;
	}

	public void setParCode(String parCode) {
		this.parCode = parCode;
	}

	@Column(name = "par_desc", length = 512)
	public String getParDesc() {
		return this.parDesc;
	}

	public void setParDesc(String parDesc) {
		this.parDesc = parDesc;
	}

	@Column(name = "par_value")
	public String getParValue() {
		return this.parValue;
	}

	public void setParValue(String parValue) {
		this.parValue = parValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUserParId))
			return false;
		VUserParId castOther = (VUserParId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getParCode() == castOther.getParCode()) || (this.getParCode() != null
						&& castOther.getParCode() != null && this.getParCode().equals(castOther.getParCode())))
				&& ((this.getParDesc() == castOther.getParDesc()) || (this.getParDesc() != null
						&& castOther.getParDesc() != null && this.getParDesc().equals(castOther.getParDesc())))
				&& ((this.getParValue() == castOther.getParValue()) || (this.getParValue() != null
						&& castOther.getParValue() != null && this.getParValue().equals(castOther.getParValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getParCode() == null ? 0 : this.getParCode().hashCode());
		result = 37 * result + (getParDesc() == null ? 0 : this.getParDesc().hashCode());
		result = 37 * result + (getParValue() == null ? 0 : this.getParValue().hashCode());
		return result;
	}

}
