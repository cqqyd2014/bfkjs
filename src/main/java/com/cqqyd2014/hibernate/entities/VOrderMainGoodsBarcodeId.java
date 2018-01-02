package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VOrderMainGoodsBarcodeId generated by hbm2java
 */
@Embeddable
public class VOrderMainGoodsBarcodeId implements java.io.Serializable {

	private String sysUserName;
	private String orderVehicleId;
	private String orderVehicleVechicleName;
	private String orderExpressCom;
	private String orderExpressComName;
	private String userId;
	private String deliverWhId;
	private String deliverWhName;
	private String detailMemo;
	private String comId;
	private String orderNo;
	private String CUserName;
	private String addrProvince;
	private String addrCity;
	private String addrDistrict;
	private Date orderDat;
	private String CStatus;
	private String gtStatus;
	private String emsStatus;
	private String orderTypeName;
	private String orderTypeFullName;
	private String packageUser;
	private String originalId;
	private String deliverNo;
	private String deliverBillStatus;
	private String goodsBarcode;
	private String CTell;
	private String goodsId;
	private String deliverExpressNo;
	private String deliverExpressComName;
	private String deliverExpressCom;
	private String deliverExpressVehicle;
	private String CName;

	public VOrderMainGoodsBarcodeId() {
	}

	public VOrderMainGoodsBarcodeId(String sysUserName, String orderVehicleId, String orderVehicleVechicleName,
			String orderExpressCom, String orderExpressComName, String userId, String deliverWhId, String deliverWhName,
			String detailMemo, String comId, String orderNo, String CUserName, String addrProvince, String addrCity,
			String addrDistrict, Date orderDat, String CStatus, String gtStatus, String emsStatus, String orderTypeName,
			String orderTypeFullName, String packageUser, String originalId, String deliverNo, String deliverBillStatus,
			String goodsBarcode, String CTell, String goodsId, String deliverExpressNo, String deliverExpressComName,
			String deliverExpressCom, String deliverExpressVehicle, String CName) {
		this.sysUserName = sysUserName;
		this.orderVehicleId = orderVehicleId;
		this.orderVehicleVechicleName = orderVehicleVechicleName;
		this.orderExpressCom = orderExpressCom;
		this.orderExpressComName = orderExpressComName;
		this.userId = userId;
		this.deliverWhId = deliverWhId;
		this.deliverWhName = deliverWhName;
		this.detailMemo = detailMemo;
		this.comId = comId;
		this.orderNo = orderNo;
		this.CUserName = CUserName;
		this.addrProvince = addrProvince;
		this.addrCity = addrCity;
		this.addrDistrict = addrDistrict;
		this.orderDat = orderDat;
		this.CStatus = CStatus;
		this.gtStatus = gtStatus;
		this.emsStatus = emsStatus;
		this.orderTypeName = orderTypeName;
		this.orderTypeFullName = orderTypeFullName;
		this.packageUser = packageUser;
		this.originalId = originalId;
		this.deliverNo = deliverNo;
		this.deliverBillStatus = deliverBillStatus;
		this.goodsBarcode = goodsBarcode;
		this.CTell = CTell;
		this.goodsId = goodsId;
		this.deliverExpressNo = deliverExpressNo;
		this.deliverExpressComName = deliverExpressComName;
		this.deliverExpressCom = deliverExpressCom;
		this.deliverExpressVehicle = deliverExpressVehicle;
		this.CName = CName;
	}

