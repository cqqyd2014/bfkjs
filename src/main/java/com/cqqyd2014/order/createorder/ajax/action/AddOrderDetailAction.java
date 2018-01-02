package com.cqqyd2014.order.createorder.ajax.action;

import java.math.RoundingMode;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.dao.VInventoryByGoodsIdAvailableDAO;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class AddOrderDetailAction extends UserLoginedAction {
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

	@Action(value = "add_order_detail", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_goods_info", privilege = "[00010001]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> odis = (java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>) session_http
				.get("temp_order_detail");
		session = HibernateSessionFactory.getSession();
		
		
		try {
			//得到商品基本信息
			//com.cqqyd2014.hibernate.dao.VGoodsInfoDAO vgidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			com.cqqyd2014.hibernate.entities.VGoodsInfo vgi=com.cqqyd2014.hibernate.dao.VGoodsInfoDAO.getGoodsInfo(goods_id, session, com_id);
			com.cqqyd2014.wh.model.GoodsInfo gi=com.cqqyd2014.wh.logic.GoodsInfoLogic.getModelFromView(vgi);
		//得到税费信息
			//com.cqqyd2014.hibernate.dao.EcsTaxRateDAO etrdao = new com.cqqyd2014.hibernate.dao.EcsTaxRateDAO();
			java.util.ArrayList<java.math.BigDecimal> ert = com.cqqyd2014.hibernate.dao.EcsTaxRateDAO.getRegTax(session, vgi.getId().getCHs());
		//com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO fgpdao = new com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO();
		//com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO vupadao=new com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO();
		java.util.LinkedHashMap<String, java.math.BigDecimal> odis_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(odis, "getGoods_id", "getNum");
		// 先看看这个goods_id是否在订单明细中是否存在
		if (odis_map.get(goods_id)!=null) {
			//存在，更新数量
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods_by_goodsid=(java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail>)
					com.cqqyd2014.util.ArrayListTools.searchStringField(odis, "getGoods_id", goods_id);
			com.cqqyd2014.order.model.OrderDetail od=ods_by_goodsid.get(0);
			java.math.BigDecimal new_count=od.getNum().add(c_count);
			od.setNum(new_count);
			od.setTotal1(c_price.multiply(new_count));
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
			od.setGoods_name(gi.getGoods_name());
			od.setMemo(getWarehouseMemo(session,goods_id,c_count,com_id));
			od.setNot_air(gi.isNot_air());
			od.setNum(c_count);
			od.setOrder_detail_dat(new java.util.Date());
			od.setOrder_no("");
			java.util.Date now=new java.util.Date();
			//得到客户价格
			com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO vupdao=new com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO();
			com.cqqyd2014.usergroup.model.UserPrice vupa=vupdao.getGoodsInfo(session, goods_id, com_id, user_id,now);
			
			od.setPrice(vupa.getPrice());
			//得到财务入账价格
			com.cqqyd2014.hibernate.entities.FinanceGoodsPrice fgp=com.cqqyd2014.hibernate.dao.FinanceGoodsPriceDAO.getEntityByGoodsIdDate(session, com_id, goods_id, now);
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
			sm.setBody(e.getMessage());
			
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