package com.cqqyd2014.hibernate.dao;
import org.hibernate.Query;
import org.hibernate.Session;

public class SysParDAO {
	
	
	public com.cqqyd2014.hibernate.entities.SysPar getEntityByCode(Session session, String code) {
		String hql = "from SysPar where code=:code";

		Query q = session.createQuery(hql);
		q.setParameter("code", code);
		
		@SuppressWarnings("unchecked")
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar> sws = (java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar>) q
				.list();
		if (sws.size() == 0) {
			System.out.println("不能得到SysPar数据，参数code:" + code);
			return null;
		} else {
			return sws.get(0);
		}
	}
	
	
	//快递单信息
	
	public String getExpressSender(Session session){
		return getSysPar(session,"express_sender");
	}
	public String getExpressCom(Session session){
		return getSysPar(session,"express_com");
	}
	public String getExpressAddr(Session session){
		return getSysPar(session,"express_addr");
	}
	public String getExpressTell(Session session){
		return getSysPar(session,"express_tell");
	}
	public String getExpressSomething(Session session){
		return getSysPar(session,"express_something");
	}
	
	
	public int getTaobaoOrderLen(Session session){
		return getIntPar(session,"tb_order_len");
	}
	
	public int getGmOrderLen(Session session){
		
		return getIntPar(session,"gm_order_len");
	}
	
	//失去心跳，强制下线
	public int getOffLineTime(Session session){
		return getIntPar(session,"offline_time");
	}
	
	//smtp发件服务器信息
	public String getSmtpServer(Session session){
		
		return getSysPar(session,"smtp_server");
	}
	//韵达快递单号长度
	
	public int getYundaExpressNoLen(Session session){
		return getIntPar(session,"yunda_express_no_len");
	}
	
	public String getSmtpUser(Session session){
		return getSysPar(session,"smtp_user");
	}
	public String getSmtpPwd(Session session){
		return getSysPar(session,"smtp_pwd");
	}
	
	
	//微信申报信息保留的时间X天
	
	public int getWxPubDays(Session session){
		
		return getIntPar(session,"wx_pub_days");
	}
	
	
	//微信近几个月的回话记录
	
	public int getWxTalkMonths(Session session){
		return getIntPar(session,"wx_talk_months");
	}
	
	
	//微信问候语间隔
	
	public int getWxNewtalkHours(Session session){
		return getIntPar(session,"wx_newtalk_hours");
	}
	
	
	
	//通用返回 整形参数
	private int getIntPar(Session session,String par){
		String s_par=getSysPar(session,par);
		return Integer.parseInt(s_par);
	}
	
	//返回用户保持登录时间
	public int getIntervalTime(Session session){
		return getIntPar(session,"interval_time");
	}
	
	
	//微信token有效期
	public int getWxDeclareDiffSec(Session session){
		return getIntPar(session,"wx_declare_diff_sec");
		
	}
	

	
	
	public boolean getEmsRegistIf(Session session){
		String emsRegistIf=getSysPar(session,"em_regist_if");
		if (emsRegistIf.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	//EMS邮路信息是否测试
	public boolean getEmsDebug(Session session){
		String emsDebug=getSysPar(session,"ems_debug");
		if (emsDebug.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	//系统级错误邮箱提醒
	public String getWarningMailAddr(Session session){
		return getSysPar(session,"warning_mail_addr");
	}
	
	
	//EMS参数
	public String getEmsRegistUrl(Session session){
		return getSysPar(session,"ems_regist_url");
	}
	
	public String getEmsAuthenticate(Session session){
		return getSysPar(session,"ems_authenticate");
	}
	public String getEmsVersion(Session session){
		return getSysPar(session,"ems_version");
	}
	public String getEmsCustCode(Session session){
		return getSysPar(session,"ems_custcode");
	}
	public String getEmsPass(Session session){
		return getSysPar(session,"ems_pass");
	}
	public String getEmsNoGet(Session session){
		return getSysPar(session,"ems_no_get");
	}
	public String getEmsNoGetUrl(Session session){
		return getSysPar(session,"ems_no_get_url");
	}
	public String getEmsAppId(Session session){
		return getSysPar(session,"ems_app_id");
	}
	public String getEmsAppSecert(Session session){
		return getSysPar(session,"ems_app_secret");
	}
	
	
	//及时达的debug
	public boolean getJsDebug(Session session){
		String jsDebug=getSysPar(session,"js_debug");
		if (jsDebug.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	//及时达的推送url
	public String getJsUrl(Session session){
		return getSysPar(session,"js_url");
	}
	
	
	
	//刀具提醒，给各自发货仓库的ems联系人
	/*
	public String getJsEmsKnifeEmail(Session session){
		return getSysPar(session,"js_ems_knife_email");
	}
	
	
	public String getGtEmsKnifeEmail(Session session){
		return getSysPar(session,"gt_ems_knife_email");
	}
	*/
	public String getEmsKinifeEmail(Session session,String hw_type){
		String hql="from SysPar where code=:code";
		Query query = session.createQuery(hql);
		query.setParameter("code", hw_type+"_ems_knife_email");
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar> sps=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar>)query.list();
		if (sps.size()>0){
			return sps.get(0).getValue();
		}
		else{
			System.out.println("不能查询"+hw_type+"的刀具联系人");
			return null;
		}
	}
	
	private String  getSysPar(Session session,String code){
		String hql="from SysPar where code=:code";
		Query query = session.createQuery(hql);
		query.setParameter("code", code);
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar> sps=(java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar>)query.list();
		if (sps.size()>0){
			return sps.get(0).getValue();
		}
		else{
			System.out.println("不能查询系统参数"+code);
			return null;
		}
		
	}
	
	public String getGtUrl(Session session){
		return getSysPar(session,"gt_url");
	}
	
	
	public boolean getWxDebug(Session session){
		String wxDebug=getSysPar(session,"wx_debug");
		if (wxDebug.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean getGtDebug(Session session){
		String gtDebug=getSysPar(session,"gt_debug");
		if (gtDebug.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public String getPostUrl(Session session){
		
		String post_url=null;
		if (getLocalDebug(session)){
			//测试环境的推送地址
			post_url=getSysPar(session,"local_post_url");
		}
		else{
			//正式环境的推送地址
			post_url=getSysPar(session,"post_url");
		}
		
		return post_url;
	}
	
	
	public boolean getPost20Debug(Session session){
		String postDebug=getSysPar(session,"post20_debug");
		if (postDebug.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean getLocalDebug(Session session){
		String postDebug=getSysPar(session,"local_debug");
		if (postDebug.equals("是")){
			return true;
		}
		else{
			return false;
		}
	}
	


}