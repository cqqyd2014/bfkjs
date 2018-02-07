package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ContractMId generated by hbm2java
 */
@Embeddable
public class ContractMId implements java.io.Serializable {

	private String contractNo;
	private String comId;

	public ContractMId() {
	}

	public ContractMId(String contractNo, String comId) {
		this.contractNo = contractNo;
		this.comId = comId;
	}

	@Column(name = "contract_no", nullable = false, length = 45)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
		if (!(other instanceof ContractMId))
			return false;
		ContractMId castOther = (ContractMId) other;

		return ((this.getContractNo() == castOther.getContractNo()) || (this.getContractNo() != null
				&& castOther.getContractNo() != null && this.getContractNo().equals(castOther.getContractNo())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getContractNo() == null ? 0 : this.getContractNo().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		return result;
	}

}
