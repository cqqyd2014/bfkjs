package com.cqqyd2014.contract.contractList.ajax.action;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.Session;


import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/contract")
public class GetContractDetailAjaxAction  extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String contract_no;
	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	@Action(value = "get_contract_detail", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_contract_detail_in_session", privilege = "[00030002]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
Session session = HibernateSessionFactory.getSession();
		
		//com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			
			com.cqqyd2014.hibernate.dao.VContractDDAO vcmdao=new com.cqqyd2014.hibernate.dao.VContractDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VContractD> vcms=vcmdao.getArrayListByContractNo(session, com_id,contract_no);
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cms=new java.util.ArrayList<>();
			for (int i=0;i<vcms.size();i++){
				com.cqqyd2014.hibernate.entities.VContractD vcm=vcms.get(i);
				com.cqqyd2014.contract.model.ContractD cm=new com.cqqyd2014.contract.model.ContractD();
				cm.setAmount(vcm.getId().getNum().multiply(vcm.getId().getPrice()));
				cm.setGoods_id(vcm.getId().getGoodsId());
				cm.setGoods_name(vcm.getId().getCName());
				cm.setNum(vcm.getId().getNum());
				cm.setPrice(vcm.getId().getPrice());
				cm.setUnit(vcm.getId().getUnit());
				cm.setC_unit(vcm.getId().getCUnit());
				cm.setNum_in(vcm.getId().getNumIn());
				cm.setNum_out(vcm.getId().getNumOut());
				cms.add(cm);
			}
			session_http.put("temp_contract_d", cms);
				
				sm.setSuccess(true);
				sm.setO(cms);
			
			
			
			

		
		
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