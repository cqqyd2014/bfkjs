package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VPrePackageNeedPrintId generated by hbm2java
 */
@Embeddable
public class VPrePackageNeedPrintId implements java.io.Serializable {

	private String sn;
	private Boolean ifEffective;
	private Boolean ifPrint;
	private String comId;
	private Date createDat;
	private Boolean ifPackage;
	private Date packageTime;
	private String memoNames;
	private String memoBarcodes;

	public VPrePackageNeedPrintId() {
	}

	public VPrePackageNeedPrintId(String sn, Boolean ifEffective, Boolean ifPrint, String comId, Date createDat,
			Boolean ifPackage, Date packageTime, String memoNames, String memoBarcodes) {
		this.sn = sn;
		this.ifEffective = ifEffective;
		this.ifPrint = ifPrint;
		this.comId = comId;
		this.createDat = createDat;
		this.ifPackage = ifPackage;
		this.packageTime = packageTime;
		this.memoNames = memoNames;
		this.memoBarcodes = memoBarcodes;
	}

	@Column(name = "sn", length = 18)
	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "if_effective")
	public Boolean getIfEffective() {
		return this.ifEffective;
	}

	public void setIfEffective(Boolean ifEffective) {
		this.ifEffective = ifEffective;
	}

	@Column(name = "if_print")
	public Boolean getIfPrint() {
		return this.ifPrint;
	}

	public void setIfPrint(Boolean ifPrint) {
		this.ifPrint = ifPrint;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "create_dat", length = 35)
	public Date getCreateDat() {
		return this.createDat;
	}

	public void setCreateDat(Date createDat) {
		this.createDat = createDat;
	}

	@Column(name = "if_package")
	public Boolean getIfPackage() {
		return this.ifPackage;
	}

	public void setIfPackage(Boolean ifPackage) {
		this.ifPackage = ifPackage;
	}

	@Column(name = "package_time", length = 35)
	public Date getPackageTime() {
		return this.packageTime;
	}

	public void setPackageTime(Date packageTime) {
		this.packageTime = packageTime;
	}

	@Column(name = "memo_names", length = 512)
	public String getMemoNames() {
		return this.memoNames;
	}

	public void setMemoNames(String memoNames) {
		this.memoNames = memoNames;
	}

	@Column(name = "memo_barcodes", length = 512)
	public String getMemoBarcodes() {
		return this.memoBarcodes;
	}

	public void setMemoBarcodes(String memoBarcodes) {
		this.memoBarcodes = memoBarcodes;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VPrePackageNeedPrintId))
			return false;
		VPrePackageNeedPrintId castOther = (VPrePackageNeedPrintId) other;

		return ((this.getSn() == castOther.getSn())
				|| (this.getSn() != null && castOther.getSn() != null && this.getSn().equals(castOther.getSn())))
				&& ((this.getIfEffective() == castOther.getIfEffective())
						|| (this.getIfEffective() != null && castOther.getIfEffective() != null
								&& this.getIfEffective().equals(castOther.getIfEffective())))
				&& ((this.getIfPrint() == castOther.getIfPrint()) || (this.getIfPrint() != null
						&& castOther.getIfPrint() != null && this.getIfPrint().equals(castOther.getIfPrint())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getCreateDat() == castOther.getCreateDat()) || (this.getCreateDat() != null
						&& castOther.getCreateDat() != null && this.getCreateDat().equals(castOther.getCreateDat())))
				&& ((this.getIfPackage() == castOther.getIfPackage()) || (this.getIfPackage() != null
						&& castOther.getIfPackage() != null && this.getIfPackage().equals(castOther.getIfPackage())))
				&& ((this.getPackageTime() == castOther.getPackageTime())
						|| (this.getPackageTime() != null && castOther.getPackageTime() != null
								&& this.getPackageTime().equals(castOther.getPackageTime())))
				&& ((this.getMemoNames() == castOther.getMemoNames()) || (this.getMemoNames() != null
						&& castOther.getMemoNames() != null && this.getMemoNames().equals(castOther.getMemoNames())))
				&& ((this.getMemoBarcodes() == castOther.getMemoBarcodes())
						|| (this.getMemoBarcodes() != null && castOther.getMemoBarcodes() != null
								&& this.getMemoBarcodes().equals(castOther.getMemoBarcodes())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSn() == null ? 0 : this.getSn().hashCode());
		result = 37 * result + (getIfEffective() == null ? 0 : this.getIfEffective().hashCode());
		result = 37 * result + (getIfPrint() == null ? 0 : this.getIfPrint().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getCreateDat() == null ? 0 : this.getCreateDat().hashCode());
		result = 37 * result + (getIfPackage() == null ? 0 : this.getIfPackage().hashCode());
		result = 37 * result + (getPackageTime() == null ? 0 : this.getPackageTime().hashCode());
		result = 37 * result + (getMemoNames() == null ? 0 : this.getMemoNames().hashCode());
		result = 37 * result + (getMemoBarcodes() == null ? 0 : this.getMemoBarcodes().hashCode());
		return result;
	}

}
