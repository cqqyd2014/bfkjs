package com.cqqyd2014.hibernate.entities;
// Generated 2017-12-16 20:52:26 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TaobaoGoodsDId generated by hbm2java
 */
@Embeddable
public class TaobaoGoodsDId implements java.io.Serializable {

	private String GId;
	private String comId;
	private String orderTypeCode;
	private String goodsId;

	public TaobaoGoodsDId() {
	}

	public TaobaoGoodsDId(String GId, String comId, String orderTypeCode, String goodsId) {
		this.GId = GId;
		this.comId = comId;
		this.orderTypeCode = orderTypeCode;
		this.goodsId = goodsId;
	}

	@Column(name = "g_id", nullable = false, length = 100)
	public String getGId() {
		return this.GId;
	}

	public void setGId(String GId) {
		this.GId = GId;
	}

	@Column(name = "com_id", nullable = false, length = 4)
	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Column(name = "order_type_code", nullable = false, length = 2)
	public String getOrderTypeCode() {
		return this.orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	@Column(name = "goods_id", nullable = false, length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaobaoGoodsDId))
			return false;
		TaobaoGoodsDId castOther = (TaobaoGoodsDId) other;

		return ((this.getGId() == castOther.getGId())
				|| (this.getGId() != null && castOther.getGId() != null && this.getGId().equals(castOther.getGId())))
				&& ((this.getComId() == castOther.getComId()) || (this.getComId() != null
						&& castOther.getComId() != null && this.getComId().equals(castOther.getComId())))
				&& ((this.getOrderTypeCode() == castOther.getOrderTypeCode())
						|| (this.getOrderTypeCode() != null && castOther.getOrderTypeCode() != null
								&& this.getOrderTypeCode().equals(castOther.getOrderTypeCode())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId().equals(castOther.getGoodsId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getGId() == null ? 0 : this.getGId().hashCode());
		result = 37 * result + (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result + (getOrderTypeCode() == null ? 0 : this.getOrderTypeCode().hashCode());
		result = 37 * result + (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		return result;
	}

}
