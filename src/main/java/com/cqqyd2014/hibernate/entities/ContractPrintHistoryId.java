package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ContractPrintHistoryId generated by hbm2java
 */
@Embeddable
public class ContractPrintHistoryId implements java.io.Serializable {

	private String comId;
	private String contractId;
	private Date printDat;

	public ContractPrintHistoryId() {
	}

	public ContractPrintHistoryId(String comId, String contractId, Date printDat) {
		this.comId = comId;
		this.contractId = contractId;
		this.printDat = printDat;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "contract_id", nullable = false, length = 45)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "print_dat", nullable = false, length = 35)
	public Date getPrintDat() {
		return this.printDat;
	}

	public void setPrintDat(Date printDat) {
		this.printDat = printDat;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ContractPrintHistoryId))
			return false;
		ContractPrintHistoryId castOther = (ContractPrintHistoryId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getContractId() == castOther.getContractId()) || (this.getContractId() != null
						&& castOther.getContractId() != null && this.getContractId().equals(castOther.getContractId())))
				&& ((this.getPrintDat() == castOther.getPrintDat()) || (this.getPrintDat() != null
						&& castOther.getPrintDat() != null && this.getPrintDat().equals(castOther.getPrintDat())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getContractId() == null ? 0 : this.getContractId().hashCode());
		result = 37 * result + (getPrintDat() == null ? 0 : this.getPrintDat().hashCode());
		return result;
	}

}
