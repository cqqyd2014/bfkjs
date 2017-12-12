package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VSnCodeFlag generated by hbm2java
 */
@Entity
@Table(name = "v_sn_code_flag", schema = "public")
public class VSnCodeFlag implements java.io.Serializable {

	private VSnCodeFlagId id;

	public VSnCodeFlag() {
	}

	public VSnCodeFlag(VSnCodeFlagId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "snCode", column = @Column(name = "sn_code", precision = 131089, scale = 0)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)) })
	public VSnCodeFlagId getId() {
		return this.id;
	}

	public void setId(VSnCodeFlagId id) {
		this.id = id;
	}

}
