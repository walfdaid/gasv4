package com.igas.express.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.igas.express.model.entitiy.User;
import com.igas.express.service.UserServices;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserServices userServices;
	
	
	// get all Users active and inactive
	@RequestMapping( method = RequestMethod.GET )
	public ResponseObject getAllUser() {
	return  userServices.getAllUsers();
	}
	// get all inactive users 
	@RequestMapping( method = RequestMethod.GET , value = "/inactive" )
	public ResponseObject getAllUserInActive() {
	return  userServices.getAllUsersInActive();
	}
	// get by id 
	@RequestMapping( method = RequestMethod.GET , value = "/{id}" )
	public ResponseObject getUsersById(@PathVariable long id){
		return userServices.getUserById(id);
	}
     // get by phoneNumber
	@RequestMapping( method = RequestMethod.GET  , params = "phoneNumber")
	public ResponseObject getUsersByPhoneNumber(@RequestParam String phoneNumber ){
		return userServices.getUserByPhoneNumber(phoneNumber);
	}
	 // get by email
		@RequestMapping( method = RequestMethod.GET  , params = "email")
		public ResponseObject getUsersByPhoneEmail(@RequestParam String email ){
			return userServices.getUserByEmail(email);
		}
	// get user by createdAt 
	@RequestMapping( method = RequestMethod.GET  , params = "createdAt" 
			)
	public ResponseObject getUsersByCreatedAt(@RequestParam String createdAt ){
		return userServices.getUserBycreatedAt(createdAt);
	}	
	// create new user
	@RequestMapping( method = RequestMethod.POST)
	public ResponseObject createNewUser (@RequestBody User user){
		return userServices.createUser(user);
	}
	// update user 
	@RequestMapping( method = RequestMethod.PUT , value ="/{id}")
	public ResponseObject updateUsers(@PathVariable long id , @RequestBody User user ){
		return userServices.updateUser(id, user);		
	}
	// delete user 
	@RequestMapping( method = RequestMethod.DELETE , value ="/{id}")
	public ResponseObject deleteUsers(@PathVariable long id){
		return userServices.deleteUser(id);
	}
	// login user
	@RequestMapping( method = RequestMethod.POST , value ="/login")
	public ResponseObject loginUser(@RequestBody User user){
    return userServices.loginUser(user.getPhoneNumber(), user.getPassword());
	}
	
	// logout user
		@RequestMapping( method = RequestMethod.POST , value ="/logout/{id}")
		public ResponseObject loginUser(@PathVariable long id){
	    return userServices.logoutUser(id);
		}

	
	// change user password 
	@RequestMapping( method = RequestMethod.PUT , value ="/password/{id}")
	public ResponseObject changeUserPassword(@RequestBody User user,@PathVariable long id){
		return userServices.changePassword(id , user.getPassword());
	}
	/////////////
	@RequestMapping( method = RequestMethod.GET , value = "/flight" )
	public ResponseObject getAllUserFlight(@RequestParam boolean status , @RequestParam String startDate , @RequestParam String endDate) {
	return  userServices.getAllUsersFlight(status , startDate , endDate);
	}
	
	// update user status
	@RequestMapping( method = RequestMethod.PUT , value ="/status/{id}")
	public ResponseObject UserStatus(@PathVariable long id,@RequestBody User user){
		return userServices.UpdateUserStatus(id,user.isStatus());
	}
	
	  // exist  phoneNumber or email
		@RequestMapping( method = RequestMethod.GET  , value="/exist")
		public ResponseObject existPhoneNumberOrEmail(@RequestParam String phoneNumber, @RequestParam String email){
			return userServices.existPhoneNumberOrEmail(phoneNumber,email);
		}
	
		//get user's in specific date
		@RequestMapping( method = RequestMethod.GET , params = {"startDate" , "endDate"} )
		public ResponseObject getUserByDate( @RequestParam String startDate , @RequestParam String endDate) {
		return  userServices.getUserByDate(startDate, endDate);
		}
	
		
		
	
}
