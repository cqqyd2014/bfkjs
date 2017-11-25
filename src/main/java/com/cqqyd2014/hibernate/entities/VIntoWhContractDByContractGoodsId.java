package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VIntoWhContractDByContractGoodsId generated by hbm2java
 */
@Embeddable
public class VIntoWhContractDByContractGoodsId implements java.io.Serializable {

	private String contractId;
	private String goodsId;
	private BigDecimal sumBuy;
	private BigDecimal sumOut;
	private BigDecimal sumYue;

	public VIntoWhContractDByContractGoodsId() {
	}

	public VIntoWhContractDByContractGoodsId(String contractId, String goodsId, BigDecimal sumBuy, BigDecimal sumOut,
			BigDecimal sumYue) {
		this.contractId = contractId;
		this.goodsId = goodsId;
		this.sumBuy = sumBuy;
		this.sumOut = sumOut;
		this.sumYue = sumYue;
	}

	@Column(name = "contract_id", length = 45)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "goods_id", length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "sum_buy", precision = 131089, scale = 0)
	public BigDecimal getSumBuy() {
		return this.sumBuy;
	}

	public void setSumBuy(BigDecimal sumBuy) {
		this.sumBuy = sumBuy;
	}

	@Column(name = "sum_out", precision = 131089, scale = 0)
	public BigDecimal getSumOut() {
		return this.sumOut;
	}

	public void setSumOut(BigDecimal sumOut) {
		this.sumOut = sumOut;
	}

	@Column(name = "sum_yue", precision = 131089, scale = 0)
	public BigDecimal getSumYue() {
		return this.sumYue;
	}

	public void setSumYue(BigDecimal sumYue) {
		this.sumYue = sumYue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VIntoWhContractDByContractGoodsId))
			return false;
		VIntoWhContractDByContractGoodsId castOther = (VIntoWhContractDByContractGoodsId) other;

		return ((this.getContractId() == castOther.getContractId()) || (this.getContractId() != null
				&& castOther.getContractId() != null && this.getContractId().equals(castOther.getContractId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getSumBuy() == castOther.getSumBuy()) || (this.getSumBuy() != null
						&& castOther.getSumBuy() != null && this.getSumBuy().equals(castOther.getSumBuy())))
				&& ((this.getSumOut() == castOther.getSumOut()) || (this.getSumOut() != null
						&& castOther.getSumOut() != null && this.getSumOut().equals(castOther.getSumOut())))
				&& ((this.getSumYue() == castOther.getSumYue()) || (this.getSumYue() != null
						&& castOther.getSumYue() != null && this.getSumYue().equals(castOther.getSumYue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getContractId() == null ? 0 : this.getContractId().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getSumBuy() == null ? 0 : this.getSumBuy().hashCode());
		result = 37 * result + (getSumOut() == null ? 0 : this.getSumOut().hashCode());
		result = 37 * result + (getSumYue() == null ? 0 : this.getSumYue().hashCode());
		return result;
	}

}
