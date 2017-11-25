package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VInventoryByGoodsIdId generated by hbm2java
 */
@Embeddable
public class VInventoryByGoodsIdId implements java.io.Serializable {

	private Integer priority;
	private String storageId;
	private String whId;
	private String comId;
	private String goodsId;
	private BigDecimal num;
	private String CName;
	private String unit;

	public VInventoryByGoodsIdId() {
	}

	public VInventoryByGoodsIdId(Integer priority, String storageId, String whId, String comId, String goodsId,
			BigDecimal num, String CName, String unit) {
		this.priority = priority;
		this.storageId = storageId;
		this.whId = whId;
		this.comId = comId;
		this.goodsId = goodsId;
		this.num = num;
		this.CName = CName;
		this.unit = unit;
	}

	@Column(name = "priority")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column(name = "storage_id", length = 6)
	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	@Column(name = "wh_id", length = 6)
	public String getWhId() {
		return this.whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
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

	@Column(name = "num", precision = 131089, scale = 0)
	public BigDecimal getNum() {
		return this.num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "unit", length = 90)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VInventoryByGoodsIdId))
			return false;
		VInventoryByGoodsIdId castOther = (VInventoryByGoodsIdId) other;

		return ((this.getPriority() == castOther.getPriority()) || (this.getPriority() != null
				&& castOther.getPriority() != null && this.getPriority().equals(castOther.getPriority())))
				&& ((this.getStorageId() == castOther.getStorageId()) || (this.getStorageId() != null
						&& castOther.getStorageId() != null && this.getStorageId().equals(castOther.getStorageId())))
				&& ((this.getWhId() == castOther.getWhId()) || (this.getWhId() != null && castOther.getWhId() != null
						&& this.getWhId().equals(castOther.getWhId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null
						&& this.getNum().equals(castOther.getNum())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPriority() == null ? 0 : this.getPriority().hashCode());
		result = 37 * result + (getStorageId() == null ? 0 : this.getStorageId().hashCode());
		result = 37 * result + (getWhId() == null ? 0 : this.getWhId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		return result;
	}

}
