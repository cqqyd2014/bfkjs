package com.cqqyd2014.system.run.night;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Aliyun {

	public static void main(String[] args) {
		Aliyun al=new Aliyun();
		
		
		

	}
	public Aliyun() {
		super();
		Common common=new Common();
		common.sendMail("3","wangli@cqqyd.com.cn","磁盘："+com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date()),common.exec("du / --max-depth 1 -h"));
		common.sendMail("3","wangli@cqqyd.com.cn","备份："+com.cqqyd2014.util.DateUtil.getLocalFullString(new java.util.Date()),common.exec("ls -h -l /backup -lt"));
		//Common.sendMail("3","wangli@cqqyd.com.cn","系统："+com.cqqyd2014.util.ConvertDate.getLocalstr(new java.util.Date()),Common.exec("vmstat"));
		common=null;
		// TODO Auto-generated constructor stub
	}
	

	}

