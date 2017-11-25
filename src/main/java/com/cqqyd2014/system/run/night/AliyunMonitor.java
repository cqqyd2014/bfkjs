package com.cqqyd2014.system.run.night;

public class AliyunMonitor {
	public static void main(String[] args) {
		AliyunMonitor al=new AliyunMonitor();
		
		
		

	}
	public AliyunMonitor() {
		super();
		//Common.sendMail("3","wangli@cqqyd.com.cn","磁盘："+com.cqqyd2014.util.ConvertDate.getLocalstr(new java.util.Date()),Common.exec("du / --max-depth 1 -h"));
		//Common.sendMail("3","wangli@cqqyd.com.cn","备份："+com.cqqyd2014.util.ConvertDate.getLocalstr(new java.util.Date()),Common.exec("ls -h -l /backup -lt"));
		Common common=new Common();
		common.sendMail("3","wangli@cqqyd.com.cn","系统："+com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date()),common.exec("free -h"));
		common=null;
		// TODO Auto-generated constructor stub
	}

}
