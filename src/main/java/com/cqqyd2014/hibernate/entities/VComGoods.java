package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VComGoods generated by hbm2java
 */
@Entity
@Table(name = "v_com_goods", schema = "public")
public class VComGoods implements java.io.Serializable {

	private VComGoodsId id;

	public VComGoods() {
	}

	public VComGoods(VComGoodsId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)) })
	public VComGoodsId getId() {
		return this.id;
	}

	public void setId(VComGoodsId id) {
		this.id = id;
	}

}
