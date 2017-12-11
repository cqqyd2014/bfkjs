package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VDeliverYueId generated by hbm2java
 */
@Embeddable
public class VDeliverYueId implements java.io.Serializable {

	private BigDecimal orderNum;
	private String CName;
	private String comId;
	private String orderNo;
	private String CGoodsId;
	private Long sendedNum;

	public VDeliverYueId() {
	}

	public VDeliverYueId(BigDecimal orderNum, String CName, String comId, String orderNo, String CGoodsId,
			Long sendedNum) {
		this.orderNum = orderNum;
		this.CName = CName;
		this.comId = comId;
		this.orderNo = orderNo;
		this.CGoodsId = CGoodsId;
		this.sendedNum = sendedNum;
	}

	@Column(name = "order_num", precision = 131089, scale = 0)
	public BigDecimal getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
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

	@Column(name = "c_goods_id", length = 45)
	public String getCGoodsId() {
		return this.CGoodsId;
	}

	public void setCGoodsId(String CGoodsId) {
		this.CGoodsId = CGoodsId;
	}

	@Column(name = "sended_num")
	public Long getSendedNum() {
		return this.sendedNum;
	}

	public void setSendedNum(Long sendedNum) {
		this.sendedNum = sendedNum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VDeliverYueId))
			return false;
		VDeliverYueId castOther = (VDeliverYueId) other;

		return ((this.getOrderNum() == castOther.getOrderNum()) || (this.getOrderNum() != null
				&& castOther.getOrderNum() != null && this.getOrderNum().equals(castOther.getOrderNum())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getCGoodsId() == castOther.getCGoodsId()) || (this.getCGoodsId() != null
						&& castOther.getCGoodsId() != null && this.getCGoodsId().equals(castOther.getCGoodsId())))
				&& ((this.getSendedNum() == castOther.getSendedNum()) || (this.getSendedNum() != null
						&& castOther.getSendedNum() != null && this.getSendedNum().equals(castOther.getSendedNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderNum() == null ? 0 : this.getOrderNum().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getCGoodsId() == null ? 0 : this.getCGoodsId().hashCode());
		result = 37 * result + (getSendedNum() == null ? 0 : this.getSendedNum().hashCode());
		return result;
	}

}
