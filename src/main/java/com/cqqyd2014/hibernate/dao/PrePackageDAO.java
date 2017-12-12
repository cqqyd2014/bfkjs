package com.cqqyd2014.hibernate.dao;

import org.apache.struts2.json.annotations.SMD;
import org.hibernate.Query;
import org.hibernate.Session;

import com.cqqyd2014.util.message.IfMessage;



public class PrePackageDAO {

	
	

	public void makePreSnClean(Session session,String com_id,String userid){
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> psws=getUnPrintPreSn(session,com_id,userid);
		for (int i=0;i<psws.size();i++){
			com.cqqyd2014.hibernate.entities.PrePackageM psw=psws.get(i);
			psw.setPrinted(true);
			session.saveOrUpdate(psw);
		}
		
	}
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> getPrepackUnSended(Session session,String com_id){
		
		String hql="from PrePackageM where effective=true and packaged=true and id.comId=:com_id and sended=false order by packageTime desc";
		
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM>)q.list();
		return sws;
		
	}

	
	
	
	public java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> getUnPrintPreSn(Session session,String com_id,String userid){
		String hql="from PrePackageM where printed=false and id.comId=:com_id and createUserid=:userid order by packageTime desc";
		
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("userid", userid);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM>)q.list();
		return sws;
	}

	
	
	public com.cqqyd2014.hibernate.entities.PrePackageM getEntityByPrepackageBarcode(Session session,String com_id,String prepacakge_barcode){
		String hql="from PrePackageM where  id.comId=:com_id and id.packageBarcode=:prepacakge_barcode";
		
		Query q = session.createQuery(hql);
		q.setParameter("com_id", com_id);
		q.setParameter("prepacakge_barcode", prepacakge_barcode);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM> sws=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.PrePackageM>)q.list();
		if (sws.size()==0){
			return null;
		}
		else{
			return sws.get(0);
		}
		
	}
}