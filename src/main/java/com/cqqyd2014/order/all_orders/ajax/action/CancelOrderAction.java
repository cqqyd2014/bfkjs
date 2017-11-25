package com.cqqyd2014.order.all_orders.ajax.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.dao.VWeixinScanQrLogDAO;
import com.cqqyd2014.util.hashmap.HashMapToolsCompareResult;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/order")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "json"),
		@Result(name = ActionSupport.ERROR, type = "json", params = { "root", "msg" }) })
@SuppressWarnings("serial")
public class CancelOrderAction extends ActionSupport {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}



	public String getCancel_memo() {
		return cancel_memo;
	}

	public void setCancel_memo(String cancel_memo) {
		this.cancel_memo = cancel_memo;
	}



	String order_no;

	String cancel_memo;



	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	@Action(value = "cancel_order", results = { @Result(type = "json", params = { "root", "msg" }) })
	public String cancel_order() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String,Object> session_http = ActionContext.getContext().getSession();


		String userid = (String) session_http.get("USER_ID");
		String com_id = (String) session_http.get("com_code");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vomdao.getVOrderMain(session, com_id, order_no);
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getOrderModelFromHiberanteEntities(vom);
			if (!order.getCancel_status().equals("    ")) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该订单已经在取消中，或已经取消");
			}
			com.cqqyd2014.hibernate.dao.VOrderDetailDAO voddao=new com.cqqyd2014.hibernate.dao.VOrderDetailDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods=voddao.getArrayListViewsByOrderNo(session, com_id, order_no);
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=com.cqqyd2014.order.logic.OrderDetailLogic.getArrayListModelFromArrayListView(vods);
			java.util.Date now=new java.util.Date();
			order.setCancel_request_dat(now);
			order.setCancel_request_memo(cancel_memo);
			order.setCancel_request_userid(userid);
			order.setCancel_status("申请取消");
			//根据状态
			//1、“订单生成”，只锁定的order库房的数量，没有拣货
			
			switch(order.getOrder_status()){
            default:
                System.out.println("默认值");
                break;
            case "订单生成":
                //设置退货完成
            	order.setCancel_confirm_memo("订单处于-订单生成状态-取消");
            	order.setCancel_confirm_userid(userid);
            	order.setCancel_confrim_dat(now);
            	order.setCancel_status("取消成功");
            	order.setOrder_status("取消成功");
            	//调整order库位
            	for (int i=0;i<vods.size();i++) {
            		com.cqqyd2014.order.model.OrderDetail od=ods.get(i);
            		com.cqqyd2014.wh.logic.Storage.addStorage(session, od.getGoods_id(), "ORDER_", "DEFAUL", od.getNum().multiply(new java.math.BigDecimal(-1)), order.getCom_id());
            	}
            	
                break;
            case "订单已付":
                //设置退货完成
            	order.setCancel_confirm_memo("订单处于-订单已付状态-取消");
            	order.setCancel_confirm_userid(userid);
            	order.setCancel_confrim_dat(now);
            	order.setCancel_status("取消成功");
            	order.setOrder_status("取消成功");
            	//调整order库位
            	for (int i=0;i<vods.size();i++) {
            		com.cqqyd2014.order.model.OrderDetail od=ods.get(i);
            		com.cqqyd2014.wh.logic.Storage.addStorage(session, od.getGoods_id(), "ORDER_", "DEFAUL", od.getNum().multiply(new java.math.BigDecimal(-1)), order.getCom_id());
            	}
            	//退款
            	com.cqqyd2014.quota.logic.QuotaTransLogic.changeQuota(session, com_id, userid, userid, "0003", order.getActual_amount(), "已经付款订单取消解冻", order.getOrder_no(), "");
            	
            	
                break;
           
            
        }
			
			com.cqqyd2014.order.logic.OrderLogic.save(session, order);
			
			
			sm.setSuccess(true);
			
			
		
			
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
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
		}

		finally {
			HibernateSessionFactory.closeSession();
		}
		
		msg=sm.toMap();
		return SUCCESS;
	}
	

}