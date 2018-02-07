package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * InvertoryAjustDetail generated by hbm2java
 */
@Entity
@Table(name = "invertory_ajust_detail", schema = "public")
public class InvertoryAjustDetail implements java.io.Serializable {

	private InvertoryAjustDetailId id;
	private Integer IAjust;
	private String IMemo;

	public InvertoryAjustDetail() {
	}

	public InvertoryAjustDetail(InvertoryAjustDetailId id) {
		this.id = id;
	}

	public InvertoryAjustDetail(InvertoryAjustDetailId id, Integer IAjust, String IMemo) {
		this.id = id;
		this.IAjust = IAjust;
		this.IMemo = IMemo;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "IUuid", column = @Column(name = "i_uuid", nullable = false, length = 45)),
			@AttributeOverride(name = "IHwCId", column = @Column(name = "i_hw_c_id", nullable = false, length = 45)) })
	public InvertoryAjustDetailId getId() {
		return this.id;
	}

	public void setId(InvertoryAjustDetailId id) {
		this.id = id;
	}

	@Column(name = "i_ajust")
	public Integer getIAjust() {
		return this.IAjust;
	}

	public void setIAjust(Integer IAjust) {
		this.IAjust = IAjust;
	}

	@Column(name = "i_memo", length = 450)
	public String getIMemo() {
		return this.IMemo;
	}

	public void setIMemo(String IMemo) {
		this.IMemo = IMemo;
	}

}
