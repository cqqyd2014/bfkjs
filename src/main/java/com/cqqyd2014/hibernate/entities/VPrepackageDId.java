package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VPrepackageDId generated by hbm2java
 */
@Embeddable
public class VPrepackageDId implements java.io.Serializable {

	private String packageBarcode;
	private String goodsBarcode;
	private String CId;
	private String CName;
	private String CSpec;
	private String CHs;
	private String CMemo;
	private BigDecimal grossWeight;
	private String barcode;
	private String unit;
	private String comId;
	private String origin;
	private String snCode;
	private String shortName;
	private Boolean inUse;
	private Boolean notAir;
	private BigDecimal netWeight;
	private BigDecimal packageWeight;

	public VPrepackageDId() {
	}

	public VPrepackageDId(String packageBarcode, String goodsBarcode, String CId, String CName, String CSpec,
			String CHs, String CMemo, BigDecimal grossWeight, String barcode, String unit, String comId, String origin,
			String snCode, String shortName, Boolean inUse, Boolean notAir, BigDecimal netWeight,
			BigDecimal packageWeight) {
		this.packageBarcode = packageBarcode;
		this.goodsBarcode = goodsBarcode;
		this.CId = CId;
		this.CName = CName;
		this.CSpec = CSpec;
		this.CHs = CHs;
		this.CMemo = CMemo;
		this.grossWeight = grossWeight;
		this.barcode = barcode;
		this.unit = unit;
		this.comId = comId;
		this.origin = origin;
		this.snCode = snCode;
		this.shortName = shortName;
		this.inUse = inUse;
		this.notAir = notAir;
		this.netWeight = netWeight;
		this.packageWeight = packageWeight;
	}

	@Column(name = "package_barcode", length = 18)
	public String getPackageBarcode() {
		return this.packageBarcode;
	}

	public void setPackageBarcode(String packageBarcode) {
		this.packageBarcode = packageBarcode;
	}

	@Column(name = "goods_barcode", length = 22)
	public String getGoodsBarcode() {
		return this.goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	@Column(name = "c_id")
	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "c_spec")
	public String getCSpec() {
		return this.CSpec;
	}

	public void setCSpec(String CSpec) {
		this.CSpec = CSpec;
	}

	@Column(name = "c_hs", length = 45)
	public String getCHs() {
		return this.CHs;
	}

	public void setCHs(String CHs) {
		this.CHs = CHs;
	}

	@Column(name = "c_memo")
	public String getCMemo() {
		return this.CMemo;
	}

	public void setCMemo(String CMemo) {
		this.CMemo = CMemo;
	}

