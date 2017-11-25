package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SysRole generated by hbm2java
 */
@Entity
@Table(name = "sys_role", schema = "public")
public class SysRole implements java.io.Serializable {

	private SysRoleId id;
	private String roleName;

	public SysRole() {
	}

	public SysRole(SysRoleId id) {
		this.id = id;
	}

	public SysRole(SysRoleId id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false, length = 45)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 45)) })
	public SysRoleId getId() {
		return this.id;
	}

	public void setId(SysRoleId id) {
		this.id = id;
	}

	@Column(name = "role_name", length = 45)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
