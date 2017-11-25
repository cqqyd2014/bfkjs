package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

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
 * SfPostLog generated by hbm2java
 */
@Entity
@Table(name = "sf_post_log", schema = "public")
public class SfPostLog implements java.io.Serializable {

	private SfPostLogId id;
	private String custId;
	private Date opTime;
	private String serviceName;
	private String postText;
	private String errorCode;
	private String errorMessage;

	public SfPostLog() {
	}

	public SfPostLog(SfPostLogId id) {
		this.id = id;
	}

	public SfPostLog(SfPostLogId id, String custId, Date opTime, String serviceName, String postText, String errorCode,
			String errorMessage) {
		this.id = id;
		this.custId = custId;
		this.opTime = opTime;
		this.serviceName = serviceName;
		this.postText = postText;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 36)) })
	public SfPostLogId getId() {
		return this.id;
	}

	public void setId(SfPostLogId id) {
		this.id = id;
	}

	@Column(name = "cust_id", length = 64)
	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "op_time", length = 35)
	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	@Column(name = "service_name", length = 64)
	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Column(name = "post_text", length = 10240)
	public String getPostText() {
		return this.postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	@Column(name = "error_code", length = 512)
	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Column(name = "error_message", length = 512)
	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
