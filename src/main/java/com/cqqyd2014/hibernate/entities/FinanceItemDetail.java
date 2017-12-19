package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FinanceItemDetail generated by hbm2java
 */
@Entity
@Table(name = "finance_item_detail", schema = "public")
public class FinanceItemDetail implements java.io.Serializable {

	private FinanceItemDetailId id;
	private String voucherId;

	public FinanceItemDetail() {
	}

	public FinanceItemDetail(FinanceItemDetailId id) {
		this.id = id;
	}

	public FinanceItemDetail(FinanceItemDetailId id, String voucherId) {
		this.id = id;
		this.voucherId = voucherId;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)),
			@AttributeOverride(name = "bookId", column = @Column(name = "book_id", nullable = false)),
			@AttributeOverride(name = "detailId", column = @Column(name = "detail_id", nullable = false, length = 45)),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false, length = 45)),
			@AttributeOverride(name = "itemId", column = @Column(name = "item_id", nullable = false, length = 45)),
			@AttributeOverride(name = "itemCode", column = @Column(name = "item_code", nullable = false, length = 45)) })
	public FinanceItemDetailId getId() {
		return this.id;
	}

	public void setId(FinanceItemDetailId id) {
		this.id = id;
	}

	@Column(name = "voucher_id", length = 45)
	public String getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

}
