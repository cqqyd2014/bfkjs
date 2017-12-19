package com.cqqyd2014.hibernate.dao;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.cqqyd2014.hibernate.HibernateSessionFactory;

import com.cqqyd2014.hibernate.entities.*;
import com.cqqyd2014.util.message.IfMessage;


public final class OrderMainDAO {
	
	
	
	
	
	
	
	
	//得到所有未付款订单
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> getEntityUnPaid(Session session){
		String hql = "from COrderMain where effective=true and paid=false and CStatus='订单生成' and cancelStatus=''";
		Query q = session.createQuery(hql);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q.list();
		return oms;
	}
	
	//未分配打包任务的订单
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> getListUnAssignedPackager(Session session,String com_id){
		//System.out.println(com_id);
		String hql = "from COrderMain where effective=true and id.comId=:com_id and packageUser='' and CStatus='订单已付'";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q.list();
		return oms;
	}
	//未分配发货任务的发货单所关联的订单
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverNeedAssign> getListUnAssignedSender(Session session,String com_id){
		String hql = "from VDeliverNeedAssign where id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverNeedAssign> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverNeedAssign>) q.list();
		return oms;
	}
	
	
		
	
	
	


	
	

	// 分析是否含有刀具,并更新标志位

	public void checkIfKnife(Session session, String orderNo,String com_id) {
		com.cqqyd2014.hibernate.dao.VOrderIfKnifeDAO voikdao = new com.cqqyd2014.hibernate.dao.VOrderIfKnifeDAO();
		if (voikdao.check(session, orderNo)) {
			com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
			om.setNotAir(true);
			session.saveOrUpdate(om);

		}
	}

	

	

	
	/*

	// 得到订单"取消标志位"的状态
	public String getCancleStatus(Session session, String orderNo) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo);
		String cancle_status = om.getCancleFlag();
		om = null;
		return cancle_status;

	}
	*/

	// 得到订单在仓库的状态
	public String getGtStatus(Session session, String orderNo,String com_id) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		String gt_status = om.getGtStatus();
		om = null;
		return gt_status;

	}

	

	// 更新预估重量

	public void updateWeight(Session session, String orderNo, float weight,String com_id) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCWeight(new BigDecimal(weight));
		session.saveOrUpdate(om);
	}
	/*
	 * // 更新订单的odoo_id public void updateOdooId(Session session, String orderNo,
	 * int odoo_id) { com.cqqyd2014.hibernate.entities.COrderMain om =
	 * getOrder(session, orderNo); om.setOdooId(new Integer(odoo_id));
	 * session.saveOrUpdate(om);
	 * 
	 * }
	 
	// 通过kjsNo得到订单

	public com.cqqyd2014.hibernate.entities.COrderMain getOrderByKjsNo(Session session, String kjsNo) {
		String hql = "from COrderMain where kjsNo=:kjsNo ";
		Query q = session.createQuery(hql);
		q.setParameter("kjsNo", kjsNo);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q
				.list();

		return oms.get(0);
	}
*/
	/*
	 * 
	 * // 更新发货完成时间，也是快递开始时间
	 * 
	 * public void updateExpressStartTime(Session session, String orderNo) {
	 * com.cqqyd2014.hibernate.entities.COrderMain fa = getOrder(session,
	 * orderNo); java.util.Date dat = new java.util.Date();
	 * fa.setExpressStartTime(new java.sql.Timestamp(dat.getTime()));
	 * session.saveOrUpdate(fa); }
	 */
/*
	// 是否微信申报

	public boolean checkIfWeixinFromUser(Session session, String orderNo) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo);
		if (om.getWeixinFromuser().length() == 0) {
			om = null;
			return false;
		} else {
			om = null;
			return true;
		}
	}
	*/
