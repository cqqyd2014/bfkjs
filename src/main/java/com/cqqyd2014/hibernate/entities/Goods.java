package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-2 21:24:22 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Goods generated by hbm2java
 */
@Entity
@Table(name = "goods", schema = "public")
public class Goods implements java.io.Serializable {

	private GoodsId id;
	private Boolean effective;
	private Boolean printed;
	private String currentWh;
	private String intoWhUuid;
	private Date createDat;
	private String currentStorage;
	private String goodsId;
	private BigDecimal contractPrice;
	private Date uneffectiveDat;
	private String maker;
	private String contractId;
	private String uneffectiveUserid;

	public Goods() {
	}

	public Goods(GoodsId id) {
		this.id = id;
	}

	public Goods(GoodsId id, Boolean effective, Boolean printed, String currentWh, String intoWhUuid, Date createDat,
			String currentStorage, String goodsId, BigDecimal contractPrice, Date uneffectiveDat, String maker,
			String contractId, String uneffectiveUserid) {
		this.id = id;
		this.effective = effective;
		this.printed = printed;
		this.currentWh = currentWh;
		this.intoWhUuid = intoWhUuid;
		this.createDat = createDat;
		this.currentStorage = currentStorage;
		this.goodsId = goodsId;
		this.contractPrice = contractPrice;
		this.uneffectiveDat = uneffectiveDat;
		this.maker = maker;
		this.contractId = contractId;
		this.uneffectiveUserid = uneffectiveUserid;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "goodsBarcode", column = @Column(name = "goods_barcode", nullable = false, length = 22)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)) })
	public GoodsId getId() {
		return this.id;
	}

	public void setId(GoodsId id) {
		this.id = id;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	@Column(name = "printed")
	public Boolean getPrinted() {
		return this.printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	@Column(name = "current_wh", length = 6)
	public String getCurrentWh() {
		return this.currentWh;
	}

	public void setCurrentWh(String currentWh) {
		this.currentWh = currentWh;
	}

	@Column(name = "into_wh_uuid", length = 36)
	public String getIntoWhUuid() {
		return this.intoWhUuid;
	}

	public void setIntoWhUuid(String intoWhUuid) {
		this.intoWhUuid = intoWhUuid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dat", length = 35)
	public Date getCreateDat() {
		return this.createDat;
	}

	public void setCreateDat(Date createDat) {
		this.createDat = createDat;
	}

	@Column(name = "current_storage", length = 6)
	public String getCurrentStorage() {
		return this.currentStorage;
	}

	public void setCurrentStorage(String currentStorage) {
		this.currentStorage = currentStorage;
	}

	@Column(name = "goods_id", length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "contract_price", precision = 131089, scale = 0)
	public BigDecimal getContractPrice() {
		return this.contractPrice;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "uneffective_dat", length = 29)
	public Date getUneffectiveDat() {
		return this.uneffectiveDat;
	}

	public void setUneffectiveDat(Date uneffectiveDat) {
		this.uneffectiveDat = uneffectiveDat;
	}

	@Column(name = "maker", length = 36)
	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	@Column(name = "contract_id", length = 45)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "uneffective_userid", length = 36)
	public String getUneffectiveUserid() {
		return this.uneffectiveUserid;
	}

	public void setUneffectiveUserid(String uneffectiveUserid) {
		this.uneffectiveUserid = uneffectiveUserid;
	}

}
