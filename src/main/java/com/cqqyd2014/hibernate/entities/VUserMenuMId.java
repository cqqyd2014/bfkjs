package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VUserMenuMId generated by hbm2java
 */
@Embeddable
public class VUserMenuMId implements java.io.Serializable {

	private String menuId;
	private String menuName;
	private String comId;
	private String desc;
	private String userId;

	public VUserMenuMId() {
	}

	public VUserMenuMId(String menuId, String menuName, String comId, String desc, String userId) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.comId = comId;
		this.desc = desc;
		this.userId = userId;
	}

	@Column(name = "menu_id", length = 4)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "menu_name", length = 45)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "desc", length = 45)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
		if (!(other instanceof VUserMenuMId))
			return false;
		VUserMenuMId castOther = (VUserMenuMId) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null
				&& castOther.getMenuId() != null && this.getMenuId().equals(castOther.getMenuId())))
				&& ((this.getMenuName() == castOther.getMenuName()) || (this.getMenuName() != null
						&& castOther.getMenuName() != null && this.getMenuName().equals(castOther.getMenuName())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getDesc() == castOther.getDesc()) || (this.getDesc() != null && castOther.getDesc() != null
						&& this.getDesc().equals(castOther.getDesc())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result + (getMenuName() == null ? 0 : this.getMenuName().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getDesc() == null ? 0 : this.getDesc().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}
