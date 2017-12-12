package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LogisticsFee generated by hbm2java
 */
@Entity
@Table(name = "logistics_fee", schema = "public")
public class LogisticsFee implements java.io.Serializable {

	private LogisticsFeeId id;
	private BigDecimal nextFee;
	private BigDecimal firstFee;

	public LogisticsFee() {
	}

	public LogisticsFee(LogisticsFeeId id) {
		this.id = id;
	}

	public LogisticsFee(LogisticsFeeId id, BigDecimal nextFee, BigDecimal firstFee) {
		this.id = id;
		this.nextFee = nextFee;
		this.firstFee = firstFee;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "logistics", column = @Column(name = "logistics", nullable = false, length = 64)),
			@AttributeOverride(name = "vehicle", column = @Column(name = "vehicle", nullable = false, length = 64)) })
	public LogisticsFeeId getId() {
		return this.id;
	}

	public void setId(LogisticsFeeId id) {
		this.id = id;
	}

	@Column(name = "next_fee", precision = 131089, scale = 0)
	public BigDecimal getNextFee() {
		return this.nextFee;
	}

	public void setNextFee(BigDecimal nextFee) {
		this.nextFee = nextFee;
	}

	@Column(name = "first_fee", precision = 131089, scale = 0)
	public BigDecimal getFirstFee() {
		return this.firstFee;
	}

	public void setFirstFee(BigDecimal firstFee) {
		this.firstFee = firstFee;
	}

}
