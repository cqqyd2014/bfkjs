package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VLogisticsVehicleFee generated by hbm2java
 */
@Entity
@Table(name = "v_logistics_vehicle_fee", schema = "public")
public class VLogisticsVehicleFee implements java.io.Serializable {

	private VLogisticsVehicleFeeId id;

	public VLogisticsVehicleFee() {
	}

	public VLogisticsVehicleFee(VLogisticsVehicleFeeId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "logisticsName", column = @Column(name = "logistics_name", length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "logistics", column = @Column(name = "logistics", length = 64)),
			@AttributeOverride(name = "vehicle", column = @Column(name = "vehicle", length = 64)),
			@AttributeOverride(name = "nextFee", column = @Column(name = "next_fee", precision = 131089, scale = 0)),
			@AttributeOverride(name = "firstFee", column = @Column(name = "first_fee", precision = 131089, scale = 0)),
			@AttributeOverride(name = "effective", column = @Column(name = "effective")),
			@AttributeOverride(name = "vehicleName", column = @Column(name = "vehicle_name", length = 64)),
			@AttributeOverride(name = "vehicleOrder", column = @Column(name = "vehicle_order", precision = 131089, scale = 0)) })
	public VLogisticsVehicleFeeId getId() {
		return this.id;
	}

	public void setId(VLogisticsVehicleFeeId id) {
		this.id = id;
	}

}
