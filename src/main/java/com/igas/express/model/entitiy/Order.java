package com.igas.express.model.entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.igas.express.util.CustomSupplier;
import com.igas.express.util.CustomUser;


@Entity
@Table(name="orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@NonNull
	private long orderId; // primary key
	@NonNull
	private int status;
	@NonNull
	private Date createdAt;
	@NonNull
	private String statusName;
	


	

	//
	private double totalAmount; 
	private Date updatedAt;
	private Date deliveredAt;
	
	// forgin keys alternatives 
	private long customerId;
	private long supplierId;

	



	// Relations
	@ManyToOne
	@NonNull
	private Supplier supplier;
	
	@ManyToOne
	@NonNull
	private User user;
	
	@OneToMany
	@NonNull
	private List<OrderItems> orderItems;
	
	
	
	public Order(){
		status = 0;
		setStatusName(status);
		setCreatedAt(new Date());
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if(status <= 5 && status >= 0){ //change the status of order by 5 status 
		this.status = status;
		setStatusName(status);
		}
	}
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public CustomSupplier getSupplier() {
		return new CustomSupplier(supplier.getPhoneNumber() , supplier.getFullName());
	}


	public CustomUser getUser() {
		return new CustomUser(user.getAddress() , user.getPhoneNumber(), user.getFullName(),user.getEmail() );
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDeliveredAt() {
		return deliveredAt;

	}

	public void setDeliveredAt(Date deliveredAt) {
		this.deliveredAt = deliveredAt;
	}
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}


	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user="  + ", orderItems="  + ", supplier=" 
				+ ", paymentMethod=" + ", status=" + status + ", created_at=" + createdAt
				+ ", totalAmount=" + totalAmount + ", updated_at=" + updatedAt + ", delivered_at=" + deliveredAt
				+ "]";
	}
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(int status) {
		switch (status) {
		case 0 : statusName = "New";break;
		case 1 : statusName = "Picked";break;
		case 2 : statusName = "Deliverd";break;
		case 3 : statusName = "Canceled";break;
		case 4 : statusName = "PostPoned";break;
		case 5 : statusName = "Rejected";break;
		
		}
	}
	

}
