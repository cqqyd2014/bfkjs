package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VIntoWhContractDId generated by hbm2java
 */
@Embeddable
public class VIntoWhContractDId implements java.io.Serializable {

	private String intoWhUuid;
	private String comId;
	private String contractNo;
	private String goodsId;
	private BigDecimal price;
	private Date inDat;
	private Date paperDat;
	private String supplyName;

	public VIntoWhContractDId() {
	}

	public VIntoWhContractDId(String intoWhUuid, String comId, String contractNo, String goodsId, BigDecimal price,
			Date inDat, Date paperDat, String supplyName) {
		this.intoWhUuid = intoWhUuid;
		this.comId = comId;
		this.contractNo = contractNo;
		this.goodsId = goodsId;
		this.price = price;
		this.inDat = inDat;
		this.paperDat = paperDat;
		this.supplyName = supplyName;
	}

	@Column(name = "into_wh_uuid", length = 36)
	public String getIntoWhUuid() {
		return this.intoWhUuid;
	}

	public void setIntoWhUuid(String intoWhUuid) {
		this.intoWhUuid = intoWhUuid;
	}

	@Column(name = "com_id", length = 4)
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

	@Column(name = "price", precision = 131089, scale = 0)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "in_dat", length = 35)
	public Date getInDat() {
		return this.inDat;
	}

	public void setInDat(Date inDat) {
		this.inDat = inDat;
	}

	@Column(name = "paper_dat", length = 35)
	public Date getPaperDat() {
		return this.paperDat;
	}

	public void setPaperDat(Date paperDat) {
		this.paperDat = paperDat;
	}

	@Column(name = "supply_name", length = 45)
	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VIntoWhContractDId))
			return false;
		VIntoWhContractDId castOther = (VIntoWhContractDId) other;

		return ((this.getIntoWhUuid() == castOther.getIntoWhUuid()) || (this.getIntoWhUuid() != null
				&& castOther.getIntoWhUuid() != null && this.getIntoWhUuid().equals(castOther.getIntoWhUuid())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getContractNo() == castOther.getContractNo()) || (this.getContractNo() != null
						&& castOther.getContractNo() != null && this.getContractNo().equals(castOther.getContractNo())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getPrice() == castOther.getPrice()) || (this.getPrice() != null
						&& castOther.getPrice() != null && this.getPrice().equals(castOther.getPrice())))
				&& ((this.getInDat() == castOther.getInDat()) || (this.getInDat() != null
						&& castOther.getInDat() != null && this.getInDat().equals(castOther.getInDat())))
				&& ((this.getPaperDat() == castOther.getPaperDat()) || (this.getPaperDat() != null
						&& castOther.getPaperDat() != null && this.getPaperDat().equals(castOther.getPaperDat())))
				&& ((this.getSupplyName() == castOther.getSupplyName())
						|| (this.getSupplyName() != null && castOther.getSupplyName() != null
								&& this.getSupplyName().equals(castOther.getSupplyName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIntoWhUuid() == null ? 0 : this.getIntoWhUuid().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getContractNo() == null ? 0 : this.getContractNo().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result + (getInDat() == null ? 0 : this.getInDat().hashCode());
		result = 37 * result + (getPaperDat() == null ? 0 : this.getPaperDat().hashCode());
		result = 37 * result + (getSupplyName() == null ? 0 : this.getSupplyName().hashCode());
		return result;
	}

}
