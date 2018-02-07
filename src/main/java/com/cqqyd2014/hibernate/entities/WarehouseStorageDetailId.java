package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WarehouseStorageDetailId generated by hbm2java
 */
@Embeddable
public class WarehouseStorageDetailId implements java.io.Serializable {

	private String comId;
	private String whId;
	private String storageId;
	private String goodsId;

	public WarehouseStorageDetailId() {
	}

	public WarehouseStorageDetailId(String comId, String whId, String storageId, String goodsId) {
		this.comId = comId;
		this.whId = whId;
		this.storageId = storageId;
		this.goodsId = goodsId;
	}

	@Column(name = "com_id", nullable = false, length = 45)
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

	@Column(name = "storage_id", nullable = false, length = 6)
	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	@Column(name = "goods_id", nullable = false, length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WarehouseStorageDetailId))
			return false;
		WarehouseStorageDetailId castOther = (WarehouseStorageDetailId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getWhId() == castOther.getWhId()) || (this.getWhId() != null && castOther.getWhId() != null
						&& this.getWhId().equals(castOther.getWhId())))
				&& ((this.getStorageId() == castOther.getStorageId()) || (this.getStorageId() != null
						&& castOther.getStorageId() != null && this.getStorageId().equals(castOther.getStorageId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getWhId() == null ? 0 : this.getWhId().hashCode());
		result = 37 * result + (getStorageId() == null ? 0 : this.getStorageId().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		return result;
	}

}
