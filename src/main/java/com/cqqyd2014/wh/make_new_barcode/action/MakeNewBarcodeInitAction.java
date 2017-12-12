package com.cqqyd2014.wh.make_new_barcode.action;
import java.util.Map;

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
public class MakeNewBarcodeInitAction extends UserLoginedAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	java.util.LinkedHashMap<String, String > giList;
	
	@Actions({     
	    
		 @Action( //表示请求的Action及处理方法  
		            value="make_new_barcode_init",  //表示action的请求名称  
		            results={  //表示结果跳转  
		                    @Result(name="success",location="/WEB-INF/wh/make_new_barcode.jsp") },
		            interceptorRefs={  
                            @InterceptorRef("authorityInterceptor")  
            }
    )    

})  





@Authority(module="mainframe", privilege="[00020002]",error_url="authority_error") 
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
		com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
        giList=gidao.getGoodsInfosMapInUse(session, com_id);
        tx.commit();
		// session.close();
	}

	catch (HibernateException e) {
		
		if (null != tx) {
			tx.rollback();// 撤销事务

		}
		sm.setSuccess(false);
		sm.setBody(e.toString());
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
		 finally {
				HibernateSessionFactory.closeSession();
			}
		
		return "success";
	}

	public java.util.LinkedHashMap<String, String> getGiList() {
		return giList;
	}

	public void setGiList(java.util.LinkedHashMap<String, String> giList) {
		this.giList = giList;
	}
	
	

}
