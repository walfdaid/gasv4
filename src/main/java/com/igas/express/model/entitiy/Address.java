package com.igas.express.model.entitiy;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@NonNull
	private long addressId;// primary key 
	//
	private String country;
	private String city;
	private String neighborhood;
	private String street;
	private int buildingNumber;
	private int floor;
	private int apartmentNumber;
	private double longittude;
	private double lattiude;
	@NonNull
	private String image;
	
	//relations 

	
	


	public Address () {
		
		setImage("http://i.imgur.com/DH4FRuq.jpg");
		
	}
	
    public Address (String image) {
    	this.image = image;
		
	}






	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
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
	@Override
	public String toString() {
		return "Address [addressID=" + addressId + ", user=" + ", country=" + country + ", city=" + city
				+ ", neighborhood=" + neighborhood + ", street=" + street + ", buldingNumber=" 
				+ ", floor=" + floor + ", apartmentNumber=" + apartmentNumber + ", longittude=" + longittude
				+ ", lattiude=" + lattiude + "]";
	}
	

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	public long getAddressId() {
		return addressId;
	}





	public int getBuildingNumber() {
		return buildingNumber;
	}


	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	
	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	
	

}
