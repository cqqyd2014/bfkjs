package com.cqqyd2014.wh.prepackage.ajax.action;


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
import com.cqqyd2014.wh.logic.PrePackageDLogic;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class SavePrepackageAction   extends UserLoginedAction {
	String prepackage_barcode;

	String wh_id;
	public String getWh_id() {
		return wh_id;
	}

	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}

	public String getPrepackage_barcode() {
		return prepackage_barcode;
	}

	public void setPrepackage_barcode(String prepackage_barcode) {
		this.prepackage_barcode = prepackage_barcode;
	}
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "save_prepackage", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "get_unprinted_prepackage_barcode", privilege = "[00020005]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
//将预包装数据保存到数据库。
			
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.wh.model.Goods > odis = (java.util.ArrayList<com.cqqyd2014.wh.model.Goods>) session_http
					.get("temp_add_prepackage_barcode");
			
			//测试是否都在一个仓库
			java.util.ArrayList<String> whs=new java.util.ArrayList<>();
			
			java.util.ArrayList<com.cqqyd2014.wh.model.PrePackageD> ppds=new java.util.ArrayList<>();
			
			com.cqqyd2014.hibernate.dao.GoodsInfoDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsInfoDAO();
			
			for (int i=0;i<odis.size();i++){
				com.cqqyd2014.wh.model.Goods odi=odis.get(i);
				if (!whs.contains(odi.getWh_id())){
					whs.add(odi.getWh_id());
				}
				if (whs.size()>1){
					//商品在不同仓库
					throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("预打包商品必须在同一个仓库");
					
				}
				
			com.cqqyd2014.wh.model.PrePackageD ppd=new com.cqqyd2014.wh.model.PrePackageD();
			ppd.setCom_id(com_id);
			ppd.setGoods_barcode(odi.getBarcode());
			ppd.setGoods_id(odi.getGoods_id());
			ppd.setPackege_barcode(prepackage_barcode);
			com.cqqyd2014.hibernate.entities.GoodsInfo gi=gdao.getEntityByGoodsId(session, odi.getGoods_id(), com_id);
			String goods_name=gi.getCName();
			ppd.setGoods_name(goods_name);
			ppd.setPackage_weight(gi.getPackageWeight());
			ppds.add(ppd);
				//移动商品
				
				com.cqqyd2014.wh.logic.GoodsLogic.PrePacItemsInWh(session, odi.getBarcode(), com_id,user_id);
				//预包装减库存（StorageDetail）
				com.cqqyd2014.wh.logic.Storage.PrePackItemsInWh(session, odi.getGoods_id(), odi.getWh_id(), new java.math.BigDecimal(1), com_id);
			
				PrePackageDLogic.save(session, ppd);
			}
			
			
			
			//更新包裹主体数据，打包时间，打包标志
			com.cqqyd2014.hibernate.dao.PrePackageDAO ppdao=new com.cqqyd2014.hibernate.dao.PrePackageDAO();
			com.cqqyd2014.hibernate.entities.PrePackageM ppm=ppdao.getEntityByPrepackageBarcode(session, com_id, prepackage_barcode);
			/*
			
			com.cqqyd2014.wh.model.PrePackageM ppm=ppml.getModelFromEntity(ppd);
			ppm.setNum(new java.math.BigDecimal(ppds.size()));
			ppm.setPackage_userid(user_id);
			ppm.setPrepackage_dat(new java.util.Date());
			ppm.setMemo_barcodes(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(ppds.toArray(), "getPackege_barcode"));
			ppm.setMemo_names(com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(ppds.toArray(), "getGoods_name"));
			ppm.setPackage_weight(com.cqqyd2014.util.ArrayListTools.sumFields(ppds, "getPackage_weight"));
			ppm.setPackaged(true);
			ppm.setWh_id(wh_id);
			ppm.setPpds(ppds);
			
			
			ppml.save(session, ppm);
			
			*/
			ppm.setNum(new java.math.BigDecimal(ppds.size()));
			ppm.setPackageTime(new java.util.Date());
			ppm.setMemoBarcodes(com.cqqyd2014.util.ArrayTools.convertFieldToArrayString(ppds, "getPackege_barcode",String.class));
			ppm.setMemoNames(com.cqqyd2014.util.ArrayTools.convertFieldToArrayString(ppds, "getGoods_name",String.class));
			ppm.setPackageWeight(com.cqqyd2014.util.ArrayListTools.sumFields(ppds, "getPackage_weight"));
			ppm.setPackaged(true);
			ppm.setWhId(wh_id);
			session.saveOrUpdate(ppm);
			
			
			
			odis.clear();
			
			session_http.put("temp_add_prepackage_barcode", odis);
			sm.setSuccess(true);
			sm.setBody("预包装成功");
			sm.setSound("picked_ok");
			

		tx.commit();
		
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		sm.setSuccess(false);
		sm.setBody(e.toString());
		
	}
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessage());
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSound("error");
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return SUCCESS;
	}
}
