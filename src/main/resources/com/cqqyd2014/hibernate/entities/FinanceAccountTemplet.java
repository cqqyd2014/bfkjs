package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-6 13:56:00 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FinanceAccountTemplet generated by hbm2java
 */
@Entity
@Table(name = "finance_account_templet", schema = "public")
public class FinanceAccountTemplet implements java.io.Serializable {

	private FinanceAccountTempletId id;
	private String accountName;
	private Integer balanceDirection;

	public FinanceAccountTemplet() {
	}

	public FinanceAccountTemplet(FinanceAccountTempletId id) {
		this.id = id;
	}

	public FinanceAccountTemplet(FinanceAccountTempletId id, String accountName, Integer balanceDirection) {
		this.id = id;
		this.accountName = accountName;
		this.balanceDirection = balanceDirection;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false, length = 45)),
			@AttributeOverride(name = "templetId", column = @Column(name = "templet_id", nullable = false, length = 45)) })
	public FinanceAccountTempletId getId() {
		return this.id;
	}

	public void setId(FinanceAccountTempletId id) {
		this.id = id;
	}

	@Column(name = "account_name", length = 45)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "balance_direction")
	public Integer getBalanceDirection() {
		return this.balanceDirection;
	}

	public void setBalanceDirection(Integer balanceDirection) {
		this.balanceDirection = balanceDirection;
	}

}
