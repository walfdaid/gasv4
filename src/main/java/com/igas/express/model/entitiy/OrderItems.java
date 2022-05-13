package com.igas.express.model.entitiy;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/*
 * created by novent Group backend team (shakalns) 
 */

import org.springframework.lang.NonNull;

@Entity
public class OrderItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@NonNull
	private long orderItemsId;
	// item forgin key alternative 
	private long itemId;
	//
	private int quantity;
	// relations 
	@OneToOne
	private Item item;
	
	
	OrderItems(){
		
	}

	public long getOrderItemsId() {
		return orderItemsId;
	}

	public void setId(long orderItemId) {
		this.orderItemsId = orderItemId;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + orderItemsId + ", qantity="  + "]";
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemName() {
		return item.getItemName();
	}

	public void setOrderItemsId(long orderItemsId) {
		this.orderItemsId = orderItemsId;
	}


	
}
