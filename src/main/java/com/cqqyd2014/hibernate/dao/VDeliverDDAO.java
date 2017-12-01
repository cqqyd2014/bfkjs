package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VDeliverDDAO {
	
	
	//发货单数量的查询
	
		public java.math.BigInteger getDeliverNoCount(Session session,java.util.Date start_dat,java.util.Date end_dat, String goods_barcode, java.util.ArrayList<String> deliverbill_statuss,
				String express_com, String express_no, String com_id, String rows, String receiver_name,
				String receiver_mobile, String reciever_addr, String page, String send_user, String user_id,String order_no){
			String hql="select count(*) from ( select deliver_no from v_deliver_d where ";
			
			if (goods_barcode!=null&&!goods_barcode.equals("")){
				hql=hql+"goods_barcode like \'%"+goods_barcode+"%\' and ";
			}
			if (order_no!=null&&!order_no.equals("")){
				hql=hql+"order_no like \'%"+order_no+"%\' and ";
			}
			if (deliverbill_statuss.size()>0){
				hql=hql+" deliver_bill_status in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(deliverbill_statuss)+" and ";
				
			}
			if (express_no!=null&&!express_no.equals("")){
				hql=hql+" deliver_express_no like \'%"+express_no+"%\' and ";
			}
			if (express_com!=null&&!express_com.equals("")){
				hql=hql+" express_com=\'"+express_com+"\' and ";
			}
			if (com_id!=null&&!com_id.equals("")){
				hql=hql+" com_id=\'"+com_id+"\' and ";
			}
			if (user_id!=null&&!user_id.equals("")){
				hql=hql+"create_user_name like \'%"+user_id+"%\' and ";
			}
			
			if (receiver_name!=null&&!receiver_name.equals("")){
				hql=hql+"c_user_name like \'%"+receiver_name+"%\' and ";
			}
			if (receiver_mobile!=null&&!receiver_mobile.equals("")){
				hql=hql+"receiver_mobile like \'%"+receiver_mobile+"%\' and ";
			}
			if (reciever_addr!=null&&!reciever_addr.equals("")){
				hql=hql+"c_user_addr like \'%"+reciever_addr+"%\' and ";
			}
			
			if (send_user!=null&&!send_user.equals("")){
				hql=hql+"send_user_name like \'%"+send_user+"%\' and ";
			}
			
			hql=hql+"order_dat between \'"+com.cqqyd2014.util.DateUtil.JDateToFullString(start_dat)+"\' and \'"+com.cqqyd2014.util.DateUtil.JDateToFullString(end_dat)+"\' group by deliver_no) t1";
			//System.out.println(hql);
			java.math.BigInteger l=(java.math.BigInteger)session.createSQLQuery(hql).uniqueResult();
			return l;

			
		}
		
		
		//发货单列表查询
		
		public java.util.ArrayList<String> getDeliverNos(Session session,java.util.Date start_date,java.util.Date end_date, String goods_barcode, java.util.ArrayList<String> deliverbill_statuss,
				String express_com, String express_no, String com_id, String rows, String receiver_name,
				String receiver_mobile, String reciever_addr, String page, String send_user, String user_id,String order_no){
			int currentPage=Integer.parseInt(page);
			int pageSize=Integer.parseInt(rows);
			String hql="select id.deliverNo from VDeliverD where id.comId=:com_id and id.orderDat between :start_dat and :end_dat ";
			if (order_no!=null&&!order_no.equals("")){
				hql=hql+"and id.orderNo like :order_no ";
			}
			if (goods_barcode!=null&&!goods_barcode.equals("")){
				hql=hql+"and id.goodsBarcode like :goods_barcode ";
			}
			if (deliverbill_statuss.size()>0){
				hql=hql+"and id.deliverBillStatus in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(deliverbill_statuss)+" ";
				
			}
			if (express_com!=null&&!express_com.equals("")){
				
				hql=hql+"and id.deliverExpressCom=\'"+express_com+"\' ";
			}
			if (express_no!=null&&!express_no.equals("")){
				hql=hql+"and id.deliverExpressNo like :express_no ";
			}
			
				hql=hql+"and id.comId=\'"+com_id+"\' ";
				if (receiver_name!=null&&!receiver_name.equals("")){
					hql=hql+"and id.CUserName like :receiver_name ";
				}
			
			hql=hql+ "group by id.deliverNo,id.orderDat order by id.orderDat desc";
			//System.out.print(hql);
			int offset = pageSize * (currentPage - 1);
			
			Query q=session.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize);;
			q.setParameter("com_id", com_id);
			q.setParameter("start_dat", start_date);
			q.setParameter("end_dat", end_date);
			if (goods_barcode!=null&&!goods_barcode.equals("")){
				q.setParameter("goods_barcode", "%"+goods_barcode+"%");
			}
			if (order_no!=null&&!order_no.equals("")){
				q.setParameter("order_no", "%"+order_no+"%");
			}
			if (express_no!=null&&!express_no.equals("")){
				q.setParameter("express_no", "%"+express_no+"%");
			}
			if (express_com!=null&&!express_com.equals("")){
				q.setParameter("express_com", "%"+express_com+"%");
			}
			if (receiver_name!=null&&!receiver_name.equals("")){
				q.setParameter("receiver_name", "%"+receiver_name+"%");
			}
	java.util.ArrayList<String> voms=(java.util.ArrayList<String>)q.list();
			
			return voms;
		}

	
	
	public com.cqqyd2014.hibernate.entities.VDeliverD getViewByOrderNoSeqGoodsBarcode(Session session,String order_no,String seq,String goods_barcode,String com_id){
		String hql="from VDeliverD where id.orderNo=:order_no and id.comId=:com_id and id.seq=:seq and id.goodsBarcode=:goods_barcode";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		q.setParameter("seq", seq);
		q.setParameter("goods_barcode", goods_barcode);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
		if (vdds.size()>0) {
			return vdds.get(0);
		}
		else {
			return null;
		}
	}

	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> getViewByGoodsBarcode(Session session,String goods_barcode,String com_id){
		String hql="from VDeliverD where  id.comId=:com_id and  id.goodsBarcode=:goods_barcode";
		Query q = session.createQuery(hql);
		
		q.setParameter("com_id", com_id);
		
		q.setParameter("goods_barcode", goods_barcode);
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
		return vdds;
	}

	
	public java.util.LinkedHashMap<String, java.math.BigDecimal> getGoodsIdMapByOrderSended(Session session,String orderNo,String com_id) {
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=getArrayListViewByOrderNoSended(session,orderNo,com_id);
		
		java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(vdds);
		java.util.LinkedHashMap<String, java.math.BigDecimal> map=com.cqqyd2014.util.HashMapTools.convertArrayListToHashMapCount(dbds, "getGoods_id");
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> getArrayListViewByOrderNoSeq(Session session,String com_id,String order_no,String seq){
		String hql="from VDeliverD where id.orderNo=:order_no and id.comId=:com_id and id.seq=:seq";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		q.setParameter("seq", seq);
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> getArrayListViewByDeliverNo(Session session,String com_id,String deliver_no){
		String hql="from VDeliverD where id.deliverNo=:deliver_no and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("deliver_no", deliver_no);
		q.setParameter("com_id", com_id);
		
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> getArrayListViewByOrderNoReturned(Session session,String com_id,String order_no){
		String hql="from VDeliverD where id.orderNo=:order_no and id.comId=:com_id and id.returned=true";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> getArrayListViewByOrderNo(Session session,String com_id,String order_no){
		String hql="from VDeliverD where id.orderNo=:order_no and id.comId=:com_id";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> getArrayListViewByOrderNoSended(Session session,String order_no,String com_id){
		String hql="from VDeliverD where id.orderNo=:order_no and id.comId=:com_id and id.sended=true";
		Query q = session.createQuery(hql);
		q.setParameter("order_no", order_no);
		q.setParameter("com_id", com_id);
		
		return (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD>)q.list();
	}
	

}
