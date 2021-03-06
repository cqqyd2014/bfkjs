package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EcsTaxRate generated by hbm2java
 */
@Entity
@Table(name = "ecs_tax_rate", schema = "public")
public class EcsTaxRate implements java.io.Serializable {

	private String hsCode;
	private String goodsCode;
	private String goodsCodeAdd;
	private String goodsName;
	private BigDecimal lowRate;
	private BigDecimal highRate;
	private BigDecimal regRate;
	private BigDecimal taxRate;
	private String unit1;
	private String unit2;
	private String note;

	public EcsTaxRate() {
	}

	public EcsTaxRate(String hsCode, String goodsCode, String goodsCodeAdd, String goodsName, BigDecimal lowRate,
			BigDecimal highRate, BigDecimal regRate, BigDecimal taxRate) {
		this.hsCode = hsCode;
		this.goodsCode = goodsCode;
		this.goodsCodeAdd = goodsCodeAdd;
		this.goodsName = goodsName;
		this.lowRate = lowRate;
		this.highRate = highRate;
		this.regRate = regRate;
		this.taxRate = taxRate;
	}

	public EcsTaxRate(String hsCode, String goodsCode, String goodsCodeAdd, String goodsName, BigDecimal lowRate,
			BigDecimal highRate, BigDecimal regRate, BigDecimal taxRate, String unit1, String unit2, String note) {
		this.hsCode = hsCode;
		this.goodsCode = goodsCode;
		this.goodsCodeAdd = goodsCodeAdd;
		this.goodsName = goodsName;
		this.lowRate = lowRate;
		this.highRate = highRate;
		this.regRate = regRate;
		this.taxRate = taxRate;
		this.unit1 = unit1;
		this.unit2 = unit2;
		this.note = note;
	}

	@Id

	@Column(name = "hs_code", unique = true, nullable = false, length = 200)
	public String getHsCode() {
		return this.hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	@Column(name = "goods_code", nullable = false, length = 20)
	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "goods_code_add", nullable = false, length = 20)
	public String getGoodsCodeAdd() {
		return this.goodsCodeAdd;
	}

	public void setGoodsCodeAdd(String goodsCodeAdd) {
		this.goodsCodeAdd = goodsCodeAdd;
	}

	@Column(name = "goods_name", nullable = false, length = 200)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "low_rate", nullable = false, precision = 10, scale = 4)
	public BigDecimal getLowRate() {
		return this.lowRate;
	}

	public void setLowRate(BigDecimal lowRate) {
		this.lowRate = lowRate;
	}

	@Column(name = "high_rate", nullable = false, precision = 10, scale = 4)
	public BigDecimal getHighRate() {
		return this.highRate;
	}

	public void setHighRate(BigDecimal highRate) {
		this.highRate = highRate;
	}

	@Column(name = "reg_rate", nullable = false, precision = 10, scale = 4)
	public BigDecimal getRegRate() {
		return this.regRate;
	}

	public void setRegRate(BigDecimal regRate) {
		this.regRate = regRate;
	}

	@Column(name = "tax_rate", nullable = false, precision = 10, scale = 4)
	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "unit_1", length = 50)
	public String getUnit1() {
		return this.unit1;
	}

	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}

	@Column(name = "unit_2", length = 50)
	public String getUnit2() {
		return this.unit2;
	}

	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}

	@Column(name = "note", length = 225)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
