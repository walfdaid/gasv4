package com.igas.express.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.OrderItems;

@Repository
@Transactional
public interface OrderItemsDao extends CrudRepository<OrderItems, Long> {
	
	/*
	 * * Task : return all OrderItems in OrderItems Table 
	 * @param : --- 
	 * @return : collection of OrderItems as List<OrderItems> 
	 * */
	public List<OrderItems> findAll();
	/*
	 * * Task : return OrderItems with specified id from OrderItems Table 
	 * @param : id as long 
	 * @return : Object of type OrderItems   
	 * */
	public OrderItems findByorderItemsId(long id);	
	
}
