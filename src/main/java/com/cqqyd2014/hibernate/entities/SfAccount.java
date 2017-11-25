package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SfAccount generated by hbm2java
 */
@Entity
@Table(name = "sf_account", schema = "public")
public class SfAccount implements java.io.Serializable {

	private String comId;
	private String sfMonthPayAccount;
	private String postAddr;
	private String webserviceAddr;
	private String accessCode;
	private String checkword;

	public SfAccount() {
	}

	public SfAccount(String comId) {
		this.comId = comId;
	}

	public SfAccount(String comId, String sfMonthPayAccount, String postAddr, String webserviceAddr, String accessCode,
			String checkword) {
		this.comId = comId;
		this.sfMonthPayAccount = sfMonthPayAccount;
		this.postAddr = postAddr;
		this.webserviceAddr = webserviceAddr;
		this.accessCode = accessCode;
		this.checkword = checkword;
	}

	@Id

	@Column(name = "com_id", unique = true, nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "sf_month_pay_account", length = 45)
	public String getSfMonthPayAccount() {
		return this.sfMonthPayAccount;
	}

	public void setSfMonthPayAccount(String sfMonthPayAccount) {
		this.sfMonthPayAccount = sfMonthPayAccount;
	}

	@Column(name = "post_addr", length = 512)
	public String getPostAddr() {
		return this.postAddr;
	}

	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}

	@Column(name = "webservice_addr", length = 512)
	public String getWebserviceAddr() {
		return this.webserviceAddr;
	}

	public void setWebserviceAddr(String webserviceAddr) {
		this.webserviceAddr = webserviceAddr;
	}

	@Column(name = "access_code", length = 512)
	public String getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@Column(name = "checkword", length = 512)
	public String getCheckword() {
		return this.checkword;
	}

	public void setCheckword(String checkword) {
		this.checkword = checkword;
	}

}
