package com.igas.express.service;


import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.User;
import com.igas.express.util.ResponseObject;

@Service
public interface UserServices {
	/*
	 * *Task : find  all Users
	 * @param : ---
	 * @return : collection of User as List<User> else empty list and response massages , code and status
	 */
    public ResponseObject getAllUsers();
    
    
   public ResponseObject UpdateUserStatus(long id,boolean status);
    /*
	 * *Task : find  all User in avtive 
	 * @param : ---
	 * @return :collection of User as List<User> else empty list and response massages , code and status
	 */
    public ResponseObject getAllUsersInActive(); 
    /*
	 * *Task : find User with specified id 
	 * @param : id as long 
	 * @return : User as User object else null and response massages , code and status
	 */
	public ResponseObject getUserById(long id);
	  /*
	 * *Task : find User with specified phoneNumber 
	 * @param : phoneNumber as String 
	 * @return : user as User object else null and response massages , code and status
	*/
	public ResponseObject getUserByPhoneNumber(String phoneNumber);
	/*
	 * *Task : create new User  
	 * @param : user as Users object
	 * @return : created User id as long and response massages , code and status
	 */
	public ResponseObject createUser(User user );
	/*
	 * *Task : update User info  
	 * @param : user as User object and id as long 
	 * @return : updated User id as long and response massages , code and status
	 */
	public ResponseObject updateUser(long id ,User user );
	/*
	 * *Task : delete with specified id 
	 * @param : id as long 
	 * @return : deleted User id as long and response massages , code and status
	 */
	public ResponseObject deleteUser(long id);
	/*
	 * *Task : do all user login processes 	    
	 * @param : full name as String , password as String 
	 * @return : response massages , code and status
	 */
	public ResponseObject loginUser(String phoneNumber , String password);
	/*
	 * *Task : change user password	    
	 * @param : id as long , password as String 
	 * @return : response massages , code and status
	 */
	public ResponseObject changePassword(long id, String password);
	/*
	 * *Task : find  all User in with specified day 
	 * @param : created at as String
	 * @return : collection of User as List<User> else empty list and response massages , code and status
	 */
	public ResponseObject getUserBycreatedAt(String createdAt);
	public ResponseObject getAllUsersFlight(boolean status, String startDate, String endDate);
	public ResponseObject getUserByDate(String startDate, String endDate);
	public ResponseObject getUserByEmail(String email);
	public ResponseObject existPhoneNumberOrEmail( String phoneNumber,  String email);


	public ResponseObject logoutUser(long id);

	
	

}
