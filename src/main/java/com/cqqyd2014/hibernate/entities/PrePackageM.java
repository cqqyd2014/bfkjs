package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PrePackageM generated by hbm2java
 */
@Entity
@Table(name = "pre_package_m", schema = "public")
public class PrePackageM implements java.io.Serializable {

	private PrePackageMId id;
	private Boolean effective;
	private Boolean printed;
	private Date createDat;
	private Boolean packaged;
	private Date packageTime;
	private String memoNames;
	private BigDecimal packageWeight;
	private Boolean sended;
	private String memoBarcodes;
	private String createUserid;
	private String whId;
	private String unPackageUser;
	private Date unPackageTime;
	private Date sendDat;
	private BigDecimal actualWeight;
	private BigDecimal num;
	private String packageUserid;

	public PrePackageM() {
	}

	public PrePackageM(PrePackageMId id) {
		this.id = id;
	}

	public PrePackageM(PrePackageMId id, Boolean effective, Boolean printed, Date createDat, Boolean packaged,
			Date packageTime, String memoNames, BigDecimal packageWeight, Boolean sended, String memoBarcodes,
			String createUserid, String whId, String unPackageUser, Date unPackageTime, Date sendDat,
			BigDecimal actualWeight, BigDecimal num, String packageUserid) {
		this.id = id;
		this.effective = effective;
		this.printed = printed;
		this.createDat = createDat;
		this.packaged = packaged;
		this.packageTime = packageTime;
		this.memoNames = memoNames;
		this.packageWeight = packageWeight;
		this.sended = sended;
		this.memoBarcodes = memoBarcodes;
		this.createUserid = createUserid;
		this.whId = whId;
		this.unPackageUser = unPackageUser;
		this.unPackageTime = unPackageTime;
		this.sendDat = sendDat;
		this.actualWeight = actualWeight;
		this.num = num;
		this.packageUserid = packageUserid;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "packageBarcode", column = @Column(name = "package_barcode", nullable = false, length = 18)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)) })
	public PrePackageMId getId() {
		return this.id;
	}

	public void setId(PrePackageMId id) {
		this.id = id;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	@Column(name = "printed")
	public Boolean getPrinted() {
		return this.printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dat", length = 35)
	public Date getCreateDat() {
		return this.createDat;
	}

	public void setCreateDat(Date createDat) {
		this.createDat = createDat;
	}

	@Column(name = "packaged")
	public Boolean getPackaged() {
		return this.packaged;
	}

	public void setPackaged(Boolean packaged) {
		this.packaged = packaged;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "package_time", length = 35)
	public Date getPackageTime() {
		return this.packageTime;
	}

	public void setPackageTime(Date packageTime) {
		this.packageTime = packageTime;
	}

	@Column(name = "memo_names", length = 512)
	public String getMemoNames() {
		return this.memoNames;
	}

	public void setMemoNames(String memoNames) {
		this.memoNames = memoNames;
	}

	@Column(name = "package_weight", precision = 131089, scale = 0)
	public BigDecimal getPackageWeight() {
		return this.packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	@Column(name = "sended")
	public Boolean getSended() {
		return this.sended;
	}

	public void setSended(Boolean sended) {
		this.sended = sended;
	}

	@Column(name = "memo_barcodes", length = 512)
	public String getMemoBarcodes() {
		return this.memoBarcodes;
	}

	public void setMemoBarcodes(String memoBarcodes) {
		this.memoBarcodes = memoBarcodes;
	}

	@Column(name = "create_userid", length = 36)
	public String getCreateUserid() {
		return this.createUserid;
	}

	public void setCreateUserid(String createUserid) {
		this.createUserid = createUserid;
	}

	@Column(name = "wh_id", length = 6)
	public String getWhId() {
		return this.whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	@Column(name = "un_package_user", length = 36)
	public String getUnPackageUser() {
		return this.unPackageUser;
	}

	public void setUnPackageUser(String unPackageUser) {
		this.unPackageUser = unPackageUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "un_package_time", length = 35)
	public Date getUnPackageTime() {
		return this.unPackageTime;
	}

	public void setUnPackageTime(Date unPackageTime) {
		this.unPackageTime = unPackageTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "send_dat", length = 35)
	public Date getSendDat() {
		return this.sendDat;
	}

	public void setSendDat(Date sendDat) {
		this.sendDat = sendDat;
	}

	@Column(name = "actual_weight", precision = 131089, scale = 0)
	public BigDecimal getActualWeight() {
		return this.actualWeight;
	}

	public void setActualWeight(BigDecimal actualWeight) {
		this.actualWeight = actualWeight;
	}

	@Column(name = "num", precision = 131089, scale = 0)
	public BigDecimal getNum() {
		return this.num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	@Column(name = "package_userid", length = 36)
	public String getPackageUserid() {
		return this.packageUserid;
	}

	public void setPackageUserid(String packageUserid) {
		this.packageUserid = packageUserid;
	}

}
