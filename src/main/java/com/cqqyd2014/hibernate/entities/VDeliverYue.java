package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VDeliverYue generated by hbm2java
 */
@Entity
@Table(name = "v_deliver_yue", schema = "public")
public class VDeliverYue implements java.io.Serializable {

	private VDeliverYueId id;

	public VDeliverYue() {
	}

	public VDeliverYue(VDeliverYueId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "orderNum", column = @Column(name = "order_num", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 45)),
			@AttributeOverride(name = "CGoodsId", column = @Column(name = "c_goods_id", length = 45)),
			@AttributeOverride(name = "sendedNum", column = @Column(name = "sended_num")) })
	public VDeliverYueId getId() {
		return this.id;
	}

	public void setId(VDeliverYueId id) {
		this.id = id;
	}

}
