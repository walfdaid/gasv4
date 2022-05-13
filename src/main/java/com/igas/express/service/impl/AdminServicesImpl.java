package com.igas.express.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.AdminDao;
import com.igas.express.model.entitiy.Admin;
import com.igas.express.model.entitiy.Order;
import com.igas.express.service.AdminServices;
import com.igas.express.util.AdminLogin;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class AdminServicesImpl implements AdminServices {
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public ResponseObject getAllAdmin() {
		List<Admin> allAdmins = adminDao.findAll();
		ResponseObject response = null;
		activeAdmin(allAdmins);
		
		if (allAdmins.isEmpty()){
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
		}else{
		nullPassword(allAdmins);
		response = new ResponseObjectAll<Admin>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , allAdmins);
		}
		return response;
	
	}

	
	@Override
	public ResponseObject createAdmin(Admin admin) {
		
		boolean valid  = (((adminDao.findByPhoneNumber(admin.getPhoneNumber()) == null) && (adminDao.findByEmail(admin.getEmail()) == null)&& (adminDao.findByUserName(admin.getUserName()) == null))? true : false) ;
		
		ResponseObject response = null;
		long id  =-1;
		if (valid) {
		 admin.setCreatedAt(new Date());
		 adminDao.save(admin);
		 id = admin.getAdminId();
		 response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_CREATE_CODE , ResponseMessage.SUCCESS_CREATING_MESSAGE ,id );  
		}
		else 
	    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "phone number , email or user name is already exist");  

		return response;
		
	}
	@Override
	public ResponseObject  updateAdmin(long id, Admin admin ) {
		
		Admin adminId    = adminDao.findByAdminId(id);
		Admin adminPhone = adminDao.findByPhoneNumber(admin.getPhoneNumber());
		Admin adminEmail = adminDao.findByEmail(admin.getEmail());
		Admin adminName = adminDao.findByUserName(admin.getUserName());
		
		boolean valid = false;
		ResponseObject response = null;

		
		if (adminId != null)
	    valid = (adminPhone == null && adminEmail == null) || 
	    (adminEmail != null && adminPhone == null && adminId.equals(adminEmail)) || 
	    (adminPhone != null && adminEmail == null && adminId.equals(adminPhone)) || 
	    ((adminPhone!= null && adminEmail != null) && (adminId .equals(adminEmail)) && adminId.equals(adminPhone));
		
		if (adminName != null && !(adminName.equals(adminId)))
			valid = false;
		
		
		if (valid && adminId.isStatus()){
		admin.setUpdatedAt(new Date());
		admin.setCreatedAt(adminId.getCreatedAt());
		admin.setAdminId(id);
		admin.setPassword(adminId.getPassword());
		admin.setRole(adminId.getRole());
		adminDao.save(admin);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE , id );
		}else if (!valid) {
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "phone number , email or user name is already exist" );
		}else if (!admin.isStatus()){
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "Deactivated admin " );

		}
		
		return response;
	}
	
	@Override
	public ResponseObject updateAdminStatus(long id, boolean status) {
		Admin admin = adminDao.findByAdminId(id); 
		ResponseObject response = null;
	
		if (admin != null){		
		admin.setStatus(status);
		if(status == false)
		admin.setDeletedAt(new Date());
		else{
			admin.setUpdatedAt(new Date());
			admin.setDeletedAt(null);
		}
			
		
		adminDao.save(admin);
		response = new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_PATCHING_MESSAGE );
		}else{
		response = new ResponseObjectCrud(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_PATCHING_MESSAGE , id);	
		}
		return response ; 
		
	}

	@Override
	public ResponseObject deleteAdmin(long id ) {
		Admin admin = adminDao.findByAdminId(id);
		ResponseObject response = null; 
		
		if (admin != null && admin.isStatus()){
		admin.setStatus(false);
		admin.setDeletedAt(new Date());
		adminDao.save(admin);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_DELETTING_MESSAGE , id);
		}else{
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_DELETTING_MESSAGE );	
		}
		
		return response;
		
	}
	


	@Override
	public ResponseObject loginAdmin(String userName, String password) {
		Admin admin = adminDao.findByUserName(userName);
		ResponseObject response = null;
		
		if(admin==null){response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_LOGIN_MESSAGE );	
		
		}		
		else if (!admin.isStatus()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_AUTH_CODE ,"Deavctivated admin");	
			
		}
		else if((admin!=null)&&(password.equals(admin.getPassword())) && admin.isStatus())
			{long id =admin.getAdminId();
			 int role = admin.getRole();
			 String token = generateToken();
			 admin.setToken(token);
			 adminDao.save(admin);
		   response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_LOGIN_MESSAGE ,new AdminLogin(id , role,admin.getToken()));
			}
		else{
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_LOGIN_MESSAGE );	
         }
	
		return response;	
	}
	
	
	
	
	//generate login token
	private String generateToken() {
		String token = "";
		char[]charSet = "ABCDEFGHIJKLMNOPQRSTUZWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		while(token.length() < 20){
			token += charSet[(int)(Math.random()*60)];
		}
		return token;
	}


	// make password null before returrning 
	private void nullPassword(List<Admin> admins){
		for (Iterator<Admin> iterator = admins.iterator(); iterator.hasNext(); ) {
		    Admin value = iterator.next();
		    value.setPassword(null);
		    
		}
		
	}
	//RETURN ACTIVE ADMIN 
	private void activeAdmin(List<Admin> admins){
		for (Iterator<Admin> iterator = admins.iterator(); iterator.hasNext();){
			Admin value = iterator.next();
			if( value.isStatus()==false ){
	    		iterator.remove();
	                          }
		}
	}


	@Override
	public ResponseObject changePassword(long id, String password) {
		Admin admin = adminDao.findByAdminId(id); 
		ResponseObject response = null;
	
		if (admin != null && admin.isStatus()){			
		admin.setPassword(password);
		admin.setUpdatedAt(new Date());
		adminDao.save(admin);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_PATCHING_MESSAGE , id);	
		}else if (!admin.isStatus()){
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,"Deactivated admin");
		}
		else{
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_PATCHING_MESSAGE);	
		}
		return response ; 
	}


	@Override
	public ResponseObject getAdminById(long adminId) {
       ResponseObject response = null;
       Admin admin = adminDao.findByAdminId(adminId);
		
		if (admin == null)
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
		else{
		admin.setPassword(null);
		response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , admin);
		}
		return response;
			}


	@Override
	public ResponseObject logOut(long id) {
		Admin admin = adminDao.findByAdminId(id);
		ResponseObject response = null;
		if (admin !=  null){
	    admin.setToken("0000");
	    adminDao.save(admin);
		response = new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , "logged out successfully");
		}else 
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "faild logging out");	
		
		return response;
		
	
	}


	
	

	

}
