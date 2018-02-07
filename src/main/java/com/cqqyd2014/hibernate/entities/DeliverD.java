package com.cqqyd2014.hibernate.entities;
// Generated 2018-1-24 14:27:47 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DeliverD generated by hbm2java
 */
@Entity
@Table(name = "deliver_d", schema = "public")
public class DeliverD implements java.io.Serializable {

	private DeliverDId id;
	private String orderNo;
	private String seq;
	private String goodsId;
	private String goodsBarcode;
	private BigDecimal netWeight;
	private BigDecimal grossWeight;
	private BigDecimal packageWeight;
	private Boolean returned;
	private String returnedUserid;
	private Date returnedDat;
	private String returnedMemo;

	public DeliverD() {
	}

	public DeliverD(DeliverDId id, String orderNo, String seq, String goodsId, String goodsBarcode) {
		this.id = id;
		this.orderNo = orderNo;
		this.seq = seq;
		this.goodsId = goodsId;
		this.goodsBarcode = goodsBarcode;
	}

	public DeliverD(DeliverDId id, String orderNo, String seq, String goodsId, String goodsBarcode,
			BigDecimal netWeight, BigDecimal grossWeight, BigDecimal packageWeight, Boolean returned,
			String returnedUserid, Date returnedDat, String returnedMemo) {
		this.id = id;
		this.orderNo = orderNo;
		this.seq = seq;
		this.goodsId = goodsId;
		this.goodsBarcode = goodsBarcode;
		this.netWeight = netWeight;
		this.grossWeight = grossWeight;
		this.packageWeight = packageWeight;
		this.returned = returned;
		this.returnedUserid = returnedUserid;
		this.returnedDat = returnedDat;
		this.returnedMemo = returnedMemo;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "comId", column = @Column(name = "com_id", nullable = false, length = 4)),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", nullable = false, length = 36)) })
	public DeliverDId getId() {
		return this.id;
	}

	public void setId(DeliverDId id) {
		this.id = id;
	}

	@Column(name = "order_no", nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "seq", nullable = false, length = 4)
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Column(name = "goods_id", nullable = false, length = 45)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "goods_barcode", nullable = false, length = 22)
	public String getGoodsBarcode() {
		return this.goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	@Column(name = "net_weight", precision = 131089, scale = 0)
	public BigDecimal getNetWeight() {
		return this.netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	@Column(name = "gross_weight", precision = 131089, scale = 0)
	public BigDecimal getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "package_weight", precision = 131089, scale = 0)
	public BigDecimal getPackageWeight() {
		return this.packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	@Column(name = "returned")
	public Boolean getReturned() {
		return this.returned;
	}

	public void setReturned(Boolean returned) {
		this.returned = returned;
	}

	@Column(name = "returned_userid", length = 36)
	public String getReturnedUserid() {
		return this.returnedUserid;
	}

	public void setReturnedUserid(String returnedUserid) {
		this.returnedUserid = returnedUserid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "returned_dat", length = 35)
	public Date getReturnedDat() {
		return this.returnedDat;
	}

	public void setReturnedDat(Date returnedDat) {
		this.returnedDat = returnedDat;
	}

	@Column(name = "returned_memo", length = 512)
	public String getReturnedMemo() {
		return this.returnedMemo;
	}

	public void setReturnedMemo(String returnedMemo) {
		this.returnedMemo = returnedMemo;
	}

}
