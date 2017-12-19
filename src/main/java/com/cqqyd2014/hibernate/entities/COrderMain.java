package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

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
 * COrderMain generated by hbm2java
 */
@Entity
@Table(name = "c_order_main", schema = "public")
public class COrderMain implements java.io.Serializable {

	private COrderMainId id;
	private String CUserName;
	private String CUserAddr;
	private BigDecimal CAmount;
	private BigDecimal CTax;
	private String CMemo;
	private Date CTime;
	private BigDecimal CTransCost;
	private String CTell;
	private BigDecimal CWeight;
	private Date lastTime;
	private BigDecimal CQty;
	private String detailMemo;
	private String originalId;
	private String addrProvince;
	private String addrCity;
	private String addrDistrict;
	private String gtStatus;
	private String emsStatus;
	private BigDecimal CAmount2;
	private BigDecimal CTax2;
	private BigDecimal discount;
	private BigDecimal cardPay;
	private BigDecimal shipFee;
	private Date orderDat;
	private BigDecimal actualAmount;
	private BigDecimal CRegTax2;
	private Boolean effective;
	private String userId;
	private Date cancelRequestDat;
	private String cancelStatus;
	private String CStatus;
	private String packageUser;
	private Boolean notAir;
	private String cancelRequestUserid;
	private String tell2;
	private String userCom;
	private String defaultExpressCom;
	private String defaultExpressVehicle;
	private Boolean paid;
	private BigDecimal paidMoney;
	private Date packageUserAssignTime;
	private Date paidTime;
	private Date cancelConfirmDat;
	private String cancelConfirmUserid;
	private String cancelRequestMemo;
	private String cancelConfirmMemo;
	private BigDecimal actualAmount2;

	public COrderMain() {
	}

	public COrderMain(COrderMainId id) {
		this.id = id;
	}

