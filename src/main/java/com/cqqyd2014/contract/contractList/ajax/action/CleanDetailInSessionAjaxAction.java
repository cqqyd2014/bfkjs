package com.cqqyd2014.contract.contractList.ajax.action;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/contract")
public class CleanDetailInSessionAjaxAction   extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Action(value = "clean_detail_in_session", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_contract_detail_in_session", privilege = "[00050003]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
//Session session = HibernateSessionFactory.getSession();
		
		
		
        
        
        try {
                
                
                java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds= new java.util.ArrayList<com.cqqyd2014.contract.model.ContractD>();
                        
                session_http.put("temp_contract_d", cds);
                        sm.setSuccess(true);
                        sm.setO(cds);
                        
                
                
                
                

        
        
}



	catch (HibernateException e) {
		
		
		
		System.out.println(e.getMessage());
		
		
	}

		
		finally {
			HibernateSessionFactory.closeSession();
		}
        msg=sm.toMap();
		return "success";
	}

}