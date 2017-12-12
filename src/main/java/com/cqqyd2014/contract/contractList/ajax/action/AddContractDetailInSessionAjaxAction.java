package com.cqqyd2014.contract.contractList.ajax.action;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/contract")
public class AddContractDetailInSessionAjaxAction  extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}
	String goods_id;
	String price;
	String num;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}


	
	@Action(value = "add_contract_detail_in_session", results = { @Result(type = "json", params = { "root", "msg" })  }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "add_contract_detail_in_session", privilege = "[00030002]", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
Session session = HibernateSessionFactory.getSession();
		
		
		try {
			
			
			
			
			
			@SuppressWarnings("unchecked")
			java.util.ArrayList<com.cqqyd2014.contract.model.ContractD> cds=(java.util.ArrayList<com.cqqyd2014.contract.model.ContractD>)session_http.get("temp_contract_d");
				
			//检测这个goods_id在session中有没有
			com.cqqyd2014.contract.model.ContractD cd=null;
			for (int i=0;i<cds.size();i++){
				com.cqqyd2014.contract.model.ContractD cd_in_session=cds.get(i);
				if (goods_id.equals(cd_in_session.getGoods_id())){
					cd=cd_in_session;
					
					//throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException(goods_id+"商品编码重复录入");
				}
			}
			//如果没有，新建
			if (cd==null){
				cd=new com.cqqyd2014.contract.model.ContractD();
				cd.setGoods_id(goods_id);
			}
			
			
				java.math.BigDecimal b_price=new java.math.BigDecimal(price);
				java.math.BigDecimal b_num=new java.math.BigDecimal(num);
				cd.setAmount(b_price.multiply(b_num));
				cd.setGoods_id(goods_id);
				com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
				com.cqqyd2014.hibernate.entities.VGoodsInfo gi=gidao.getGoodsInfo(goods_id, session, com_id);
				
				
				
				cd.setGoods_name(gi.getId().getCName());

				cd.setNum(b_num);
				cd.setPrice(b_price);
				cd.setUnit(gi.getId().getUnit());
				cd.setC_unit(gi.getId().getCUnit());
				cd.setNum_in(new java.math.BigDecimal(0));
				cd.setNum_out(new java.math.BigDecimal(0));
				
				
				
				cds.add(cd);
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
