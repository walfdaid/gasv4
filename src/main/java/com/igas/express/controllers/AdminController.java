package com.igas.express.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.igas.express.model.entitiy.Admin;
import com.igas.express.service.AdminServices;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	private AdminServices adminServices;
	
	// get all admins
	@RequestMapping( method = RequestMethod.GET )
	public ResponseObject getAllAdmins() {
		return  adminServices.getAllAdmin();
	}
	//get admin by id 
	@RequestMapping( method = RequestMethod.GET , value = "/{adminId}")
	public ResponseObject getAdminId(@PathVariable long adminId) {
		return  adminServices.getAdminById(adminId);
	}
	// create new admin
	@RequestMapping( method = RequestMethod.POST )
	public ResponseObject createAdmin (@RequestBody Admin admin){
		return adminServices.createAdmin(admin);		
	}
	// update  admin 
	@RequestMapping( method = RequestMethod.PUT , value = "/{id}")
	public ResponseObject updateAdmin (@PathVariable long id, @RequestBody Admin admin) {	
		return adminServices.updateAdmin( id , admin );			
	}
	
	//delete admin
	@RequestMapping( method = RequestMethod.DELETE , value = "/{id}")
	public ResponseObject deleteAdmin(@PathVariable long id) {
		return adminServices.deleteAdmin(id);			
	}
	
	// change password 
	@RequestMapping( method = RequestMethod.PUT , value = "/password/{id}")	
	public ResponseObject changeAdimnPassword(@RequestBody Admin amdin , @PathVariable long id) {
		return adminServices.changePassword(id , amdin.getPassword());			
	}
	
	// login admin 	
    @RequestMapping( method = RequestMethod.POST , value = "/login")
	public ResponseObject loginAdmin(@RequestBody Admin admin){
			return adminServices.loginAdmin(admin.getUserName(),admin.getPassword());
	}
 // logout admin 	
    @RequestMapping( method = RequestMethod.POST , value = "/logout/{id}")
	public ResponseObject loginOut(@PathVariable long id ){
			return adminServices.logOut(id);
	}
    
 // update admin status
 	@RequestMapping( method = RequestMethod.PUT , value ="/status/{id}")
 	public ResponseObject UserStatus(@PathVariable long id,@RequestBody Admin admin){
 		return adminServices.updateAdminStatus(id,admin.isStatus());
 	}
 	
    
}


