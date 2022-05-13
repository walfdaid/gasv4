package com.igas.express.util;

public class AdminLogin {
	
	private long id;
	
	private int role;
	
	private String token;
	
	public AdminLogin (long id , int role , String token){
		this.id = id;
		this.role = role;
		this.setToken(token);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
