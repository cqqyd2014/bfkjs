package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VOrderFromUserLen generated by hbm2java
 */
@Entity
@Table(name = "v_order_from_user_len", schema = "public")
public class VOrderFromUserLen implements java.io.Serializable {

	private VOrderFromUserLenId id;

	public VOrderFromUserLen() {
	}

	public VOrderFromUserLen(VOrderFromUserLenId id) {
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
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", length = 36)),
			@AttributeOverride(name = "orderNoLen", column = @Column(name = "order_no_len")) })
	public VOrderFromUserLenId getId() {
		return this.id;
	}

	public void setId(VOrderFromUserLenId id) {
		this.id = id;
	}

}