	public COrderMain(COrderMainId id, String CUserName, String CUserAddr, BigDecimal CAmount, BigDecimal CTax,
			String CMemo, Date CTime, BigDecimal CTransCost, String CTell, BigDecimal CWeight, Date lastTime,
			BigDecimal CQty, String detailMemo, String originalId, String addrProvince, String addrCity,
			String addrDistrict, String gtStatus, String emsStatus, BigDecimal CAmount2, BigDecimal CTax2,
			BigDecimal discount, BigDecimal cardPay, BigDecimal shipFee, Date orderDat, BigDecimal actualAmount,
			BigDecimal CRegTax2, Boolean effective, String userId, Date cancelRequestDat, String cancelStatus,
			String CStatus, String packageUser, Boolean notAir, String cancelRequestUserid, String tell2,
			String userCom, String defaultExpressCom, String defaultExpressVehicle, Boolean paid, BigDecimal paidMoney,
			Date packageUserAssignTime, Date paidTime, Date cancelConfirmDat, String cancelConfirmUserid,
			String cancelRequestMemo, String cancelConfirmMemo, BigDecimal actualAmount2) {
		this.id = id;
		this.CUserName = CUserName;
		this.CUserAddr = CUserAddr;
		this.CAmount = CAmount;
		this.CTax = CTax;
		this.CMemo = CMemo;
		this.CTime = CTime;
		this.CTransCost = CTransCost;
		this.CTell = CTell;
		this.CWeight = CWeight;
		this.lastTime = lastTime;
		this.CQty = CQty;
		this.detailMemo = detailMemo;
		this.originalId = originalId;
		this.addrProvince = addrProvince;
		this.addrCity = addrCity;
		this.addrDistrict = addrDistrict;
		this.gtStatus = gtStatus;
		this.emsStatus = emsStatus;
		this.CAmount2 = CAmount2;
		this.CTax2 = CTax2;
		this.discount = discount;
		this.cardPay = cardPay;
		this.shipFee = shipFee;
		this.orderDat = orderDat;
		this.actualAmount = actualAmount;
		this.CRegTax2 = CRegTax2;
		this.effective = effective;
		this.userId = userId;
		this.cancelRequestDat = cancelRequestDat;
		this.cancelStatus = cancelStatus;
		this.CStatus = CStatus;
		this.packageUser = packageUser;
		this.notAir = notAir;
		this.cancelRequestUserid = cancelRequestUserid;
		this.tell2 = tell2;
		this.userCom = userCom;
		this.defaultExpressCom = defaultExpressCom;
		this.defaultExpressVehicle = defaultExpressVehicle;
		this.paid = paid;
		this.paidMoney = paidMoney;
		this.packageUserAssignTime = packageUserAssignTime;
		this.paidTime = paidTime;
		this.cancelConfirmDat = cancelConfirmDat;
		this.cancelConfirmUserid = cancelConfirmUserid;
		this.cancelRequestMemo = cancelRequestMemo;
		this.cancelConfirmMemo = cancelConfirmMemo;
		this.actualAmount2 = actualAmount2;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", nullable = false, length = 20)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)) })
	public COrderMainId getId() {
		return this.id;
	}

	public void setId(COrderMainId id) {
		this.id = id;
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

	@Column(name = "c_tax", precision = 131089, scale = 0)
	public BigDecimal getCTax() {
		return this.CTax;
	}

	public void setCTax(BigDecimal CTax) {
		this.CTax = CTax;
	}

	@Column(name = "c_memo", length = 100)
	public String getCMemo() {
		return this.CMemo;
	}

	public void setCMemo(String CMemo) {
		this.CMemo = CMemo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", length = 35)
	public Date getCTime() {
		return this.CTime;
	}

	public void setCTime(Date CTime) {
		this.CTime = CTime;
	}

	@Column(name = "c_trans_cost", precision = 131089, scale = 0)
	public BigDecimal getCTransCost() {
		return this.CTransCost;
	}

	public void setCTransCost(BigDecimal CTransCost) {
		this.CTransCost = CTransCost;
	}

	@Column(name = "c_tell", length = 45)
	public String getCTell() {
		return this.CTell;
	}

	public void setCTell(String CTell) {
		this.CTell = CTell;
	}

	@Column(name = "c_weight", precision = 131089, scale = 0)
	public BigDecimal getCWeight() {
		return this.CWeight;
	}

	public void setCWeight(BigDecimal CWeight) {
		this.CWeight = CWeight;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_time", length = 35)
	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "c_qty", precision = 131089, scale = 0)
	public BigDecimal getCQty() {
		return this.CQty;
	}

	public void setCQty(BigDecimal CQty) {
		this.CQty = CQty;
	}

	@Column(name = "detail_memo")
	public String getDetailMemo() {
		return this.detailMemo;
	}

	public void setDetailMemo(String detailMemo) {
		this.detailMemo = detailMemo;
	}

	@Column(name = "original_id", length = 45)
	public String getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
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

	@Column(name = "c_amount2", precision = 131089, scale = 0)
	public BigDecimal getCAmount2() {
		return this.CAmount2;
	}

	public void setCAmount2(BigDecimal CAmount2) {
		this.CAmount2 = CAmount2;
	}

	@Column(name = "c_tax2", precision = 131089, scale = 0)
	public BigDecimal getCTax2() {
		return this.CTax2;
	}

	public void setCTax2(BigDecimal CTax2) {
		this.CTax2 = CTax2;
	}

	@Column(name = "discount", precision = 131089, scale = 0)
	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column(name = "card_pay", precision = 131089, scale = 0)
	public BigDecimal getCardPay() {
		return this.cardPay;
	}

	public void setCardPay(BigDecimal cardPay) {
		this.cardPay = cardPay;
	}

	@Column(name = "ship_fee", precision = 131089, scale = 0)
	public BigDecimal getShipFee() {
		return this.shipFee;
	}

	public void setShipFee(BigDecimal shipFee) {
		this.shipFee = shipFee;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_dat", length = 35)
	public Date getOrderDat() {
		return this.orderDat;
	}

	public void setOrderDat(Date orderDat) {
		this.orderDat = orderDat;
	}

	@Column(name = "actual_amount", precision = 131089, scale = 0)
	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	@Column(name = "c_reg_tax2", precision = 131089, scale = 0)
	public BigDecimal getCRegTax2() {
		return this.CRegTax2;
	}

	public void setCRegTax2(BigDecimal CRegTax2) {
		this.CRegTax2 = CRegTax2;
	}

	@Column(name = "effective")
	public Boolean getEffective() {
		return this.effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	@Column(name = "user_id", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancel_request_dat", length = 35)
	public Date getCancelRequestDat() {
		return this.cancelRequestDat;
	}

	public void setCancelRequestDat(Date cancelRequestDat) {
		this.cancelRequestDat = cancelRequestDat;
	}

	@Column(name = "cancel_status", length = 4)
	public String getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	@Column(name = "c_status", length = 4)
	public String getCStatus() {
		return this.CStatus;
	}

	public void setCStatus(String CStatus) {
		this.CStatus = CStatus;
	}

	@Column(name = "package_user", length = 36)
	public String getPackageUser() {
		return this.packageUser;
	}

	public void setPackageUser(String packageUser) {
		this.packageUser = packageUser;
	}

	@Column(name = "not_air")
	public Boolean getNotAir() {
		return this.notAir;
	}

	public void setNotAir(Boolean notAir) {
		this.notAir = notAir;
	}

	@Column(name = "cancel_request_userid", length = 36)
	public String getCancelRequestUserid() {
		return this.cancelRequestUserid;
	}

	public void setCancelRequestUserid(String cancelRequestUserid) {
		this.cancelRequestUserid = cancelRequestUserid;
	}

	@Column(name = "tell2", length = 45)
	public String getTell2() {
		return this.tell2;
	}

	public void setTell2(String tell2) {
		this.tell2 = tell2;
	}

	@Column(name = "user_com", length = 45)
	public String getUserCom() {
		return this.userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}

	@Column(name = "default_express_com", length = 45)
	public String getDefaultExpressCom() {
		return this.defaultExpressCom;
	}

	public void setDefaultExpressCom(String defaultExpressCom) {
		this.defaultExpressCom = defaultExpressCom;
	}

	@Column(name = "default_express_vehicle", length = 45)
	public String getDefaultExpressVehicle() {
		return this.defaultExpressVehicle;
	}

	public void setDefaultExpressVehicle(String defaultExpressVehicle) {
		this.defaultExpressVehicle = defaultExpressVehicle;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "package_user_assign_time", length = 35)
	public Date getPackageUserAssignTime() {
		return this.packageUserAssignTime;
	}

	public void setPackageUserAssignTime(Date packageUserAssignTime) {
		this.packageUserAssignTime = packageUserAssignTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "paid_time", length = 35)
	public Date getPaidTime() {
		return this.paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancel_confirm_dat", length = 35)
	public Date getCancelConfirmDat() {
		return this.cancelConfirmDat;
	}

	public void setCancelConfirmDat(Date cancelConfirmDat) {
		this.cancelConfirmDat = cancelConfirmDat;
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

	@Column(name = "actual_amount2", precision = 131089, scale = 0)
	public BigDecimal getActualAmount2() {
		return this.actualAmount2;
	}

	public void setActualAmount2(BigDecimal actualAmount2) {
		this.actualAmount2 = actualAmount2;
	}

}
