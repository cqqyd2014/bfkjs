package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WarehouseStorageId generated by hbm2java
 */
@Embeddable
public class WarehouseStorageId implements java.io.Serializable {

	private String comId;
	private String whId;
	private String SId;

	public WarehouseStorageId() {
	}

	public WarehouseStorageId(String comId, String whId, String SId) {
		this.comId = comId;
		this.whId = whId;
		this.SId = SId;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "wh_id", nullable = false, length = 6)
	public String getWhId() {
		return this.whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	@Column(name = "s_id", nullable = false, length = 6)
	public String getSId() {
		return this.SId;
	}

	public void setSId(String SId) {
		this.SId = SId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WarehouseStorageId))
			return false;
		WarehouseStorageId castOther = (WarehouseStorageId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getWhId() == castOther.getWhId()) || (this.getWhId() != null && castOther.getWhId() != null
						&& this.getWhId().equals(castOther.getWhId())))
				&& ((this.getSId() == castOther.getSId()) || (this.getSId() != null && castOther.getSId() != null
						&& this.getSId().equals(castOther.getSId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getWhId() == null ? 0 : this.getWhId().hashCode());
		result = 37 * result + (getSId() == null ? 0 : this.getSId().hashCode());
		return result;
	}

}
