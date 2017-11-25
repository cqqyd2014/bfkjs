package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MenuD generated by hbm2java
 */
@Entity
@Table(name = "menu_d", schema = "public")
public class MenuD implements java.io.Serializable {

	private MenuDId id;
	private String menuDName;
	private String menuDJsMethod;
	private String menuDJsUrl;
	private Integer orderId;

	public MenuD() {
	}

	public MenuD(MenuDId id) {
		this.id = id;
	}

	public MenuD(MenuDId id, String menuDName, String menuDJsMethod, String menuDJsUrl, Integer orderId) {
		this.id = id;
		this.menuDName = menuDName;
		this.menuDJsMethod = menuDJsMethod;
		this.menuDJsUrl = menuDJsUrl;
		this.orderId = orderId;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "menuId", column = @Column(name = "menu_id", nullable = false, length = 4)),
			@AttributeOverride(name = "menuDId", column = @Column(name = "menu_d_id", nullable = false, length = 4)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)) })
	public MenuDId getId() {
		return this.id;
	}

	public void setId(MenuDId id) {
		this.id = id;
	}

	@Column(name = "menu_d_name", length = 45)
	public String getMenuDName() {
		return this.menuDName;
	}

	public void setMenuDName(String menuDName) {
		this.menuDName = menuDName;
	}

	@Column(name = "menu_d_js_method", length = 45)
	public String getMenuDJsMethod() {
		return this.menuDJsMethod;
	}

	public void setMenuDJsMethod(String menuDJsMethod) {
		this.menuDJsMethod = menuDJsMethod;
	}

	@Column(name = "menu_d_js_url", length = 256)
	public String getMenuDJsUrl() {
		return this.menuDJsUrl;
	}

	public void setMenuDJsUrl(String menuDJsUrl) {
		this.menuDJsUrl = menuDJsUrl;
	}

	@Column(name = "order_id")
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
