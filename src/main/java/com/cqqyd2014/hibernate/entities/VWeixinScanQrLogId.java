package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VWeixinScanQrLogId generated by hbm2java
 */
@Embeddable
public class VWeixinScanQrLogId implements java.io.Serializable {

	private String comId;
	private String goodsBarcode;
	private Date scanTime;
	private String ipAddr;
	private Boolean effective;

	public VWeixinScanQrLogId() {
	}

	public VWeixinScanQrLogId(String comId, String goodsBarcode, Date scanTime, String ipAddr, Boolean effective) {
		this.comId = comId;
		this.goodsBarcode = goodsBarcode;
		this.scanTime = scanTime;
		this.ipAddr = ipAddr;
		this.effective = effective;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "goods_barcode", length = 22)
	public String getGoodsBarcode() {
		return this.goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	@Column(name = "scan_time", length = 35)
	public Date getScanTime() {
		return this.scanTime;
	}

	public void setScanTime(Date scanTime) {
		this.scanTime = scanTime;
	}

	@Column(name = "ip_addr", length = 45)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWeixinScanQrLogId))
			return false;
		VWeixinScanQrLogId castOther = (VWeixinScanQrLogId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getGoodsBarcode() == castOther.getGoodsBarcode())
						|| (this.getGoodsBarcode() != null && castOther.getGoodsBarcode() != null
								&& this.getGoodsBarcode().equals(castOther.getGoodsBarcode())))
				&& ((this.getScanTime() == castOther.getScanTime()) || (this.getScanTime() != null
						&& castOther.getScanTime() != null && this.getScanTime().equals(castOther.getScanTime())))
				&& ((this.getIpAddr() == castOther.getIpAddr()) || (this.getIpAddr() != null
						&& castOther.getIpAddr() != null && this.getIpAddr().equals(castOther.getIpAddr())))
				&& ((this.getEffective() == castOther.getEffective()) || (this.getEffective() != null
						&& castOther.getEffective() != null && this.getEffective().equals(castOther.getEffective())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getGoodsBarcode() == null ? 0 : this.getGoodsBarcode().hashCode());
		result = 37 * result + (getScanTime() == null ? 0 : this.getScanTime().hashCode());
		result = 37 * result + (getIpAddr() == null ? 0 : this.getIpAddr().hashCode());
		result = 37 * result + (getEffective() == null ? 0 : this.getEffective().hashCode());
		return result;
	}

}
