package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MenuM generated by hbm2java
 */
@Entity
@Table(name = "menu_m", schema = "public")
public class MenuM implements java.io.Serializable {

	private MenuMId id;
	private String menuName;
	private String desc;
	private String orderId;

	public MenuM() {
	}

	public MenuM(MenuMId id) {
		this.id = id;
	}

	public MenuM(MenuMId id, String menuName, String desc, String orderId) {
		this.id = id;
		this.menuName = menuName;
		this.desc = desc;
		this.orderId = orderId;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "menuId", column = @Column(name = "menu_id", nullable = false, length = 4)) })
	public MenuMId getId() {
		return this.id;
	}

	public void setId(MenuMId id) {
		this.id = id;
	}

	@Column(name = "menu_name", length = 45)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "desc", length = 45)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "order_id", length = 4)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
