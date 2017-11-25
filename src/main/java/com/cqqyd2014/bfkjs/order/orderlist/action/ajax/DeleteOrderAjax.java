package com.cqqyd2014.bfkjs.order.orderlist.action.ajax;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class DeleteOrderAjax extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	String order_no;

	

	public String getOrder_no() {
		return order_no;
	}



	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}



	@Override
	public String execute() throws Exception {
		//System.out.println("ajax get");


		String user_name = (String) this.session.get("USER_NAME");

		String com_id = (String) this.session.get("com_code");


		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		String user_id=(String)this.session.get("USER_ID");
		com.cqqyd2014.util.AjaxSuccessMessage sm=new com.cqqyd2014.util.AjaxSuccessMessage();
		
		try {
			
			com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.OrderMainDAO();
			com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao=new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
			com.cqqyd2014.hibernate.entities.COrderMain om=omdao.getOrder(session, order_no,com_id);
			
			if (!om.getEffective()){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("订单"+order_no+"已经作废，不能重复取消订单");
				
			}
			if (om.getCStatus().equals("订单已发")||om.getCStatus().equals("订单完毕")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("订单"+order_no+"已经移交快递（全部或者部分），不能取消，只能对未未移交快递对发货单申请退货");
			}

			//如果是生成订单还未开始拣货状态，商品没有移动，只有订单仓库锁定
			//1、订单生成，未分配捡货
			//2、订单已经分配，未开始捡货
			if ((om.getCStatus().equals("订单生成")&&om.getGtStatus().equals("等待分配"))||(om.getCStatus().equals("订单处理")&&om.getGtStatus().equals("等待捡货"))){
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail> ods=oddao.getDetails(session, order_no, com_id);
				for (int i=0;i<ods.size();i++){
					com.cqqyd2014.hibernate.entities.COrderDetail od=ods.get(i);
					//com.cqqyd2014.wh.logic.WareHouse.orderUnLock(session, od.getCGoodsId(), od.getCCount(), com_id);
				}
				
				
			}
			if (om.getCStatus().equals("订单处理")){
				/*老的处理，直接退货
				//得到这个订单相关的发货单
				com.cqqyd2014.hibernate.dao.DeliverMDAO dmdao=new com.cqqyd2014.hibernate.dao.DeliverMDAO();
				com.cqqyd2014.hibernate.dao.DeliverDDAO dddao=new com.cqqyd2014.hibernate.dao.DeliverDDAO();
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverM> dms=dmdao.getDelivers(session, order_no, com_id);
				for (int i=0;i<dms.size();i++){
					com.cqqyd2014.hibernate.entities.DeliverM dm=dms.get(i);
					//部分发货的订单，只需要将未发货的发货单处理
					if (!dm.getSended()){
						//将运单作废
						dm.setEffective(false);
						dm.setUneffectiveDat(new java.util.Date());
						dm.setUneffectiveUserId(user_id);
						
						session.saveOrUpdate(dm);
						//运单中的商品从锁定到正常状态
						java.util.ArrayList<com.cqqyd2014.hibernate.entities.DeliverD> dds=dddao.getDeliverS(session, dm.getId().getOrderNo(), dm.getId().getSeq(), com_id);
						for (int j=0;j<dds.size();j++){
							com.cqqyd2014.hibernate.entities.DeliverD dd=dds.get(j);
							com.cqqyd2014.wh.logic.Goods.LockItemToDefaultItem(session, dd.getId().getGoodsBarcode(), com_id,user_id);
							com.cqqyd2014.wh.logic.Storage.LockItemBackToWh(session, dd.getGoodsId(), om.getWhId(), new java.math.BigDecimal(1), com_id);
						}
					}
					
					
					
					
					
				}

				*/
				//新的处理，提交发货单退货申请
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("订单"+order_no+"已经开始打包处理，不能取消，只能对未未移交快递对发货单申请退货");
				
				
			}
			//对于部分拣货的情况，将未拣货的，做库存调整，不做移库
			/*
			com.cqqyd2014.hibernate.dao.VPickupYueDAO dydao=new com.cqqyd2014.hibernate.dao.VPickupYueDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VPickupYue> need_deliver_list=dydao.getNeedDeliverList(session, order_no, com_id);
			for (int i=0;i<need_deliver_list.size();i++){
				com.cqqyd2014.hibernate.entities.VPickupYue vpy=need_deliver_list.get(i);
				com.cqqyd2014.wh.logic.Storage.OrderItemBackToWh(session, vpy.getId().getCGoodsId(), om.getWhId(), vpy.getId().getYue(), com_id);
			}
			
			
			
			//将订单设置为作废，部分发货的，为“发货完毕”
			
			if (om.getCStatus().equals("部分发货")){
				om.setCStatus("发货完毕");
				om.setEmsStatus("发货完毕");
				
			}
			else{
				om.setEffective(false);
				om.setUneffectiveDat(new java.util.Date());
				om.setUneffectiveUserId(user_id);
			}
			
			*/
			session.saveOrUpdate(om);
			
			
			com.cqqyd2014.hibernate.dao.OrderLogDAO oldao=new com.cqqyd2014.hibernate.dao.OrderLogDAO();
			oldao.addLog(session, order_no, "删除订单", "0004", com_id, user_id);
			
			
			tx.commit();
			sm.setSuccess(true);

		}

		catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		catch(com.cqqyd2014.util.exception.AjaxSuccessMessageException e){
			sm.setSuccess(false);
			sm.setBody(e.getMessageString());
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			
		}

		
		finally {
			HibernateSessionFactory.closeSession();
		}
		JsonConfig jsonConfig = new JsonConfig();  
		
		JSONArray ja1 = JSONArray.fromObject(sm);
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ja1); 
		return null;

	}
}