package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-31 21:46:23 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VDeliverM generated by hbm2java
 */
@Entity
@Table(name = "v_deliver_m", schema = "public")
public class VDeliverM implements java.io.Serializable {

	private VDeliverMId id;

	public VDeliverM() {
	}

	public VDeliverM(VDeliverMId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "originalId", column = @Column(name = "original_id", length = 45)),
			@AttributeOverride(name = "CTell", column = @Column(name = "c_tell", length = 45)),
			@AttributeOverride(name = "tell2", column = @Column(name = "tell2", length = 45)),
			@AttributeOverride(name = "CStatus", column = @Column(name = "c_status", length = 4)),
			@AttributeOverride(name = "memoBarcodes", column = @Column(name = "memo_barcodes", length = 8000)),
			@AttributeOverride(name = "memoNames", column = @Column(name = "memo_names", length = 8000)),
			@AttributeOverride(name = "packageDat", column = @Column(name = "package_dat", length = 35)),
			@AttributeOverride(name = "orderDat", column = @Column(name = "order_dat", length = 35)),
			@AttributeOverride(name = "receiverMobile", column = @Column(name = "receiver_mobile", length = 45)),
			@AttributeOverride(name = "userCom", column = @Column(name = "user_com", length = 45)),
			@AttributeOverride(name = "CUserName", column = @Column(name = "c_user_name", length = 45)),
			@AttributeOverride(name = "addrProvince", column = @Column(name = "addr_province", length = 45)),
			@AttributeOverride(name = "addrCity", column = @Column(name = "addr_city", length = 45)),
			@AttributeOverride(name = "addrDistrict", column = @Column(name = "addr_district", length = 45)),
			@AttributeOverride(name = "CUserAddr", column = @Column(name = "c_user_addr", length = 450)),
			@AttributeOverride(name = "createUserid", column = @Column(name = "create_userid", length = 36)),
			@AttributeOverride(name = "comName", column = @Column(name = "com_name", length = 90)),
			@AttributeOverride(name = "createUsername", column = @Column(name = "create_username", length = 45)),
			@AttributeOverride(name = "num", column = @Column(name = "num", precision = 131089, scale = 0)),
			@AttributeOverride(name = "cartonType", column = @Column(name = "carton_type", length = 4)),
			@AttributeOverride(name = "cartonWeight", column = @Column(name = "carton_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "vehicleId", column = @Column(name = "vehicle_id", length = 4)),
			@AttributeOverride(name = "vehicleName", column = @Column(name = "vehicle_name", length = 64)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "order_no", length = 20)),
			@AttributeOverride(name = "seq", column = @Column(name = "seq", length = 4)),
			@AttributeOverride(name = "expressCom", column = @Column(name = "express_com", length = 45)),
			@AttributeOverride(name = "logisticsName", column = @Column(name = "logistics_name", length = 45)),
			@AttributeOverride(name = "expressNo", column = @Column(name = "express_no", length = 45)),
			@AttributeOverride(name = "actualWeight", column = @Column(name = "actual_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "deliverNo", column = @Column(name = "deliver_no", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "prePackageBarcode", column = @Column(name = "pre_package_barcode", length = 18)),
			@AttributeOverride(name = "effective", column = @Column(name = "effective")),
			@AttributeOverride(name = "sended", column = @Column(name = "sended")),
			@AttributeOverride(name = "packageUserid", column = @Column(name = "package_userid", length = 36)),
			@AttributeOverride(name = "sendDat", column = @Column(name = "send_dat", length = 35)),
			@AttributeOverride(name = "packageWeight", column = @Column(name = "package_weight", precision = 131089, scale = 0)),
			@AttributeOverride(name = "sendUserid", column = @Column(name = "send_userid", length = 36)),
			@AttributeOverride(name = "deliverBillStatus", column = @Column(name = "deliver_bill_status", length = 4)),
			@AttributeOverride(name = "whId", column = @Column(name = "wh_id", length = 6)),
			@AttributeOverride(name = "whName", column = @Column(name = "wh_name")),
			@AttributeOverride(name = "cancelRequestUserid", column = @Column(name = "cancel_request_userid", length = 36)),
			@AttributeOverride(name = "cancelRequestMemo", column = @Column(name = "cancel_request_memo", length = 500)),
			@AttributeOverride(name = "cancelRequestDat", column = @Column(name = "cancel_request_dat", length = 35)),
			@AttributeOverride(name = "cancelStatus", column = @Column(name = "cancel_status", length = 4)),
			@AttributeOverride(name = "cancelConfirmDat", column = @Column(name = "cancel_confirm_dat", length = 35)),
			@AttributeOverride(name = "cancelConfirmUserid", column = @Column(name = "cancel_confirm_userid", length = 36)),
			@AttributeOverride(name = "cancelConfirmMemo", column = @Column(name = "cancel_confirm_memo", length = 500)),
			@AttributeOverride(name = "logisticsFbStatus", column = @Column(name = "logistics_fb_status", length = 4)),
			@AttributeOverride(name = "logisticsFbMemo", column = @Column(name = "logistics_fb_memo", length = 1024)),
			@AttributeOverride(name = "logisticsFbCode", column = @Column(name = "logistics_fb_code", length = 512)),
			@AttributeOverride(name = "logisticsFbDat", column = @Column(name = "logistics_fb_dat", length = 35)),
			@AttributeOverride(name = "sendUserAssignTime", column = @Column(name = "send_user_assign_time", length = 35)) })
	public VDeliverMId getId() {
		return this.id;
	}

	public void setId(VDeliverMId id) {
		this.id = id;
	}

}
