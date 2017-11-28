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
public class DelGoodsIdInSessionAjaxAction   extends ActionSupport {
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
	@Action(value = "del_goods_id_in_session", results = { @Result(type = "json", params = { "root", "msg" }) })

	public String del_goods_id_in_session() throws Exception {
		
		
		Map<String,Object> session_http = ActionContext.getContext().getSession();

		String user = (String) session_http.get("USER");
		String user_name = (String) session_http.get("USER_NAME");
		String user_id = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		
Session session = HibernateSessionFactory.getSession();
		
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		try {
			
			
			
			
			
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
