package com.cqqyd2014.express.sf.bsp;

public class BspException extends Exception{
	String code;
	String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BspException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
