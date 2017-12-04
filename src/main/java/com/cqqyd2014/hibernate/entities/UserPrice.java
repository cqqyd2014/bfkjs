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
 * UserPrice generated by hbm2java
 */
@Entity
@Table(name = "user_price", schema = "public")
public class UserPrice implements java.io.Serializable {

	private UserPriceId id;
	private String userId;
	private String goodsId;
	private BigDecimal userPrice;
	private Date startTime;
	private Date endTime;
	private Boolean effective;

	public UserPrice() {
	}

	public UserPrice(UserPriceId id, String userId, String goodsId, Date startTime, Date endTime) {
		this.id = id;
		this.userId = userId;
		this.goodsId = goodsId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public UserPrice(UserPriceId id, String userId, String goodsId, BigDecimal userPrice, Date startTime, Date endTime,
			Boolean effective) {
		this.id = id;
		this.userId = userId;
		this.goodsId = goodsId;
		this.userPrice = userPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.effective = effective;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 36)) })
	public UserPriceId getId() {
		return this.id;
	}

	public void setId(UserPriceId id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false, length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "goods_id", nullable = false, length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "user_price", precision = 131089, scale = 0)
	public BigDecimal getUserPrice() {
		return this.userPrice;
	}

	public void setUserPrice(BigDecimal userPrice) {
		this.userPrice = userPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", nullable = false, length = 35)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", nullable = false, length = 35)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

}
