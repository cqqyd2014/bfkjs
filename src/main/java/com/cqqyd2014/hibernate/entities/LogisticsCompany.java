package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LogisticsCompany generated by hbm2java
 */
@Entity
@Table(name = "logistics_company", schema = "public")
public class LogisticsCompany implements java.io.Serializable {

	private String logisticsId;
	private String logisticsName;
	private BigDecimal logisticsOrder;
	private Boolean logisticsOversea;
	private Boolean logisticsElectronicBill;
	private Boolean logisticsMonthBill;
	private BigDecimal logisticsBillLength;

	public LogisticsCompany() {
	}

	public LogisticsCompany(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public LogisticsCompany(String logisticsId, String logisticsName, BigDecimal logisticsOrder,
			Boolean logisticsOversea, Boolean logisticsElectronicBill, Boolean logisticsMonthBill,
			BigDecimal logisticsBillLength) {
		this.logisticsId = logisticsId;
		this.logisticsName = logisticsName;
		this.logisticsOrder = logisticsOrder;
		this.logisticsOversea = logisticsOversea;
		this.logisticsElectronicBill = logisticsElectronicBill;
		this.logisticsMonthBill = logisticsMonthBill;
		this.logisticsBillLength = logisticsBillLength;
	}

	@Id

	@Column(name = "logistics_id", unique = true, nullable = false, length = 45)
	public String getLogisticsId() {
		return this.logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	@Column(name = "logistics_name", length = 45)
	public String getLogisticsName() {
		return this.logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	@Column(name = "logistics_order", precision = 131089, scale = 0)
	public BigDecimal getLogisticsOrder() {
		return this.logisticsOrder;
	}

	public void setLogisticsOrder(BigDecimal logisticsOrder) {
		this.logisticsOrder = logisticsOrder;
	}

	@Column(name = "logistics_oversea")
	public Boolean getLogisticsOversea() {
		return this.logisticsOversea;
	}

	public void setLogisticsOversea(Boolean logisticsOversea) {
		this.logisticsOversea = logisticsOversea;
	}

	@Column(name = "logistics_electronic_bill")
	public Boolean getLogisticsElectronicBill() {
		return this.logisticsElectronicBill;
	}

	public void setLogisticsElectronicBill(Boolean logisticsElectronicBill) {
		this.logisticsElectronicBill = logisticsElectronicBill;
	}

	@Column(name = "logistics_month_bill")
	public Boolean getLogisticsMonthBill() {
		return this.logisticsMonthBill;
	}

	public void setLogisticsMonthBill(Boolean logisticsMonthBill) {
		this.logisticsMonthBill = logisticsMonthBill;
	}

	@Column(name = "logistics_bill_length", precision = 131089, scale = 0)
	public BigDecimal getLogisticsBillLength() {
		return this.logisticsBillLength;
	}

	public void setLogisticsBillLength(BigDecimal logisticsBillLength) {
		this.logisticsBillLength = logisticsBillLength;
	}

}
