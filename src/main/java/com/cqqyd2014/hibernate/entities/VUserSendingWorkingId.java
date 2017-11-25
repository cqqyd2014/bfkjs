package com.cqqyd2014.hibernate.entities;
// Generated 2017-11-19 15:08:19 by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VUserSendingWorkingId generated by hbm2java
 */
@Embeddable
public class VUserSendingWorkingId implements java.io.Serializable {

	private String comId;
	private Long sendingCount;
	private String id;
	private BigDecimal sendWeighting;

	public VUserSendingWorkingId() {
	}

	public VUserSendingWorkingId(String comId, Long sendingCount, String id, BigDecimal sendWeighting) {
		this.comId = comId;
		this.sendingCount = sendingCount;
		this.id = id;
		this.sendWeighting = sendWeighting;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "sending_count")
	public Long getSendingCount() {
		return this.sendingCount;
	}

	public void setSendingCount(Long sendingCount) {
		this.sendingCount = sendingCount;
	}

	@Column(name = "id", length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "send_weighting", precision = 131089, scale = 0)
	public BigDecimal getSendWeighting() {
		return this.sendWeighting;
	}

	public void setSendWeighting(BigDecimal sendWeighting) {
		this.sendWeighting = sendWeighting;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUserSendingWorkingId))
			return false;
		VUserSendingWorkingId castOther = (VUserSendingWorkingId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getSendingCount() == castOther.getSendingCount())
						|| (this.getSendingCount() != null && castOther.getSendingCount() != null
								&& this.getSendingCount().equals(castOther.getSendingCount())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null
						&& this.getId().equals(castOther.getId())))
				&& ((this.getSendWeighting() == castOther.getSendWeighting())
						|| (this.getSendWeighting() != null && castOther.getSendWeighting() != null
								&& this.getSendWeighting().equals(castOther.getSendWeighting())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getSendingCount() == null ? 0 : this.getSendingCount().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getSendWeighting() == null ? 0 : this.getSendWeighting().hashCode());
		return result;
	}

}
