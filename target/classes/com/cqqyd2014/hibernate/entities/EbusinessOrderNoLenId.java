package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-6 13:56:00 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EbusinessOrderNoLenId generated by hbm2java
 */
@Embeddable
public class EbusinessOrderNoLenId implements java.io.Serializable {

	private String EId;
	private int orderNoLen;

	public EbusinessOrderNoLenId() {
	}

	public EbusinessOrderNoLenId(String EId, int orderNoLen) {
		this.EId = EId;
		this.orderNoLen = orderNoLen;
	}

	@Column(name = "e_id", nullable = false, length = 4)
	public String getEId() {
		return this.EId;
	}

	public void setEId(String EId) {
		this.EId = EId;
	}

	@Column(name = "order_no_len", nullable = false)
	public int getOrderNoLen() {
		return this.orderNoLen;
	}

	public void setOrderNoLen(int orderNoLen) {
		this.orderNoLen = orderNoLen;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EbusinessOrderNoLenId))
			return false;
		EbusinessOrderNoLenId castOther = (EbusinessOrderNoLenId) other;

		return ((this.getEId() == castOther.getEId())
				|| (this.getEId() != null && castOther.getEId() != null && this.getEId().equals(castOther.getEId())))
				&& (this.getOrderNoLen() == castOther.getOrderNoLen());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getEId() == null ? 0 : this.getEId().hashCode());
		result = 37 * result + this.getOrderNoLen();
		return result;
	}

}
