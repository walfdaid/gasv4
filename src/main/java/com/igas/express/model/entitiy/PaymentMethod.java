package com.igas.express.model.entitiy;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class PaymentMethod implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@NonNull
	private long paymentMethodId; // primary key
	@NonNull
	private String paymentName;
	@NonNull
	private boolean Status;

	

	public long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	
	@Override
	public String toString() {
		return "PaymentMethod [id=" + paymentMethodId + ", paymentName=" + paymentName + ", Status=" + Status 
				+ "]";
	}

}
