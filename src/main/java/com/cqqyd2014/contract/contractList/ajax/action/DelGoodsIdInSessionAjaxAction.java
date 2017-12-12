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
public class DelGoodsIdInSessionAjaxAction   extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	String goods_id;

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	@Action(value = "del_goods_id_in_session", results = { @Result(type = "json", params = { "root", "msg" })}, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_contract_detail_in_session", privilege = "[00030002]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
//Session session = HibernateSessionFactory.getSession();
		
		
		try {
			
			
			
			
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds=(java.util.ArrayList<com.cqqyd2014.contract.model.ContractD>)session_http.get("temp_contract_d");
				
			//检测这个的值
			int flag=-1;
			
			for (int i=0;i<cds.size();i++){
				com.cqqyd2014.contract.model.ContractD cd=cds.get(i);
				if (goods_id.equals(cd.getGoods_id())){
					flag=i;
				}
			}
			if (flag==-1){
				
				sm.setSuccess(false);
			}
			else{
				//是否已经开始入库，已经入库的不能删除
				if (cds.get(flag).getNum_in().compareTo(new java.math.BigDecimal(0))==1){
					sm.setSuccess(false);
					sm.setBody("已经有入库的合同项目，不能删除");
				}
				else{
					cds.remove(flag);
					session_http.put("temp_contract_d", cds);
					sm.setSuccess(true);
					sm.setO(cds);
				}
				
			}
			
				
			
			
			
			

		
		
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
