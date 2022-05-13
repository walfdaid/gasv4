package com.igas.express.util;

public class SupplierSales {
	
	private String supplierName;
	private int numberOfOrders;
	private int quantity;
	private double totalAmount;
	private int newStatus;
	private int pickedStatus;
	private int deleverdStatus;
	private int cancelStatus;
	private int pospondStatus;
	private int rejectedStatus;
	
	public SupplierSales(String supplierName, int numberOfOrders, int quantity, double totalAmount, int newStatus,
			int pikedStatus, int deleverdStatus, int cancelStaus, int pospondStatus, int rejectedStatus) {
		this.supplierName = supplierName;
		this.numberOfOrders = numberOfOrders;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.newStatus = newStatus;
		this.pickedStatus = pikedStatus;
		this.deleverdStatus = deleverdStatus;
		this.cancelStatus = cancelStaus;
		this.pospondStatus = pospondStatus;
		this.rejectedStatus = rejectedStatus;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}

	

	public int getDeleverdStatus() {
		return deleverdStatus;
	}

	public void setDeleverdStatus(int deleverdStatus) {
		this.deleverdStatus = deleverdStatus;
	}

	public int getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(int cancelStaus) {
		this.cancelStatus = cancelStaus;
	}

	public int getPospondStatus() {
		return pospondStatus;
	}

	public void setPospondStatus(int pospondStatus) {
		this.pospondStatus = pospondStatus;
	}

	public int getRejectedStatus() {
		return rejectedStatus;
	}

	public void setRejectedStatus(int rejectedStatus) {
		this.rejectedStatus = rejectedStatus;
	}
	
	public int getPickedStatus() {
		return pickedStatus;
	}

	public void setPickedStatus(int pickedStatus) {
		this.pickedStatus = pickedStatus;
	}

	
	

}
