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
public class GetContractAjaxAction   extends ActionSupport {
	private Map<String, Object> msg;
	String contract_no;

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "get_contract", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String get_contract() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		

		Session session = HibernateSessionFactory.getSession();
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			
			com.cqqyd2014.hibernate.dao.ContractMDAO cmdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
			com.cqqyd2014.hibernate.entities.ContractM cm=cmdao.getObjectByContractId(session, com_id, contract_no);
				sm.setSuccess(true);
				sm.setO(cm);
	
		
	}

	catch (Exception e) {
		
		
		
		System.out.println(e.getMessage());
		
		
	}
		finally {
			HibernateSessionFactory.closeSession();
		}
		msg=sm.toMap();
		return "success";
	}
}
