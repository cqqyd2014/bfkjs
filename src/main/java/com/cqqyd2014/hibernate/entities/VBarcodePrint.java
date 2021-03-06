package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VBarcodePrint generated by hbm2java
 */
@Entity
@Table(name = "v_barcode_print", schema = "public")
public class VBarcodePrint implements java.io.Serializable {

	private VBarcodePrintId id;

	public VBarcodePrint() {
	}

	public VBarcodePrint(VBarcodePrintId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "goodsBarcode", column = @Column(name = "goods_barcode", length = 22)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", length = 100)),
			@AttributeOverride(name = "CSpec", column = @Column(name = "c_spec", length = 45)),
			@AttributeOverride(name = "url", column = @Column(name = "url")) })
	public VBarcodePrintId getId() {
		return this.id;
	}

	public void setId(VBarcodePrintId id) {
		this.id = id;
	}

}
