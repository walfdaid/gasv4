package com.igas.express.model.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="item")
public class Item implements Serializable {



	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue
	@NonNull
	private long itemId; // primary key 
	@NonNull
	private String itemName;
	@NonNull
	private boolean status;
	@NonNull
	private double price;
	@NonNull
	private Date createdAt;
	
	//
	private Date updatedAt;
	private Date deletedAt;
	
	// relations 
	
	public Item(){
		setCreatedAt(new Date());
		setStatus(true);

	}
	public Item(String typeName , double price){
		this.itemName = typeName;
		this.price = price;
		setStatus(true);
		setCreatedAt(new Date());

		
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
//	@Override
//	public String toString() {
//		return "GasType [gasTypeID=" + itemId + ", typeName=" + itemName + ", status=" + status + ", price=" + price
//				+ "]";
//	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	

}
