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
 * BigEvent generated by hbm2java
 */
@Entity
@Table(name = "big_event", schema = "public")
public class BigEvent implements java.io.Serializable {

	private BigEventId id;
	private Date opDate;
	private String title;
	private String content;
	private Date BDate;
	private String userId;

	public BigEvent() {
	}

	public BigEvent(BigEventId id) {
		this.id = id;
	}

	public BigEvent(BigEventId id, Date opDate, String title, String content, Date BDate, String userId) {
		this.id = id;
		this.opDate = opDate;
		this.title = title;
		this.content = content;
		this.BDate = BDate;
		this.userId = userId;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 36)) })
	public BigEventId getId() {
		return this.id;
	}

	public void setId(BigEventId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "op_date", length = 35)
	public Date getOpDate() {
		return this.opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	@Column(name = "title", length = 512)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "b_date", length = 35)
	public Date getBDate() {
		return this.BDate;
	}

	public void setBDate(Date BDate) {
		this.BDate = BDate;
	}

	@Column(name = "user_id", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