	@Column(name = "gross_weight", precision = 131089, scale = 0)
	public BigDecimal getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "barcode", length = 45)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "unit", length = 45)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "origin", length = 45)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "sn_code", length = 4)
	public String getSnCode() {
		return this.snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	@Column(name = "short_name", length = 45)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "in_use")
	public Boolean getInUse() {
		return this.inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	@Column(name = "not_air")
	public Boolean getNotAir() {
		return this.notAir;
	}

	public void setNotAir(Boolean notAir) {
		this.notAir = notAir;
	}

	@Column(name = "net_weight", precision = 131089, scale = 0)
	public BigDecimal getNetWeight() {
		return this.netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	@Column(name = "package_weight", precision = 131089, scale = 0)
	public BigDecimal getPackageWeight() {
		return this.packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VPrepackageDId))
			return false;
		VPrepackageDId castOther = (VPrepackageDId) other;

		return ((this.getPackageBarcode() == castOther.getPackageBarcode())
				|| (this.getPackageBarcode() != null && castOther.getPackageBarcode() != null
						&& this.getPackageBarcode().equals(castOther.getPackageBarcode())))
				&& ((this.getGoodsBarcode() == castOther.getGoodsBarcode())
						|| (this.getGoodsBarcode() != null && castOther.getGoodsBarcode() != null
								&& this.getGoodsBarcode().equals(castOther.getGoodsBarcode())))
				&& ((this.getCId() == castOther.getCId()) || (this.getCId() != null && castOther.getCId() != null
						&& this.getCId().equals(castOther.getCId())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getCSpec() == castOther.getCSpec()) || (this.getCSpec() != null
						&& castOther.getCSpec() != null && this.getCSpec().equals(castOther.getCSpec())))
				&& ((this.getCHs() == castOther.getCHs()) || (this.getCHs() != null && castOther.getCHs() != null
						&& this.getCHs().equals(castOther.getCHs())))
				&& ((this.getCMemo() == castOther.getCMemo()) || (this.getCMemo() != null
						&& castOther.getCMemo() != null && this.getCMemo().equals(castOther.getCMemo())))
				&& ((this.getGrossWeight() == castOther.getGrossWeight())
						|| (this.getGrossWeight() != null && castOther.getGrossWeight() != null
								&& this.getGrossWeight().equals(castOther.getGrossWeight())))
				&& ((this.getBarcode() == castOther.getBarcode()) || (this.getBarcode() != null
						&& castOther.getBarcode() != null && this.getBarcode().equals(castOther.getBarcode())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getOrigin() == castOther.getOrigin()) || (this.getOrigin() != null
						&& castOther.getOrigin() != null && this.getOrigin().equals(castOther.getOrigin())))
				&& ((this.getSnCode() == castOther.getSnCode()) || (this.getSnCode() != null
						&& castOther.getSnCode() != null && this.getSnCode().equals(castOther.getSnCode())))
				&& ((this.getShortName() == castOther.getShortName()) || (this.getShortName() != null
						&& castOther.getShortName() != null && this.getShortName().equals(castOther.getShortName())))
				&& ((this.getInUse() == castOther.getInUse()) || (this.getInUse() != null
						&& castOther.getInUse() != null && this.getInUse().equals(castOther.getInUse())))
				&& ((this.getNotAir() == castOther.getNotAir()) || (this.getNotAir() != null
						&& castOther.getNotAir() != null && this.getNotAir().equals(castOther.getNotAir())))
				&& ((this.getNetWeight() == castOther.getNetWeight()) || (this.getNetWeight() != null
						&& castOther.getNetWeight() != null && this.getNetWeight().equals(castOther.getNetWeight())))
				&& ((this.getPackageWeight() == castOther.getPackageWeight())
						|| (this.getPackageWeight() != null && castOther.getPackageWeight() != null
								&& this.getPackageWeight().equals(castOther.getPackageWeight())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPackageBarcode() == null ? 0 : this.getPackageBarcode().hashCode());
		result = 37 * result + (getGoodsBarcode() == null ? 0 : this.getGoodsBarcode().hashCode());
		result = 37 * result + (getCId() == null ? 0 : this.getCId().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getCSpec() == null ? 0 : this.getCSpec().hashCode());
		result = 37 * result + (getCHs() == null ? 0 : this.getCHs().hashCode());
		result = 37 * result + (getCMemo() == null ? 0 : this.getCMemo().hashCode());
		result = 37 * result + (getGrossWeight() == null ? 0 : this.getGrossWeight().hashCode());
		result = 37 * result + (getBarcode() == null ? 0 : this.getBarcode().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrigin() == null ? 0 : this.getOrigin().hashCode());
		result = 37 * result + (getSnCode() == null ? 0 : this.getSnCode().hashCode());
		result = 37 * result + (getShortName() == null ? 0 : this.getShortName().hashCode());
		result = 37 * result + (getInUse() == null ? 0 : this.getInUse().hashCode());
		result = 37 * result + (getNotAir() == null ? 0 : this.getNotAir().hashCode());
		result = 37 * result + (getNetWeight() == null ? 0 : this.getNetWeight().hashCode());
		result = 37 * result + (getPackageWeight() == null ? 0 : this.getPackageWeight().hashCode());
		return result;
	}

}
