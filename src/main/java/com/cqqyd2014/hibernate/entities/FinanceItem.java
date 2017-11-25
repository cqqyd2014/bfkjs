package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FinanceItem generated by hbm2java
 */
@Entity
@Table(name = "finance_item", schema = "public")
public class FinanceItem implements java.io.Serializable {

	private FinanceItemId id;
	private String itemName;

	public FinanceItem() {
	}

	public FinanceItem(FinanceItemId id) {
		this.id = id;
	}

	public FinanceItem(FinanceItemId id, String itemName) {
		this.id = id;
		this.itemName = itemName;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)),
			@AttributeOverride(name = "bookId", column = @Column(name = "book_id", nullable = false, length = 45)),
			@AttributeOverride(name = "itemId", column = @Column(name = "item_id", nullable = false, length = 45)) })
	public FinanceItemId getId() {
		return this.id;
	}

	public void setId(FinanceItemId id) {
		this.id = id;
	}

	@Column(name = "item_name", length = 45)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
