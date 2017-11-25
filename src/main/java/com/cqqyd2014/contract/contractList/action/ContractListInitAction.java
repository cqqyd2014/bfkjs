package com.cqqyd2014.contract.contractList.action;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/contract") //表示当前Action所在命名空间  
public class ContractListInitAction extends ActionSupport {
	java.util.HashMap goods_id_map;
	public java.util.LinkedHashMap<String, String> supply_map;
	public java.util.LinkedHashMap<String, String> getSupply_map() {
		return supply_map;
	}

	public void setSupply_map(java.util.LinkedHashMap<String, String> supply_map) {
		this.supply_map = supply_map;
	}

	public java.util.HashMap getGoods_id_map() {
		return goods_id_map;
	}

	public void setGoods_id_map(java.util.HashMap goods_id_map) {
		this.goods_id_map = goods_id_map;
	}
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="contract_list_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/contract/contractList.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	
 
	


	public String contract_list_init() throws Exception {
		
		
		Map session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
		Session session = HibernateSessionFactory.getSession();

		com.cqqyd2014.util.AjaxSuccessMessage sm = new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao = new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			
			goods_id_map=gidao.getGoodsInfosMapInUse(session, com_id);
			com.cqqyd2014.hibernate.dao.SupplyInfoDAO	sidao=new com.cqqyd2014.hibernate.dao.SupplyInfoDAO();
			supply_map=sidao.getSupplyMap(session, com_id);
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds=new java.util.ArrayList<>();
			
			session_http.put("temp_contract_d", cds);
			
		} catch (HibernateException e) {

			System.out.println(e.getMessage());

		} finally {
			HibernateSessionFactory.closeSession();
		}
		return "success";
	}
}
