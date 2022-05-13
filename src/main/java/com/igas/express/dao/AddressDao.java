package com.igas.express.dao;

import java.util.List;


import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.Address;

@Repository
@Transactional
public interface AddressDao extends CrudRepository<Address, Long> {
	
	/*
	 * * Task : return all Addresses in Address Table 
	 * @param : --- 
	 * @return : collection of Address as List<Address> 
	 * */
	public List<Address> findAll ();
	
	/*
	 * * Task : return Address with specified id from Address table 
	 * @param : id as long  
	 * @return : Object of type Address 
	 * */
	public Address findByAddressId(long id);
	
	
}
