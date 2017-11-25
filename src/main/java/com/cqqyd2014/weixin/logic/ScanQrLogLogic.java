package com.cqqyd2014.weixin.logic;

import org.hibernate.Session;

public class ScanQrLogLogic {
	
	public static java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> getArrayListModelFromArrayListObject(java.util.ArrayList<Object> os){
		java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> dbds=new java.util.ArrayList<>();
		for (int i=0;i<os.size();i++) {
			Object o=os.get(i);
			com.cqqyd2014.weixin.model.ScanQrLog dbd=(com.cqqyd2014.weixin.model.ScanQrLog)o;
			dbds.add(dbd);
		}
		return dbds;
	}
	
	
	public static com.cqqyd2014.weixin.model.ScanQrLog getModelFromView(com.cqqyd2014.hibernate.entities.VWeixinScanQrLog sqlog_h){
		com.cqqyd2014.weixin.model.ScanQrLog sqlog=new com.cqqyd2014.weixin.model.ScanQrLog();
		sqlog.setBarcode(sqlog_h.getId().getGoodsBarcode());
		sqlog.setCom_id(sqlog_h.getId().getComId());
		sqlog.setIp(sqlog_h.getId().getIpAddr());
		sqlog.setScan_dat(sqlog_h.getId().getScanTime());
		sqlog.setEffective(sqlog_h.getId().getEffective());
		return sqlog;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> getArrayListModelFromArrayListViews(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWeixinScanQrLog> sqlogs_h){
		java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> sqlogs=new java.util.ArrayList<>();
		for (int i=0;i<sqlogs_h.size();i++) {
			com.cqqyd2014.weixin.model.ScanQrLog sqlog=getModelFromView(sqlogs_h.get(i));
			sqlogs.add(sqlog);
		}
		return sqlogs;
	}
	
	public static void save(Session session,com.cqqyd2014.weixin.model.ScanQrLog sqlog) {
		com.cqqyd2014.hibernate.entities.WeixinScanQrLog log_h=new com.cqqyd2014.hibernate.entities.WeixinScanQrLog();
		log_h.setId(new com.cqqyd2014.hibernate.entities.WeixinScanQrLogId(sqlog.getCom_id(), sqlog.getBarcode(), sqlog.getScan_dat()));
		log_h.setEffective(sqlog.isEffective());
		log_h.setIpAddr(sqlog.getIp());
		session.saveOrUpdate(log_h);
	}
	
	public static void cleanLogs(Session session,java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> sqls) {
		for (int i=0;i<sqls.size();i++) {
			com.cqqyd2014.weixin.model.ScanQrLog log=sqls.get(i);
			log.setEffective(false);
			save(session,log);
		}
	}

}
