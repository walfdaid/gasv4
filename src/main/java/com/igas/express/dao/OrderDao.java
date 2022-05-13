package com.igas.express.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.Order;

@Repository
@Transactional
public interface OrderDao  extends CrudRepository <Order , Long> {
	
	/*
	 * * Task : return all Orders in Orders Table 
	 * @param : --- 
	 * @return : collection of Order as List<Order> 
	 * */
    public List<Order> findAll();
    
    /*
	 * * Task : return Order with specified id from Orders Table 
	 * @param : id as long 
	 * @return : Object of type Order   
	 * */
	public Order findByOrderId(long id);
	
	/*
	 * * Task : return Orders with specified status from Orders Table 
	 * @param : status as integer 
	 * @return : Object of type Order   
	 * */
	public List<Order> findByStatus(int status);
	/*
	 * * Task : return all Orders with specified supplier id from orders table
	 * @param : --- 
	 * @return : collection of Order as List<Order> 
	 * */
	public List<Order> findBySupplierId(long supplierId);
	
	public List<Order> findByStatusAndSupplierId(int status , long supplierId);
	
	public List<Order> findByCustomerId(long customerId);
	
	public List<Order> findByUserPhoneNumber(String phoneNumber);
	
	
	
	
	

}
