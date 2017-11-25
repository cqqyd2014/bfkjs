package com.cqqyd2014.system.message;

public  class SendNormailMail extends SendMail {

	public SendNormailMail(org.hibernate.Session session) {
		super(session);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPriority(String priority) {
		// TODO Auto-generated method stub
		this.priority="3";
		
	}

	

}
