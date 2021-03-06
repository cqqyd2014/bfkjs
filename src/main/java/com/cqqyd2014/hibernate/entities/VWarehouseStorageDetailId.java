package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VWarehouseStorageDetailId generated by hbm2java
 */
@Embeddable
public class VWarehouseStorageDetailId implements java.io.Serializable {

	private String goodsId;
	private BigDecimal num;
	private String comId;
	private String whId;
	private String storageId;
	private String CName;

	public VWarehouseStorageDetailId() {
	}

	public VWarehouseStorageDetailId(String goodsId, BigDecimal num, String comId, String whId, String storageId,
			String CName) {
		this.goodsId = goodsId;
		this.num = num;
		this.comId = comId;
		this.whId = whId;
		this.storageId = storageId;
		this.CName = CName;
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

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "wh_id", length = 6)
	public String getWhId() {
		return this.whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	@Column(name = "storage_id", length = 6)
	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWarehouseStorageDetailId))
			return false;
		VWarehouseStorageDetailId castOther = (VWarehouseStorageDetailId) other;

		return ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
				&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null
						&& this.getNum().equals(castOther.getNum())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getWhId() == castOther.getWhId()) || (this.getWhId() != null && castOther.getWhId() != null
						&& this.getWhId().equals(castOther.getWhId())))
				&& ((this.getStorageId() == castOther.getStorageId()) || (this.getStorageId() != null
						&& castOther.getStorageId() != null && this.getStorageId().equals(castOther.getStorageId())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getWhId() == null ? 0 : this.getWhId().hashCode());
		result = 37 * result + (getStorageId() == null ? 0 : this.getStorageId().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		return result;
	}

}
