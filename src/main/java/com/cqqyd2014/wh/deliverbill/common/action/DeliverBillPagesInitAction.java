package com.cqqyd2014.wh.deliverbill.common.action;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@SuppressWarnings("serial")
@ParentPackage("bfkjs-json-default")
@Namespace("/wh")
public class DeliverBillPagesInitAction extends UserLoginedAction {
	java.util.LinkedHashMap<String, String> wh_map;
	String default_warehouse;
	public String getDefault_warehouse() {
		return default_warehouse;
	}
	public void setDefault_warehouse(String default_warehouse) {
		this.default_warehouse = default_warehouse;
	}
	public java.util.LinkedHashMap<String, String> getWh_map() {
		return wh_map;
	}
	public void setWh_map(java.util.LinkedHashMap<String, String> wh_map) {
		this.wh_map = wh_map;
	}


	int pageSize;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
	

	public void init() {

		super.execute();
		sm.setAuth_success(true);
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			com.cqqyd2014.hibernate.dao.WareHouseDAO whdao=new com.cqqyd2014.hibernate.dao.WareHouseDAO();
			
			wh_map=whdao.getUserWareHouseMapByComId(session, com_id);
			com.cqqyd2014.hibernate.dao.UserParDAO updao=new com.cqqyd2014.hibernate.dao.UserParDAO();
			pageSize=Integer.parseInt(updao.getValue(session, user_id, com_id, "default_rows_in_page"));
			default_warehouse=updao.getValue(session, user_id, com_id, "default_warehouse");
			tx.commit();
		
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
	}


}