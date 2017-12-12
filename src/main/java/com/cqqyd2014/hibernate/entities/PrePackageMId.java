package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PrePackageMId generated by hbm2java
 */
@Embeddable
public class PrePackageMId implements java.io.Serializable {

	private String packageBarcode;
	private String comId;

	public PrePackageMId() {
	}

	public PrePackageMId(String packageBarcode, String comId) {
		this.packageBarcode = packageBarcode;
		this.comId = comId;
	}

	@Column(name = "package_barcode", nullable = false, length = 18)
	public String getPackageBarcode() {
		return this.packageBarcode;
	}

	public void setPackageBarcode(String packageBarcode) {
		this.packageBarcode = packageBarcode;
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
		if (!(other instanceof PrePackageMId))
			return false;
		PrePackageMId castOther = (PrePackageMId) other;

		return ((this.getPackageBarcode() == castOther.getPackageBarcode())
				|| (this.getPackageBarcode() != null && castOther.getPackageBarcode() != null
						&& this.getPackageBarcode().equals(castOther.getPackageBarcode())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPackageBarcode() == null ? 0 : this.getPackageBarcode().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
