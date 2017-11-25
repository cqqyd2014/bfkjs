package com.cqqyd2014.contract.contractList.ajax.action;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("json-default")  //表示继承的父包  
@Namespace(value="/contract") //表示当前Action所在命名空间  
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
	@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
public class GetContractListAjaxAction    extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_contract_list", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String get_contract_list() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		

Session session = HibernateSessionFactory.getSession();
Transaction tx = session.beginTransaction();
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			
			com.cqqyd2014.hibernate.dao.VContractMDAO vcmdao=new com.cqqyd2014.hibernate.dao.VContractMDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractM> vcms=vcmdao.getListAll(session, com_id);
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractM> cms=new java.util.ArrayList<>();
			for (int i=0;i<vcms.size();i++){
				com.cqqyd2014.hibernate.entities.VContractM vcm=vcms.get(i);
				com.cqqyd2014.contract.model.ContractM cm=new com.cqqyd2014.contract.model.ContractM();
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
		
		
	}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}
