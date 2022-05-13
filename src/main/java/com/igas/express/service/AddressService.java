package com.igas.express.service;

import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.Address;
import com.igas.express.util.ResponseObject;

@Service
public interface AddressService {

	
	/*Task : create new Address 
	 * @param : address as Address object
	 * @return : id for created address as long if 
	 * the operation done successfully
	 * else return null  and response massages , code and status 
	 * */
	public ResponseObject createAddress(Address address);
	
	
	/*
	 * *Task : update address with specified id 
	 * @param : admin as Address object    
	 * @return : response massages , code and status
	 */
	public ResponseObject updateAddress(long id,Address address);
	
	
	
	/*
	 * *Task : delete address with specified id 
	 * @param : id as long   
	 * @return :  response massages , code and status
	 * 
	 */
	public ResponseObject  deleteAddress(long id);
	
	/*
	 * *Task : return all address's 
	 * @param : ---
	 * @return : collection of address's as List<Admin> if the operation done successfully(list not empty) else emptyList and response meassages and code and status
	 */
	public ResponseObject getAllAddress();
	
	public ResponseObject getAddressById(long addressId);
	
}
