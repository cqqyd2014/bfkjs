package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VComGoodsId generated by hbm2java
 */
@Embeddable
public class VComGoodsId implements java.io.Serializable {

	private String CName;
	private String CId;
	private String comId;

	public VComGoodsId() {
	}

	public VComGoodsId(String CName, String CId, String comId) {
		this.CName = CName;
		this.CId = CId;
		this.comId = comId;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "c_id", length = 45)
	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	@Column(name = "com_id", length = 45)
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
		if (!(other instanceof VComGoodsId))
			return false;
		VComGoodsId castOther = (VComGoodsId) other;

		return ((this.getCName() == castOther.getCName()) || (this.getCName() != null && castOther.getCName() != null
				&& this.getCName().equals(castOther.getCName())))
				&& ((this.getCId() == castOther.getCId()) || (this.getCId() != null && castOther.getCId() != null
						&& this.getCId().equals(castOther.getCId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getCId() == null ? 0 : this.getCId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}