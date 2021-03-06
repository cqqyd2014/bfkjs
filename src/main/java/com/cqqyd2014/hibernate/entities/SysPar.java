package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysPar generated by hbm2java
 */
@Entity
@Table(name = "sys_par", schema = "public")
public class SysPar implements java.io.Serializable {

	private String code;
	private String codeDesc;
	private String value;
	private String selectItems;

	public SysPar() {
	}

	public SysPar(String code) {
		this.code = code;
	}

	public SysPar(String code, String codeDesc, String value, String selectItems) {
		this.code = code;
		this.codeDesc = codeDesc;
		this.value = value;
		this.selectItems = selectItems;
	}

	@Id

	@Column(name = "code", unique = true, nullable = false, length = 45)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code_desc", length = 45)
	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "select_items", length = 1024)
	public String getSelectItems() {
		return this.selectItems;
	}

	public void setSelectItems(String selectItems) {
		this.selectItems = selectItems;
	}

}
