package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FinanceVoucher generated by hbm2java
 */
@Entity
@Table(name = "finance_voucher", schema = "public")
public class FinanceVoucher implements java.io.Serializable {

	private String voucherUuid;
	private String comId;
	private String bookId;
	private String voucherNo;
	private Date voucherDat;
	private Integer voucherMonth;
	private Integer voucherYear;

	public FinanceVoucher() {
	}

	public FinanceVoucher(String voucherUuid) {
		this.voucherUuid = voucherUuid;
	}

	public FinanceVoucher(String voucherUuid, String comId, String bookId, String voucherNo, Date voucherDat,
			Integer voucherMonth, Integer voucherYear) {
		this.voucherUuid = voucherUuid;
		this.comId = comId;
		this.bookId = bookId;
		this.voucherNo = voucherNo;
		this.voucherDat = voucherDat;
		this.voucherMonth = voucherMonth;
		this.voucherYear = voucherYear;
	}

	@Id

	@Column(name = "voucher_uuid", unique = true, nullable = false, length = 45)
	public String getVoucherUuid() {
		return this.voucherUuid;
	}

	public void setVoucherUuid(String voucherUuid) {
		this.voucherUuid = voucherUuid;
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

	@Column(name = "voucher_no", length = 45)
	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "voucher_dat", length = 35)
	public Date getVoucherDat() {
		return this.voucherDat;
	}

	public void setVoucherDat(Date voucherDat) {
		this.voucherDat = voucherDat;
	}

	@Column(name = "voucher_month")
	public Integer getVoucherMonth() {
		return this.voucherMonth;
	}

	public void setVoucherMonth(Integer voucherMonth) {
		this.voucherMonth = voucherMonth;
	}

	@Column(name = "voucher_year")
	public Integer getVoucherYear() {
		return this.voucherYear;
	}

	public void setVoucherYear(Integer voucherYear) {
		this.voucherYear = voucherYear;
	}

}
