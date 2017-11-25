package com.cqqyd2014.hibernate.entities;
// Generated 2017-10-27 7:29:43 by Hibernate Tools 5.2.5.Final

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
 * SfResponseOrderConfirmBack generated by hbm2java
 */
@Entity
@Table(name = "sf_response_order_confirm_back", schema = "public")
public class SfResponseOrderConfirmBack implements java.io.Serializable {

	private SfResponseOrderConfirmBackId id;
	private String custId;
	private Date opTime;
	private String orderid;
	private String mailno;
	private String resStatus;

	public SfResponseOrderConfirmBack() {
	}

	public SfResponseOrderConfirmBack(SfResponseOrderConfirmBackId id) {
		this.id = id;
	}

	public SfResponseOrderConfirmBack(SfResponseOrderConfirmBackId id, String custId, Date opTime, String orderid,
			String mailno, String resStatus) {
		this.id = id;
		this.custId = custId;
		this.opTime = opTime;
		this.orderid = orderid;
		this.mailno = mailno;
		this.resStatus = resStatus;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 36)) })
	public SfResponseOrderConfirmBackId getId() {
		return this.id;
	}

	public void setId(SfResponseOrderConfirmBackId id) {
		this.id = id;
	}

	@Column(name = "cust_id", length = 32)
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

	@Column(name = "orderid", length = 64)
	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Column(name = "mailno", length = 64)
	public String getMailno() {
		return this.mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}

	@Column(name = "res_status", length = 32)
	public String getResStatus() {
		return this.resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

}
