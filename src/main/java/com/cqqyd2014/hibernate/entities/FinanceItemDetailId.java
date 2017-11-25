package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FinanceItemDetailId generated by hbm2java
 */
@Embeddable
public class FinanceItemDetailId implements java.io.Serializable {

	private String comId;
	private String bookId;
	private String detailId;
	private String accountId;
	private String itemId;
	private String itemCode;

	public FinanceItemDetailId() {
	}

	public FinanceItemDetailId(String comId, String bookId, String detailId, String accountId, String itemId,
			String itemCode) {
		this.comId = comId;
		this.bookId = bookId;
		this.detailId = detailId;
		this.accountId = accountId;
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

	@Column(name = "book_id", nullable = false)
	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name = "detail_id", nullable = false, length = 45)
	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	@Column(name = "account_id", nullable = false, length = 45)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
		if (!(other instanceof FinanceItemDetailId))
			return false;
		FinanceItemDetailId castOther = (FinanceItemDetailId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getBookId() == castOther.getBookId()) || (this.getBookId() != null
						&& castOther.getBookId() != null && this.getBookId().equals(castOther.getBookId())))
				&& ((this.getDetailId() == castOther.getDetailId()) || (this.getDetailId() != null
						&& castOther.getDetailId() != null && this.getDetailId().equals(castOther.getDetailId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this.getAccountId() != null
						&& castOther.getAccountId() != null && this.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getItemId() == castOther.getItemId()) || (this.getItemId() != null
						&& castOther.getItemId() != null && this.getItemId().equals(castOther.getItemId())))
				&& ((this.getItemCode() == castOther.getItemCode()) || (this.getItemCode() != null
						&& castOther.getItemCode() != null && this.getItemCode().equals(castOther.getItemCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getBookId() == null ? 0 : this.getBookId().hashCode());
		result = 37 * result + (getDetailId() == null ? 0 : this.getDetailId().hashCode());
		result = 37 * result + (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result + (getItemId() == null ? 0 : this.getItemId().hashCode());
		result = 37 * result + (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		return result;
	}

}
