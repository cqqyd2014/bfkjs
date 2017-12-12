package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VGoodsId generated by hbm2java
 */
@Embeddable
public class VGoodsId implements java.io.Serializable {

	private String intoWhUuid;
	private Date createDat;
	private String uneffectiveUserid;
	private BigDecimal grossWeight;
	private String maker;
	private String CName;
	private String CSpec;
	private String goodsBarcode;
	private Boolean printed;
	private String goodsId;
	private String comId;
	private String currentWh;
	private String whName;
	private String currentStorage;
	private String storageName;
	private Boolean effective;
	private String origin;
	private String CHs;
	private Boolean notAir;
	private String snCode;
	private String snSpec;
	private String unit;
	private BigDecimal netWeight;
	private BigDecimal packageWeight;
	private Date uneffectiveDat;
	private Date inDat;
	private String contractId;
	private BigDecimal contractPrice;
	private Date paperDat;
	private String supplyName;
	private String supplyId;
	private String CCountryName;

	public VGoodsId() {
	}

	public VGoodsId(String intoWhUuid, Date createDat, String uneffectiveUserid, BigDecimal grossWeight, String maker,
			String CName, String CSpec, String goodsBarcode, Boolean printed, String goodsId, String comId,
			String currentWh, String whName, String currentStorage, String storageName, Boolean effective,
			String origin, String CHs, Boolean notAir, String snCode, String snSpec, String unit, BigDecimal netWeight,
			BigDecimal packageWeight, Date uneffectiveDat, Date inDat, String contractId, BigDecimal contractPrice,
			Date paperDat, String supplyName, String supplyId, String CCountryName) {
		this.intoWhUuid = intoWhUuid;
		this.createDat = createDat;
		this.uneffectiveUserid = uneffectiveUserid;
		this.grossWeight = grossWeight;
		this.maker = maker;
		this.CName = CName;
		this.CSpec = CSpec;
		this.goodsBarcode = goodsBarcode;
		this.printed = printed;
		this.goodsId = goodsId;
		this.comId = comId;
		this.currentWh = currentWh;
		this.whName = whName;
		this.currentStorage = currentStorage;
		this.storageName = storageName;
		this.effective = effective;
		this.origin = origin;
		this.CHs = CHs;
		this.notAir = notAir;
		this.snCode = snCode;
		this.snSpec = snSpec;
		this.unit = unit;
		this.netWeight = netWeight;
		this.packageWeight = packageWeight;
		this.uneffectiveDat = uneffectiveDat;
		this.inDat = inDat;
		this.contractId = contractId;
		this.contractPrice = contractPrice;
		this.paperDat = paperDat;
		this.supplyName = supplyName;
		this.supplyId = supplyId;
		this.CCountryName = CCountryName;
	}

	@Column(name = "into_wh_uuid", length = 36)
	public String getIntoWhUuid() {
		return this.intoWhUuid;
	}

	public void setIntoWhUuid(String intoWhUuid) {
		this.intoWhUuid = intoWhUuid;
	}

	@Column(name = "create_dat", length = 35)
	public Date getCreateDat() {
		return this.createDat;
	}

	public void setCreateDat(Date createDat) {
		this.createDat = createDat;
	}

	@Column(name = "uneffective_userid", length = 36)
	public String getUneffectiveUserid() {
		return this.uneffectiveUserid;
	}

	public void setUneffectiveUserid(String uneffectiveUserid) {
		this.uneffectiveUserid = uneffectiveUserid;
	}

