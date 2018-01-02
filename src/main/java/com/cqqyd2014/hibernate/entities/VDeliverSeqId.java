package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VDeliverSeqId generated by hbm2java
 */
@Embeddable
public class VDeliverSeqId implements java.io.Serializable {

	private String comId;
	private String COrderId;
	private String CGoodsId;
	private BigDecimal sendCount;
	private BigDecimal CCount;
	private BigDecimal CPrice;
	private String seq;
	private BigDecimal yue;
	private String CName;
	private String unit;

	public VDeliverSeqId() {
	}

	public VDeliverSeqId(String comId, String COrderId, String CGoodsId, BigDecimal sendCount, BigDecimal CCount,
			BigDecimal CPrice, String seq, BigDecimal yue, String CName, String unit) {
		this.comId = comId;
		this.COrderId = COrderId;
		this.CGoodsId = CGoodsId;
		this.sendCount = sendCount;
		this.CCount = CCount;
		this.CPrice = CPrice;
		this.seq = seq;
		this.yue = yue;
		this.CName = CName;
		this.unit = unit;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "c_order_id", length = 45)
	public String getCOrderId() {
		return this.COrderId;
	}

	public void setCOrderId(String COrderId) {
		this.COrderId = COrderId;
	}

	@Column(name = "c_goods_id", length = 45)
	public String getCGoodsId() {
		return this.CGoodsId;
	}

	public void setCGoodsId(String CGoodsId) {
		this.CGoodsId = CGoodsId;
	}

	@Column(name = "send_count", precision = 131089, scale = 0)
	public BigDecimal getSendCount() {
		return this.sendCount;
	}

	public void setSendCount(BigDecimal sendCount) {
		this.sendCount = sendCount;
	}

	@Column(name = "c_count", precision = 131089, scale = 0)
	public BigDecimal getCCount() {
		return this.CCount;
	}

	public void setCCount(BigDecimal CCount) {
		this.CCount = CCount;
	}

	@Column(name = "c_price", precision = 131089, scale = 0)
	public BigDecimal getCPrice() {
		return this.CPrice;
	}

	public void setCPrice(BigDecimal CPrice) {
		this.CPrice = CPrice;
	}

	@Column(name = "seq")
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Column(name = "yue", precision = 131089, scale = 0)
	public BigDecimal getYue() {
		return this.yue;
	}

	public void setYue(BigDecimal yue) {
		this.yue = yue;
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
		if (!(other instanceof VDeliverSeqId))
			return false;
		VDeliverSeqId castOther = (VDeliverSeqId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getCOrderId() == castOther.getCOrderId()) || (this.getCOrderId() != null
						&& castOther.getCOrderId() != null && this.getCOrderId().equals(castOther.getCOrderId())))
				&& ((this.getCGoodsId() == castOther.getCGoodsId()) || (this.getCGoodsId() != null
						&& castOther.getCGoodsId() != null && this.getCGoodsId().equals(castOther.getCGoodsId())))
				&& ((this.getSendCount() == castOther.getSendCount()) || (this.getSendCount() != null
						&& castOther.getSendCount() != null && this.getSendCount().equals(castOther.getSendCount())))
				&& ((this.getCCount() == castOther.getCCount()) || (this.getCCount() != null
						&& castOther.getCCount() != null && this.getCCount().equals(castOther.getCCount())))
				&& ((this.getCPrice() == castOther.getCPrice()) || (this.getCPrice() != null
						&& castOther.getCPrice() != null && this.getCPrice().equals(castOther.getCPrice())))
				&& ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null && castOther.getSeq() != null
						&& this.getSeq().equals(castOther.getSeq())))
				&& ((this.getYue() == castOther.getYue()) || (this.getYue() != null && castOther.getYue() != null
						&& this.getYue().equals(castOther.getYue())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getCOrderId() == null ? 0 : this.getCOrderId().hashCode());
		result = 37 * result + (getCGoodsId() == null ? 0 : this.getCGoodsId().hashCode());
		result = 37 * result + (getSendCount() == null ? 0 : this.getSendCount().hashCode());
		result = 37 * result + (getCCount() == null ? 0 : this.getCCount().hashCode());
		result = 37 * result + (getCPrice() == null ? 0 : this.getCPrice().hashCode());
		result = 37 * result + (getSeq() == null ? 0 : this.getSeq().hashCode());
		result = 37 * result + (getYue() == null ? 0 : this.getYue().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		return result;
	}

}
