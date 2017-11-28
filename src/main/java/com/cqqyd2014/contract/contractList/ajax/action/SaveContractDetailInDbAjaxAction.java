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
public class SaveContractDetailInDbAjaxAction  extends ActionSupport {
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

	@Action(value = "save_contract_detail_in_db", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String save_contract_detail_in_db() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		java.math.BigDecimal amount=new java.math.BigDecimal("0");
		try {
			java.util.Date paper_date=com.cqqyd2014.util.DateUtil.FullStringToJDate(paper_dat+" 00:00:00");
			com.cqqyd2014.hibernate.dao.ContractMDAO mdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
			

			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds = (java.util.ArrayList<com.cqqyd2014.contract.model.ContractD>) session_http
					.get("temp_contract_d");
			com.cqqyd2014.hibernate.dao.ContractDDAO cddao = new com.cqqyd2014.hibernate.dao.ContractDDAO();
			amount=cddao.addOrUpdateDetailUsingArray(session, com_id, contract_no, cds);
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

		} finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
}
	}
