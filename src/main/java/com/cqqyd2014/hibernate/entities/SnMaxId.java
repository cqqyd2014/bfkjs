package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SnMaxId generated by hbm2java
 */
@Embeddable
public class SnMaxId implements java.io.Serializable {

	private String comId;
	private String snType;
	private Date dat;

	public SnMaxId() {
	}

	public SnMaxId(String comId, String snType, Date dat) {
		this.comId = comId;
		this.snType = snType;
		this.dat = dat;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "sn_type", nullable = false, length = 6)
	public String getSnType() {
		return this.snType;
	}

	public void setSnType(String snType) {
		this.snType = snType;
	}

	@Column(name = "dat", nullable = false, length = 13)
	public Date getDat() {
		return this.dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SnMaxId))
			return false;
		SnMaxId castOther = (SnMaxId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getSnType() == castOther.getSnType()) || (this.getSnType() != null
						&& castOther.getSnType() != null && this.getSnType().equals(castOther.getSnType())))
				&& ((this.getDat() == castOther.getDat()) || (this.getDat() != null && castOther.getDat() != null
						&& this.getDat().equals(castOther.getDat())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getSnType() == null ? 0 : this.getSnType().hashCode());
		result = 37 * result + (getDat() == null ? 0 : this.getDat().hashCode());
		return result;
	}

}
