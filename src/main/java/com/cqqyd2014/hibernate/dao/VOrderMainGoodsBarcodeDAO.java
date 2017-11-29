package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderMainGoodsBarcodeDAO {
	
	
	
	
	//订单数量的查询
	
	public java.math.BigInteger getOrderNoCount(Session session, java.util.Date start_dat,java.util.Date end_dat,String com_id,java.util.ArrayList<String> order_statuss,String user_name,String user_tell,String goods_name,String original_id,String barcode,String express_no,String package_user,String user_id,java.util.ArrayList<String> gt_statuss,java.util.ArrayList<String> ems_statuss){
		String hql="select count(*) from ( select order_no from v_order_main_goods_barcode where ";

		if (order_statuss.size()>0){
			hql=hql+" c_status in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(order_statuss);
			
		}
		if (gt_statuss.size()>0){
			hql=hql+" and gt_status in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(gt_statuss)+" ";
			
		}
		if (ems_statuss.size()>0){
			hql=hql+" and ems_status in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(ems_statuss)+" ";
		}
		if (package_user!=null){
			hql=hql+" and package_user=\'"+package_user+"\'";
		}
		if (user_id!=null){
			hql=hql+" and user_id=\'"+user_id+"\'";
		}
		if (goods_name!=null){
			hql=hql+" and  detail_memo like \'%"+goods_name+"%\' ";
		}
		if (user_name!=null){
			hql=hql+"and c_user_name like \'%"+user_name+"%\' ";
		}
		if (user_tell!=null){
			hql=hql+"and c_tell like \'%"+user_tell+"%\' ";
		}
		if (original_id!=null){
			hql=hql+"and original_id like \'%"+original_id+"%\' ";
		}
		if (barcode!=null){
			hql=hql+"and goods_barcode like \'%"+barcode+"%\' ";
		}
		if (express_no!=null){
			hql=hql+"and deliver_express_no like \'%"+express_no+"%\' ";
		}
		hql=hql+"and order_dat between \'"+com.cqqyd2014.util.DateUtil.JDateToFullString(start_dat)+"\' and \'"+com.cqqyd2014.util.DateUtil.JDateToFullString(end_dat)+"\' group by order_no) t1";
		System.out.println(hql);
		hql=hql.replace("where and ", "where ");
		return (java.math.BigInteger)session.createSQLQuery(hql).uniqueResult();

		
	}
	

	
	
	
	//订单列表的查询
	public java.util.ArrayList<String> getOrderNos(Session session, java.util.Date start_dat,java.util.Date end_dat,String com_id,String rows,String page,java.util.ArrayList<String> order_statuss,String user_name,String user_tell,String goods_name,String original_id,String barcode,String express_no,String package_user,String user_id,java.util.ArrayList<String> gt_statuss,java.util.ArrayList<String> ems_statuss){
		int currentPage=Integer.parseInt(page);
		int pageSize=Integer.parseInt(rows);
		String hql="select id.orderNo from VOrderMainGoodsBarcode where id.comId=:com_id and id.orderDat between :start_dat and :end_dat ";
		if (package_user!=null){
			hql=hql+"and id.packageUser=\'"+package_user+"\' ";
		}

		if (order_statuss.size()>0){
			hql=hql+"and id.CStatus in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(order_statuss);
			
		}
		if (gt_statuss.size()>0){
			hql=hql+"and id.gtStatus in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(gt_statuss);
			
		}
		if (ems_statuss.size()>0){
			hql=hql+"and id.emsStatus in "+com.cqqyd2014.util.StringUtil.arrayListToSQLInString(ems_statuss);
		}
		if (user_id!=null){
			hql=hql+"and id.userId=\'"+user_id+"\' ";
		}
		if (user_name!=null&&!user_name.equals("")){
			hql=hql+"and id.CUserName like :user_name ";
		}
		if (express_no!=null&&!express_no.equals("")){
			hql=hql+"and id.deliverExpressNo like :express_no ";
		}
		if (original_id!=null&&!original_id.equals("")){
			hql=hql+"and id.originalId like :original_id ";
		}
		if (barcode!=null&&!barcode.equals("")){
			hql=hql+"and id.goodsBarcode like :barcode ";
		}
		if (goods_name!=null&&!goods_name.equals("")){
			hql=hql+"and id.detailMemo like :barcode ";
		}
		if (user_tell!=null&&!user_tell.equals("")){
			hql=hql+"and id.CTell like :user_tell ";
		}
		hql=hql+ "group by id.orderNo,id.orderDat order by id.orderDat desc";
		//System.out.print(hql);
		int offset = pageSize * (currentPage - 1);
		
		Query q=session.createQuery(hql).setFirstResult(offset)
				.setMaxResults(pageSize);;
		q.setParameter("com_id", com_id);
		q.setParameter("start_dat", start_dat);
		q.setParameter("end_dat", end_dat);
		if (user_name!=null&&!user_name.equals("")){
			q.setParameter("user_name", "%"+user_name+"%");
		}
		if (goods_name!=null&&!goods_name.equals("")){
			q.setParameter("goods_name", "%"+goods_name+"%");
		}
		if (user_tell!=null&&!user_tell.equals("")){
			q.setParameter("user_tell", "%"+user_tell+"%");
		}
		
		
		if (express_no!=null&&!express_no.equals("")){
			q.setParameter("express_no", "%"+express_no+"%");
		}
		if (original_id!=null&&!original_id.equals("")){
			q.setParameter("original_id", "%"+original_id+"%");
		}
		if (barcode!=null&&!barcode.equals("")){
			q.setParameter("barcode", "%"+barcode+"%");
		}
		
		
		java.util.ArrayList<String> voms=(java.util.ArrayList<String>)q.list();
		
		return voms;
		
	}
	
	public java.util.ArrayList<com.cqqyd2014.wh.model.OrderBarcode> getOrderBarcodes(Session session,String com_id,String barcode){
		java.util.ArrayList<com.cqqyd2014.wh.model.OrderBarcode> obs=new java.util.ArrayList<>();
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> list=getByBarcodesAll(session,com_id,barcode);
		for (int i=0;i<list.size();i++){
			com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode vmgb=list.get(i);
			com.cqqyd2014.wh.model.OrderBarcode ob=new com.cqqyd2014.wh.model.OrderBarcode();
			ob.setBuyer_addr(vmgb.getId().getAddrProvince()+vmgb.getId().getAddrCity()+vmgb.getId().getAddrDistrict());
			ob.setBuyer_name(vmgb.getId().getCUserName());
			ob.setBuyer_tell(vmgb.getId().getCTell());
			ob.setLogistical_name(vmgb.getId().getDeliverExpressComName());
			ob.setLogistical_number(vmgb.getId().getDeliverExpressNo());
			ob.setOrder_no(vmgb.getId().getOrderNo());
			ob.setOrder_time(com.cqqyd2014.util.DateUtil.JDateToSimpleString(vmgb.getId().getOrderDat()));
			ob.setOriginal_id(vmgb.getId().getOriginalId());
			ob.setWh_name(vmgb.getId().getDeliverWhName());
			ob.setOrder_from_name(vmgb.getId().getOrderTypeFullName());
			obs.add(ob);
		}
		return obs;
		
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> getByBarcodesAll(Session session,String com_id,String barcode){
		String hql="from VOrderMainGoodsBarcode where id.comId=:com_id and id.barcode=:barcode";
		Query q = session.createQuery(hql);
		q.setParameter("barcode",barcode);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> list=(java.util.ArrayList)q.list();
		return list;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> getByBarcodes(Session session,String com_id,String barcode){
		String hql="from VOrderMainGoodsBarcode where id.comId=:com_id and id.barcode=:barcode and id.returnFlag=false";
		Query q = session.createQuery(hql);
		q.setParameter("barcode",barcode);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> list=(java.util.ArrayList)q.list();
		return list;
	}
	
	public java.util.ArrayList<String> getArrayListOrderNosByGoodsBarcode(Session session,String com_id,String barcode){
		String hql="select id.orderNo from VOrderMainGoodsBarcode where id.comId=:com_id and id.goodsBarcode=:barcode group by id.orderNo";
		Query q = session.createQuery(hql);
		q.setParameter("barcode",barcode);
		q.setParameter("com_id",com_id);
		java.util.ArrayList<String> list=(java.util.ArrayList)q.list();
		return list;
	}
	
	
	public com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode getByBarcode(Session session,String com_id,String barcode){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> list=getByBarcodes(session,com_id,barcode);
		if (list.size()>0){
			return list.get(0);
		}
		else{
			return null;
		}
		
		
	}
	public java.math.BigDecimal getDeliverGoodsCount(Session session,String com_id,String order_no){
		String hql="select count(*) from VOrderMainGoodsBarcode where id.comId=:com_id and id.orderNo=:order_no";
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("order_no", order_no);

		return new java.math.BigDecimal(((Long) q.iterate().next()));
	}
	
	private java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> getBarcodeReturnFlag(Session session,String com_id,String order_no,boolean return_flag){
		String hqlString="from VOrderMainGoodsBarcode where id.sended=true and id.comId=:com_id and id.orderNo=:order_no and id.returnFlag=:return_flag";
		Query query = session.createQuery(hqlString);
		query.setParameter("com_id", com_id);
		query.setParameter("order_no", order_no);
		query.setParameter("return_flag", return_flag);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> gis = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode>)query.list();
		return gis;
	}
	
	
	private java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> getUnReturnBarcode(Session session,String com_id,String order_no){
	
	return getBarcodeReturnFlag(session,com_id,order_no,false);
}
	
	
	public java.util.ArrayList<com.cqqyd2014.wh.model.Goods> getUnReturnBarcodeModel(Session session,String com_id,String order_no){
		 java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode> vodrbs=getUnReturnBarcode(session,com_id,order_no);
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> gbs=new java.util.ArrayList<>();
		for (int i=0;i<vodrbs.size();i++){
			com.cqqyd2014.wh.model.Goods gb=new com.cqqyd2014.wh.model.Goods();
			com.cqqyd2014.hibernate.entities.VOrderMainGoodsBarcode vodrb=vodrbs.get(i);
			gb.setBarcode(vodrb.getId().getGoodsBarcode());
			gb.setGoods_id(vodrb.getId().getGoodsId());
			gb.setGoods_name(vodrb.getId().getCName());
			gbs.add(gb);
		}
		return gbs;
	}
}
