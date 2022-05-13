package com.igas.express.util;

public class dashborad {
	 
	
	private int users;
	private int normalCylinder;
	private int squeezCylinder;  
	private int totalOrders;  
	private double totalAmount ;  
	private int totalCylinder;  
	private double totalIncome;  
	private double totalOutcome;
	
	
	public dashborad(int users, int normalCylinder, int squeezCylinder, int totalOrders, double totalAmount,
		int totalCylinder, double totalIncome, double totalOutcome) {
		this.users = users;
		this.normalCylinder = normalCylinder;
		this.squeezCylinder = squeezCylinder;
		this.totalOrders = totalOrders;
		this.totalAmount = totalAmount;
		this.totalCylinder = totalCylinder;
		this.totalIncome = totalIncome;
		this.totalOutcome = totalOutcome;
	}
	
public dashborad () {
		
	}
	
	
	

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public int getNormalCylinder() {
		return normalCylinder;
	}

	public void setNormalCylinder(int normalCylinder) {
		this.normalCylinder = normalCylinder;
	}

	public int getSqueezCylinder() {
		return squeezCylinder;
	}

	public void setSqueezCylinder(int squeezCylinder) {
		this.squeezCylinder = squeezCylinder;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalCylinder() {
		return totalCylinder;
	}

	public void setTotalCylinder(int totalCylinder) {
		this.totalCylinder = totalCylinder;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalOutcome() {
		return totalOutcome;
	}

	public void setTotalOutcome(double totalOutcome) {
		this.totalOutcome = totalOutcome;
	}


   
	

}
