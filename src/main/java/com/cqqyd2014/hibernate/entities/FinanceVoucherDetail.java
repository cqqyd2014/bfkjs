package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FinanceVoucherDetail generated by hbm2java
 */
@Entity
@Table(name = "finance_voucher_detail", schema = "public")
public class FinanceVoucherDetail implements java.io.Serializable {

	private String detailUuid;
	private String voucherUuid;
	private String accountId;
	private Integer dirction;
	private BigDecimal amount;
	private String comId;
	private String bookId;
	private Integer voucherYear;
	private Integer voucherMonth;
	private String voucherAbstract;

	public FinanceVoucherDetail() {
	}

	public FinanceVoucherDetail(String detailUuid) {
		this.detailUuid = detailUuid;
	}

	public FinanceVoucherDetail(String detailUuid, String voucherUuid, String accountId, Integer dirction,
			BigDecimal amount, String comId, String bookId, Integer voucherYear, Integer voucherMonth,
			String voucherAbstract) {
		this.detailUuid = detailUuid;
		this.voucherUuid = voucherUuid;
		this.accountId = accountId;
		this.dirction = dirction;
		this.amount = amount;
		this.comId = comId;
		this.bookId = bookId;
		this.voucherYear = voucherYear;
		this.voucherMonth = voucherMonth;
		this.voucherAbstract = voucherAbstract;
	}

	@Id

	@Column(name = "detail_uuid", unique = true, nullable = false, length = 45)
	public String getDetailUuid() {
		return this.detailUuid;
	}

	public void setDetailUuid(String detailUuid) {
		this.detailUuid = detailUuid;
	}

	@Column(name = "voucher_uuid", length = 45)
	public String getVoucherUuid() {
		return this.voucherUuid;
	}

	public void setVoucherUuid(String voucherUuid) {
		this.voucherUuid = voucherUuid;
	}

	@Column(name = "account_id", length = 45)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "dirction")
	public Integer getDirction() {
		return this.dirction;
	}

	public void setDirction(Integer dirction) {
		this.dirction = dirction;
	}

	@Column(name = "amount", precision = 131089, scale = 0)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "book_id", length = 45)
	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name = "voucher_year")
	public Integer getVoucherYear() {
		return this.voucherYear;
	}

	public void setVoucherYear(Integer voucherYear) {
		this.voucherYear = voucherYear;
	}

	@Column(name = "voucher_month")
	public Integer getVoucherMonth() {
		return this.voucherMonth;
	}

	public void setVoucherMonth(Integer voucherMonth) {
		this.voucherMonth = voucherMonth;
	}

	@Column(name = "voucher_abstract", length = 45)
	public String getVoucherAbstract() {
		return this.voucherAbstract;
	}

	public void setVoucherAbstract(String voucherAbstract) {
		this.voucherAbstract = voucherAbstract;
	}

}
