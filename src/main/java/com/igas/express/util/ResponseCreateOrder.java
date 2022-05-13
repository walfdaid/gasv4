package com.igas.express.util;

public class ResponseCreateOrder  extends ResponseObject{

	private long id;
	private String Email;
	
	
	
	public ResponseCreateOrder(String status, String code, String message, long id, String Email) {
		super(status, code, message);
		setId(id);
		setEmail(Email);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	

}
