package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VInventoryByGoodsIdAvailableId generated by hbm2java
 */
@Embeddable
public class VInventoryByGoodsIdAvailableId implements java.io.Serializable {

	private String comId;
	private String goodsId;
	private String unit;
	private String CName;
	private BigDecimal sumAvailable;

	public VInventoryByGoodsIdAvailableId() {
	}

	public VInventoryByGoodsIdAvailableId(String comId, String goodsId, String unit, String CName,
			BigDecimal sumAvailable) {
		this.comId = comId;
		this.goodsId = goodsId;
		this.unit = unit;
		this.CName = CName;
		this.sumAvailable = sumAvailable;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "goods_id", length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "unit", length = 90)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "sum_available", precision = 131089, scale = 0)
	public BigDecimal getSumAvailable() {
		return this.sumAvailable;
	}

	public void setSumAvailable(BigDecimal sumAvailable) {
		this.sumAvailable = sumAvailable;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VInventoryByGoodsIdAvailableId))
			return false;
		VInventoryByGoodsIdAvailableId castOther = (VInventoryByGoodsIdAvailableId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getSumAvailable() == castOther.getSumAvailable())
						|| (this.getSumAvailable() != null && castOther.getSumAvailable() != null
								&& this.getSumAvailable().equals(castOther.getSumAvailable())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getSumAvailable() == null ? 0 : this.getSumAvailable().hashCode());
		return result;
	}

}
