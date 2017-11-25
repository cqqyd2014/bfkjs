package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VUserMenuDId generated by hbm2java
 */
@Embeddable
public class VUserMenuDId implements java.io.Serializable {

	private Integer orderId;
	private String menuId;
	private String menuDId;
	private String menuDName;
	private String menuDJsMethod;
	private String menuDJsUrl;
	private String comId;
	private String userId;

	public VUserMenuDId() {
	}

	public VUserMenuDId(Integer orderId, String menuId, String menuDId, String menuDName, String menuDJsMethod,
			String menuDJsUrl, String comId, String userId) {
		this.orderId = orderId;
		this.menuId = menuId;
		this.menuDId = menuDId;
		this.menuDName = menuDName;
		this.menuDJsMethod = menuDJsMethod;
		this.menuDJsUrl = menuDJsUrl;
		this.comId = comId;
		this.userId = userId;
	}

	@Column(name = "order_id")
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "menu_id", length = 4)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "menu_d_id", length = 4)
	public String getMenuDId() {
		return this.menuDId;
	}

	public void setMenuDId(String menuDId) {
		this.menuDId = menuDId;
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

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "user_id", length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUserMenuDId))
			return false;
		VUserMenuDId castOther = (VUserMenuDId) other;

		return ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
				&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId().equals(castOther.getMenuId())))
				&& ((this.getMenuDId() == castOther.getMenuDId()) || (this.getMenuDId() != null
						&& castOther.getMenuDId() != null && this.getMenuDId().equals(castOther.getMenuDId())))
				&& ((this.getMenuDName() == castOther.getMenuDName()) || (this.getMenuDName() != null
						&& castOther.getMenuDName() != null && this.getMenuDName().equals(castOther.getMenuDName())))
				&& ((this.getMenuDJsMethod() == castOther.getMenuDJsMethod())
						|| (this.getMenuDJsMethod() != null && castOther.getMenuDJsMethod() != null
								&& this.getMenuDJsMethod().equals(castOther.getMenuDJsMethod())))
				&& ((this.getMenuDJsUrl() == castOther.getMenuDJsUrl()) || (this.getMenuDJsUrl() != null
						&& castOther.getMenuDJsUrl() != null && this.getMenuDJsUrl().equals(castOther.getMenuDJsUrl())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result + (getMenuDId() == null ? 0 : this.getMenuDId().hashCode());
		result = 37 * result + (getMenuDName() == null ? 0 : this.getMenuDName().hashCode());
		result = 37 * result + (getMenuDJsMethod() == null ? 0 : this.getMenuDJsMethod().hashCode());
		result = 37 * result + (getMenuDJsUrl() == null ? 0 : this.getMenuDJsUrl().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}
