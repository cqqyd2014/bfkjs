package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrderFromId generated by hbm2java
 */
@Embeddable
public class OrderFromId implements java.io.Serializable {

	private String comId;
	private String orderTypeCode;
	private String EId;

	public OrderFromId() {
	}

	public OrderFromId(String comId, String orderTypeCode, String EId) {
		this.comId = comId;
		this.orderTypeCode = orderTypeCode;
		this.EId = EId;
	}

	@Column(name = "com_id", nullable = false, length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "order_type_code", nullable = false, length = 2)
	public String getOrderTypeCode() {
		return this.orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	@Column(name = "e_id", nullable = false, length = 4)
	public String getEId() {
		return this.EId;
	}

	public void setEId(String EId) {
		this.EId = EId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderFromId))
			return false;
		OrderFromId castOther = (OrderFromId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getOrderTypeCode() == castOther.getOrderTypeCode())
						|| (this.getOrderTypeCode() != null && castOther.getOrderTypeCode() != null
								&& this.getOrderTypeCode().equals(castOther.getOrderTypeCode())))
				&& ((this.getEId() == castOther.getEId()) || (this.getEId() != null && castOther.getEId() != null
						&& this.getEId().equals(castOther.getEId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderTypeCode() == null ? 0 : this.getOrderTypeCode().hashCode());
		result = 37 * result + (getEId() == null ? 0 : this.getEId().hashCode());
		return result;
	}

}
