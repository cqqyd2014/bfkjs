package com.cqqyd2014.wh.vol.addvol.ajax.action;

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


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class AddVolAddTempBarcodeAction   extends UserLoginedAction {
	String barcode;
	String contract_id;
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	@Action(value = "add_vol_add_temp_barcode", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_vol_add_temp_barcode", privilege = "[00020003]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
	// TODO Auto-generated method stub
	super.execute();
	sm.setAuth_success(true);
		
		Session session = HibernateSessionFactory.getSession();
		
		
		try {
			
			com.cqqyd2014.util.message.IfMessage ir=com.cqqyd2014.wh.logic.GoodsLogic.if_available_checksn(barcode);
			if (ir.isIf_ok()==false) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(ir.getMessage());
				
			}
			
			com.cqqyd2014.hibernate.dao.VGoodsDAO omdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			com.cqqyd2014.hibernate.entities.VGoods vg=omdao.getEntityViewByBarcode(session, com_id, barcode) ;
			com.cqqyd2014.wh.model.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(vg);
			//需要仓库库房，普通库位的记录
			java.util.ArrayList<String> whs=com.cqqyd2014.util.StringUtil.toArrayList("SUPPLY");
			java.util.ArrayList<String> storages=com.cqqyd2014.util.StringUtil.toArrayList("DEFAUL");
			ir=com.cqqyd2014.wh.logic.GoodsLogic.if_available_in_wh_storage(session, g, whs,null, storages,null);
			
			if (!ir.isIf_ok()){
				//库位和库房不合要求
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(ir.getMessage());
				
			}
			
			
			
			
			
			
			
				
				@SuppressWarnings("unchecked")
				java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
						.get("temp_add_vol_barcode");
				
				//4、本次入库不要重复录入
				for (int i=0;i<odis.size();i++){
					
					
					String old_sn=odis.get(i).getBarcode();
					if (old_sn.equals(barcode)){
						throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("不要重复录入该条码");
						
					}
				}

				
				//com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
				//com.cqqyd2014.hibernate.entities.Goods g=gdao.getSnItem(session, sn, com_id);
				String goodsId=g.getGoods_id();
				//4、该商品是否在合同中
				
				com.cqqyd2014.hibernate.dao.ContractDDAO cddao=new com.cqqyd2014.hibernate.dao.ContractDDAO();
				com.cqqyd2014.hibernate.entities.ContractD cd=cddao.getObjectByGoodsId(session, com_id, contract_id, goodsId);
				if (cd==null){
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该商品不在合同中");
				}
				
				
				
				
				
				odis.add(g);
				
				session_http.put("temp_add_vol_barcode", odis);
				sm.setO(odis);
				sm.setO2(odis.size());
				
				sm.setSuccess(true);
				sm.setSound("picked_ok");
			
			sm.setBody(barcode+"已经录入");
			
			
			

		
		
	}

		catch (HibernateException e) {
			
			//if (null != tx) {
			//	tx.rollback();// 撤销事务

			//}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
			sm.setSound("error");
			
		}
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
			sm.setSound("error");
			
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		sm.setSuccess(true);
		msg=sm.toMap();
		return SUCCESS;
	}
}