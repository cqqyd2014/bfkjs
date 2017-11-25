package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Region generated by hbm2java
 */
@Entity
@Table(name = "region", schema = "public")
public class Region implements java.io.Serializable {

	private BigDecimal regionId;
	private String regionCode;
	private String regionName;
	private BigDecimal parentId;
	private BigDecimal regionLevel;
	private BigDecimal regionOrder;
	private String regionNameEn;
	private String regionShortnameEn;

	public Region() {
	}

	public Region(BigDecimal regionId) {
		this.regionId = regionId;
	}

	public Region(BigDecimal regionId, String regionCode, String regionName, BigDecimal parentId,
			BigDecimal regionLevel, BigDecimal regionOrder, String regionNameEn, String regionShortnameEn) {
		this.regionId = regionId;
		this.regionCode = regionCode;
		this.regionName = regionName;
		this.parentId = parentId;
		this.regionLevel = regionLevel;
		this.regionOrder = regionOrder;
		this.regionNameEn = regionNameEn;
		this.regionShortnameEn = regionShortnameEn;
	}

	@Id

	@Column(name = "region_id", unique = true, nullable = false, precision = 131089, scale = 0)
	public BigDecimal getRegionId() {
		return this.regionId;
	}

	public void setRegionId(BigDecimal regionId) {
		this.regionId = regionId;
	}

	@Column(name = "region_code", length = 45)
	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name = "region_name", length = 45)
	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "parent_id", precision = 131089, scale = 0)
	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	@Column(name = "region_level", precision = 131089, scale = 0)
	public BigDecimal getRegionLevel() {
		return this.regionLevel;
	}

	public void setRegionLevel(BigDecimal regionLevel) {
		this.regionLevel = regionLevel;
	}

	@Column(name = "region_order", precision = 131089, scale = 0)
	public BigDecimal getRegionOrder() {
		return this.regionOrder;
	}

	public void setRegionOrder(BigDecimal regionOrder) {
		this.regionOrder = regionOrder;
	}

	@Column(name = "region_name_en")
	public String getRegionNameEn() {
		return this.regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}

	@Column(name = "region_shortname_en", length = 45)
	public String getRegionShortnameEn() {
		return this.regionShortnameEn;
	}

	public void setRegionShortnameEn(String regionShortnameEn) {
		this.regionShortnameEn = regionShortnameEn;
	}

}
