package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VOrderMainId generated by hbm2java
 */
@Embeddable
public class VOrderMainId implements java.io.Serializable {

	private String receiverMobile;
	private Boolean paid;
	private BigDecimal paidMoney;
	private Date paidTime;
	private BigDecimal CQty;
	private BigDecimal CTax2;
	private BigDecimal CRegTax2;
	private BigDecimal originalAmount2;
	private BigDecimal actualAmount2;
	private Date cancelRequestDat;
	private Date cancelConfirmDat;
	private String cancelStatus;
	private String cancelRequestUserid;
	private String cancelConfirmUserid;
	private String cancelRequestMemo;
	private String cancelConfirmMemo;
	private String sysUserName;
	private String vehicleId;
	private String vehicleName;
	private String logisticsId;
	private String logisticsName;
	private String userCom;
	private String tell2;
	private String userId;
	private Boolean notAir;
	private String orderNo;
	private String CUserName;
	private String CUserAddr;
	private BigDecimal CAmount;
	private BigDecimal cardPay;
	private String CMemo;
	private BigDecimal discount;
	private BigDecimal shipFee;
	private BigDecimal actualAmount;
	private String CTell;
	private String detailMemo;
	private Date orderDat;
	private Date CTime;
	private String comId;
	private String CStatus;
	private String addrProvince;
	private String addrCity;
	private String addrDistrict;
	private String gtStatus;
	private String emsStatus;
	private String orderTypeName;
	private String orderTypeFullName;
	private String originalId;
	private String packageUser;
	private String packageUserName;
	private Boolean effective;
	private Date packageUserAssignTime;

	public VOrderMainId() {
	}

	public VOrderMainId(String receiverMobile, Boolean paid, BigDecimal paidMoney, Date paidTime, BigDecimal CQty,
			BigDecimal CTax2, BigDecimal CRegTax2, BigDecimal originalAmount2, BigDecimal actualAmount2,
			Date cancelRequestDat, Date cancelConfirmDat, String cancelStatus, String cancelRequestUserid,
			String cancelConfirmUserid, String cancelRequestMemo, String cancelConfirmMemo, String sysUserName,
			String vehicleId, String vehicleName, String logisticsId, String logisticsName, String userCom,
			String tell2, String userId, Boolean notAir, String orderNo, String CUserName, String CUserAddr,
			BigDecimal CAmount, BigDecimal cardPay, String CMemo, BigDecimal discount, BigDecimal shipFee,
			BigDecimal actualAmount, String CTell, String detailMemo, Date orderDat, Date CTime, String comId,
			String CStatus, String addrProvince, String addrCity, String addrDistrict, String gtStatus,
			String emsStatus, String orderTypeName, String orderTypeFullName, String originalId, String packageUser,
			String packageUserName, Boolean effective, Date packageUserAssignTime) {
		this.receiverMobile = receiverMobile;
		this.paid = paid;
		this.paidMoney = paidMoney;
		this.paidTime = paidTime;
		this.CQty = CQty;
		this.CTax2 = CTax2;
		this.CRegTax2 = CRegTax2;
		this.originalAmount2 = originalAmount2;
		this.actualAmount2 = actualAmount2;
		this.cancelRequestDat = cancelRequestDat;
		this.cancelConfirmDat = cancelConfirmDat;
		this.cancelStatus = cancelStatus;
		this.cancelRequestUserid = cancelRequestUserid;
		this.cancelConfirmUserid = cancelConfirmUserid;
		this.cancelRequestMemo = cancelRequestMemo;
		this.cancelConfirmMemo = cancelConfirmMemo;
		this.sysUserName = sysUserName;
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.logisticsId = logisticsId;
		this.logisticsName = logisticsName;
		this.userCom = userCom;
		this.tell2 = tell2;
		this.userId = userId;
		this.notAir = notAir;
		this.orderNo = orderNo;
		this.CUserName = CUserName;
		this.CUserAddr = CUserAddr;
		this.CAmount = CAmount;
		this.cardPay = cardPay;
		this.CMemo = CMemo;
		this.discount = discount;
		this.shipFee = shipFee;
		this.actualAmount = actualAmount;
		this.CTell = CTell;
		this.detailMemo = detailMemo;
		this.orderDat = orderDat;
		this.CTime = CTime;
		this.comId = comId;
		this.CStatus = CStatus;
		this.addrProvince = addrProvince;
		this.addrCity = addrCity;
		this.addrDistrict = addrDistrict;
		this.gtStatus = gtStatus;
		this.emsStatus = emsStatus;
		this.orderTypeName = orderTypeName;
		this.orderTypeFullName = orderTypeFullName;
		this.originalId = originalId;
		this.packageUser = packageUser;
		this.packageUserName = packageUserName;
		this.effective = effective;
		this.packageUserAssignTime = packageUserAssignTime;
	}

