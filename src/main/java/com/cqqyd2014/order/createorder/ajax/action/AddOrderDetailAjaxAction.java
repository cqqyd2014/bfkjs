package com.cqqyd2014.order.createorder.ajax.action;

import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.Map;


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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class AddOrderDetailAjaxAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String goods_id;
	String goods_name;
	java.math.BigDecimal c_price;
	java.math.BigDecimal c_count;
	
	String unit;
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

	public java.math.BigDecimal getC_price() {
		return c_price;
	}

	public void setC_price(java.math.BigDecimal c_price) {
		this.c_price = c_price;
	}

	public java.math.BigDecimal getC_count() {
		return c_count;
	}

	public void setC_count(java.math.BigDecimal c_count) {
		this.c_count = c_count;
	}


	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Action(value = "add_order_detail", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String add_order_detail() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis = (java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>) session_http
				.get("temp_order_detail");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			//得到商品基本信息
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO vgidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			com.cqqyd2014.hibernate.entities.VGoodsInfo vgi=vgidao.getGoodsInfo(goods_id, session, com_id);
			com.cqqyd2014.wh.model.GoodsInfo gi=com.cqqyd2014.wh.logic.GoodsInfoLogic.getModelFromView(vgi);
		//得到税费信息
			com.cqqyd2014.hibernate.dao.EcsTaxRateDAO etrdao = new com.cqqyd2014.hibernate.dao.EcsTaxRateDAO();
			java.util.ArrayList<java.math.BigDecimal> ert = etrdao.getRegTax(session, vgi.getId().getCHs());
		com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO fgpdao = new com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO();
		com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO vupadao=new com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO();
		java.util.LinkedHashMap<String, java.math.BigDecimal> odis_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(odis.toArray(), "getGoods_id", "getNum");
		// 先看看这个goods_id是否在订单明细中是否存在
		if (odis_map.get(goods_id)!=null) {
			//存在，更新数量
			Object[] od_array=com.cqqyd2014.util.ArrayListTools.searchStringField(odis.toArray(), "getGoods_id", goods_id);
			com.cqqyd2014.order.model.OrderDetail od=(com.cqqyd2014.order.model.OrderDetail)od_array[0];
			od.setNum(c_count);
			od.setTotal1(c_price.multiply(c_count));
			java.math.BigDecimal goods_finace_price=od.getPrice2();
			od.setTotal2(c_count.multiply(goods_finace_price));
			
			//不含增值税净价
			java.math.BigDecimal goods_finance_price_stock=goods_finace_price.divide(new java.math.BigDecimal("1").add(ert.get(0)),2, RoundingMode.HALF_DOWN);
			// 得到增值税
			java.math.BigDecimal ctax2 = goods_finance_price_stock.multiply(ert.get(0)).multiply(c_count);
			
			// 计算消费税cregtax2
			java.math.BigDecimal cregtax2 = goods_finace_price.multiply(ert.get(1)).multiply(c_count);
			od.setTax(ctax2);
			od.setReg_tax(cregtax2);
			od.setMemo(getWarehouseMemo(session,goods_id,c_count,com_id));
			
		}
		else {
			com.cqqyd2014.order.model.OrderDetail od=new com.cqqyd2014.order.model.OrderDetail();
			od.setCom_id(com_id);
			od.setDetail_id(com.cqqyd2014.util.StringUtil.getUUID());
			od.setGoods_id(goods_id);
			od.setMemo(getWarehouseMemo(session,goods_id,c_count,com_id));
			od.setNot_air(gi.isNot_air());
			od.setNum(c_count);
			od.setOrder_detail_dat(new java.util.Date());
			od.setOrder_no("");
			java.util.Date now=new java.util.Date();
			//得到客户价格
			com.cqqyd2014.hibernate.entities.VUserPriceAvailable vupa=vupadao.getGoodsInfos(session, goods_id, com_id, userid,now);
			
			od.setPrice(vupa.getId().getUserPrice());
			//得到财务入账价格
			com.cqqyd2014.hibernate.entities.FinanceGoodsPrice fgp=fgpdao.getEntityByGoodsIdDate(session, com_id, goods_id, now);
			if (fgp == null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(goods_id + "无可用的财务入账价格");
				
			}
			
			
			od.setPrice2(fgp.getPrice());
			
			java.math.BigDecimal goods_finace_price=od.getPrice2();
			od.setTotal1(c_count.multiply(c_price));
			od.setTotal2(c_count.multiply(goods_finace_price));
			
			//不含增值税净价
			java.math.BigDecimal goods_finance_price_stock=goods_finace_price.divide(new java.math.BigDecimal("1").add(ert.get(0)),2, RoundingMode.HALF_DOWN);
			// 得到增值税
			java.math.BigDecimal ctax2 = goods_finance_price_stock.multiply(ert.get(0)).multiply(c_count);
			
			// 计算消费税cregtax2
			java.math.BigDecimal cregtax2 = goods_finace_price.multiply(ert.get(1)).multiply(c_count);
			od.setTax(ctax2);
			od.setReg_tax(cregtax2);
			od.setUnit(gi.getUnit_name());
			odis.add(od);
			
			
			
		}
			
			
			
		

		session_http.put("temp_order_detail", odis);

			sm.setO(odis);
			sm.setSuccess(true);
			
			tx.commit();
			// session.close();
		}

		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.toString());
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		
		msg=sm.toMap();
		return SUCCESS;
	}



	private String getWarehouseMemo(Session session,String goods_id,java.math.BigDecimal num,String com_id){
		VInventoryByGoodsIdAvailableDAO vidadao=new VInventoryByGoodsIdAvailableDAO();
		com.cqqyd2014.hibernate.entities.VInventoryByGoodsIdAvailable vida=vidadao.getObjectByGoodsIdNum(session, com_id, goods_id,num);
		if (vida==null){
			return "仓库无货";
		}
		else{
			return "现有库存"+num+"个";//+vida.getId().getSumAvailable()	);
		}
		
	}
}