	@Column(name = "sys_user_name", length = 45)
	public String getSysUserName() {
		return this.sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	@Column(name = "order_vehicle_id", length = 4)
	public String getOrderVehicleId() {
		return this.orderVehicleId;
	}

	public void setOrderVehicleId(String orderVehicleId) {
		this.orderVehicleId = orderVehicleId;
	}

	@Column(name = "order_vehicle_vechicle_name", length = 64)
	public String getOrderVehicleVechicleName() {
		return this.orderVehicleVechicleName;
	}

	public void setOrderVehicleVechicleName(String orderVehicleVechicleName) {
		this.orderVehicleVechicleName = orderVehicleVechicleName;
	}

	@Column(name = "order_express_com", length = 45)
	public String getOrderExpressCom() {
		return this.orderExpressCom;
	}

	public void setOrderExpressCom(String orderExpressCom) {
		this.orderExpressCom = orderExpressCom;
	}

	@Column(name = "order_express_com_name", length = 45)
	public String getOrderExpressComName() {
		return this.orderExpressComName;
	}

	public void setOrderExpressComName(String orderExpressComName) {
		this.orderExpressComName = orderExpressComName;
	}

	@Column(name = "user_id", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "deliver_wh_id")
	public String getDeliverWhId() {
		return this.deliverWhId;
	}

	public void setDeliverWhId(String deliverWhId) {
		this.deliverWhId = deliverWhId;
	}

	@Column(name = "deliver_wh_name")
	public String getDeliverWhName() {
		return this.deliverWhName;
	}

	public void setDeliverWhName(String deliverWhName) {
		this.deliverWhName = deliverWhName;
	}

	@Column(name = "detail_memo")
	public String getDetailMemo() {
		return this.detailMemo;
	}

	public void setDetailMemo(String detailMemo) {
		this.detailMemo = detailMemo;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "order_no", length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "c_user_name", length = 45)
	public String getCUserName() {
		return this.CUserName;
	}

	public void setCUserName(String CUserName) {
		this.CUserName = CUserName;
	}

	@Column(name = "addr_province", length = 45)
	public String getAddrProvince() {
		return this.addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	@Column(name = "addr_city", length = 45)
	public String getAddrCity() {
		return this.addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	@Column(name = "addr_district", length = 45)
	public String getAddrDistrict() {
		return this.addrDistrict;
	}

	public void setAddrDistrict(String addrDistrict) {
		this.addrDistrict = addrDistrict;
	}

	@Column(name = "order_dat", length = 35)
	public Date getOrderDat() {
		return this.orderDat;
	}

	public void setOrderDat(Date orderDat) {
		this.orderDat = orderDat;
	}

	@Column(name = "c_status", length = 4)
	public String getCStatus() {
		return this.CStatus;
	}

	public void setCStatus(String CStatus) {
		this.CStatus = CStatus;
	}

	@Column(name = "gt_status", length = 4)
	public String getGtStatus() {
		return this.gtStatus;
	}

	public void setGtStatus(String gtStatus) {
		this.gtStatus = gtStatus;
	}

	@Column(name = "ems_status", length = 4)
	public String getEmsStatus() {
		return this.emsStatus;
	}

	public void setEmsStatus(String emsStatus) {
		this.emsStatus = emsStatus;
	}

	@Column(name = "order_type_name", length = 4)
	public String getOrderTypeName() {
		return this.orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	@Column(name = "order_type_full_name", length = 45)
	public String getOrderTypeFullName() {
		return this.orderTypeFullName;
	}

	public void setOrderTypeFullName(String orderTypeFullName) {
		this.orderTypeFullName = orderTypeFullName;
	}

	@Column(name = "package_user")
	public String getPackageUser() {
		return this.packageUser;
	}

	public void setPackageUser(String packageUser) {
		this.packageUser = packageUser;
	}

	@Column(name = "original_id", length = 45)
	public String getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	@Column(name = "deliver_no")
	public String getDeliverNo() {
		return this.deliverNo;
	}

	public void setDeliverNo(String deliverNo) {
		this.deliverNo = deliverNo;
	}

	@Column(name = "deliver_bill_status")
	public String getDeliverBillStatus() {
		return this.deliverBillStatus;
	}

	public void setDeliverBillStatus(String deliverBillStatus) {
		this.deliverBillStatus = deliverBillStatus;
	}

	@Column(name = "goods_barcode")
	public String getGoodsBarcode() {
		return this.goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	@Column(name = "c_tell", length = 45)
	public String getCTell() {
		return this.CTell;
	}

	public void setCTell(String CTell) {
		this.CTell = CTell;
	}

	@Column(name = "goods_id")
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "deliver_express_no")
	public String getDeliverExpressNo() {
		return this.deliverExpressNo;
	}

	public void setDeliverExpressNo(String deliverExpressNo) {
		this.deliverExpressNo = deliverExpressNo;
	}

	@Column(name = "deliver_express_com_name")
	public String getDeliverExpressComName() {
		return this.deliverExpressComName;
	}

	public void setDeliverExpressComName(String deliverExpressComName) {
		this.deliverExpressComName = deliverExpressComName;
	}

	@Column(name = "deliver_express_com")
	public String getDeliverExpressCom() {
		return this.deliverExpressCom;
	}

	public void setDeliverExpressCom(String deliverExpressCom) {
		this.deliverExpressCom = deliverExpressCom;
	}

	@Column(name = "deliver_express_vehicle")
	public String getDeliverExpressVehicle() {
		return this.deliverExpressVehicle;
	}

	public void setDeliverExpressVehicle(String deliverExpressVehicle) {
		this.deliverExpressVehicle = deliverExpressVehicle;
	}

	@Column(name = "c_name")
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VOrderMainGoodsBarcodeId))
			return false;
		VOrderMainGoodsBarcodeId castOther = (VOrderMainGoodsBarcodeId) other;

		return ((this.getSysUserName() == castOther.getSysUserName()) || (this.getSysUserName() != null
				&& castOther.getSysUserName() != null && this.getSysUserName().equals(castOther.getSysUserName())))
				&& ((this.getOrderVehicleId() == castOther.getOrderVehicleId())
						|| (this.getOrderVehicleId() != null && castOther.getOrderVehicleId() != null
								&& this.getOrderVehicleId().equals(castOther.getOrderVehicleId())))
				&& ((this.getOrderVehicleVechicleName() == castOther.getOrderVehicleVechicleName())
						|| (this.getOrderVehicleVechicleName() != null
								&& castOther.getOrderVehicleVechicleName() != null
								&& this.getOrderVehicleVechicleName().equals(castOther.getOrderVehicleVechicleName())))
				&& ((this.getOrderExpressCom() == castOther.getOrderExpressCom())
						|| (this.getOrderExpressCom() != null && castOther.getOrderExpressCom() != null
								&& this.getOrderExpressCom().equals(castOther.getOrderExpressCom())))
				&& ((this.getOrderExpressComName() == castOther.getOrderExpressComName())
						|| (this.getOrderExpressComName() != null && castOther.getOrderExpressComName() != null
								&& this.getOrderExpressComName().equals(castOther.getOrderExpressComName())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getDeliverWhId() == castOther.getDeliverWhId())
						|| (this.getDeliverWhId() != null && castOther.getDeliverWhId() != null
								&& this.getDeliverWhId().equals(castOther.getDeliverWhId())))
				&& ((this.getDeliverWhName() == castOther.getDeliverWhName())
						|| (this.getDeliverWhName() != null && castOther.getDeliverWhName() != null
								&& this.getDeliverWhName().equals(castOther.getDeliverWhName())))
				&& ((this.getDetailMemo() == castOther.getDetailMemo()) || (this.getDetailMemo() != null
						&& castOther.getDetailMemo() != null && this.getDetailMemo().equals(castOther.getDetailMemo())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getCUserName() == castOther.getCUserName()) || (this.getCUserName() != null
						&& castOther.getCUserName() != null && this.getCUserName().equals(castOther.getCUserName())))
				&& ((this.getAddrProvince() == castOther.getAddrProvince())
						|| (this.getAddrProvince() != null && castOther.getAddrProvince() != null
								&& this.getAddrProvince().equals(castOther.getAddrProvince())))
				&& ((this.getAddrCity() == castOther.getAddrCity()) || (this.getAddrCity() != null
						&& castOther.getAddrCity() != null && this.getAddrCity().equals(castOther.getAddrCity())))
				&& ((this.getAddrDistrict() == castOther.getAddrDistrict())
						|| (this.getAddrDistrict() != null && castOther.getAddrDistrict() != null
								&& this.getAddrDistrict().equals(castOther.getAddrDistrict())))
				&& ((this.getOrderDat() == castOther.getOrderDat()) || (this.getOrderDat() != null
						&& castOther.getOrderDat() != null && this.getOrderDat().equals(castOther.getOrderDat())))
				&& ((this.getCStatus() == castOther.getCStatus()) || (this.getCStatus() != null
						&& castOther.getCStatus() != null && this.getCStatus().equals(castOther.getCStatus())))
				&& ((this.getGtStatus() == castOther.getGtStatus()) || (this.getGtStatus() != null
						&& castOther.getGtStatus() != null && this.getGtStatus().equals(castOther.getGtStatus())))
				&& ((this.getEmsStatus() == castOther.getEmsStatus()) || (this.getEmsStatus() != null
						&& castOther.getEmsStatus() != null && this.getEmsStatus().equals(castOther.getEmsStatus())))
				&& ((this.getOrderTypeName() == castOther.getOrderTypeName())
						|| (this.getOrderTypeName() != null && castOther.getOrderTypeName() != null
								&& this.getOrderTypeName().equals(castOther.getOrderTypeName())))
				&& ((this.getOrderTypeFullName() == castOther.getOrderTypeFullName())
						|| (this.getOrderTypeFullName() != null && castOther.getOrderTypeFullName() != null
								&& this.getOrderTypeFullName().equals(castOther.getOrderTypeFullName())))
				&& ((this.getPackageUser() == castOther.getPackageUser())
						|| (this.getPackageUser() != null && castOther.getPackageUser() != null
								&& this.getPackageUser().equals(castOther.getPackageUser())))
				&& ((this.getOriginalId() == castOther.getOriginalId()) || (this.getOriginalId() != null
						&& castOther.getOriginalId() != null && this.getOriginalId().equals(castOther.getOriginalId())))
				&& ((this.getDeliverNo() == castOther.getDeliverNo()) || (this.getDeliverNo() != null
						&& castOther.getDeliverNo() != null && this.getDeliverNo().equals(castOther.getDeliverNo())))
				&& ((this.getDeliverBillStatus() == castOther.getDeliverBillStatus())
						|| (this.getDeliverBillStatus() != null && castOther.getDeliverBillStatus() != null
								&& this.getDeliverBillStatus().equals(castOther.getDeliverBillStatus())))
				&& ((this.getGoodsBarcode() == castOther.getGoodsBarcode())
						|| (this.getGoodsBarcode() != null && castOther.getGoodsBarcode() != null
								&& this.getGoodsBarcode().equals(castOther.getGoodsBarcode())))
				&& ((this.getCTell() == castOther.getCTell()) || (this.getCTell() != null
						&& castOther.getCTell() != null && this.getCTell().equals(castOther.getCTell())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getDeliverExpressNo() == castOther.getDeliverExpressNo())
						|| (this.getDeliverExpressNo() != null && castOther.getDeliverExpressNo() != null
								&& this.getDeliverExpressNo().equals(castOther.getDeliverExpressNo())))
				&& ((this.getDeliverExpressComName() == castOther.getDeliverExpressComName())
						|| (this.getDeliverExpressComName() != null && castOther.getDeliverExpressComName() != null
								&& this.getDeliverExpressComName().equals(castOther.getDeliverExpressComName())))
				&& ((this.getDeliverExpressCom() == castOther.getDeliverExpressCom())
						|| (this.getDeliverExpressCom() != null && castOther.getDeliverExpressCom() != null
								&& this.getDeliverExpressCom().equals(castOther.getDeliverExpressCom())))
				&& ((this.getDeliverExpressVehicle() == castOther.getDeliverExpressVehicle())
						|| (this.getDeliverExpressVehicle() != null && castOther.getDeliverExpressVehicle() != null
								&& this.getDeliverExpressVehicle().equals(castOther.getDeliverExpressVehicle())))
				&& ((this.getCName() == castOther.getCName()) || (this.getCName() != null
						&& castOther.getCName() != null && this.getCName().equals(castOther.getCName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSysUserName() == null ? 0 : this.getSysUserName().hashCode());
		result = 37 * result + (getOrderVehicleId() == null ? 0 : this.getOrderVehicleId().hashCode());
		result = 37 * result
				+ (getOrderVehicleVechicleName() == null ? 0 : this.getOrderVehicleVechicleName().hashCode());
		result = 37 * result + (getOrderExpressCom() == null ? 0 : this.getOrderExpressCom().hashCode());
		result = 37 * result + (getOrderExpressComName() == null ? 0 : this.getOrderExpressComName().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getDeliverWhId() == null ? 0 : this.getDeliverWhId().hashCode());
		result = 37 * result + (getDeliverWhName() == null ? 0 : this.getDeliverWhName().hashCode());
		result = 37 * result + (getDetailMemo() == null ? 0 : this.getDetailMemo().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getCUserName() == null ? 0 : this.getCUserName().hashCode());
		result = 37 * result + (getAddrProvince() == null ? 0 : this.getAddrProvince().hashCode());
		result = 37 * result + (getAddrCity() == null ? 0 : this.getAddrCity().hashCode());
		result = 37 * result + (getAddrDistrict() == null ? 0 : this.getAddrDistrict().hashCode());
		result = 37 * result + (getOrderDat() == null ? 0 : this.getOrderDat().hashCode());
		result = 37 * result + (getCStatus() == null ? 0 : this.getCStatus().hashCode());
		result = 37 * result + (getGtStatus() == null ? 0 : this.getGtStatus().hashCode());
		result = 37 * result + (getEmsStatus() == null ? 0 : this.getEmsStatus().hashCode());
		result = 37 * result + (getOrderTypeName() == null ? 0 : this.getOrderTypeName().hashCode());
		result = 37 * result + (getOrderTypeFullName() == null ? 0 : this.getOrderTypeFullName().hashCode());
		result = 37 * result + (getPackageUser() == null ? 0 : this.getPackageUser().hashCode());
		result = 37 * result + (getOriginalId() == null ? 0 : this.getOriginalId().hashCode());
		result = 37 * result + (getDeliverNo() == null ? 0 : this.getDeliverNo().hashCode());
		result = 37 * result + (getDeliverBillStatus() == null ? 0 : this.getDeliverBillStatus().hashCode());
		result = 37 * result + (getGoodsBarcode() == null ? 0 : this.getGoodsBarcode().hashCode());
		result = 37 * result + (getCTell() == null ? 0 : this.getCTell().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getDeliverExpressNo() == null ? 0 : this.getDeliverExpressNo().hashCode());
		result = 37 * result + (getDeliverExpressComName() == null ? 0 : this.getDeliverExpressComName().hashCode());
		result = 37 * result + (getDeliverExpressCom() == null ? 0 : this.getDeliverExpressCom().hashCode());
		result = 37 * result + (getDeliverExpressVehicle() == null ? 0 : this.getDeliverExpressVehicle().hashCode());
		result = 37 * result + (getCName() == null ? 0 : this.getCName().hashCode());
		return result;
	}

}