	@Column(name = "gross_weight", precision = 131089, scale = 0)
	public BigDecimal getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "maker", length = 36)
	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	@Column(name = "c_name", length = 100)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "c_spec", length = 45)
	public String getCSpec() {
		return this.CSpec;
	}

	public void setCSpec(String CSpec) {
		this.CSpec = CSpec;
	}

	@Column(name = "goods_barcode", length = 22)
	public String getGoodsBarcode() {
		return this.goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	@Column(name = "printed")
	public Boolean getPrinted() {
		return this.printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	@Column(name = "goods_id")
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "current_wh", length = 6)
	public String getCurrentWh() {
		return this.currentWh;
	}

	public void setCurrentWh(String currentWh) {
		this.currentWh = currentWh;
	}

	@Column(name = "wh_name", length = 45)
	public String getWhName() {
		return this.whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	@Column(name = "current_storage", length = 6)
	public String getCurrentStorage() {
		return this.currentStorage;
	}

	public void setCurrentStorage(String currentStorage) {
		this.currentStorage = currentStorage;
	}

	@Column(name = "storage_name", length = 90)
	public String getStorageName() {
		return this.storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	@Column(name = "origin", length = 45)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "c_hs", length = 45)
	public String getCHs() {
		return this.CHs;
	}

	public void setCHs(String CHs) {
		this.CHs = CHs;
	}

	@Column(name = "not_air")
	public Boolean getNotAir() {
		return this.notAir;
	}

	public void setNotAir(Boolean notAir) {
		this.notAir = notAir;
	}

	@Column(name = "sn_code", length = 4)
	public String getSnCode() {
		return this.snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	@Column(name = "sn_spec", length = 45)
	public String getSnSpec() {
		return this.snSpec;
	}

	public void setSnSpec(String snSpec) {
		this.snSpec = snSpec;
	}

	@Column(name = "unit", length = 90)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "net_weight", precision = 131089, scale = 0)
	public BigDecimal getNetWeight() {
		return this.netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	@Column(name = "package_weight", precision = 131089, scale = 0)
	public BigDecimal getPackageWeight() {
		return this.packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	@Column(name = "uneffective_dat", length = 35)
	public Date getUneffectiveDat() {
		return this.uneffectiveDat;
	}

	public void setUneffectiveDat(Date uneffectiveDat) {
		this.uneffectiveDat = uneffectiveDat;
	}

	@Column(name = "in_dat", length = 35)
	public Date getInDat() {
		return this.inDat;
	}

	public void setInDat(Date inDat) {
		this.inDat = inDat;
	}

	@Column(name = "contract_id")
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "contract_price", precision = 131089, scale = 0)
	public BigDecimal getContractPrice() {
		return this.contractPrice;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "paper_dat", length = 35)
	public Date getPaperDat() {
		return this.paperDat;
	}

	public void setPaperDat(Date paperDat) {
		this.paperDat = paperDat;
	}

	@Column(name = "supply_name")
	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "supply_id")
	public String getSupplyId() {
		return this.supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	@Column(name = "c_country_name", length = 90)
	public String getCCountryName() {
		return this.CCountryName;
	}

	public void setCCountryName(String CCountryName) {
		this.CCountryName = CCountryName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VGoodsId))
			return false;
		VGoodsId castOther = (VGoodsId) other;

		return ((this.getIntoWhUuid() == castOther.getIntoWhUuid()) || (this.getIntoWhUuid() != null
				&& castOther.getIntoWhUuid() != null && this.getIntoWhUuid().equals(castOther.getIntoWhUuid())))
				&& ((this.getCreateDat() == castOther.getCreateDat()) || (this.getCreateDat() != null
						&& castOther.getCreateDat() != null && this.getCreateDat().equals(castOther.getCreateDat())))
				&& ((this.getUneffectiveUserid() == castOther.getUneffectiveUserid())
						|| (this.getUneffectiveUserid() != null && castOther.getUneffectiveUserid() != null
								&& this.getUneffectiveUserid().equals(castOther.getUneffectiveUserid())))
				&& ((this.getGrossWeight() == castOther.getGrossWeight())
						|| (this.getGrossWeight() != null && castOther.getGrossWeight() != null
								&& this.getGrossWeight().equals(castOther.getGrossWeight())))
				&& ((this.getMaker() == castOther.getMaker()) || (this.getMaker() != null
						&& castOther.getMaker() != null && this.getMaker().equals(castOther.getMaker())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())))
				&& ((this.getCSpec() == castOther.getCSpec()) || (this.getCSpec() != null
						&& castOther.getCSpec() != null && this.getCSpec().equals(castOther.getCSpec())))
				&& ((this.getGoodsBarcode() == castOther.getGoodsBarcode())
						|| (this.getGoodsBarcode() != null && castOther.getGoodsBarcode() != null
								&& this.getGoodsBarcode().equals(castOther.getGoodsBarcode())))
				&& ((this.getPrinted() == castOther.getPrinted()) || (this.getPrinted() != null
						&& castOther.getPrinted() != null && this.getPrinted().equals(castOther.getPrinted())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getCurrentWh() == castOther.getCurrentWh()) || (this.getCurrentWh() != null
						&& castOther.getCurrentWh() != null && this.getCurrentWh().equals(castOther.getCurrentWh())))
				&& ((this.getWhName() == castOther.getWhName()) || (this.getWhName() != null
						&& castOther.getWhName() != null && this.getWhName().equals(castOther.getWhName())))
				&& ((this.getCurrentStorage() == castOther.getCurrentStorage())
						|| (this.getCurrentStorage() != null && castOther.getCurrentStorage() != null
								&& this.getCurrentStorage().equals(castOther.getCurrentStorage())))
				&& ((this.getStorageName() == castOther.getStorageName())
						|| (this.getStorageName() != null && castOther.getStorageName() != null
								&& this.getStorageName().equals(castOther.getStorageName())))
				&& ((this.getEffective() == castOther.getEffective()) || (this.getEffective() != null
						&& castOther.getEffective() != null && this.getEffective().equals(castOther.getEffective())))
				&& ((this.getOrigin() == castOther.getOrigin()) || (this.getOrigin() != null
						&& castOther.getOrigin() != null && this.getOrigin().equals(castOther.getOrigin())))
				&& ((this.getCHs() == castOther.getCHs()) || (this.getCHs() != null && castOther.getCHs() != null
						&& this.getCHs().equals(castOther.getCHs())))
				&& ((this.getNotAir() == castOther.getNotAir()) || (this.getNotAir() != null
						&& castOther.getNotAir() != null && this.getNotAir().equals(castOther.getNotAir())))
				&& ((this.getSnCode() == castOther.getSnCode()) || (this.getSnCode() != null
						&& castOther.getSnCode() != null && this.getSnCode().equals(castOther.getSnCode())))
				&& ((this.getSnSpec() == castOther.getSnSpec()) || (this.getSnSpec() != null
						&& castOther.getSnSpec() != null && this.getSnSpec().equals(castOther.getSnSpec())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())))
				&& ((this.getNetWeight() == castOther.getNetWeight()) || (this.getNetWeight() != null
						&& castOther.getNetWeight() != null && this.getNetWeight().equals(castOther.getNetWeight())))
				&& ((this.getPackageWeight() == castOther.getPackageWeight())
						|| (this.getPackageWeight() != null && castOther.getPackageWeight() != null
								&& this.getPackageWeight().equals(castOther.getPackageWeight())))
				&& ((this.getUneffectiveDat() == castOther.getUneffectiveDat())
						|| (this.getUneffectiveDat() != null && castOther.getUneffectiveDat() != null
								&& this.getUneffectiveDat().equals(castOther.getUneffectiveDat())))
				&& ((this.getInDat() == castOther.getInDat()) || (this.getInDat() != null
						&& castOther.getInDat() != null && this.getInDat().equals(castOther.getInDat())))
				&& ((this.getContractId() == castOther.getContractId()) || (this.getContractId() != null
						&& castOther.getContractId() != null && this.getContractId().equals(castOther.getContractId())))
				&& ((this.getContractPrice() == castOther.getContractPrice())
						|| (this.getContractPrice() != null && castOther.getContractPrice() != null
								&& this.getContractPrice().equals(castOther.getContractPrice())))
				&& ((this.getPaperDat() == castOther.getPaperDat()) || (this.getPaperDat() != null
						&& castOther.getPaperDat() != null && this.getPaperDat().equals(castOther.getPaperDat())))
				&& ((this.getSupplyName() == castOther.getSupplyName()) || (this.getSupplyName() != null
						&& castOther.getSupplyName() != null && this.getSupplyName().equals(castOther.getSupplyName())))
				&& ((this.getSupplyId() == castOther.getSupplyId()) || (this.getSupplyId() != null
						&& castOther.getSupplyId() != null && this.getSupplyId().equals(castOther.getSupplyId())))
				&& ((this.getCCountryName() == castOther.getCCountryName())
						|| (this.getCCountryName() != null && castOther.getCCountryName() != null
								&& this.getCCountryName().equals(castOther.getCCountryName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIntoWhUuid() == null ? 0 : this.getIntoWhUuid().hashCode());
		result = 37 * result + (getCreateDat() == null ? 0 : this.getCreateDat().hashCode());
		result = 37 * result + (getUneffectiveUserid() == null ? 0 : this.getUneffectiveUserid().hashCode());
		result = 37 * result + (getGrossWeight() == null ? 0 : this.getGrossWeight().hashCode());
		result = 37 * result + (getMaker() == null ? 0 : this.getMaker().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		result = 37 * result + (getCSpec() == null ? 0 : this.getCSpec().hashCode());
		result = 37 * result + (getGoodsBarcode() == null ? 0 : this.getGoodsBarcode().hashCode());
		result = 37 * result + (getPrinted() == null ? 0 : this.getPrinted().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getCurrentWh() == null ? 0 : this.getCurrentWh().hashCode());
		result = 37 * result + (getWhName() == null ? 0 : this.getWhName().hashCode());
		result = 37 * result + (getCurrentStorage() == null ? 0 : this.getCurrentStorage().hashCode());
		result = 37 * result + (getStorageName() == null ? 0 : this.getStorageName().hashCode());
		result = 37 * result + (getEffective() == null ? 0 : this.getEffective().hashCode());
		result = 37 * result + (getOrigin() == null ? 0 : this.getOrigin().hashCode());
		result = 37 * result + (getCHs() == null ? 0 : this.getCHs().hashCode());
		result = 37 * result + (getNotAir() == null ? 0 : this.getNotAir().hashCode());
		result = 37 * result + (getSnCode() == null ? 0 : this.getSnCode().hashCode());
		result = 37 * result + (getSnSpec() == null ? 0 : this.getSnSpec().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result + (getNetWeight() == null ? 0 : this.getNetWeight().hashCode());
		result = 37 * result + (getPackageWeight() == null ? 0 : this.getPackageWeight().hashCode());
		result = 37 * result + (getUneffectiveDat() == null ? 0 : this.getUneffectiveDat().hashCode());
		result = 37 * result + (getInDat() == null ? 0 : this.getInDat().hashCode());
		result = 37 * result + (getContractId() == null ? 0 : this.getContractId().hashCode());
		result = 37 * result + (getContractPrice() == null ? 0 : this.getContractPrice().hashCode());
		result = 37 * result + (getPaperDat() == null ? 0 : this.getPaperDat().hashCode());
		result = 37 * result + (getSupplyName() == null ? 0 : this.getSupplyName().hashCode());
		result = 37 * result + (getSupplyId() == null ? 0 : this.getSupplyId().hashCode());
		result = 37 * result + (getCCountryName() == null ? 0 : this.getCCountryName().hashCode());
		return result;
	}

}