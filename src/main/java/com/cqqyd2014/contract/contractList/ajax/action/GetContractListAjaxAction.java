package com.cqqyd2014.contract.contractList.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/contract")
public class GetContractListAjaxAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	@Action(value = "get_contract_array_list", results = {
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {

					@InterceptorRef("defaultStack"), @InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_contract_array_list", privilege = "[00030002]", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		// com.cqqyd2014.util.AjaxSuccessMessage sm=new
		// com.cqqyd2014.util.AjaxSuccessMessage();
		try {

			com.cqqyd2014.hibernate.dao.VContractMDAO vcmdao = new com.cqqyd2014.hibernate.dao.VContractMDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractM> vcms = vcmdao.getListAll(session, com_id);
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractM> cms = new java.util.ArrayList<>();
			for (int i = 0; i < vcms.size(); i++) {
				com.cqqyd2014.hibernate.entities.VContractM vcm = vcms.get(i);
				com.cqqyd2014.contract.model.ContractM cm = new com.cqqyd2014.contract.model.ContractM();
				cm.setContract_no(vcm.getId().getContractNo());
				cm.setAmount(vcm.getId().getAmount());
				cm.setPaper_dat(vcm.getId().getPaperDat());
				cm.setSupply_name(vcm.getId().getSupplyName());
				cm.setPrint_count(vcm.getId().getPrintCount());
				cm.setLast_print_dat(vcm.getId().getLastPrintDat());
				cm.setArrival(vcm.getId().getArrival());
				cms.add(cm);
			}

			sm.setSuccess(true);
			sm.setO(cms);

			tx.commit();

		}

		catch (Exception e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}

			System.out.println(e.getMessage());

		} finally {
			HibernateSessionFactory.closeSession();
		}
		msg = sm.toMap();
		return "success";
	}
}