/*
	// 得到OpenID
	public String getWeixinOpenID(Session session, String orderNo) {
		com.cqqyd2014.hibernate.entities.COrderMain com = getOrder(session, orderNo);
		return com.getWeixinFromuser();
	}
	*/



	public java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> getStatus(String str, Session session,
			String userid, String OrderFrom) {
		java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> oms = null;
		String hqlString = "from COrderMain where effective=true and CStatus=:CStatus and userId=:user and substring(originalId,1,2)=:OrderFrom";

		Query query = session.createQuery(hqlString);
		query.setParameter("CStatus", str);
		query.setParameter("user", userid);
		query.setParameter("OrderFrom", OrderFrom);
		oms = query.list();
		session.flush();
		return oms;

	}

	

	

	

	// 微信申报,微信申报来源设置为WX，统计的全网数量
	public int getStatusWXCount(String com_id, String str, Session session, String userid, int type, String OrderFrom,
			String orderNo, String searchUserName, String searchOriginalNo, String searchAddr, String searchGoods,
			String searchKjsNo, String searchBarcode, String orderStatusSelect, String searchExpressNo) {
		int i = 0;
		String hqlString;
		if (orderNo == null) {
			orderNo = "";
		}
		if (searchExpressNo == null) {
			searchExpressNo = "";
		}
		if (searchUserName == null) {
			searchUserName = "";
		}
		if (orderStatusSelect == null) {
			orderStatusSelect = "";
		}
		if (orderStatusSelect.equals("所有订单")) {
			orderStatusSelect = "";
		}
		if (searchAddr == null) {
			searchAddr = "";
		}
		if (searchGoods == null) {
			searchGoods = "";
		}
		if (searchKjsNo == null) {
			searchKjsNo = "";
		}
		if (searchBarcode == null) {
			searchBarcode = "";
		}
		if (OrderFrom == null || OrderFrom.equals("")) {
			if (type == 0) {
				OrderFrom = "ALL";
			} else {
				OrderFrom = userid + "-" + "ALL";
			}
		}
		if (searchOriginalNo == null) {
			searchOriginalNo = "";
		}

		hqlString = "select count(*) from COrderMain where comId=:com_id and effective=true and expressNo like :searchExpressNo and CStatus like :orderStatusSelect and detailMemo like :searchGoods and kjsNo like :searchKjsNo and barCode like :searchBarcode and CUserAddr like :searchAddr and CStatus=:CStatus and originalId like :OriginalNo and SUBSTRING(COrderId,4,2) ='WX'"
				+ "  and COrderId like :COrderId and CUserName like :CUserName";

		Query query = session.createQuery(hqlString);
		query.setParameter("CStatus", str);

		query.setParameter("com_id", com_id);

		query.setParameter("COrderId", "%" + orderNo + "%");
		query.setParameter("CUserName", "%" + searchUserName + "%");
		query.setParameter("OriginalNo", "%" + searchOriginalNo + "%");
		query.setParameter("searchAddr", "%" + searchAddr + "%");
		query.setParameter("searchGoods", "%" + searchGoods + "%");
		query.setParameter("searchKjsNo", "%" + searchKjsNo + "%");
		query.setParameter("searchBarcode", "%" + searchBarcode + "%");
		query.setParameter("orderStatusSelect", "%" + orderStatusSelect + "%");
		query.setParameter("searchExpressNo", "%" + searchExpressNo + "%");
		i = Integer.parseInt(query.list().get(0).toString());
		session.flush();
		return i;

	}



	public java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> getOrderList(String com_id, Session session,
			String userid, int type) {
		java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> rs = null;
		String hql;
		if (type != 0) {
			hql = "from COrderMain where effective=true and userId=:user order by CTime desc";
		} else {
			hql = "from COrderMain where effective=true and id.comId=:com_id order by CTime desc";
		}

		Query q = session.createQuery(hql);
		if (type != 0) {
			q.setParameter("user", userid);
		} else {
			q.setParameter("com_id", com_id);
		}

		rs = (java.util.List<com.cqqyd2014.hibernate.entities.COrderMain>) q.list();

		session.flush();

		return rs;
	}



	public java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> getOrderListDays(String com_id, Session session,
			int days, String userid, int type) {
		java.util.List<com.cqqyd2014.hibernate.entities.COrderMain> rs = null;
		String hql;
		if (type == 0) {
			hql = "from COrderMain where id.comId=:com_id and effective=true and CTime>:t order by CTime desc";
		} else {
			hql = "from COrderMain where effective=true  and CTime>:t and userId=:user order by CTime desc";
		}
		Query q = session.createQuery(hql);
		java.util.Date t = com.cqqyd2014.util.DateUtil.getNearDays(new java.util.Date(), days);
		// System.out.println(t.toString());
		q.setParameter("t", t);
		if (type != 0) {
			q.setParameter("user", userid);
		} else {
			q.setParameter("com_id", com_id);
		}
		rs = (java.util.List<com.cqqyd2014.hibernate.entities.COrderMain>) q.list();

		session.flush();

		return rs;
	}

	// 更新系统计费的总价
	public BigDecimal updateAmount2(Session session, String orderNo,String com_id) {
		BigDecimal rs;
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailSum2(session, orderNo);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCAmount2(rs);
		session.update(om);
		session.flush();
		return rs;

	}

	// 更新用户价格表的总价
	public BigDecimal updateAmount(Session session, String orderNo,String com_id) {
		BigDecimal rs;
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailSum(session, orderNo);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCAmount(rs);
		session.update(om);
		session.flush();
		return rs;

	}

	public void updateCStatus(Session session, String orderNo, String status, String com_id) {

		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCStatus(status);
		om.setLastTime(new java.sql.Timestamp((new java.util.Date()).getTime()));
		session.update(om);
		session.flush();

	}

	// 更新拣货状态
	public float updateGTStatus(Session session, String orderNo, String status,String com_id) {
		float rs = 0;

		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setGtStatus(status);
		om.setLastTime(new java.sql.Timestamp((new java.util.Date()).getTime()));


		session.update(om);
		session.flush();
		om = null;
		return rs;

	}

	// 后台使用，单参数更新EMS状态
	public float updateEMSStatus(Session session, String orderNo, String status,String com_id) {
		float rs = 0;

		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setEmsStatus(status);
		om.setLastTime(new java.sql.Timestamp((new java.util.Date()).getTime()));
		// om.setHwStartTime(new java.sql.Timestamp((new
		// java.util.Date()).getTime()));
		if (status.equals("发货完毕")) {
			om.setCStatus("发货完毕");
		}
		session.update(om);
		session.flush();
		om = null;
		return rs;

	}

	// 后台使用的，单参数更新状态

	public float updateStatus(Session session, String orderNo, String status,String com_id) {

		float rs = 0;

		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCStatus(status);
		// om.setLastTime(new java.sql.Timestamp((new
		// java.util.Date()).getTime()));
		session.update(om);
		session.flush();
		return rs;

	}

	// 检测状态是否为“待申报”

	public boolean checkIfReadyForDeclear(Session session, String orderNo,String com_id) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		String status = om.getCStatus();
		if (status.equals("待申报") || status.equals("审核退单")) {
			return true;
		} else {
			return false;
		}

	}


	// 检测状态是否为“有效”
	public boolean checkIfReadyForDel(Session session, String orderNo,String com_id) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		String status = om.getCStatus();
		if (status.equals("待申报")) {
			return true;
		} else {
			return false;
		}

	}

	// 检测是否已经支付
	/*
	 * public boolean checkIfReadyForPayment(Session session, String orderNo,
	 * String userid) { com.cqqyd2014.hibernate.entities.COrderMain om =
	 * getOrder(session, orderNo); String status = om.getIfPayment(); if
	 * (status.equals("是")) { return true; } else { return false; }
	 * 
	 * }
	 */

	// 更新系统价格表税
	public BigDecimal updateTax2(Session session, String orderNo,String com_id) {
		BigDecimal rs = new java.math.BigDecimal("0");
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailTax2(session, orderNo);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCTax2(rs);
		session.update(om);
		session.flush();
		return rs;

	}

	// 更新客户价格表税
	public BigDecimal updateTax(Session session, String orderNo,String com_id) {
		BigDecimal rs = new java.math.BigDecimal("0");
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailTax(session, orderNo);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCTax(rs);
		session.update(om);
		session.flush();
		return rs;

	}

	public double updateCount(Session session, String orderNo,String com_id) {
		double rs = 0;
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailQty(session, orderNo);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCQty(new BigDecimal(rs));
		session.update(om);
		session.flush();
		return rs;

	}

	public String updatDeatilMemo(Session session, String orderNo,String com_id) {
		String rs = "";
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailStr(session, orderNo,com_id);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setDetailMemo(rs);
		session.update(om);
		session.flush();
		return rs;

	}

	// 从跨境平台单号，得到勤驿达系统单号
	public String getOrderNoByJksNo(Session session, String kjsNo) {
		String hql = "from COrderMain where kjsNo=:kjsNo";
		Query q = session.createQuery(hql);
		q.setParameter("kjsNo", kjsNo);
		com.cqqyd2014.hibernate.entities.COrderMain om = (com.cqqyd2014.hibernate.entities.COrderMain) q.list().get(0);
		String c_order_id = om.getId().getOrderNo();
		om = null;
		q = null;
		return c_order_id;
	}

	public double updateWeight(Session session, String orderNo,String com_id) {
		double rs = 0;
		com.cqqyd2014.hibernate.dao.OrderDetailDAO oddao = new com.cqqyd2014.hibernate.dao.OrderDetailDAO();
		rs = oddao.getDetailWeight(session, orderNo);
		oddao = null;
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCWeight(new BigDecimal(rs));
		session.update(om);
		session.flush();
		return rs;
	}

	public void declareOrder(Session session, String orderNo, String userid,String com_id) {

		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		om.setCStatus("待审核");
		session.update(om);
		session.flush();
	}
	//仅通过单号获取订单
	public com.cqqyd2014.hibernate.entities.COrderMain getOrder(Session session, String orderNo) {
		com.cqqyd2014.hibernate.entities.COrderMain om = null;

		String hql = "from COrderMain where id.orderNo=:COrderId";
		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q
				.list();
		if (oms.size() == 0) {
			oms = null;
			q = null;
			return null;
		} else {
			om = oms.get(0);
			oms = null;
			q = null;
		}
		// session.flush();
		return om;
	}

	//标准获取订单
	public com.cqqyd2014.hibernate.entities.COrderMain getOrder(Session session, String orderNo, String com_id) {
		com.cqqyd2014.hibernate.entities.COrderMain om = null;

		String hql = "from COrderMain where id.orderNo=:COrderId and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q
				.list();
		if (oms.size() == 0) {
			oms = null;
			q = null;
			return null;
		} else {
			om = oms.get(0);
			oms = null;
			q = null;
		}
		// session.flush();
		return om;
	}



	// 没有user参数的loadOrder由系统后台程序使用,load锁定该行
	public com.cqqyd2014.hibernate.entities.COrderMain loadOrder(Session session, String orderNo) {
		com.cqqyd2014.hibernate.entities.COrderMain om = null;
		// COrderMain om_load=null;
		String hql = "from COrderMain as om where id.orderNo=:COrderId ";
		Query q = session.createQuery(hql);
		q.setLockMode("om", LockMode.UPGRADE_NOWAIT);
		q.setParameter("COrderId", orderNo);

		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q
				.list();
		if (oms.size() == 0) {
			return null;
		} else {
			om = oms.get(0);
			// String id=om.getCOrderId();
			// om_load = (COrderMain)
			// session.load(COrderMain.class, id, LockMode.UPGRADE_NOWAIT);

		}
		session.flush();
		return om;
	}




	// 返回的是uuid的userid
	public String getUserIdFromOrderNo(Session session, String orderNo,String com_id) {
		com.cqqyd2014.hibernate.entities.COrderMain om = getOrder(session, orderNo,com_id);
		String user_id = om.getUserId();
		om = null;
		return user_id;
	}



	

	public boolean checkOrderIdIfExist(Session session, String orderNo) {
		boolean b = false;
		String hql = "from COrderMain where id.orderNo=:COrderId";
		Query q = session.createQuery(hql);
		q.setParameter("COrderId", orderNo);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain>) q
				.list();
		if (oms.size() > 0) {
			b = true;
		}
		return b;
	}
}
