package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * InventoryAjust generated by hbm2java
 */
@Entity
@Table(name = "inventory_ajust", schema = "public")
public class InventoryAjust implements java.io.Serializable {

	private String uuid;
	private String IType;
	private Date ITime;
	private String IMemo;

	public InventoryAjust() {
	}

	public InventoryAjust(String uuid) {
		this.uuid = uuid;
	}

	public InventoryAjust(String uuid, String IType, Date ITime, String IMemo) {
		this.uuid = uuid;
		this.IType = IType;
		this.ITime = ITime;
		this.IMemo = IMemo;
	}

	@Id

	@Column(name = "uuid", unique = true, nullable = false, length = 45)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "i_type", length = 45)
	public String getIType() {
		return this.IType;
	}

	public void setIType(String IType) {
		this.IType = IType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "i_time", length = 35)
	public Date getITime() {
		return this.ITime;
	}

	public void setITime(Date ITime) {
		this.ITime = ITime;
	}

	@Column(name = "i_memo")
	public String getIMemo() {
		return this.IMemo;
	}

	public void setIMemo(String IMemo) {
		this.IMemo = IMemo;
	}

}