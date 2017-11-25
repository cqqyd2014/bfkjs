package com.cqqyd2014.hibernate.dao;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

public class OrderDetailDAO {
	
	//得到系统所有订单的商品需要发货map

	
	
	
	public java.util.LinkedHashMap<String, java.math.BigDecimal> getGoodsIdMapByOrder(Session session,String orderNo,String com_id){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail> ods=getDetails(session,orderNo,com_id);
		java.util.LinkedHashMap<String, java.math.BigDecimal> map=new java.util.LinkedHashMap<>();
		for (int i=0;i<ods.size();i++){
			map.put(ods.get(i).getCGoodsId(), ods.get(i).getCCount());
		}
		return map;
	}
	
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail> getDetails(Session session,String orderNo,String com_id){
		String hql="from COrderDetail where COrderId=:orderNo and comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("orderNo", orderNo);
		q.setParameter("com_id", com_id);

		return			(java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail>)q.list();
		
	}
	
	public boolean ifExistGoodId(Session session,String order_no,String goods_id){
		String hql="from COrderDetail where COrderId=:order_no and CGoodsId=:goods_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("goods_id", goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail> rs=		(java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail>)q.list();
		if (rs.size()==0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public com.cqqyd2014.hibernate.entities.COrderDetail getDetailItem(Session session,String orderNo,String goods_id){
		String hql="from COrderDetail where CGoodsId=:goods_id and COrderId=:orderNo";
		Query q = session.createQuery(hql);
		q.setParameter("orderNo", orderNo);
		q.setParameter("goods_id", goods_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail> cods=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderDetail>)q.list();
		if (cods.size()>0){
			return cods.get(0);
		}
		else{
			return null;
		}
	}
	/*
	
	//用于微信端
	public  String getDetailHtml(Session session,String orderNo,String com_id){
		String htmlStr="<table width='100%' border='0' class='datalist'><tr><td width='90%' class='detail_head'>商品描述</td><td width='10%' class='detail_head'>数量</td></tr>";
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail> vdas=getOrderVDetails(session,orderNo);
		for (int i=0;i<vdas.size();i++){
			com.cqqyd2014.hibernate.entities.V41OrderinfoDetail vda=vdas.get(i);
			htmlStr=htmlStr+"<tr>";
			htmlStr=htmlStr+"<td><img width='80px' height='80px' src=\'../img/product/"+com_id+"/"+vda.getId().getCGoodsId()+".jpg'/><br/>"
					+vda.getId().getCName()+"</td><td>"+vda.getId().getCCount()+"</td>";
			
			htmlStr=htmlStr+"</tr>";
		}
		
		return htmlStr+"</table>";
	}
	
	*/


	//订单的明细数量
	public double getDetailQty(Session session, String orderNo) {
		double count = 0;
		String hql;
		
			hql= "select sum(id.CCount) from VDetailAllNoHw where id.COrderId=:COrderId";
		

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);

		java.util.List<Object> list = q.list();
		Object o = (Object) list.iterator().next();
		if (o == null) {
			count = 0;
		} else {
			count = ((java.math.BigDecimal) o).doubleValue();
		}
		session.flush();
		return count;
	}

	
	//用于更新主表的detail_memo
	public String getDetailStr(Session session, String orderNo,String com_id) {
		String rs = "";
		String hql;
		
			hql= "from VOrderDetailId where id.COrderId=:COrderId and id.comId=:com_id";
		

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);
		q.setParameter("com_id", com_id);
			
		
		
		java.util.List<com.cqqyd2014.hibernate.entities.VOrderDetail> list = q.list();
		for (int i = 0; i < list.size(); i++) {
			rs += "[型号：" + list.get(i).getId().getCName() + "；数量："
					+ list.get(i).getId().getCCount() + "]";
		}
		session.flush();
		return rs;
	}
	
	//得到系统价格表税
	public  BigDecimal getDetailTax2(Session session, String orderNo) {
		BigDecimal tax2 = new java.math.BigDecimal("0");
		String hql = "select sum(id.tax2) from VDetailAllNoHw where id.COrderId=:COrderId";

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);

