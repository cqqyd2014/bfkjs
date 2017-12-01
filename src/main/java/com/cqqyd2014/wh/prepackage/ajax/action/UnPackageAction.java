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


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class UnPackageAction   extends UserLoginedAction {
	String prepackage_barcode;

	

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
	@Action(value = "un_package", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "un_package", privilege = "[00020005]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			//预包装条码作废
			com.cqqyd2014.hibernate.dao.PrePackageDAO ppdao=new com.cqqyd2014.hibernate.dao.PrePackageDAO();
			com.cqqyd2014.hibernate.entities.PrePackageM ppm=ppdao.getEntityByPrepackageBarcode(session, com_id, prepackage_barcode);
			ppm.setEffective(false);
			java.util.Date now=new java.util.Date();
			
			ppm.setUnPackageTime(now);
			ppm.setUnPackageUser(user_id);
			session.saveOrUpdate(ppm);
			//商品和库存还原
			
			com.cqqyd2014.hibernate.dao.PrePackageDDAO ppddao=new com.cqqyd2014.hibernate.dao.PrePackageDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageD> ppds=ppddao.getArrayListByPrepackageBarcode(session, com_id, prepackage_barcode);
			com.cqqyd2014.hibernate.dao.VGoodsDAO vddao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			for (int i=0;i<ppds.size();i++){
				com.cqqyd2014.hibernate.entities.PrePackageD ppd=ppds.get(i);
				com.cqqyd2014.wh.model.Goods gb=vddao.getSimpleModelByBarcode(session, com_id, ppd.getId().getGoodsBarcode());
				//库存
				com.cqqyd2014.wh.logic.Storage.PrePackItemUnPack(session, gb.getGoods_id(), gb.getWh_id(), new java.math.BigDecimal(1), com_id);
				//goods移动
				com.cqqyd2014.wh.logic.GoodsLogic.PrePacItemsUnPack(session, gb.getBarcode(), com_id,user_id);
				
				
			}

			sm.setSuccess(true);

			sm.setSound("picked_ok");

		tx.commit();

	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
		/*
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
			sm.setSound("error");
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
		}
		*/
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return SUCCESS;
	}
}
