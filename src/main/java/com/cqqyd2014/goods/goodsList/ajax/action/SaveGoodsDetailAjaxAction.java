package com.cqqyd2014.goods.goodsList.ajax.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.dao.VInventoryByGoodsIdAvailableDAO;
import com.cqqyd2014.util.message.IfMessage;
import com.cqqyd2014.util.taobao.OrderAutoAnalysisException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/goods")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class SaveGoodsDetailAjaxAction  extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}
	String spec;
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	String goods_id;
	String goods_name;
	boolean in_use;
	String goods_name_short;
	String unit;
	String country;
	String hs_code;
	String barcode;
	boolean not_air;
	String memo;
	java.math.BigDecimal net_weight;
	java.math.BigDecimal gross_weight;
	java.math.BigDecimal package_weight;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public boolean isIn_use() {
		return in_use;
	}
	public void setIn_use(boolean in_use) {
		this.in_use = in_use;
	}
	public String getGoods_name_short() {
		return goods_name_short;
	}
	public void setGoods_name_short(String goods_name_short) {
		this.goods_name_short = goods_name_short;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHs_code() {
		return hs_code;
	}
	public void setHs_code(String hs_code) {
		this.hs_code = hs_code;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public boolean isNot_air() {
		return not_air;
	}
	public void setNot_air(boolean not_air) {
		this.not_air = not_air;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public java.math.BigDecimal getNet_weight() {
		return net_weight;
	}
	public void setNet_weight(java.math.BigDecimal net_weight) {
		this.net_weight = net_weight;
	}
	public java.math.BigDecimal getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(java.math.BigDecimal gross_weight) {
		this.gross_weight = gross_weight;
	}
	public java.math.BigDecimal getPackage_weight() {
		return package_weight;
	}
	public void setPackage_weight(java.math.BigDecimal package_weight) {
		this.package_weight = package_weight;
	}
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String sn_code;
	public String getSn_code() {
		return sn_code;
	}
	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}
	@Action(value = "save_goods_detail", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String save_goods_detail() {
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
Session session = HibernateSessionFactory.getSession();
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.wh.model.GoodsInfo gi=new com.cqqyd2014.wh.model.GoodsInfo();
			gi.setBarcode(barcode);
			gi.setCom_id(com_id);
			gi.setCountry_id(country);
			gi.setGoods_id(goods_id);
			gi.setGross_weight(gross_weight);
			gi.setHs_code(hs_code);
			gi.setIn_use(in_use);
			gi.setMemo(memo);
			gi.setNet_weight(net_weight);
			gi.setNot_air(not_air);
			gi.setPackage_weight(package_weight);
			gi.setShort_name(goods_name_short);
			
			if (sn_code.equals("")){
				
				String new_sn_code=com.cqqyd2014.wh.logic.GoodsInfoLogic.getNewSnCode(session, com_id);
				gi.setSn_code(new_sn_code);
				
			}
			else{
				gi.setSn_code(sn_code);
			}
			gi.setSpec(spec);
			gi.setUnit_code(unit);
			com.cqqyd2014.wh.logic.GoodsInfoLogic.save(session, gi);
			
			
			
			
				
				sm.setSuccess(true);
				sm.setO(gi);
			
			
			tx.commit();
			

		
		
	}

	catch (HibernateException e) {
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		
		System.out.println(e.getMessage());
		sm.setSuccess(false);
		sm.setBody(e.toString());
		
		
	}
		finally {
			HibernateSessionFactory.closeSession();
		}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}
