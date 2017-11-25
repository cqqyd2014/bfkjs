package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VIntoWhContractD generated by hbm2java
 */
@Entity
@Table(name = "v_into_wh_contract_d", schema = "public")
public class VIntoWhContractD implements java.io.Serializable {

	private VIntoWhContractDId id;

	public VIntoWhContractD() {
	}

	public VIntoWhContractD(VIntoWhContractDId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "intoWhUuid", column = @Column(name = "into_wh_uuid", length = 36)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "contractNo", column = @Column(name = "contract_no", length = 45)),
			@AttributeOverride(name = "goodsId", column = @Column(name = "goods_id", length = 45)),
			@AttributeOverride(name = "price", column = @Column(name = "price", precision = 131089, scale = 0)),
			@AttributeOverride(name = "inDat", column = @Column(name = "in_dat", length = 35)),
			@AttributeOverride(name = "paperDat", column = @Column(name = "paper_dat", length = 35)),
			@AttributeOverride(name = "supplyName", column = @Column(name = "supply_name", length = 45)) })
	public VIntoWhContractDId getId() {
		return this.id;
	}

	public void setId(VIntoWhContractDId id) {
		this.id = id;
	}

}
