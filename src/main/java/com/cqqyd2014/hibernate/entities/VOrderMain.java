package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VOrderMain generated by hbm2java
 */
@Entity
@Table(name = "v_order_main", schema = "public")
public class VOrderMain implements java.io.Serializable {

	private VOrderMainId id;

	public VOrderMain() {
	}

	public VOrderMain(VOrderMainId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "receiverMobile", column = @Column(name = "receiver_mobile", length = 45)),
			@AttributeOverride(name = "paid", column = @Column(name = "paid")),
			@AttributeOverride(name = "paidMoney", column = @Column(name = "paid_money", precision = 131089, scale = 0)),
			@AttributeOverride(name = "paidTime", column = @Column(name = "paid_time", length = 35)),
			@AttributeOverride(name = "CQty", column = @Column(name = "c_qty", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CTax2", column = @Column(name = "c_tax2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CRegTax2", column = @Column(name = "c_reg_tax2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "originalAmount2", column = @Column(name = "original_amount2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "actualAmount2", column = @Column(name = "actual_amount2", precision = 131089, scale = 0)),
			@AttributeOverride(name = "cancelRequestDat", column = @Column(name = "cancel_request_dat", length = 35)),
			@AttributeOverride(name = "cancelConfirmDat", column = @Column(name = "cancel_confirm_dat", length = 35)),
			@AttributeOverride(name = "cancelStatus", column = @Column(name = "cancel_status", length = 4)),
			@AttributeOverride(name = "cancelRequestUserid", column = @Column(name = "cancel_request_userid", length = 36)),
			@AttributeOverride(name = "cancelConfirmUserid", column = @Column(name = "cancel_confirm_userid", length = 36)),
			@AttributeOverride(name = "cancelRequestMemo", column = @Column(name = "cancel_request_memo", length = 512)),
			@AttributeOverride(name = "cancelConfirmMemo", column = @Column(name = "cancel_confirm_memo", length = 512)),
			@AttributeOverride(name = "sysUserName", column = @Column(name = "sys_user_name", length = 45)),
			@AttributeOverride(name = "vehicleId", column = @Column(name = "vehicle_id", length = 4)),
			@AttributeOverride(name = "vehicleName", column = @Column(name = "vehicle_name", length = 64)),
			@AttributeOverride(name = "logisticsId", column = @Column(name = "logistics_id", length = 45)),
			@AttributeOverride(name = "logisticsName", column = @Column(name = "logistics_name", length = 45)),
			@AttributeOverride(name = "userCom", column = @Column(name = "user_com", length = 45)),
			@AttributeOverride(name = "tell2", column = @Column(name = "tell2", length = 45)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", length = 36)),
			@AttributeOverride(name = "notAir", column = @Column(name = "not_air")),
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 20)),
			@AttributeOverride(name = "CUserName", column = @Column(name = "c_user_name", length = 45)),
			@AttributeOverride(name = "CUserAddr", column = @Column(name = "c_user_addr", length = 450)),
			@AttributeOverride(name = "CAmount", column = @Column(name = "c_amount", precision = 131089, scale = 0)),
			@AttributeOverride(name = "cardPay", column = @Column(name = "card_pay", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CMemo", column = @Column(name = "c_memo")),
			@AttributeOverride(name = "discount", column = @Column(name = "discount", precision = 131089, scale = 0)),
			@AttributeOverride(name = "shipFee", column = @Column(name = "ship_fee", precision = 131089, scale = 0)),
			@AttributeOverride(name = "actualAmount", column = @Column(name = "actual_amount", precision = 131089, scale = 0)),
			@AttributeOverride(name = "CTell", column = @Column(name = "c_tell", length = 45)),
			@AttributeOverride(name = "detailMemo", column = @Column(name = "detail_memo")),
			@AttributeOverride(name = "orderDat", column = @Column(name = "order_dat", length = 35)),
			@AttributeOverride(name = "CTime", column = @Column(name = "c_time", length = 35)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "CStatus", column = @Column(name = "c_status", length = 4)),
			@AttributeOverride(name = "addrProvince", column = @Column(name = "addr_province", length = 45)),
			@AttributeOverride(name = "addrCity", column = @Column(name = "addr_city", length = 45)),
			@AttributeOverride(name = "addrDistrict", column = @Column(name = "addr_district", length = 45)),
			@AttributeOverride(name = "gtStatus", column = @Column(name = "gt_status", length = 4)),
			@AttributeOverride(name = "emsStatus", column = @Column(name = "ems_status", length = 4)),
			@AttributeOverride(name = "orderTypeName", column = @Column(name = "order_type_name", length = 4)),
			@AttributeOverride(name = "orderTypeFullName", column = @Column(name = "order_type_full_name", length = 45)),
			@AttributeOverride(name = "originalId", column = @Column(name = "original_id", length = 45)),
			@AttributeOverride(name = "packageUser", column = @Column(name = "package_user")),
			@AttributeOverride(name = "packageUserName", column = @Column(name = "package_user_name")),
			@AttributeOverride(name = "effective", column = @Column(name = "effective")),
			@AttributeOverride(name = "packageUserAssignTime", column = @Column(name = "package_user_assign_time", length = 35)) })
	public VOrderMainId getId() {
		return this.id;
	}

	public void setId(VOrderMainId id) {
		this.id = id;
	}

}
