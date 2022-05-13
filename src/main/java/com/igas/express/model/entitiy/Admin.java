package com.igas.express.model.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Admin implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@NonNull
	private long adminId; // primary key 
	@Column(unique = true)
	@NonNull
	private String email;
	@NonNull
	@Column(unique = true)
	private String phoneNumber;
	@NonNull
	@Column(unique = true)
	private String userName;
	@NonNull
	private String fullName; 
	@NonNull
	private Date createdAt;
	@NonNull
	private boolean status;
	@NonNull
	private String password;
	@NonNull
	private int role;
	@NonNull
	private String token;
	
	

	//
	private Date updatedAt;
	private Date deletedAt;

	// Relations
	
	public Admin(){
		setStatus(true);
		setCreatedAt(new Date());
		setRole(0); //the role is admin
		setToken("0000"); //root token
	}
	
	
	public Admin(String fullName, String email, String phoneNumber , String password , String userName , int role) {
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userName = userName;
		this.role = role;
		setStatus(true);
		setCreatedAt(new Date());
		setToken("0000");
		
		}
	
	
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Admin [fullName=" + fullName + ", email=" + email + ", password=" + password + "]";
	}
	public String getPassword() {
		return password;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		if (role >= 0 && role <=4)  //cause we have 4 role if the role between 0 and 0 do this...
		this.role = role;
	}
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}



	

}
