package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WeixinScanQrLog generated by hbm2java
 */
@Entity
@Table(name = "weixin_scan_qr_log", schema = "public")
public class WeixinScanQrLog implements java.io.Serializable {

	private WeixinScanQrLogId id;
	private String ipAddr;
	private Boolean effective;

	public WeixinScanQrLog() {
	}

	public WeixinScanQrLog(WeixinScanQrLogId id) {
		this.id = id;
	}

	public WeixinScanQrLog(WeixinScanQrLogId id, String ipAddr, Boolean effective) {
		this.id = id;
		this.ipAddr = ipAddr;
		this.effective = effective;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "goodsBarcode", column = @Column(name = "goods_barcode", nullable = false, length = 22)),
			@AttributeOverride(name = "scanTime", column = @Column(name = "scan_time", nullable = false, length = 35)) })
	public WeixinScanQrLogId getId() {
		return this.id;
	}

	public void setId(WeixinScanQrLogId id) {
		this.id = id;
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

}