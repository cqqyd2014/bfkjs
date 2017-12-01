package com.cqqyd2014.contract.contractList.ajax.action;
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
@Namespace("/contract")
public class PrintContractBarcodeAjaxAction   extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String contract_id;

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	@Action(value = "print_contract_barcode", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_contract_detail_in_session", privilege = "[00050003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.ContractDDAO cddao=new com.cqqyd2014.hibernate.dao.ContractDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractD> cds=cddao.getListByContractId(session, com_id, contract_id);
			int total=0;
			for (int i=0;i<cds.size();i++){
				com.cqqyd2014.hibernate.entities.ContractD cd=cds.get(i);
				
			com.cqqyd2014.wh.logic.GoodsLogic.makeBarcode(session, com_id, cd.getId().getGoodsId(), cd.getNum().intValue(),user_id,contract_id,cd.getPrice());
			total=total+cd.getNum().intValue();
			}
			//记录到打印日志，更新主表
			com.cqqyd2014.hibernate.entities.ContractPrintHistory cph=new com.cqqyd2014.hibernate.entities.ContractPrintHistory();
			
			java.util.Date date=new java.util.Date();
			cph.setId(new com.cqqyd2014.hibernate.entities.ContractPrintHistoryId(com_id, contract_id, date));
			cph.setPrintNum(new java.math.BigDecimal(total));
			session.saveOrUpdate(cph);
			com.cqqyd2014.hibernate.dao.ContractMDAO cmdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
			cmdao.updateLastPrintBarcode(session, com_id, contract_id, date);
			sm.setSuccess(true);
			
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
			finally {
				HibernateSessionFactory.closeSession();
			}
		
		
		msg=sm.toMap();
		return "success";
}


}
