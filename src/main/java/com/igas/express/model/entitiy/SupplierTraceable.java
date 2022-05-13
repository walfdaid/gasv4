package com.igas.express.model.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class SupplierTraceable implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@NonNull
	private long traceableId;
	@NonNull
	private String supplierName;
	@NonNull
	private String phoneNumber;
	//
	private double longittude;
	private double lattiude;
	private boolean online;
	private Date onlineTime;
	private Date offlineTime;
	private String date;
	
	public SupplierTraceable(String supplierName, String phoneNumber) {
		this.supplierName = supplierName;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	public SupplierTraceable(String supplierName, String phoneNumber, double longittude, double lattiude,
			boolean online, Date onlineTime, Date offlineTime, String date) {
		this.supplierName = supplierName;
		this.phoneNumber = phoneNumber;
		this.longittude = longittude;
		this.lattiude = lattiude;
		this.online = online;
		this.onlineTime = onlineTime;
		this.offlineTime = offlineTime;
		this.date = date;
	}



	public SupplierTraceable() {
		
	}

	public long getTraceableId() {
		return traceableId;
	}

	public void setTraceableId(long traceableId) {
		this.traceableId = traceableId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
	
	
	
	
	

}
