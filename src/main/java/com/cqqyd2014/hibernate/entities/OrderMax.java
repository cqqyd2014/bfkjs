package com.cqqyd2014.hibernate.entities;
// Generated 2017-10-27 7:29:43 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrderMax generated by hbm2java
 */
@Entity
@Table(name = "order_max", schema = "public")
public class OrderMax implements java.io.Serializable {

	private long maxid;

	public OrderMax() {
	}

	public OrderMax(long maxid) {
		this.maxid = maxid;
	}

	@Id

	@Column(name = "maxid", unique = true, nullable = false)
	public long getMaxid() {
		return this.maxid;
	}

	public void setMaxid(long maxid) {
		this.maxid = maxid;
	}

}
