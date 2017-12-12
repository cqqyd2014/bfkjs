package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VOrderFromUser generated by hbm2java
 */
@Entity
@Table(name = "v_order_from_user", schema = "public")
public class VOrderFromUser implements java.io.Serializable {

	private VOrderFromUserId id;

	public VOrderFromUser() {
	}

	public VOrderFromUser(VOrderFromUserId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "EName", column = @Column(name = "e_name", length = 45)),
			@AttributeOverride(name = "EId", column = @Column(name = "e_id", length = 4)),
			@AttributeOverride(name = "orderTypeName", column = @Column(name = "order_type_name", length = 45)),
			@AttributeOverride(name = "orderTypeDesc", column = @Column(name = "order_type_desc", length = 45)),
			@AttributeOverride(name = "seq", column = @Column(name = "seq", length = 4)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "orderTypeCode", column = @Column(name = "order_type_code", length = 2)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", length = 36)) })
	public VOrderFromUserId getId() {
		return this.id;
	}

	public void setId(VOrderFromUserId id) {
		this.id = id;
	}

}
