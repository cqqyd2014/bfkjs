package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * GoodsInfo generated by hbm2java
 */
@Entity
@Table(name = "goods_info", schema = "public")
public class GoodsInfo implements java.io.Serializable {

	private GoodsInfoId id;
	private String CName;
	private String CSpec;
	private String CHs;
	private String CMemo;
	private BigDecimal grossWeight;
	private String barcode;
	private String unit;
	private String origin;
	private String snCode;
	private String shortName;
	private Boolean inUse;
	private Boolean notAir;
	private BigDecimal netWeight;
	private BigDecimal packageWeight;

	public GoodsInfo() {
	}

	public GoodsInfo(GoodsInfoId id) {
		this.id = id;
	}

	public GoodsInfo(GoodsInfoId id, String CName, String CSpec, String CHs, String CMemo, BigDecimal grossWeight,
			String barcode, String unit, String origin, String snCode, String shortName, Boolean inUse, Boolean notAir,
			BigDecimal netWeight, BigDecimal packageWeight) {
		this.id = id;
		this.CName = CName;
		this.CSpec = CSpec;
		this.CHs = CHs;
		this.CMemo = CMemo;
		this.grossWeight = grossWeight;
		this.barcode = barcode;
		this.unit = unit;
		this.origin = origin;
		this.snCode = snCode;
		this.shortName = shortName;
		this.inUse = inUse;
		this.notAir = notAir;
		this.netWeight = netWeight;
		this.packageWeight = packageWeight;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "CId", column = @Column(name = "c_id", nullable = false)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)) })
	public GoodsInfoId getId() {
		return this.id;
	}

	public void setId(GoodsInfoId id) {
		this.id = id;
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

}
