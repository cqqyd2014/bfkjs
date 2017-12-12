package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LogisticsVehicle generated by hbm2java
 */
@Entity
@Table(name = "logistics_vehicle", schema = "public")
public class LogisticsVehicle implements java.io.Serializable {

	private String vehicleId;
	private String vehicleName;
	private BigDecimal vehicleOrder;

	public LogisticsVehicle() {
	}

	public LogisticsVehicle(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LogisticsVehicle(String vehicleId, String vehicleName, BigDecimal vehicleOrder) {
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.vehicleOrder = vehicleOrder;
	}

	@Id

	@Column(name = "vehicle_id", unique = true, nullable = false, length = 4)
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

	@Column(name = "vehicle_order", precision = 131089, scale = 0)
	public BigDecimal getVehicleOrder() {
		return this.vehicleOrder;
	}

	public void setVehicleOrder(BigDecimal vehicleOrder) {
		this.vehicleOrder = vehicleOrder;
	}

}
