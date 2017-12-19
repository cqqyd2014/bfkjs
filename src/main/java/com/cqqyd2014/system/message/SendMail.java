package com.cqqyd2014.system.message;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public abstract class SendMail {
	
		
	public abstract  void setPriority(String priority);
	
	org.hibernate.Session session;
	public SendMail(org.hibernate.Session session) {
		super();
		this.session=session;
		
		
		this.smtp_server = com.cqqyd2014.system.logic.SysParLogic.getSmtpServer(session);
		this.user =com.cqqyd2014.system.logic.SysParLogic.getSmtpUser(session);
		this.password = com.cqqyd2014.system.logic.SysParLogic.getSmtpPwd(session);
		// TODO Auto-generated constructor stub
	}

	private String smtp_server;
	private String user;
	private String password;
	public String priority;
	public String receiver;
	public String subject;
	public String content;
	
	public  void write(String receiver,String subject,String content){
		this.receiver=receiver;
		this.subject=subject;
		this.content=content;
		send();
	}

	private  void send() {
		
		// from=user;
		Properties props = new Properties();
		//com.cqqyd2014.hibernate.dao.SysParDAO spdao=new com.cqqyd2014.hibernate.dao.SysParDAO();
		props.put("mail.smtp.host", smtp_server); // 指定SMTP服务器
		props.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
		try {
			Session mailSession = Session.getDefaultInstance(props);
			Message message = new MimeMessage(mailSession);
			//不可解释的乱码问题，所以写死了Domain contains control or whitespace in string ``service@cqqyd.com.cn?''
			message.setFrom(new InternetAddress("service@cqqyd.com.cn")); // 发件人
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
			transport.connect(smtp_server, user,
					password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

