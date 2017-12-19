package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FinanceItemsId generated by hbm2java
 */
@Embeddable
public class FinanceItemsId implements java.io.Serializable {

	private String comId;
	private String bookId;
	private String itemId;
	private String itemCode;

	public FinanceItemsId() {
	}

	public FinanceItemsId(String comId, String bookId, String itemId, String itemCode) {
		this.comId = comId;
		this.bookId = bookId;
		this.itemId = itemId;
		this.itemCode = itemCode;
	}

	@Column(name = "com_id", nullable = false, length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "book_id", nullable = false, length = 45)
	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name = "item_id", nullable = false, length = 45)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_code", nullable = false, length = 45)
	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FinanceItemsId))
			return false;
		FinanceItemsId castOther = (FinanceItemsId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getBookId() == castOther.getBookId()) || (this.getBookId() != null
						&& castOther.getBookId() != null && this.getBookId().equals(castOther.getBookId())))
				&& ((this.getItemId() == castOther.getItemId()) || (this.getItemId() != null
						&& castOther.getItemId() != null && this.getItemId().equals(castOther.getItemId())))
				&& ((this.getItemCode() == castOther.getItemCode()) || (this.getItemCode() != null
						&& castOther.getItemCode() != null && this.getItemCode().equals(castOther.getItemCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getBookId() == null ? 0 : this.getBookId().hashCode());
		result = 37 * result + (getItemId() == null ? 0 : this.getItemId().hashCode());
		result = 37 * result + (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		return result;
	}

}
