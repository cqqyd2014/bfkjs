package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VDeliverDRows generated by hbm2java
 */
@Entity
@Table(name = "v_deliver_d_rows", schema = "public")
public class VDeliverDRows implements java.io.Serializable {

	private VDeliverDRowsId id;

	public VDeliverDRows() {
	}

	public VDeliverDRows(VDeliverDRowsId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "seq", column = @Column(name = "seq", length = 4)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 20)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "sns", column = @Column(name = "sns")),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)) })
	public VDeliverDRowsId getId() {
		return this.id;
	}

	public void setId(VDeliverDRowsId id) {
		this.id = id;
	}

}
