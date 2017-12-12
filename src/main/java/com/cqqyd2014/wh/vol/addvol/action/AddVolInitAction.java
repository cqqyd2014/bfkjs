package com.cqqyd2014.wh.vol.addvol.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;



@Scope("prototype")//支持多例  
@ParentPackage("bfkjs-default")  
@Namespace(value="/wh") //表示当前Action所在命名空间  
public class AddVolInitAction  extends UserLoginedAction {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	java.util.LinkedHashMap<String,String> type_map;

	java.util.LinkedHashMap<String,String> contract_map;
	java.util.LinkedHashMap<String,String> wh_map;
	public java.util.LinkedHashMap<String, String> getWh_map() {
		return wh_map;
	}
	public void setWh_map(java.util.LinkedHashMap<String, String> wh_map) {
		this.wh_map = wh_map;
	}


	String in_date;

	public java.util.LinkedHashMap<String, String> getType_map() {
		return type_map;
	}
	public void setType_map(java.util.LinkedHashMap<String, String> type_map) {
		this.type_map = type_map;
	}
	public java.util.LinkedHashMap<String, String> getContract_map() {
		return contract_map;
	}
	public void setContract_map(java.util.LinkedHashMap<String, String> contract_map) {
		this.contract_map = contract_map;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	String type_id;
	String contract_id;
	String wh_id;
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getWh_id() {
		return wh_id;
	}
	public void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="add_vol_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/add_vol.jsp")},
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  





@Authority(module="move_warehouse_init", privilege="[00020003]",error_url="authority_error") 
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> odis = new java.util.ArrayList<com.cqqyd2014.wh.model.Goods>();
		session_http.put("temp_add_vol_barcode", odis);
		in_date = com.cqqyd2014.util.DateUtil.JDateToSimpleString(new java.util.Date());
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			
			com.cqqyd2014.hibernate.dao.UserParDAO cpcdao = new com.cqqyd2014.hibernate.dao.UserParDAO();
			type_id = cpcdao.getValue(session, user_id, com_id, "default_into_hw_type");

			contract_id = cpcdao.getValue(session, user_id, com_id, "default_contract_m");
			wh_id = cpcdao.getValue(session, user_id, com_id, "default_warehouse");
			
			
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao = new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.Warehouse> whs_h = whdao.getAllByComId(session, com_id);
			
			java.util.ArrayList<com.cqqyd2014.wh.model.WareHouse> whs=com.cqqyd2014.wh.logic.WareHouseLogic.getArrayListModelFromArrayListEntity(whs_h);
			
			wh_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMap(whs, "getWh_id", "getWh_name");
			
			
			com.cqqyd2014.hibernate.dao.ContractMDAO cmdao=new com.cqqyd2014.hibernate.dao.ContractMDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.ContractM> cms_h=cmdao.getArrayListEntitesByComIdUnArrial(session, com_id);
			
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractM> cms=com.cqqyd2014.contract.logic.ContractMLogic.getArrayListModelFromArrayListEntity(cms_h);
			
			contract_map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMap(cms, "getContract_no", "getContract_no");
			
			
			com.cqqyd2014.hibernate.dao.SysCodeDAO scdao = new com.cqqyd2014.hibernate.dao.SysCodeDAO();
			type_map = scdao.getValuesMap(session, "into_hw_type");
			
			
			
			
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
		return "success";
	}

}
