package com.cqqyd2014.contract.contractList.action;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.context.annotation.Scope;

import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@Scope("prototype")//支持多例  
@ParentPackage("struts-default")  //表示继承的父包  
@Namespace(value="/contract") //表示当前Action所在命名空间  
public class ContractListInitAction extends UserLoginedAction {
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="contract_list_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/contract/contractList.jsp"),  
		                    
		            }
		    )    
	   
	   })  
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();

		
		Session session = HibernateSessionFactory.getSession();

		
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
	java.util.LinkedHashMap<String, String> goods_id_map;
	public java.util.LinkedHashMap<String, String> supply_map;
	public java.util.LinkedHashMap<String, String> getSupply_map() {
		return supply_map;
	}

	public void setSupply_map(java.util.LinkedHashMap<String, String> supply_map) {
		this.supply_map = supply_map;
	}

	public java.util.LinkedHashMap<String, String> getGoods_id_map() {
		return goods_id_map;
	}

	public void setGoods_id_map(java.util.LinkedHashMap<String, String> goods_id_map) {
		this.goods_id_map = goods_id_map;
	}
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	

}
