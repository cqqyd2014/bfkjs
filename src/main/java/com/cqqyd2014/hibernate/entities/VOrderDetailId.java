package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VOrderDetailId generated by hbm2java
 */
@Embeddable
public class VOrderDetailId implements java.io.Serializable {

	private Date CTime;
	private BigDecimal CTax2;
	private BigDecimal CRegTax2;
	private BigDecimal CPrice2;
	private BigDecimal totall;
	private BigDecimal total2;
	private String COrderId;
	private String CGoodsId;
	private BigDecimal CCount;
	private BigDecimal CPrice;
	private String CDetailId;
	private String CName;
	private String unit;
	private String comId;

	public VOrderDetailId() {
	}

	public VOrderDetailId(Date CTime, BigDecimal CTax2, BigDecimal CRegTax2, BigDecimal CPrice2, BigDecimal totall,
			BigDecimal total2, String COrderId, String CGoodsId, BigDecimal CCount, BigDecimal CPrice, String CDetailId,
			String CName, String unit, String comId) {
		this.CTime = CTime;
		this.CTax2 = CTax2;
		this.CRegTax2 = CRegTax2;
		this.CPrice2 = CPrice2;
		this.totall = totall;
		this.total2 = total2;
		this.COrderId = COrderId;
		this.CGoodsId = CGoodsId;
		this.CCount = CCount;
		this.CPrice = CPrice;
		this.CDetailId = CDetailId;
		this.CName = CName;
		this.unit = unit;
		this.comId = comId;
	}

	@Column(name = "c_time", length = 35)
	public Date getCTime() {
		return this.CTime;
	}

	public void setCTime(Date CTime) {
		this.CTime = CTime;
	}

	@Column(name = "c_tax2", precision = 131089, scale = 0)
	public BigDecimal getCTax2() {
		return this.CTax2;
	}

	public void setCTax2(BigDecimal CTax2) {
		this.CTax2 = CTax2;
	}

	@Column(name = "c_reg_tax2", precision = 131089, scale = 0)
	public BigDecimal getCRegTax2() {
		return this.CRegTax2;
	}

	public void setCRegTax2(BigDecimal CRegTax2) {
		this.CRegTax2 = CRegTax2;
	}

	@Column(name = "c_price2", precision = 131089, scale = 0)
	public BigDecimal getCPrice2() {
		return this.CPrice2;
	}

	public void setCPrice2(BigDecimal CPrice2) {
		this.CPrice2 = CPrice2;
	}

	@Column(name = "totall", precision = 131089, scale = 0)
	public BigDecimal getTotall() {
		return this.totall;
	}

	public void setTotall(BigDecimal totall) {
		this.totall = totall;
	}

	@Column(name = "total2", precision = 131089, scale = 0)
	public BigDecimal getTotal2() {
		return this.total2;
	}

	public void setTotal2(BigDecimal total2) {
		this.total2 = total2;
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

	@Column(name = "c_detail_id", length = 36)
	public String getCDetailId() {
		return this.CDetailId;
	}

	public void setCDetailId(String CDetailId) {
		this.CDetailId = CDetailId;
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
		if (!(other instanceof VOrderDetailId))
			return false;
		VOrderDetailId castOther = (VOrderDetailId) other;

		return ((this.getCTime() == castOther.getCTime()) || (this.getCTime() != null && castOther.getCTime() != null
				&& this.getCTime().equals(castOther.getCTime())))
				&& ((this.getCTax2() == castOther.getCTax2()) || (this.getCTax2() != null
						&& castOther.getCTax2() != null && this.getCTax2().equals(castOther.getCTax2())))
				&& ((this.getCRegTax2() == castOther.getCRegTax2()) || (this.getCRegTax2() != null
						&& castOther.getCRegTax2() != null && this.getCRegTax2().equals(castOther.getCRegTax2())))
				&& ((this.getCPrice2() == castOther.getCPrice2()) || (this.getCPrice2() != null
						&& castOther.getCPrice2() != null && this.getCPrice2().equals(castOther.getCPrice2())))
				&& ((this.getTotall() == castOther.getTotall()) || (this.getTotall() != null
						&& castOther.getTotall() != null && this.getTotall().equals(castOther.getTotall())))
				&& ((this.getTotal2() == castOther.getTotal2()) || (this.getTotal2() != null
						&& castOther.getTotal2() != null && this.getTotal2().equals(castOther.getTotal2())))
				&& ((this.getCOrderId() == castOther.getCOrderId()) || (this.getCOrderId() != null
						&& castOther.getCOrderId() != null && this.getCOrderId().equals(castOther.getCOrderId())))
				&& ((this.getCGoodsId() == castOther.getCGoodsId()) || (this.getCGoodsId() != null
						&& castOther.getCGoodsId() != null && this.getCGoodsId().equals(castOther.getCGoodsId())))
				&& ((this.getCCount() == castOther.getCCount()) || (this.getCCount() != null
						&& castOther.getCCount() != null && this.getCCount().equals(castOther.getCCount())))
				&& ((this.getCPrice() == castOther.getCPrice()) || (this.getCPrice() != null
						&& castOther.getCPrice() != null && this.getCPrice().equals(castOther.getCPrice())))
				&& ((this.getCDetailId() == castOther.getCDetailId()) || (this.getCDetailId() != null
						&& castOther.getCDetailId() != null && this.getCDetailId().equals(castOther.getCDetailId())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCTime() == null ? 0 : this.getCTime().hashCode());
		result = 37 * result + (getCTax2() == null ? 0 : this.getCTax2().hashCode());
		result = 37 * result + (getCRegTax2() == null ? 0 : this.getCRegTax2().hashCode());
		result = 37 * result + (getCPrice2() == null ? 0 : this.getCPrice2().hashCode());
		result = 37 * result + (getTotall() == null ? 0 : this.getTotall().hashCode());
		result = 37 * result + (getTotal2() == null ? 0 : this.getTotal2().hashCode());
		result = 37 * result + (getCOrderId() == null ? 0 : this.getCOrderId().hashCode());
		result = 37 * result + (getCGoodsId() == null ? 0 : this.getCGoodsId().hashCode());
		result = 37 * result + (getCCount() == null ? 0 : this.getCCount().hashCode());
		result = 37 * result + (getCPrice() == null ? 0 : this.getCPrice().hashCode());
		result = 37 * result + (getCDetailId() == null ? 0 : this.getCDetailId().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
