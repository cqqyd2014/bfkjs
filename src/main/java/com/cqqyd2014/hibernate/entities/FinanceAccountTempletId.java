package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FinanceAccountTempletId generated by hbm2java
 */
@Embeddable
public class FinanceAccountTempletId implements java.io.Serializable {

	private String accountId;
	private String templetId;

	public FinanceAccountTempletId() {
	}

	public FinanceAccountTempletId(String accountId, String templetId) {
		this.accountId = accountId;
		this.templetId = templetId;
	}

	@Column(name = "account_id", nullable = false, length = 45)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "templet_id", nullable = false, length = 45)
	public String getTempletId() {
		return this.templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FinanceAccountTempletId))
			return false;
		FinanceAccountTempletId castOther = (FinanceAccountTempletId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this.getAccountId() != null
				&& castOther.getAccountId() != null && this.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getTempletId() == castOther.getTempletId()) || (this.getTempletId() != null
						&& castOther.getTempletId() != null && this.getTempletId().equals(castOther.getTempletId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result + (getTempletId() == null ? 0 : this.getTempletId().hashCode());
		return result;
	}

}
