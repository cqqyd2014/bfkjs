package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VPrepackGoodsNum generated by hbm2java
 */
@Entity
@Table(name = "v_prepack_goods_num", schema = "public")
public class VPrepackGoodsNum implements java.io.Serializable {

	private VPrepackGoodsNumId id;

	public VPrepackGoodsNum() {
	}

	public VPrepackGoodsNum(VPrepackGoodsNumId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "PSn", column = @Column(name = "p_sn", length = 18)),
			@AttributeOverride(name = "num", column = @Column(name = "num")) })
	public VPrepackGoodsNumId getId() {
		return this.id;
	}

	public void setId(VPrepackGoodsNumId id) {
		this.id = id;
	}

}
