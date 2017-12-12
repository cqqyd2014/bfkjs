package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-13 2:42:12 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VPrepackGoodsNumId generated by hbm2java
 */
@Embeddable
public class VPrepackGoodsNumId implements java.io.Serializable {

	private String comId;
	private String goodsId;
	private String PSn;
	private Long num;

	public VPrepackGoodsNumId() {
	}

	public VPrepackGoodsNumId(String comId, String goodsId, String PSn, Long num) {
		this.comId = comId;
		this.goodsId = goodsId;
		this.PSn = PSn;
		this.num = num;
	}

	@Column(name = "com_id", length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "goods_id", length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "p_sn", length = 18)
	public String getPSn() {
		return this.PSn;
	}

	public void setPSn(String PSn) {
		this.PSn = PSn;
	}

	@Column(name = "num")
	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VPrepackGoodsNumId))
			return false;
		VPrepackGoodsNumId castOther = (VPrepackGoodsNumId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null && castOther.getComId() != null
				&& this.getComId().equals(castOther.getComId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getPSn() == castOther.getPSn()) || (this.getPSn() != null && castOther.getPSn() != null
						&& this.getPSn().equals(castOther.getPSn())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null
						&& this.getNum().equals(castOther.getNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result + (getPSn() == null ? 0 : this.getPSn().hashCode());
		result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
		return result;
	}

}
