package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VPickupYueAllId generated by hbm2java
 */
@Embeddable
public class VPickupYueAllId implements java.io.Serializable {

	private String comId;
	private String orderNo;
	private BigDecimal yue;

	public VPickupYueAllId() {
	}

	public VPickupYueAllId(String comId, String orderNo, BigDecimal yue) {
		this.comId = comId;
		this.orderNo = orderNo;
		this.yue = yue;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "order_no", length = 45)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "yue", precision = 131089, scale = 0)
	public BigDecimal getYue() {
		return this.yue;
	}

	public void setYue(BigDecimal yue) {
		this.yue = yue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VPickupYueAllId))
			return false;
		VPickupYueAllId castOther = (VPickupYueAllId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getYue() == castOther.getYue()) || (this.getYue() != null && castOther.getYue() != null
						&& this.getYue().equals(castOther.getYue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getYue() == null ? 0 : this.getYue().hashCode());
		return result;
	}

}
