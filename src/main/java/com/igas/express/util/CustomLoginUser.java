package com.igas.express.util;

import com.igas.express.model.entitiy.Address;

public class CustomLoginUser {
	private long customerId;
	private String email;
	private String phoneNumber;
	private String fullName;
	private Address address;
	private String token;
	
	
	
	public CustomLoginUser ( long customerId ,String email , String phoneNumber , String fullName ,Address address , String token ){
		this.customerId = customerId;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fullName = fullName;
		this.setToken(token);
	}
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
