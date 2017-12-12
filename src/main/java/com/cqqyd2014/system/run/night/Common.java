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

public class Common {
	public  void sendMail(String priority, String receiver, String subject,
			String content) {
		// from=user;
		content ="<table width='500px' border='0px'><tr><td><p>"+content+"</p></td></tr></table>";
		Properties props = new Properties();
		//System.out.println(Env.getSMTP_SERVER());
		props.put("mail.smtp.host", "smtp.binfen365.com"); // 指定SMTP服务器
		props.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
		try {
			Session mailSession = Session.getDefaultInstance(props);
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("webmaster@binfen365.com")); // 发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					receiver)); // 收件人
			message.setSubject(subject); // 邮件主题
			// 指定邮箱内容及ContentType和编码方式
			message.setContent(content, "text/html;charset=utf-8");
			// 指定邮件发送日期
			message.setSentDate(new Date());
			message.setHeader("X-Priority", priority);
			message.saveChanges();
			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.binfen365.com", "webmaster@binfen365.com",
					"Wang1980");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public  String exec (String cmd){
		String result=null;
	try {
		
		Process ps = Runtime.getRuntime().exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
		sb.append(line).append("<br/>");
		}
		result = sb.toString();
		
		} catch (Exception e) {
		e.printStackTrace();
	
		}
	return result;
	}
	public static void main(String[] args) {
		Aliyun al=new Aliyun();
		
		
		

	}
}
