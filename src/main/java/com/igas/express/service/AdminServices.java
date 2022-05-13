package com.igas.express.service;


import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.Admin;
import com.igas.express.util.ResponseObject;

@Service
public interface AdminServices {
	
	/*
	 * *Task : return all admins 
	 * @param : ---
	 * @return : collection of admins as List<Admin> if the operation done successfully(list not empty) else emptyList and response meassages and code and status
	 */
	public ResponseObject getAllAdmin() ; 
	
	
	
	
	/*
	 * *Task : create new amdin 
	 * @param : admin as Admin object
	 * @return : id for created admin as long if the operation done successfully(phoneNumber not exist) else return null  and response massages , code and status 
	 */
	public ResponseObject createAdmin(Admin admin);
	
	/*
	 * *Task : update admin with specified id 
	 * @param : admin as Admin object    
	 * @return : response massages , code and status
	 */
	public ResponseObject updateAdmin(long id , Admin amdin);
	
	/*
	 * *Task : update admin status with specified id  
	 * @param : id as long , status as boolean     
	 * @return : response massages , code and status
	 */
	public ResponseObject updateAdminStatus(long id , boolean status);
	
	/*
	 * *Task : delete admin with specified id 
	 * @param : id as long   
	 * @return :  response massages , code and status
	 * 
	 */
	public ResponseObject  deleteAdmin(long id);

	/*
	 * *Task : do all admin login processes 	    
	 * @param : user name as String , password as String 
	 * @return : response massages , code and status
	 */
	public ResponseObject loginAdmin(String userName , String password);
	/*
	 * *Task : change admin password  	    
	 * @param : password as String , id  as long 
	 * @return : response massages , code and status
	 */
	public ResponseObject changePassword(long id, String password);

	public ResponseObject getAdminById(long adminId);




	public ResponseObject logOut(long id);
	

}
