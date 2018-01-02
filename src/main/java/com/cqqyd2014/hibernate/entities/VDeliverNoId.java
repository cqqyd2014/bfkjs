package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VDeliverNoId generated by hbm2java
 */
@Embeddable
public class VDeliverNoId implements java.io.Serializable {

	private String deliverNo;
	private String orderNo;
	private String seq;

	public VDeliverNoId() {
	}

	public VDeliverNoId(String deliverNo, String orderNo, String seq) {
		this.deliverNo = deliverNo;
		this.orderNo = orderNo;
		this.seq = seq;
	}

	@Column(name = "deliver_no")
	public String getDeliverNo() {
		return this.deliverNo;
	}

	public void setDeliverNo(String deliverNo) {
		this.deliverNo = deliverNo;
	}

	@Column(name = "order_no", length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "seq", length = 4)
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VDeliverNoId))
			return false;
		VDeliverNoId castOther = (VDeliverNoId) other;

		return ((this.getDeliverNo() == castOther.getDeliverNo()) || (this.getDeliverNo() != null
				&& castOther.getDeliverNo() != null && this.getDeliverNo().equals(castOther.getDeliverNo())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null && castOther.getSeq() != null
						&& this.getSeq().equals(castOther.getSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDeliverNo() == null ? 0 : this.getDeliverNo().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getSeq() == null ? 0 : this.getSeq().hashCode());
		return result;
	}

}
