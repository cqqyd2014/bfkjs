package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VUserMenuD generated by hbm2java
 */
@Entity
@Table(name = "v_user_menu_d", schema = "public")
public class VUserMenuD implements java.io.Serializable {

	private VUserMenuDId id;

	public VUserMenuD() {
	}

	public VUserMenuD(VUserMenuDId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id")),
			@AttributeOverride(name = "menuId", column = @Column(name = "menu_id", length = 4)),
			@AttributeOverride(name = "menuDId", column = @Column(name = "menu_d_id", length = 4)),
			@AttributeOverride(name = "menuDName", column = @Column(name = "menu_d_name", length = 45)),
			@AttributeOverride(name = "menuDJsMethod", column = @Column(name = "menu_d_js_method", length = 45)),
			@AttributeOverride(name = "menuDJsUrl", column = @Column(name = "menu_d_js_url", length = 256)),
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", length = 4)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", length = 45)) })
	public VUserMenuDId getId() {
		return this.id;
	}

	public void setId(VUserMenuDId id) {
		this.id = id;
	}

}
