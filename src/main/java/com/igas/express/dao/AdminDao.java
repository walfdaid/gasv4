package com.igas.express.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.Admin;


@Repository
public interface AdminDao extends CrudRepository<Admin, Long> {
	
	/*
	 * * Task : return all Admins in Admin Table 
	 * @param : --- 
	 * @return : collection of Admins Objects as List<Admin> 
	 * */
	public List<Admin> findAll();
	
	/*
	 * * Task : return Admin with specified id from Admin table 
	 * @param : id as long  
	 * @return : Object of type Admin 
	 * */
	
	public Admin findByAdminId(long id);
	
	/*
	 * * Task : return Admin with specified phoneNumber from Admin table 
	 * @param : phoneNumber as String  
	 * @return : Object of type Admin 
	 * */
	
	public Admin findByPhoneNumber(String phoneNumber);
	
	/*
	 * * Task : return Admin with specified eamil from Admin table 
	 * @param : email as String  
	 * @return : Object of type Admin 
	 * */
	
	public Admin findByEmail(String email);
	/*
	 * * Task : return Admin with specified userName from Admin table 
	 * @param : user name as String  
	 * @return : Object of type Admin 
	 * */
	public Admin findByUserName (String userName);



}
