package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FinanceAccountId generated by hbm2java
 */
@Embeddable
public class FinanceAccountId implements java.io.Serializable {

	private String comId;
	private String bookId;
	private String accountId;

	public FinanceAccountId() {
	}

	public FinanceAccountId(String comId, String bookId, String accountId) {
		this.comId = comId;
		this.bookId = bookId;
		this.accountId = accountId;
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

	@Column(name = "account_id", nullable = false, length = 45)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FinanceAccountId))
			return false;
		FinanceAccountId castOther = (FinanceAccountId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getBookId() == castOther.getBookId()) || (this.getBookId() != null
						&& castOther.getBookId() != null && this.getBookId().equals(castOther.getBookId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this.getAccountId() != null
						&& castOther.getAccountId() != null && this.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getBookId() == null ? 0 : this.getBookId().hashCode());
		result = 37 * result + (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}
