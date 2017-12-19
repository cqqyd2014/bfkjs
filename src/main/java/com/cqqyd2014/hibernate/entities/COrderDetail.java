package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * COrderDetail generated by hbm2java
 */
@Entity
@Table(name = "c_order_detail", schema = "public")
public class COrderDetail implements java.io.Serializable {

	private String CDetailId;
	private String COrderId;
	private String CGoodsId;
	private BigDecimal CCount;
	private BigDecimal CPrice;
	private Date CTime;
	private BigDecimal CPrice2;
	private BigDecimal CTax2;
	private BigDecimal CRegTax2;
	private String comId;
	private BigDecimal totall;
	private BigDecimal total2;

	public COrderDetail() {
	}

	public COrderDetail(String CDetailId, String COrderId) {
		this.CDetailId = CDetailId;
		this.COrderId = COrderId;
	}

	public COrderDetail(String CDetailId, String COrderId, String CGoodsId, BigDecimal CCount, BigDecimal CPrice,
			Date CTime, BigDecimal CPrice2, BigDecimal CTax2, BigDecimal CRegTax2, String comId, BigDecimal totall,
			BigDecimal total2) {
		this.CDetailId = CDetailId;
		this.COrderId = COrderId;
		this.CGoodsId = CGoodsId;
		this.CCount = CCount;
		this.CPrice = CPrice;
		this.CTime = CTime;
		this.CPrice2 = CPrice2;
		this.CTax2 = CTax2;
		this.CRegTax2 = CRegTax2;
		this.comId = comId;
		this.totall = totall;
		this.total2 = total2;
	}

	@Id

	@Column(name = "c_detail_id", unique = true, nullable = false, length = 36)
	public String getCDetailId() {
		return this.CDetailId;
	}

	public void setCDetailId(String CDetailId) {
		this.CDetailId = CDetailId;
	}

	@Column(name = "c_order_id", nullable = false, length = 45)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", length = 35)
	public Date getCTime() {
		return this.CTime;
	}

	public void setCTime(Date CTime) {
		this.CTime = CTime;
	}

	@Column(name = "c_price2", precision = 131089, scale = 0)
	public BigDecimal getCPrice2() {
		return this.CPrice2;
	}

	public void setCPrice2(BigDecimal CPrice2) {
		this.CPrice2 = CPrice2;
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

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
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

}
