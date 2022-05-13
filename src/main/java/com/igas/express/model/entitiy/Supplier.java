package com.igas.express.model.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;



@Entity
public class Supplier implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@NonNull
	private long supplierId; // primary key 
	@NonNull
	@Column(unique = true)
	private String phoneNumber;
	@NonNull
	@Column(unique = true)
	private String userName;
	@Column(unique = true)
	private String email;
	@NonNull
	private String fullName;
	@NonNull
	private boolean status;
	@NonNull
	private Date createdAt;  
	@NonNull
	private String password;
	@NonNull
	private long createdBy;
	@NonNull
	private boolean online;
	@NonNull
	private String token;
	@NonNull
	private String image;
	
	//
	
	private Date deletedAt;
	private Date updatedAt;
	private double longittude;
	private double lattiude;
	
	
	//relations 
	@ManyToOne
	@NonNull
	private Admin admin;
	
	
	
	// no args constructor
	public Supplier(){
		
		setCreatedAt(new Date());
		setStatus(true);
		setOnline(false);
		setToken("0000");
		setImage("");
	}
	// user define constructor
	public Supplier(String fullName , String phoneNumber , String password , String userName ,String image){
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userName = userName;
		this.image = image;
		setCreatedAt(new Date());
		setStatus(true);
		setOnline(false);
		setToken("0000");
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getLongittude() {
		return longittude;
	}
	public void setLongittude(double longittude) {
		this.longittude = longittude;
	}
	public double getLattiude() {
		return lattiude;
	}
	public void setLattiude(double lattiude) {
		this.lattiude = lattiude;
	}
	
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getAdmin(){
		return admin.getUserName();
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	@Override
	public String toString() {
		return "Supplier [supplierID=" + supplierId  + ", status=" + status + ", fullName="
				+ fullName + ", phoneNumber=" + phoneNumber + ", createdAt=" + createdAt + ", imageURL=" 
				+ ", deletedAt=" + deletedAt + ", updatedAt=" + updatedAt + ", email=" + email + ", password="
				+ password + "]";
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	

	
	

	
	

	
	
	
	
	
	
	




}
