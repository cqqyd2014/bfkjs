package com.cqqyd2014.order.common.ajax.action;


import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;


@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/order")
public class CancelOrderAction extends UserLoginedAction {
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

	@Action(value = "cancel_order", results = { @Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {
			
			@InterceptorRef("defaultStack"),
			@InterceptorRef("authorityInterceptor") })
@Authority(module = "cancel_order", privilege = "*", error_url = "authority_ajax_error")
@Override
public String execute() {
// TODO Auto-generated method stub
super.execute();
sm.setAuth_success(true);
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			com.cqqyd2014.hibernate.dao.VOrderMainDAO vomdao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vomdao.getVOrderMain(session, com_id, order_no);
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getModelFromView(vom);
			if (!order.getCancel_status().equals("    ")) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("该订单已经在取消中，或已经取消");
			}
			com.cqqyd2014.hibernate.dao.VOrderDetailDAO voddao=new com.cqqyd2014.hibernate.dao.VOrderDetailDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods=voddao.getArrayListViewsByOrderNo(session, com_id, order_no);
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=com.cqqyd2014.order.logic.OrderDetailLogic.getArrayListModelFromArrayListView(vods);
			java.util.Date now=new java.util.Date();
			order.setCancel_request_dat(now);
			order.setCancel_request_memo(cancel_memo);
			order.setCancel_request_userid(user_id);
			order.setCancel_status("申请取消");
			//根据状态
			//1、“订单生成”，只锁定的order库位的数量，没有拣货
			
			switch(order.getOrder_status()){
            default:
                System.out.println("默认值");
                break;
            case "订单生成":
                //设置退货完成
            	order.setCancel_confirm_memo("订单处于-订单生成状态-取消");
            	order.setCancel_confirm_userid(user_id);
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
            	order.setCancel_confirm_userid(user_id);
            	order.setCancel_confrim_dat(now);
            	order.setCancel_status("取消成功");
            	order.setOrder_status("取消成功");
            	//调整order库位
            	for (int i=0;i<vods.size();i++) {
            		com.cqqyd2014.order.model.OrderDetail od=ods.get(i);
            		com.cqqyd2014.wh.logic.Storage.addStorage(session, od.getGoods_id(), "ORDER_", "DEFAUL", od.getNum().multiply(new java.math.BigDecimal(-1)), order.getCom_id());
            	}
            	//退款
            	com.cqqyd2014.quota.logic.QuotaTransLogic.changeQuota(session, com_id, order.getUser_id(), user_id, "0003", order.getActual_amount(), "已经付款订单取消解冻", order.getOrder_no(), "");
            	
            	
                break;
            case "订单处理":
                //设置退货完成
            	
            	switch(order.getWh_status()){
                default:
                    System.out.println("默认值");
                    break;
                case "等待拣货":
                	//1、仓库状态为“等待拣货”，实质上与订单已付一样
                	order.setCancel_confirm_memo("订单处于-订单处理状态未开始拣货-取消");
                	order.setCancel_confirm_userid(user_id);
                	order.setCancel_confrim_dat(now);
                	order.setCancel_status("取消成功");
                	order.setOrder_status("取消成功");
                	
                	//调整order库位
                	for (int i=0;i<vods.size();i++) {
                		com.cqqyd2014.order.model.OrderDetail od=ods.get(i);
                		com.cqqyd2014.wh.logic.Storage.addStorage(session, od.getGoods_id(), "ORDER_", "DEFAUL", od.getNum().multiply(new java.math.BigDecimal(-1)), order.getCom_id());
                	}
                	//退款
                	com.cqqyd2014.quota.logic.QuotaTransLogic.changeQuota(session, com_id, order.getUser_id(), user_id, "0003", order.getActual_amount(), "已经付款订单取消解冻", order.getOrder_no(), "");
                	
                	break;
                case "拣货处理":
                	order.setCancel_status("申请取消");
                	order.setOrder_status("申请取消");
                	order.setCancel_confirm_memo("已经开始打包的订单申请取消，等待操作员确认");
                	break;
                case "拣货完毕":
                	order.setCancel_status("申请取消");
                	order.setOrder_status("申请取消");
                	order.setCancel_confirm_memo("已经打包完毕的订单申请取消，等待操作员确认");
                	break;
            	}
            	
            	
            	
            	
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