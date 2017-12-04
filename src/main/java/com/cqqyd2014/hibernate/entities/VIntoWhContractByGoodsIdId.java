package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VIntoWhContractByGoodsIdId generated by hbm2java
 */
@Embeddable
public class VIntoWhContractByGoodsIdId implements java.io.Serializable {

	private String comId;
	private String contractNo;
	private String goodsId;
	private BigDecimal buy;
	private BigDecimal out;
	private BigDecimal yue;

	public VIntoWhContractByGoodsIdId() {
	}

	public VIntoWhContractByGoodsIdId(String comId, String contractNo, String goodsId, BigDecimal buy, BigDecimal out,
			BigDecimal yue) {
		this.comId = comId;
		this.contractNo = contractNo;
		this.goodsId = goodsId;
		this.buy = buy;
		this.out = out;
		this.yue = yue;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "contract_no", length = 45)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "goods_id", length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "buy", precision = 131089, scale = 0)
	public BigDecimal getBuy() {
		return this.buy;
	}

	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}

	@Column(name = "out", precision = 131089, scale = 0)
	public BigDecimal getOut() {
		return this.out;
	}

	public void setOut(BigDecimal out) {
		this.out = out;
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
		if (!(other instanceof VIntoWhContractByGoodsIdId))
			return false;
		VIntoWhContractByGoodsIdId castOther = (VIntoWhContractByGoodsIdId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getContractNo() == castOther.getContractNo()) || (this.getContractNo() != null
						&& castOther.getContractNo() != null && this.getContractNo().equals(castOther.getContractNo())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getBuy() == castOther.getBuy()) || (this.getBuy() != null && castOther.getBuy() != null
						&& this.getBuy().equals(castOther.getBuy())))
				&& ((this.getOut() == castOther.getOut()) || (this.getOut() != null && castOther.getOut() != null
						&& this.getOut().equals(castOther.getOut())))
				&& ((this.getYue() == castOther.getYue()) || (this.getYue() != null && castOther.getYue() != null
						&& this.getYue().equals(castOther.getYue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getContractNo() == null ? 0 : this.getContractNo().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getBuy() == null ? 0 : this.getBuy().hashCode());
		result = 37 * result + (getOut() == null ? 0 : this.getOut().hashCode());
		result = 37 * result + (getYue() == null ? 0 : this.getYue().hashCode());
		return result;
	}

}
