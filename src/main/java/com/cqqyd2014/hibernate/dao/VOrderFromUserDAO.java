package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderFromUserDAO {
	
	
	public  java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> getArrayViewByUserID(Session session,String com_id,String userid){
		
		String hql="from VOrderFromUser where id.userId=:userid and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("userid", userid);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser>) q
				.list();
		
		return rs;
		
	}

	
	
	
	
	
	public  java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> getArrayView(Session session){
		//java.util.ArrayList<com.cqqyd2014.bfkjs.order.model.OrderFrom> list=new java.util.ArrayList<com.cqqyd2014.bfkjs.order.model.OrderFrom>();
		String hql="from VOrderFromUser";
		Query q = session.createQuery(hql);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> rs = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser>) q
				.list();
		
		return rs;
		
	}

	
	
	
	/*
	public  int getAccountID(Session session,com.cqqyd2014.bfkjs.entity.COrderMain om){
		String type_id=om.getCOrderId().substring(3, 5);
		String hql="from OrderFrom where id.orderTypeId=:type_id";
		Query q = session.createQuery(hql);
		q.setParameter("type_id", type_id);
		java.util.ArrayList<com.cqqyd2014.bfkjs.entity.OrderFrom> ofs=(java.util.ArrayList<com.cqqyd2014.bfkjs.entity.OrderFrom>)q.list();
		if (ofs.size()>0){
			return ofs.get(0).getOdooAccountInt();
		}
		else{
			return 0;
		}
	}
	
	
	
	
	
	//通过odooCustomerId获得渠道名称
		public String getFromNameByOdooCustomerId(Session session,Integer odooCustomerId){
			String hql="from OrderFrom where odooCustomerId=:odooCustomerId";
			Query q = session.createQuery(hql);
			q.setParameter("odooCustomerId", odooCustomerId);
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.OrderFrom> ofs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.OrderFrom>)q.list();
			if (ofs.size()>0){
				return ofs.get(0).getOrderTypeName();
			}
			else{
				
				return null;
			}
		}
		*/
	public com.cqqyd2014.hibernate.entities.VOrderFromUser getOrderFromByOm(Session session,com.cqqyd2014.hibernate.entities.COrderMain om){
		String orderTypeId=om.getId().getOrderNo().substring(3, 5);
		String hql="from VOrderFromUser where id.orderTypeCode=:orderTypeId";
		Query q = session.createQuery(hql);
		q.setParameter("orderTypeId", orderTypeId);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> ofs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser>)q.list();
		if (ofs.size()>0){
			return ofs.get(0);
		}
		else{
			System.out.println("订单："+om.getId().getOrderNo()+"无可用的ordertypeid");
			return null;
		}
	}
	public com.cqqyd2014.hibernate.entities.VOrderFromUser getOrderFromByType(Session session,String com_id,String order_type){
		String hql="from VOrderFromUser where id.orderTypeCode=:order_type and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_type", order_type);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser> ofs=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderFromUser>)q.list();
		if (ofs.size()>0){
			return ofs.get(0);
		}
		else{
			return null;
		}
	}

}
