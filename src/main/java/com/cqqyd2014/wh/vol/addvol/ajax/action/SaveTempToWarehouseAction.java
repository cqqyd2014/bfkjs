package com.cqqyd2014.wh.vol.addvol.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.wh.logic.GoodsLogic;
import com.cqqyd2014.wh.logic.IntoWh;
import com.cqqyd2014.wh.logic.Storage;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class SaveTempToWarehouseAction   extends UserLoginedAction {
	String wh_id;
	String contract_id;
	String goods_id;
	String in_date;
	String memo;
	String type_id;


	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "save_temp_to_warehouse", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "save_temp_to_warehouse", privilege = "[00020003]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
	// TODO Auto-generated method stub
	super.execute();
	sm.setAuth_success(true);
		
//com.cqqyd2014.hibernate.dao.ContractMDAO cmdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
		
		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {

			//生成入库记录
			com.cqqyd2014.hibernate.entities.IntoWhM ih=new com.cqqyd2014.hibernate.entities.IntoWhM();
			com.cqqyd2014.hibernate.entities.IntoWhMId ihid=new com.cqqyd2014.hibernate.entities.IntoWhMId();
			String uuid_in=com.cqqyd2014.util.StringUtil.getUUID();
			ihid.setComId(com_id);
			ihid.setUuid(uuid_in);
			ih.setId(ihid);
			ih.setContractId(contract_id);
			java.util.Date in_date_d=com.cqqyd2014.util.DateUtil.FullStringToJDate(in_date+" 00:00:00");
			ih.setInDat(in_date_d);
			ih.setMemo(memo);
			ih.setOpDat(new java.util.Date());
			ih.setWhId(wh_id);
			ih.setUserId(user_id);

			
			
			session.save(ih);
			session.flush();
			
			//java.util.HashMap<String, java.math.BigDecimal> b=com.cqqyd2014.bfkjs.logic.HwChange.num_change(session, bzcbm, hw_type, com_id, goods_id, "in", (num),uuid_in,null,null,in_date_d);
			
			
			
			
			//从session中得到列表
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
					.get("temp_add_vol_barcode");
			
			//将odis按照商品分类，并放入map中
			java.util.LinkedHashMap<String, java.math.BigDecimal> goods_in_session_map=com.cqqyd2014.util.ArrayListTools.getKeyCount(odis, "getGoods_id");
			//需要发出的商品列表
			
			com.cqqyd2014.hibernate.dao.VContractDDAO cddao=new com.cqqyd2014.hibernate.dao.VContractDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> vcds=cddao.getArrayListByContractNo(session, com_id, contract_id);
			
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds=com.cqqyd2014.contract.logic.ContractDLogic.getArrayListModelFromArrayListView(vcds);
			java.util.LinkedHashMap<String, java.math.BigDecimal> need_in_map=com.cqqyd2014.util.HashMapTools.convertArrayListStringNToMap(cds, "getGoods_id", "getWait_for_arrival");
			
			if (com.cqqyd2014.util.HashMapTools.check_if_contains(need_in_map, goods_in_session_map)) {
				//可以入库,处理单个商品
				for (int i=0;i<odis.size();i++) {
					com.cqqyd2014.wh.model.Goods g=odis.get(i);
					com.cqqyd2014.hibernate.entities.Goods g_h=GoodsLogic.NewItemToWh(session, g.getBarcode(), wh_id, in_date_d, com_id,user_id);
					g_h.setContractId(contract_id);
					g_h.setIntoWhUuid(uuid_in);
					com.cqqyd2014.hibernate.entities.VContractD vcd=cddao.getViewByContractNoGoodsId(session, contract_id, g.getGoods_id(), com_id);
					g_h.setContractPrice(vcd.getId().getPrice());
					session.saveOrUpdate(g_h);
					
					
				}
				//批量处理库存变化
				for (Map.Entry<String, java.math.BigDecimal> entry : goods_in_session_map.entrySet()) {  
					  
				    String goodsId=entry.getKey();
				    java.math.BigDecimal nmu=entry.getValue();
				    Storage.NewItemToWh(session, goodsId, wh_id, nmu, com_id);
					
					IntoWh.numChange(session,com_id,uuid_in,goodsId,nmu,"0001",user_id);
					
					
					
				}  
				session.flush();
				
				
			}
			else {
				//录入的商品超范围，只能是数量超了。
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("数量或者品种超合同范围");
			}
			
			
			
			
			

			
			sm.setSuccess(true);
			
			tx.commit();
			odis.clear();
			
			session_http.put("temp_add_vol_barcode", odis);
		}
		catch (HibernateException e) {
			
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} 
		
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
		}
		
		finally {
			HibernateSessionFactory.closeSession();
		}
		
		msg=sm.toMap();
		return SUCCESS;
	}
}