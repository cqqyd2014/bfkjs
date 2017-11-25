package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ebusiness generated by hbm2java
 */
@Entity
@Table(name = "ebusiness", schema = "public")
public class Ebusiness implements java.io.Serializable {

	private String EId;
	private String EName;

	public Ebusiness() {
	}

	public Ebusiness(String EId) {
		this.EId = EId;
	}

	public Ebusiness(String EId, String EName) {
		this.EId = EId;
		this.EName = EName;
	}

	@Id

	@Column(name = "e_id", unique = true, nullable = false, length = 4)
	public String getEId() {
		return this.EId;
	}

	public void setEId(String EId) {
		this.EId = EId;
	}

	@Column(name = "e_name", length = 45)
	public String getEName() {
		return this.EName;
	}

	public void setEName(String EName) {
		this.EName = EName;
	}

}
