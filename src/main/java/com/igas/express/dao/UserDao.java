package com.igas.express.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.User;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

	/*
	 * * Task : return all Users  in User Table 
	 * @param : --- 
	 * @return : collection of Users as List<User> 
	 * */
	public List<User> findAll();
	/*
	 * * Task : return User with specified id from User Table 
	 * @param : id as long 
	 * @return : Object of type User   
	 * */
	public User findByCustomerId(long id );
	/*
	 * * Task : return User with specified phone number from User Table 
	 * @param : phoneNumber as String 
	 * @return : Object of type User   
	 * */
	public User findByPhoneNumber(String phoneNumber);
	/*
	 * * Task : return User with specified email from User Table 
	 * @param : email as String 
	 * @return : Object of type User   
	 * */
	public User findByEmail(String email);
	/*
	 * * Task : return User with specified createdAt from User Table 
	 * @param : createdAt as Date 
	 * @return : Object of type User   
	 * */
	public List<User> findByCreatedAt(Date createdAt);
	public List<User> findByStatus(boolean status);
	
	
}
