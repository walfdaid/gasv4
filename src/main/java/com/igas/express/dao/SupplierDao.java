package com.igas.express.dao;

import java.util.List;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.Supplier;

@Repository
public interface SupplierDao extends CrudRepository<Supplier, Long> {
	/*
	 * * Task : return all Suppliers  in Supplier Table 
	 * @param : --- 
	 * @return : collection of Suppliers as List<Supplier> 
	 * */
	public List<Supplier> findAll();
	public List<Supplier> findByStatus(boolean status);
	/*
	 * * Task : return Supplier with specified id from Supplier Table 
	 * @param : id as long 
	 * @return : Object of type Supplier   
	 * */
	public Supplier findBySupplierId(long id);
	/*
	 * * Task : return Supplier with specified location  
	 * @param : longittiude as double, latitude as double 
	 * @return : Object of type Supplier   
	 * */
	public Supplier findByLongittudeAndLattiude(double longittude , double lattiude );
	/*
	 * * Task : return Supplier with specified full name  
	 * @param : full name as String  
	 * @return : Object of type Supplier   
	 * */
	public Supplier findByFullName(String fullName);
	/*
	 * * Task : return Supplier with specified phone number    
	 * @param : phone number as String 
	 * @return : Object of type Supplier   
	 * */
	public Supplier findByPhoneNumber(String phoneNumber);
	/*
	 * * Task : return Supplier with specified email 
	 * @param : email as string 
	 * @return : Object of type Supplier   
	 * */
	public Supplier findByEmail(String email);
	/*
	 * * Task : return Supplier with specified user name  
	 * @param : user name as string 
	 * @return : Object of type Supplier   
	 * */
	public Supplier findByUserName (String userName);
	
	

}
