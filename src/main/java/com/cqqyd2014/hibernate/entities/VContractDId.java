package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VContractDId generated by hbm2java
 */
@Embeddable
public class VContractDId implements java.io.Serializable {

	private String contractNo;
	private String goodsId;
	private BigDecimal num;
	private BigDecimal numIn;
	private BigDecimal numOut;
	private BigDecimal waiteForArrival;
	private BigDecimal price;
	private String comId;
	private String CName;
	private String unit;
	private String CUnit;

	public VContractDId() {
	}

	public VContractDId(String contractNo, String goodsId, BigDecimal num, BigDecimal numIn, BigDecimal numOut,
			BigDecimal waiteForArrival, BigDecimal price, String comId, String CName, String unit, String CUnit) {
		this.contractNo = contractNo;
		this.goodsId = goodsId;
		this.num = num;
		this.numIn = numIn;
		this.numOut = numOut;
		this.waiteForArrival = waiteForArrival;
		this.price = price;
		this.comId = comId;
		this.CName = CName;
		this.unit = unit;
		this.CUnit = CUnit;
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

	@Column(name = "num", precision = 131089, scale = 0)
	public BigDecimal getNum() {
		return this.num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	@Column(name = "num_in", precision = 131089, scale = 0)
	public BigDecimal getNumIn() {
		return this.numIn;
	}

	public void setNumIn(BigDecimal numIn) {
		this.numIn = numIn;
	}

	@Column(name = "num_out", precision = 131089, scale = 0)
	public BigDecimal getNumOut() {
		return this.numOut;
	}

	public void setNumOut(BigDecimal numOut) {
		this.numOut = numOut;
	}

	@Column(name = "waite_for_arrival", precision = 131089, scale = 0)
	public BigDecimal getWaiteForArrival() {
		return this.waiteForArrival;
	}

	public void setWaiteForArrival(BigDecimal waiteForArrival) {
		this.waiteForArrival = waiteForArrival;
	}

	@Column(name = "price", precision = 131089, scale = 0)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "unit", length = 45)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "c_unit", length = 90)
	public String getCUnit() {
		return this.CUnit;
	}

	public void setCUnit(String CUnit) {
		this.CUnit = CUnit;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VContractDId))
			return false;
		VContractDId castOther = (VContractDId) other;

		return ((this.getContractNo() == castOther.getContractNo()) || (this.getContractNo() != null
				&& castOther.getContractNo() != null && this.getContractNo().equals(castOther.getContractNo())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null
						&& this.getNum().equals(castOther.getNum())))
				&& ((this.getNumIn() == castOther.getNumIn()) || (this.getNumIn() != null
						&& castOther.getNumIn() != null && this.getNumIn().equals(castOther.getNumIn())))
				&& ((this.getNumOut() == castOther.getNumOut()) || (this.getNumOut() != null
						&& castOther.getNumOut() != null && this.getNumOut().equals(castOther.getNumOut())))
				&& ((this.getWaiteForArrival() == castOther.getWaiteForArrival())
						|| (this.getWaiteForArrival() != null && castOther.getWaiteForArrival() != null
								&& this.getWaiteForArrival().equals(castOther.getWaiteForArrival())))
				&& ((this.getPrice() == castOther.getPrice()) || (this.getPrice() != null
						&& castOther.getPrice() != null && this.getPrice().equals(castOther.getPrice())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())))
				&& ((this.getCUnit() == castOther.getCUnit()) || (this.getCUnit() != null
						&& castOther.getCUnit() != null && this.getCUnit().equals(castOther.getCUnit())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getContractNo() == null ? 0 : this.getContractNo().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result + (getNumIn() == null ? 0 : this.getNumIn().hashCode());
		result = 37 * result + (getNumOut() == null ? 0 : this.getNumOut().hashCode());
		result = 37 * result + (getWaiteForArrival() == null ? 0 : this.getWaiteForArrival().hashCode());
		result = 37 * result + (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result + (getCUnit() == null ? 0 : this.getCUnit().hashCode());
		return result;
	}

}