		java.util.List<Object> list = q.list();
		Object o = (Object) list.iterator().next();
		if (o == null) {
			tax2 = new java.math.BigDecimal("0");
		} else {
			tax2 = (java.math.BigDecimal) o;
		}
		session.flush();
		return tax2;
	}
	
	
	
	//得到用户价格表税
	public  BigDecimal getDetailTax(Session session, String orderNo) {
		BigDecimal tax = new java.math.BigDecimal("0");
		String hql = "select sum(id.tax1) from VDetailAllNoHw where id.COrderId=:COrderId";

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);

		java.util.List<Object> list = q.list();
		Object o = (Object) list.iterator().next();
		if (o == null) {
			tax = new java.math.BigDecimal("0");
		} else {
			tax = (java.math.BigDecimal) o;
		}
		session.flush();
		return tax;
	}

	public  double getDetailWeight(Session session, String orderNo) {
		double weight = 0;
		String hql = "select sum(id.CWeight) from VDetailAllNoHw where id.COrderId=:COrderId";

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);

		java.util.List<Object> list = q.list();
		Object o = (Object) list.iterator().next();
		if (o == null) {
			weight = 0;
		} else {
			weight = ((java.math.BigDecimal) o).doubleValue();
		}
		session.flush();
		return weight;
	}
	
	
	//得到系统价格表总价
	
	public BigDecimal getDetailSum2(Session session, String orderNo) {
		BigDecimal amount2 = new java.math.BigDecimal("0");
		// System.out.println(orderNo);
		String hql = "select sum(id.CPrice2*id.CCount) from VDetailAllNoHw where id.COrderId=:COrderId ";

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);

		java.util.List<Object> list = q.list();
		Object o = (Object) list.iterator().next();
		if (o == null) {
			amount2 = new java.math.BigDecimal("0");
		} else {
			amount2 = (java.math.BigDecimal) o;
		}
		//System.out.println("系统总价是"+amount2);
		session.flush();
		return amount2;
	}
	
	
	
	//得到用户价格表总价
	public BigDecimal getDetailSum(Session session, String orderNo) {
		BigDecimal amount = new java.math.BigDecimal("0");
		// System.out.println(orderNo);
		String hql = "select sum(id.CPrice*id.CCount) from VDetailAllNoHw where id.COrderId=:COrderId ";

		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);

		java.util.List<Object> list = q.list();
		Object o = (Object) list.iterator().next();
		if (o == null) {
			amount = new java.math.BigDecimal("0");
		} else {
			amount = (java.math.BigDecimal) o;
		}
		session.flush();
		return amount;
	}
/*
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail> getOrderVDetails(
			Session session, String orderId) {
		//System.out.println(orderId);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail> list = null;

		String hql= "from V41OrderinfoDetail where id.COrderId=:COrderId";
		
		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderId);
		list = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail>)q.list();
		//session.flush();
		return list;
	}
	
	public  java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail> getOrderDetailNoHw(
			Session session, String orderId) {
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail> list = null;

		String hql;
		
			hql= "from VDetailAllNoHw  where id.COrderId=:COrderId";
		
		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderId);

		list = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.V41OrderinfoDetail>)q.list();
		session.flush();
		return list;
	}
	*/
	
	
	/*
	//通过orderId获取订单明细
	public static java.util.List<com.cqqyd2014.bfkjs.entity.VDetailAll> getOrderDetailByOrderId(
			Session session, String orderId) {
		java.util.List<com.cqqyd2014.bfkjs.entity.VDetailAll> list = null;

		String hql;
		
			hql= "from VDetailAll  where id.COrderId=:COrderId";
		
		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderId);
		list = q.list();
		session.flush();
		session.clear();
		return list;
	}
	

	public  java.util.ArrayList<java.util.ArrayList<String>> getOrderDetailXML(
			Session session, String orderId) {
		java.util.ArrayList<java.util.ArrayList<String>> rs = new java.util.ArrayList<java.util.ArrayList<String>>();
		java.util.List<com.cqqyd2014.hibernate.entities.VDetailAll> list = getOrderVDetails(
				session, orderId);
		for (int i = 0; i < list.size(); i++) {
			java.util.ArrayList<String> item = new java.util.ArrayList<String>();
			com.cqqyd2014.hibernate.entities.VDetailAll detail = list.get(i);
			item.add(detail.getId().getHwCId());
			item.add(detail.getId().getCName());
			item.add("142");
			item.add(com.cqqyd2014.util.StringUtil.getDToS(detail.getId().getCPrice()));
			item.add(String.valueOf(detail.getId().getCCount()));
			item.add(com.cqqyd2014.util.StringUtil.getDToS(detail.getId().getAmount()));
			item.add(com.cqqyd2014.util.StringUtil.getDToS(detail.getId().getTax()));
			rs.add(item);
		}
		return rs;
	}
	*/
}
