package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VUserSending generated by hbm2java
 */
@Entity
@Table(name = "v_user_sending", schema = "public")
public class VUserSending implements java.io.Serializable {

	private VUserSendingId id;

	public VUserSending() {
	}

	public VUserSending(VUserSendingId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "comId", column = @Column(name = "com_id")),
			@AttributeOverride(name = "sendingCount", column = @Column(name = "sending_count")),
			@AttributeOverride(name = "id", column = @Column(name = "id", length = 36)),
			@AttributeOverride(name = "sendWeighting", column = @Column(name = "send_weighting", precision = 131089, scale = 0)) })
	public VUserSendingId getId() {
		return this.id;
	}

	public void setId(VUserSendingId id) {
		this.id = id;
	}

}