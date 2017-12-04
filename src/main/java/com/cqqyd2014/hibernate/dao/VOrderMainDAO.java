package com.cqqyd2014.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class VOrderMainDAO {
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> getUnPaidArrayListView(Session session,String com_id){
		String hql="from VOrderMain where id.comId=:com_id and id.paid=false and id.effective=true ";
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		return voms;
	}
	
	public java.math.BigDecimal getWaitPackageCount(Session session,String com_id,String userid){
		String hql="select count(*) from VOrderMain where id.comId=:com_id and id.gtStatus=\'等待拣货\' and id.packageUser=:userid and id.effective=true ";
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("userid", userid);
		Long l=(Long)q.uniqueResult();
		return new java.math.BigDecimal(l);
	}
	
	public static java.math.BigDecimal getWaitAssginPackageCount(Session session,String com_id){
		String hql="select count(*) from VOrderMain where id.comId=:com_id and id.CStatus=\'订单已付\' and id.packageUserName=\'\' and id.effective=true ";
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		Long l=(Long)q.uniqueResult();
		return new java.math.BigDecimal(l);
	}
	
	public static java.math.BigDecimal getUnPaidCount(Session session,String com_id){
		String hql="select count(*) from VOrderMain where id.comId=:com_id and id.paid=false and id.effective=true ";
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		Long l=(Long)q.uniqueResult();
		return new java.math.BigDecimal(l);
	}
	
	
	public com.cqqyd2014.hibernate.entities.VOrderMain getVOrderMain(Session session,String com_id,String order_no){
		String hql="from VOrderMain where id.comId=:com_id and id.orderNo=:order_no ";
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("order_no", order_no);

		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		if (voms.size()==0){
			return null;
		}
		else{
			return voms.get(0);
		}

		
		
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> getOrderMain(Session session,java.util.Date start_dat,java.util.Date end_dat,String com_id
			,String rows,String page,String order_status,String user_name,String user_tell,String goods_name){
		int currentPage=Integer.parseInt(page);
		int pageSize=Integer.parseInt(rows);
		String hql="from VOrderMain where id.comId=:com_id and id.orderDat between :start_dat and :end_dat ";
		if (!order_status.equals("0")){
			hql=hql+"and id.CStatus=\'"+order_status+"\'";
		}
		hql=hql+ " and id.CUserName like :user_name and id.detailMemo like :goods_name and id.CTell like :user_tell order by id.orderDat desc";
		//System.out.print(hql);
		int offset = pageSize * (currentPage - 1);
		
		Query q=session.createQuery(hql).setFirstResult(offset)
				.setMaxResults(pageSize);;
		q.setParameter("com_id", com_id);
		q.setParameter("start_dat", start_dat);
		q.setParameter("end_dat", end_dat);
		q.setParameter("user_name", "%"+user_name+"%");
		q.setParameter("goods_name", "%"+goods_name+"%");
		q.setParameter("user_tell", "%"+user_tell+"%");
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		
		return voms;
		
	}


	
	//需要发货订单
	
public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> getWaitSendList(Session session,String com_id,String user_id,int pageSize,int currentPage){
		
		String hql="from VOrderMain where id.comId=:com_id and id.sendUser=:user_id and id.emsStatus='正在发货' and (id.gtStatus='拣货完成' or id.gtStatus='部分拣货')";
		
		//System.out.print(hql);
		int offset = pageSize * (currentPage - 1);
		
		Query q=session.createQuery(hql).setFirstResult(offset)
				.setMaxResults(pageSize);;
		q.setParameter("user_id", user_id);
		q.setParameter("com_id", com_id);
		
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		
		return voms;
	}


//某个userid正在拣货中的订单，拣货开始，但是还没有处于“拣货完毕”
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> getPackageUseridDoingList(Session session,String com_id,String package_userid){
		String hql="from VOrderMain where id.comId=:com_id and id.packageUser=:user_id and id.gtStatus='拣货处理' ";
		
		//System.out.print(hql);
		Query q=session.createQuery(hql);
		q.setParameter("user_id", package_userid);
		q.setParameter("com_id", com_id);
		
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		
		return voms;
	}

	
	//需要分配拣货的订单
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> getWaitPackageAssignPagesList(Session session,String com_id,int pageSize,int currentPage){
		
		String hql="from VOrderMain where id.comId=:com_id and id.CStatus='订单已付'";
		
		//System.out.print(hql);
		int offset = pageSize * (currentPage - 1);
		
		Query q=session.createQuery(hql).setFirstResult(offset)
				.setMaxResults(pageSize);;
		
		q.setParameter("com_id", com_id);
		
		
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		
		return voms;
	}
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> getListByOrderNoArray(Session session,java.util.ArrayList<String> order_nos,String com_id){
		String order_no_par="";
		if (order_nos.size()==0){
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=new java.util.ArrayList<>();
			return voms;
		}
		for (int i=0;i<order_nos.size();i++){
			order_no_par=order_no_par+"'"+order_nos.get(i)+"',";
		}
		order_no_par=order_no_par.substring(0, order_no_par.length()-1);
		String hql="from VOrderMain where id.orderNo in ("+order_no_par+") and id.comId=:com_id order by id.orderDat desc";
		//System.out.println(hql);
		
		
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain> voms=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderMain>)q.list();
		
		return voms;
	}
	
	
	public Long getOrderMainCount(Session session,java.util.Date start_dat,java.util.Date end_dat,String com_id,String order_status,String user_name,String user_tell,String goods_name){
		String hql="select count(*) from VOrderMain where id.comId=:com_id and id.orderDat between :start_dat and :end_dat ";
		if (!order_status.equals("0")){
			hql=hql+"and id.CStatus=\'"+order_status+"\'";
		}
			
		
		hql=hql+ " and id.CUserName like :user_name and id.detailMemo like :goods_name and id.CTell like :user_tell";
		Query q=session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("start_dat", start_dat);
		q.setParameter("end_dat", end_dat);
		q.setParameter("user_name", "%"+user_name+"%");
		q.setParameter("goods_name", "%"+goods_name+"%");
		q.setParameter("user_tell", "%"+user_tell+"%");
		return ((Long) q.iterate().next());
	}

}
