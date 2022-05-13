package com.igas.express.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.Item;

@Repository
@Transactional
public interface ItemDao extends CrudRepository<Item, Long> {
	
	/*
	 * * Task : return all GasTypes in items Table 
	 * @param : --- 
	 * @return : collection of GasType Objects as List<GasType> 
	 * */
	public List<Item> findAll();
	
	/*
	 * * Task : return GasType with specified id from items table 
	 * @param : id as long  
	 * @return : Object of type GasType 
	 * */
	
	public Item findByItemId(long id);
	
}
