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
 * WxDeclearFlag generated by hbm2java
 */
@Entity
@Table(name = "wx_declear_flag", schema = "public")
public class WxDeclearFlag implements java.io.Serializable {

	private String uuid;
	private Date createTime;
	private String effective;

	public WxDeclearFlag() {
	}

	public WxDeclearFlag(String uuid) {
		this.uuid = uuid;
	}

	public WxDeclearFlag(String uuid, Date createTime, String effective) {
		this.uuid = uuid;
		this.createTime = createTime;
		this.effective = effective;
	}

	@Id

	@Column(name = "uuid", unique = true, nullable = false, length = 45)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 35)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "effective", length = 45)
	public String getEffective() {
		return this.effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

}