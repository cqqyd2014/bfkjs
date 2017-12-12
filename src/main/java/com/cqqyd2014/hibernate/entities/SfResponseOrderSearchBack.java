package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

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
 * SfResponseOrderSearchBack generated by hbm2java
 */
@Entity
@Table(name = "sf_response_order_search_back", schema = "public")
public class SfResponseOrderSearchBack implements java.io.Serializable {

	private SfResponseOrderSearchBackId id;
	private String custId;
	private Date opTime;
	private String orderid;
	private String mailno;
	private String origincode;
	private String filterResult;
	private String destcode;

	public SfResponseOrderSearchBack() {
	}

	public SfResponseOrderSearchBack(SfResponseOrderSearchBackId id) {
		this.id = id;
	}

	public SfResponseOrderSearchBack(SfResponseOrderSearchBackId id, String custId, Date opTime, String orderid,
			String mailno, String origincode, String filterResult, String destcode) {
		this.id = id;
		this.custId = custId;
		this.opTime = opTime;
		this.orderid = orderid;
		this.mailno = mailno;
		this.origincode = origincode;
		this.filterResult = filterResult;
		this.destcode = destcode;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 36)) })
	public SfResponseOrderSearchBackId getId() {
		return this.id;
	}

	public void setId(SfResponseOrderSearchBackId id) {
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

	@Column(name = "origincode", length = 32)
	public String getOrigincode() {
		return this.origincode;
	}

	public void setOrigincode(String origincode) {
		this.origincode = origincode;
	}

	@Column(name = "filter_result", length = 32)
	public String getFilterResult() {
		return this.filterResult;
	}

	public void setFilterResult(String filterResult) {
		this.filterResult = filterResult;
	}

	@Column(name = "destcode", length = 32)
	public String getDestcode() {
		return this.destcode;
	}

	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}

}
