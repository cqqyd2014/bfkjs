package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VMenuDId generated by hbm2java
 */
@Embeddable
public class VMenuDId implements java.io.Serializable {

	private String menuId;
	private String menuDId;
	private String menuDName;
	private String menuDJsMethod;
	private String menuDJsUrl;
	private String comId;
	private Integer orderId;
	private Boolean webAttention;
	private String webAttentionTips;
	private String getNumClass;
	private String getNumMethod;

	public VMenuDId() {
	}

	public VMenuDId(String menuId, String menuDId, String menuDName, String menuDJsMethod, String menuDJsUrl,
			String comId, Integer orderId, Boolean webAttention, String webAttentionTips, String getNumClass,
			String getNumMethod) {
		this.menuId = menuId;
		this.menuDId = menuDId;
		this.menuDName = menuDName;
		this.menuDJsMethod = menuDJsMethod;
		this.menuDJsUrl = menuDJsUrl;
		this.comId = comId;
		this.orderId = orderId;
		this.webAttention = webAttention;
		this.webAttentionTips = webAttentionTips;
		this.getNumClass = getNumClass;
		this.getNumMethod = getNumMethod;
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

	@Column(name = "order_id")
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "web_attention")
	public Boolean getWebAttention() {
		return this.webAttention;
	}

	public void setWebAttention(Boolean webAttention) {
		this.webAttention = webAttention;
	}

	@Column(name = "web_attention_tips", length = 1024)
	public String getWebAttentionTips() {
		return this.webAttentionTips;
	}

	public void setWebAttentionTips(String webAttentionTips) {
		this.webAttentionTips = webAttentionTips;
	}

	@Column(name = "get_num_class", length = 1024)
	public String getGetNumClass() {
		return this.getNumClass;
	}

	public void setGetNumClass(String getNumClass) {
		this.getNumClass = getNumClass;
	}

	@Column(name = "get_num_method", length = 1024)
	public String getGetNumMethod() {
		return this.getNumMethod;
	}

	public void setGetNumMethod(String getNumMethod) {
		this.getNumMethod = getNumMethod;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VMenuDId))
			return false;
		VMenuDId castOther = (VMenuDId) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null
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
				&& ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
						&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getWebAttention() == castOther.getWebAttention())
						|| (this.getWebAttention() != null && castOther.getWebAttention() != null
								&& this.getWebAttention().equals(castOther.getWebAttention())))
				&& ((this.getWebAttentionTips() == castOther.getWebAttentionTips())
						|| (this.getWebAttentionTips() != null && castOther.getWebAttentionTips() != null
								&& this.getWebAttentionTips().equals(castOther.getWebAttentionTips())))
				&& ((this.getGetNumClass() == castOther.getGetNumClass())
						|| (this.getGetNumClass() != null && castOther.getGetNumClass() != null
								&& this.getGetNumClass().equals(castOther.getGetNumClass())))
				&& ((this.getGetNumMethod() == castOther.getGetNumMethod())
						|| (this.getGetNumMethod() != null && castOther.getGetNumMethod() != null
								&& this.getGetNumMethod().equals(castOther.getGetNumMethod())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result + (getMenuDId() == null ? 0 : this.getMenuDId().hashCode());
		result = 37 * result + (getMenuDName() == null ? 0 : this.getMenuDName().hashCode());
		result = 37 * result + (getMenuDJsMethod() == null ? 0 : this.getMenuDJsMethod().hashCode());
		result = 37 * result + (getMenuDJsUrl() == null ? 0 : this.getMenuDJsUrl().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result + (getWebAttention() == null ? 0 : this.getWebAttention().hashCode());
		result = 37 * result + (getWebAttentionTips() == null ? 0 : this.getWebAttentionTips().hashCode());
		result = 37 * result + (getGetNumClass() == null ? 0 : this.getGetNumClass().hashCode());
		result = 37 * result + (getGetNumMethod() == null ? 0 : this.getGetNumMethod().hashCode());
		return result;
	}

}
