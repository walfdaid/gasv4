package com.igas.express.util;

public class CustomSupplier {
	private String phoneNumber;
	private String name;
	
	public CustomSupplier (String phoneNumber , String name){
		this.setPhoneNumber(phoneNumber);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