	@Column(name = "receiver_mobile", length = 45)
	public String getReceiverMobile() {
		return this.receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	@Column(name = "paid")
	public Boolean getPaid() {
		return this.paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	@Column(name = "paid_money", precision = 131089, scale = 0)
	public BigDecimal getPaidMoney() {
		return this.paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	@Column(name = "paid_time", length = 35)
	public Date getPaidTime() {
		return this.paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	@Column(name = "c_qty", precision = 131089, scale = 0)
	public BigDecimal getCQty() {
		return this.CQty;
	}

	public void setCQty(BigDecimal CQty) {
		this.CQty = CQty;
	}

	@Column(name = "c_tax2", precision = 131089, scale = 0)
	public BigDecimal getCTax2() {
		return this.CTax2;
	}

	public void setCTax2(BigDecimal CTax2) {
		this.CTax2 = CTax2;
	}

	@Column(name = "c_reg_tax2", precision = 131089, scale = 0)
	public BigDecimal getCRegTax2() {
		return this.CRegTax2;
	}

	public void setCRegTax2(BigDecimal CRegTax2) {
		this.CRegTax2 = CRegTax2;
	}

	@Column(name = "original_amount2", precision = 131089, scale = 0)
	public BigDecimal getOriginalAmount2() {
		return this.originalAmount2;
	}

	public void setOriginalAmount2(BigDecimal originalAmount2) {
		this.originalAmount2 = originalAmount2;
	}

	@Column(name = "actual_amount2", precision = 131089, scale = 0)
	public BigDecimal getActualAmount2() {
		return this.actualAmount2;
	}

	public void setActualAmount2(BigDecimal actualAmount2) {
		this.actualAmount2 = actualAmount2;
	}

	@Column(name = "cancel_request_dat", length = 35)
	public Date getCancelRequestDat() {
		return this.cancelRequestDat;
	}

	public void setCancelRequestDat(Date cancelRequestDat) {
		this.cancelRequestDat = cancelRequestDat;
	}

	@Column(name = "cancel_confirm_dat", length = 35)
	public Date getCancelConfirmDat() {
		return this.cancelConfirmDat;
	}

	public void setCancelConfirmDat(Date cancelConfirmDat) {
		this.cancelConfirmDat = cancelConfirmDat;
	}

	@Column(name = "cancel_status", length = 4)
	public String getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	@Column(name = "cancel_request_userid", length = 36)
	public String getCancelRequestUserid() {
		return this.cancelRequestUserid;
	}

	public void setCancelRequestUserid(String cancelRequestUserid) {
		this.cancelRequestUserid = cancelRequestUserid;
	}

	@Column(name = "cancel_confirm_userid", length = 36)
	public String getCancelConfirmUserid() {
		return this.cancelConfirmUserid;
	}

	public void setCancelConfirmUserid(String cancelConfirmUserid) {
		this.cancelConfirmUserid = cancelConfirmUserid;
	}

	@Column(name = "cancel_request_memo", length = 512)
	public String getCancelRequestMemo() {
		return this.cancelRequestMemo;
	}

	public void setCancelRequestMemo(String cancelRequestMemo) {
		this.cancelRequestMemo = cancelRequestMemo;
	}

	@Column(name = "cancel_confirm_memo", length = 512)
	public String getCancelConfirmMemo() {
		return this.cancelConfirmMemo;
	}

	public void setCancelConfirmMemo(String cancelConfirmMemo) {
		this.cancelConfirmMemo = cancelConfirmMemo;
	}

	@Column(name = "sys_user_name", length = 45)
	public String getSysUserName() {
		return this.sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	@Column(name = "vehicle_id", length = 4)
	public String getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "vehicle_name", length = 64)
	public String getVehicleName() {
		return this.vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	@Column(name = "logistics_id", length = 45)
	public String getLogisticsId() {
		return this.logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	@Column(name = "logistics_name", length = 45)
	public String getLogisticsName() {
		return this.logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	@Column(name = "user_com", length = 45)
	public String getUserCom() {
		return this.userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}

	@Column(name = "tell2", length = 45)
	public String getTell2() {
		return this.tell2;
	}

	public void setTell2(String tell2) {
		this.tell2 = tell2;
	}

	@Column(name = "user_id", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "not_air")
	public Boolean getNotAir() {
		return this.notAir;
	}

	public void setNotAir(Boolean notAir) {
		this.notAir = notAir;
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

	@Column(name = "c_user_addr", length = 450)
	public String getCUserAddr() {
		return this.CUserAddr;
	}

	public void setCUserAddr(String CUserAddr) {
		this.CUserAddr = CUserAddr;
	}

	@Column(name = "c_amount", precision = 131089, scale = 0)
	public BigDecimal getCAmount() {
		return this.CAmount;
	}

	public void setCAmount(BigDecimal CAmount) {
		this.CAmount = CAmount;
	}

	@Column(name = "card_pay", precision = 131089, scale = 0)
	public BigDecimal getCardPay() {
		return this.cardPay;
	}

	public void setCardPay(BigDecimal cardPay) {
		this.cardPay = cardPay;
	}

	@Column(name = "c_memo")
	public String getCMemo() {
		return this.CMemo;
	}

	public void setCMemo(String CMemo) {
		this.CMemo = CMemo;
	}

	@Column(name = "discount", precision = 131089, scale = 0)
	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column(name = "ship_fee", precision = 131089, scale = 0)
	public BigDecimal getShipFee() {
		return this.shipFee;
	}

	public void setShipFee(BigDecimal shipFee) {
		this.shipFee = shipFee;
	}

	@Column(name = "actual_amount", precision = 131089, scale = 0)
	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	@Column(name = "c_tell", length = 45)
	public String getCTell() {
		return this.CTell;
	}

	public void setCTell(String CTell) {
		this.CTell = CTell;
	}

	@Column(name = "detail_memo")
	public String getDetailMemo() {
		return this.detailMemo;
	}

	public void setDetailMemo(String detailMemo) {
		this.detailMemo = detailMemo;
	}

	@Column(name = "order_dat", length = 35)
	public Date getOrderDat() {
		return this.orderDat;
	}

	public void setOrderDat(Date orderDat) {
		this.orderDat = orderDat;
	}

	@Column(name = "c_time", length = 35)
	public Date getCTime() {
		return this.CTime;
	}

	public void setCTime(Date CTime) {
		this.CTime = CTime;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "c_status", length = 4)
	public String getCStatus() {
		return this.CStatus;
	}

	public void setCStatus(String CStatus) {
		this.CStatus = CStatus;
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

	@Column(name = "original_id", length = 45)
	public String getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	@Column(name = "package_user")
	public String getPackageUser() {
		return this.packageUser;
	}

	public void setPackageUser(String packageUser) {
		this.packageUser = packageUser;
	}

	@Column(name = "package_user_name")
	public String getPackageUserName() {
		return this.packageUserName;
	}

	public void setPackageUserName(String packageUserName) {
		this.packageUserName = packageUserName;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	@Column(name = "package_user_assign_time", length = 35)
	public Date getPackageUserAssignTime() {
		return this.packageUserAssignTime;
	}

	public void setPackageUserAssignTime(Date packageUserAssignTime) {
		this.packageUserAssignTime = packageUserAssignTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VOrderMainId))
			return false;
		VOrderMainId castOther = (VOrderMainId) other;

		return ((this.getReceiverMobile() == castOther.getReceiverMobile())
				|| (this.getReceiverMobile() != null && castOther.getReceiverMobile() != null
						&& this.getReceiverMobile().equals(castOther.getReceiverMobile())))
				&& ((this.getPaid() == castOther.getPaid()) || (this.getPaid() != null && castOther.getPaid() != null
						&& this.getPaid().equals(castOther.getPaid())))
				&& ((this.getPaidMoney() == castOther.getPaidMoney()) || (this.getPaidMoney() != null
						&& castOther.getPaidMoney() != null && this.getPaidMoney().equals(castOther.getPaidMoney())))
				&& ((this.getPaidTime() == castOther.getPaidTime()) || (this.getPaidTime() != null
						&& castOther.getPaidTime() != null && this.getPaidTime().equals(castOther.getPaidTime())))
				&& ((this.getCQty() == castOther.getCQty()) || (this.getCQty() != null && castOther.getCQty() != null
						&& this.getCQty().equals(castOther.getCQty())))
				&& ((this.getCTax2() == castOther.getCTax2()) || (this.getCTax2() != null
						&& castOther.getCTax2() != null && this.getCTax2().equals(castOther.getCTax2())))
				&& ((this.getCRegTax2() == castOther.getCRegTax2()) || (this.getCRegTax2() != null
						&& castOther.getCRegTax2() != null && this.getCRegTax2().equals(castOther.getCRegTax2())))
				&& ((this.getOriginalAmount2() == castOther.getOriginalAmount2())
						|| (this.getOriginalAmount2() != null && castOther.getOriginalAmount2() != null
								&& this.getOriginalAmount2().equals(castOther.getOriginalAmount2())))
				&& ((this.getActualAmount2() == castOther.getActualAmount2())
						|| (this.getActualAmount2() != null && castOther.getActualAmount2() != null
								&& this.getActualAmount2().equals(castOther.getActualAmount2())))
				&& ((this.getCancelRequestDat() == castOther.getCancelRequestDat())
						|| (this.getCancelRequestDat() != null && castOther.getCancelRequestDat() != null
								&& this.getCancelRequestDat().equals(castOther.getCancelRequestDat())))
				&& ((this.getCancelConfirmDat() == castOther.getCancelConfirmDat())
						|| (this.getCancelConfirmDat() != null && castOther.getCancelConfirmDat() != null
								&& this.getCancelConfirmDat().equals(castOther.getCancelConfirmDat())))
				&& ((this.getCancelStatus() == castOther.getCancelStatus())
						|| (this.getCancelStatus() != null && castOther.getCancelStatus() != null
								&& this.getCancelStatus().equals(castOther.getCancelStatus())))
				&& ((this.getCancelRequestUserid() == castOther.getCancelRequestUserid())
						|| (this.getCancelRequestUserid() != null && castOther.getCancelRequestUserid() != null
								&& this.getCancelRequestUserid().equals(castOther.getCancelRequestUserid())))
				&& ((this.getCancelConfirmUserid() == castOther.getCancelConfirmUserid())
						|| (this.getCancelConfirmUserid() != null && castOther.getCancelConfirmUserid() != null
								&& this.getCancelConfirmUserid().equals(castOther.getCancelConfirmUserid())))
				&& ((this.getCancelRequestMemo() == castOther.getCancelRequestMemo())
						|| (this.getCancelRequestMemo() != null && castOther.getCancelRequestMemo() != null
								&& this.getCancelRequestMemo().equals(castOther.getCancelRequestMemo())))
				&& ((this.getCancelConfirmMemo() == castOther.getCancelConfirmMemo())
						|| (this.getCancelConfirmMemo() != null && castOther.getCancelConfirmMemo() != null
								&& this.getCancelConfirmMemo().equals(castOther.getCancelConfirmMemo())))
				&& ((this.getSysUserName() == castOther.getSysUserName())
						|| (this.getSysUserName() != null && castOther.getSysUserName() != null
								&& this.getSysUserName().equals(castOther.getSysUserName())))
				&& ((this.getVehicleId() == castOther.getVehicleId()) || (this.getVehicleId() != null
						&& castOther.getVehicleId() != null && this.getVehicleId().equals(castOther.getVehicleId())))
				&& ((this.getVehicleName() == castOther.getVehicleName())
						|| (this.getVehicleName() != null && castOther.getVehicleName() != null
								&& this.getVehicleName().equals(castOther.getVehicleName())))
				&& ((this.getLogisticsId() == castOther.getLogisticsId())
						|| (this.getLogisticsId() != null && castOther.getLogisticsId() != null
								&& this.getLogisticsId().equals(castOther.getLogisticsId())))
				&& ((this.getLogisticsName() == castOther.getLogisticsName())
						|| (this.getLogisticsName() != null && castOther.getLogisticsName() != null
								&& this.getLogisticsName().equals(castOther.getLogisticsName())))
				&& ((this.getUserCom() == castOther.getUserCom()) || (this.getUserCom() != null
						&& castOther.getUserCom() != null && this.getUserCom().equals(castOther.getUserCom())))
				&& ((this.getTell2() == castOther.getTell2()) || (this.getTell2() != null
						&& castOther.getTell2() != null && this.getTell2().equals(castOther.getTell2())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getNotAir() == castOther.getNotAir()) || (this.getNotAir() != null
						&& castOther.getNotAir() != null && this.getNotAir().equals(castOther.getNotAir())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getCUserName() == castOther.getCUserName()) || (this.getCUserName() != null
						&& castOther.getCUserName() != null && this.getCUserName().equals(castOther.getCUserName())))
				&& ((this.getCUserAddr() == castOther.getCUserAddr()) || (this.getCUserAddr() != null
						&& castOther.getCUserAddr() != null && this.getCUserAddr().equals(castOther.getCUserAddr())))
				&& ((this.getCAmount() == castOther.getCAmount()) || (this.getCAmount() != null
						&& castOther.getCAmount() != null && this.getCAmount().equals(castOther.getCAmount())))
				&& ((this.getCardPay() == castOther.getCardPay()) || (this.getCardPay() != null
						&& castOther.getCardPay() != null && this.getCardPay().equals(castOther.getCardPay())))
				&& ((this.getCMemo() == castOther.getCMemo()) || (this.getCMemo() != null
						&& castOther.getCMemo() != null && this.getCMemo().equals(castOther.getCMemo())))
				&& ((this.getDiscount() == castOther.getDiscount()) || (this.getDiscount() != null
						&& castOther.getDiscount() != null && this.getDiscount().equals(castOther.getDiscount())))
				&& ((this.getShipFee() == castOther.getShipFee()) || (this.getShipFee() != null
						&& castOther.getShipFee() != null && this.getShipFee().equals(castOther.getShipFee())))
				&& ((this.getActualAmount() == castOther.getActualAmount())
						|| (this.getActualAmount() != null && castOther.getActualAmount() != null
								&& this.getActualAmount().equals(castOther.getActualAmount())))
				&& ((this.getCTell() == castOther.getCTell()) || (this.getCTell() != null
						&& castOther.getCTell() != null && this.getCTell().equals(castOther.getCTell())))
				&& ((this.getDetailMemo() == castOther.getDetailMemo()) || (this.getDetailMemo() != null
						&& castOther.getDetailMemo() != null && this.getDetailMemo().equals(castOther.getDetailMemo())))
				&& ((this.getOrderDat() == castOther.getOrderDat()) || (this.getOrderDat() != null
						&& castOther.getOrderDat() != null && this.getOrderDat().equals(castOther.getOrderDat())))
				&& ((this.getCTime() == castOther.getCTime()) || (this.getCTime() != null
						&& castOther.getCTime() != null && this.getCTime().equals(castOther.getCTime())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getCStatus() == castOther.getCStatus()) || (this.getCStatus() != null
						&& castOther.getCStatus() != null && this.getCStatus().equals(castOther.getCStatus())))
				&& ((this.getAddrProvince() == castOther.getAddrProvince())
						|| (this.getAddrProvince() != null && castOther.getAddrProvince() != null
								&& this.getAddrProvince().equals(castOther.getAddrProvince())))
				&& ((this.getAddrCity() == castOther.getAddrCity()) || (this.getAddrCity() != null
						&& castOther.getAddrCity() != null && this.getAddrCity().equals(castOther.getAddrCity())))
				&& ((this.getAddrDistrict() == castOther.getAddrDistrict())
						|| (this.getAddrDistrict() != null && castOther.getAddrDistrict() != null
								&& this.getAddrDistrict().equals(castOther.getAddrDistrict())))
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
				&& ((this.getOriginalId() == castOther.getOriginalId()) || (this.getOriginalId() != null
						&& castOther.getOriginalId() != null && this.getOriginalId().equals(castOther.getOriginalId())))
				&& ((this.getPackageUser() == castOther.getPackageUser())
						|| (this.getPackageUser() != null && castOther.getPackageUser() != null
								&& this.getPackageUser().equals(castOther.getPackageUser())))
				&& ((this.getPackageUserName() == castOther.getPackageUserName())
						|| (this.getPackageUserName() != null && castOther.getPackageUserName() != null
								&& this.getPackageUserName().equals(castOther.getPackageUserName())))
				&& ((this.getEffective() == castOther.getEffective()) || (this.getEffective() != null
						&& castOther.getEffective() != null && this.getEffective().equals(castOther.getEffective())))
				&& ((this.getPackageUserAssignTime() == castOther.getPackageUserAssignTime())
						|| (this.getPackageUserAssignTime() != null && castOther.getPackageUserAssignTime() != null
								&& this.getPackageUserAssignTime().equals(castOther.getPackageUserAssignTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getReceiverMobile() == null ? 0 : this.getReceiverMobile().hashCode());
		result = 37 * result + (getPaid() == null ? 0 : this.getPaid().hashCode());
		result = 37 * result + (getPaidMoney() == null ? 0 : this.getPaidMoney().hashCode());
		result = 37 * result + (getPaidTime() == null ? 0 : this.getPaidTime().hashCode());
		result = 37 * result + (getCQty() == null ? 0 : this.getCQty().hashCode());
		result = 37 * result + (getCTax2() == null ? 0 : this.getCTax2().hashCode());
		result = 37 * result + (getCRegTax2() == null ? 0 : this.getCRegTax2().hashCode());
		result = 37 * result + (getOriginalAmount2() == null ? 0 : this.getOriginalAmount2().hashCode());
		result = 37 * result + (getActualAmount2() == null ? 0 : this.getActualAmount2().hashCode());
		result = 37 * result + (getCancelRequestDat() == null ? 0 : this.getCancelRequestDat().hashCode());
		result = 37 * result + (getCancelConfirmDat() == null ? 0 : this.getCancelConfirmDat().hashCode());
		result = 37 * result + (getCancelStatus() == null ? 0 : this.getCancelStatus().hashCode());
		result = 37 * result + (getCancelRequestUserid() == null ? 0 : this.getCancelRequestUserid().hashCode());
		result = 37 * result + (getCancelConfirmUserid() == null ? 0 : this.getCancelConfirmUserid().hashCode());
		result = 37 * result + (getCancelRequestMemo() == null ? 0 : this.getCancelRequestMemo().hashCode());
		result = 37 * result + (getCancelConfirmMemo() == null ? 0 : this.getCancelConfirmMemo().hashCode());
		result = 37 * result + (getSysUserName() == null ? 0 : this.getSysUserName().hashCode());
		result = 37 * result + (getVehicleId() == null ? 0 : this.getVehicleId().hashCode());
		result = 37 * result + (getVehicleName() == null ? 0 : this.getVehicleName().hashCode());
		result = 37 * result + (getLogisticsId() == null ? 0 : this.getLogisticsId().hashCode());
		result = 37 * result + (getLogisticsName() == null ? 0 : this.getLogisticsName().hashCode());
		result = 37 * result + (getUserCom() == null ? 0 : this.getUserCom().hashCode());
		result = 37 * result + (getTell2() == null ? 0 : this.getTell2().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getNotAir() == null ? 0 : this.getNotAir().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getCUserName() == null ? 0 : this.getCUserName().hashCode());
		result = 37 * result + (getCUserAddr() == null ? 0 : this.getCUserAddr().hashCode());
		result = 37 * result + (getCAmount() == null ? 0 : this.getCAmount().hashCode());
		result = 37 * result + (getCardPay() == null ? 0 : this.getCardPay().hashCode());
		result = 37 * result + (getCMemo() == null ? 0 : this.getCMemo().hashCode());
		result = 37 * result + (getDiscount() == null ? 0 : this.getDiscount().hashCode());
		result = 37 * result + (getShipFee() == null ? 0 : this.getShipFee().hashCode());
		result = 37 * result + (getActualAmount() == null ? 0 : this.getActualAmount().hashCode());
		result = 37 * result + (getCTell() == null ? 0 : this.getCTell().hashCode());
		result = 37 * result + (getDetailMemo() == null ? 0 : this.getDetailMemo().hashCode());
		result = 37 * result + (getOrderDat() == null ? 0 : this.getOrderDat().hashCode());
		result = 37 * result + (getCTime() == null ? 0 : this.getCTime().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getCStatus() == null ? 0 : this.getCStatus().hashCode());
		result = 37 * result + (getAddrProvince() == null ? 0 : this.getAddrProvince().hashCode());
		result = 37 * result + (getAddrCity() == null ? 0 : this.getAddrCity().hashCode());
		result = 37 * result + (getAddrDistrict() == null ? 0 : this.getAddrDistrict().hashCode());
		result = 37 * result + (getGtStatus() == null ? 0 : this.getGtStatus().hashCode());
		result = 37 * result + (getEmsStatus() == null ? 0 : this.getEmsStatus().hashCode());
		result = 37 * result + (getOrderTypeName() == null ? 0 : this.getOrderTypeName().hashCode());
		result = 37 * result + (getOrderTypeFullName() == null ? 0 : this.getOrderTypeFullName().hashCode());
		result = 37 * result + (getOriginalId() == null ? 0 : this.getOriginalId().hashCode());
		result = 37 * result + (getPackageUser() == null ? 0 : this.getPackageUser().hashCode());
		result = 37 * result + (getPackageUserName() == null ? 0 : this.getPackageUserName().hashCode());
		result = 37 * result + (getEffective() == null ? 0 : this.getEffective().hashCode());
		result = 37 * result + (getPackageUserAssignTime() == null ? 0 : this.getPackageUserAssignTime().hashCode());
		return result;
	}

}
