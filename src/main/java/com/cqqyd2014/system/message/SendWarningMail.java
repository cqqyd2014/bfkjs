package com.cqqyd2014.system.message;

public  class SendWarningMail extends SendMail {

	public SendWarningMail(org.hibernate.Session session) {
		super(session);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPriority(String priority) {
		// TODO Auto-generated method stub
		this.priority="1";
	}

	
	

}
