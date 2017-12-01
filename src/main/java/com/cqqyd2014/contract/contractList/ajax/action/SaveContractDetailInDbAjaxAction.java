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
public class SaveContractDetailInDbAjaxAction  extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String contract_no;
	String supply_id;
	String paper_dat;
	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getSupply_id() {
		return supply_id;
	}

	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}

	public String getPaper_dat() {
		return paper_dat;
	}

	public void setPaper_dat(String paper_dat) {
		this.paper_dat = paper_dat;
	}

	@Action(value = "save_contract_detail_in_db", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_contract_detail_in_session", privilege = "[00030002]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		java.math.BigDecimal amount=new java.math.BigDecimal("0");
		try {
			java.util.Date paper_date=com.cqqyd2014.util.DateUtil.FullStringToJDate(paper_dat+" 00:00:00");
			com.cqqyd2014.hibernate.dao.ContractMDAO mdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
			

			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds = (java.util.ArrayList<com.cqqyd2014.contract.model.ContractD>) session_http
					.get("temp_contract_d");
			com.cqqyd2014.hibernate.dao.ContractDDAO cddao = new com.cqqyd2014.hibernate.dao.ContractDDAO();
			try {
				amount=cddao.addOrUpdateDetailUsingArray(session, com_id, contract_no, cds);
			}
			catch (Exception e) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(e.toString());
			}
			
			mdao.updateContractM(session, com_id, contract_no, supply_id, paper_date,amount,user_id);
			sm.setSuccess(true);
			sm.setO(cds);

			tx.commit();

		}

		catch (HibernateException e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		
		catch (com.cqqyd2014.util.exception.AjaxSuccessMessageException e) {
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}
