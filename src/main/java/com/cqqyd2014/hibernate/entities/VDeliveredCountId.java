package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VDeliveredCountId generated by hbm2java
 */
@Embeddable
public class VDeliveredCountId implements java.io.Serializable {

	private String comId;
	private String orderNo;
	private String goodsId;
	private Long num;

	public VDeliveredCountId() {
	}

	public VDeliveredCountId(String comId, String orderNo, String goodsId, Long num) {
		this.comId = comId;
		this.orderNo = orderNo;
		this.goodsId = goodsId;
		this.num = num;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "order_no", length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "goods_id", length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "num")
	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VDeliveredCountId))
			return false;
		VDeliveredCountId castOther = (VDeliveredCountId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null
						&& this.getNum().equals(castOther.getNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
		return result;
	}

}
