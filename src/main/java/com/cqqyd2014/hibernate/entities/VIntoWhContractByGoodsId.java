package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VIntoWhContractByGoodsId generated by hbm2java
 */
@Entity
@Table(name = "v_into_wh_contract_by_goods_id", schema = "public")
public class VIntoWhContractByGoodsId implements java.io.Serializable {

	private VIntoWhContractByGoodsIdId id;

	public VIntoWhContractByGoodsId() {
	}

	public VIntoWhContractByGoodsId(VIntoWhContractByGoodsIdId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 45)),
			@AttributeOverride(name = "contractNo", column = @Column(name = "contract_no", length = 45)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "buy", column = @Column(name = "buy", precision = 131089, scale = 0)),
			@AttributeOverride(name = "out", column = @Column(name = "out", precision = 131089, scale = 0)),
			@AttributeOverride(name = "yue", column = @Column(name = "yue", precision = 131089, scale = 0)) })
	public VIntoWhContractByGoodsIdId getId() {
		return this.id;
	}

	public void setId(VIntoWhContractByGoodsIdId id) {
		this.id = id;
	}

}