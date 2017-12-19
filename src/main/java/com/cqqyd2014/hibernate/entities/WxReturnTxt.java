package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxReturnTxt generated by hbm2java
 */
@Entity
@Table(name = "wx_return_txt", schema = "public")
public class WxReturnTxt implements java.io.Serializable {

	private String txtId;
	private String txtText;
	private String comId;

	public WxReturnTxt() {
	}

	public WxReturnTxt(String txtId) {
		this.txtId = txtId;
	}

	public WxReturnTxt(String txtId, String txtText, String comId) {
		this.txtId = txtId;
		this.txtText = txtText;
		this.comId = comId;
	}

	@Id

	@Column(name = "txt_id", unique = true, nullable = false, length = 45)
	public String getTxtId() {
		return this.txtId;
	}

	public void setTxtId(String txtId) {
		this.txtId = txtId;
	}

	@Column(name = "txt_text")
	public String getTxtText() {
		return this.txtText;
	}

	public void setTxtText(String txtText) {
		this.txtText = txtText;
	}

	@Column(name = "com_id", length = 45)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

}
