package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-5 14:48:25 by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysRoleMenuDId generated by hbm2java
 */
@Embeddable
public class SysRoleMenuDId implements java.io.Serializable {

	private String roleId;
	private String menuId;
	private String menuDId;
	private String comId;

	public SysRoleMenuDId() {
	}

	public SysRoleMenuDId(String roleId, String menuId, String menuDId, String comId) {
		this.roleId = roleId;
		this.menuId = menuId;
		this.menuDId = menuDId;
		this.comId = comId;
	}

	@Column(name = "role_id", nullable = false, length = 45)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "menu_id", nullable = false, length = 4)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "menu_d_id", nullable = false, length = 4)
	public String getMenuDId() {
		return this.menuDId;
	}

	public void setMenuDId(String menuDId) {
		this.menuDId = menuDId;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRoleMenuDId))
			return false;
		SysRoleMenuDId castOther = (SysRoleMenuDId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId().equals(castOther.getMenuId())))
				&& ((this.getMenuDId() == castOther.getMenuDId()) || (this.getMenuDId() != null
						&& castOther.getMenuDId() != null && this.getMenuDId().equals(castOther.getMenuDId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result + (getMenuDId() == null ? 0 : this.getMenuDId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}