package com.igas.express.util;

import com.igas.express.model.entitiy.Address;


public class CustomUser {
	private String phoneNumber;
	private String name;
	private String Email;
	private Address address;
	
	
	public CustomUser(Address address , String phoneNumber,String name,String Email){
		this.setAddress(address);
		this.phoneNumber = phoneNumber; 
		this.name = name;
		this.Email=Email;
	}
	
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}
}
