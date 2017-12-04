package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VDeliverNeedAssignId generated by hbm2java
 */
@Embeddable
public class VDeliverNeedAssignId implements java.io.Serializable {

	private String orderNo;
	private String seq;
	private String comId;

	public VDeliverNeedAssignId() {
	}

	public VDeliverNeedAssignId(String orderNo, String seq, String comId) {
		this.orderNo = orderNo;
		this.seq = seq;
		this.comId = comId;
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

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VDeliverNeedAssignId))
			return false;
		VDeliverNeedAssignId castOther = (VDeliverNeedAssignId) other;

		return ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
				&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null && castOther.getSeq() != null
						&& this.getSeq().equals(castOther.getSeq())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getSeq() == null ? 0 : this.getSeq().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
