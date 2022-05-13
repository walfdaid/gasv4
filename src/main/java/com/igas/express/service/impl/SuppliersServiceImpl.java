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
import com.igas.express.dao.SupplierDao;
import com.igas.express.dao.SupplierTraceableDao;
import com.igas.express.model.entitiy.Admin;
import com.igas.express.model.entitiy.Supplier;
import com.igas.express.model.entitiy.SupplierTraceable;
import com.igas.express.service.SuppliersService;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class SuppliersServiceImpl implements SuppliersService {
 
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private SupplierTraceableDao supplierTraceableDao;
	
	@Override
	public ResponseObject getAllSupplier() {

		List<Supplier> allSupplier = supplierDao.findAll(); 
		ResponseObject response = null;
		activeSupplier(allSupplier);
		
	    
		if (allSupplier.isEmpty())
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
			else{
			nullPassword(allSupplier);
			response = new ResponseObjectAll<Supplier>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , allSupplier);
			}
			return response;
	}
	private void activeSupplier(List<Supplier> Suppliers){
		for (Iterator<Supplier> iterator = Suppliers.iterator(); iterator.hasNext();){
			Supplier value = iterator.next();
			if( value.isStatus()==false ){
	    		iterator.remove();
	                          }
		}
	}

	@Override
	public ResponseObject getSupplierById(long id ) {
		
		Supplier supplier = supplierDao.findBySupplierId(id);
		ResponseObject response = null;	
		
		if (supplier == null)
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
		else{
			supplier.setPassword(null);
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , supplier);
		
		}
		return response;
		
	}
	
	@Override
	public ResponseObject createSupplier(Supplier supplier) {
		
		boolean valid  = (((supplierDao.findByPhoneNumber(supplier.getPhoneNumber()) == null) && (supplierDao.findByEmail(supplier.getEmail()) == null) && (supplierDao.findByUserName(supplier.getUserName()) == null))? true : false) ;
		
		ResponseObject response = null;
//		long id = 0; 
		Admin admin = adminDao.findByAdminId(supplier.getCreatedBy());
		
		if (valid && admin.isStatus()) {
		supplier.setAdmin(admin);
		supplierDao.save(supplier);
//		id = supplier.getSupplierId();
	    response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_CREATE_CODE , ResponseMessage.SUCCESS_CREATING_MESSAGE ,supplier );  
		}
		else {
	    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "phone number , email or user name is already exist" );  }
		return response;
		
	}

	@Override
	public ResponseObject updateSupplier(long id ,Supplier supplier) {
		Supplier supplierId    = supplierDao.findBySupplierId(id);
		Supplier supplierPhone = supplierDao.findByPhoneNumber(supplier.getPhoneNumber());
		Supplier supplierEmail = supplierDao.findByEmail(supplier.getEmail());
		Supplier supplierName = supplierDao.findByUserName(supplier.getUserName());
		boolean valid = false;
		ResponseObject response = null;

		
		if (supplierId != null )
	    valid = (supplierPhone == null && supplierEmail == null) || 
	    (supplierEmail != null && supplierPhone == null && supplierId.equals(supplierEmail)) || 
	    (supplierPhone != null && supplierEmail == null && supplierId.equals(supplierPhone)) || 
	    ((supplierPhone!= null && supplierEmail != null) && (supplierId .equals(supplierEmail)) && supplierId.equals(supplierPhone));
		
		if (supplierName != null && !(supplierName.equals(supplierId)))
			valid = false;
		
			
		if (valid && supplierId.isStatus()){
		Admin admin = adminDao.findByAdminId(supplier.getCreatedBy());
		supplier.setAdmin(admin);
		supplier.setUpdatedAt(new Date());
		supplier.setCreatedAt(supplierId.getCreatedAt());
		supplier.setSupplierId(id);
		supplier.setPassword(supplierId.getPassword());
		supplier.setOnline(supplierId.isOnline());
		supplierDao.save(supplier);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE , id );
		}
		else if (!supplier.isStatus()){
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "Deactivated supllier");
		}
		else {
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "phone number , email or user name is already exist" );
		}
		
		return response;
	}
	

	@Override
	public ResponseObject deleteSupplier(long id) {
		Supplier supplier = supplierDao.findBySupplierId(id);
		ResponseObject response = null; 
		
		if (supplier != null && supplier.isStatus() ){
		supplier.setStatus(false);
		supplier.setDeletedAt(new Date ());
		supplierDao.save(supplier);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_DELETTING_MESSAGE , id);
		}else{
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_DELETTING_MESSAGE );	
		}
		
		return response;
	}
	private void nullPassword(List<Supplier> suppliers){
		for (Iterator<Supplier> iterator = suppliers.iterator(); iterator.hasNext(); ) {
			Supplier value = iterator.next();
		    value.setPassword(null);}
		    
		}

	@Override
	public ResponseObject loginSupplier(String userName, String password) {
		Supplier supplier=null;
	    supplier = supplierDao.findByUserName(userName);
			ResponseObject response = null;
			 if(supplier==null){response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_LOGIN_MESSAGE );	
	         }
			 else if (!supplier.isStatus()){
					response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_AUTH_CODE ,"Deactivated supplier" );	
				}
			 
			 else if((supplier!=null)&&(password.equals(supplier.getPassword())) && supplier.isStatus())
				{long id =supplier.getSupplierId();
				supplier.setOnline(true);
				String token = generateToken();
				supplier.setToken(token);
				supplierDao.save(supplier);
				supplierTraceableDao.save(new SupplierTraceable(supplier.getFullName() , supplier.getPhoneNumber(),supplier.getLongittude() , supplier.getLattiude() , supplier.isOnline(), new Date (),null,null));
			   response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_LOGIN_MESSAGE ,id );
				}
			 else
			 {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_LOGIN_MESSAGE );	
				 
			 }
			
			
			return response;
	

	}
	
	private String generateToken() {
		String token = "";
		char[]charSet = "ABCDEFGHIJKLMNOPQRSTUZWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		while(token.length() < 20){
			token += charSet[(int)(Math.random()*60)];
		}
		return token;
	}


	@Override
	public ResponseObject changePassword(String password, long id) {
		Supplier supplier = supplierDao.findBySupplierId(id);
		ResponseObject response = null; 
		
		if (supplier != null ){
		supplier.setPassword(password);
		supplier.setUpdatedAt(new Date());
		supplierDao.save(supplier);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_UPDATING_MESSAGE , id);
		}else{
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_DELETTING_MESSAGE );	
		}
		
		return response;	
		}

	@Override
	public ResponseObject updateSupplierLocation(double lattiude, double longittude , long id ) {
		Supplier supplier = supplierDao.findBySupplierId(id);
		ResponseObject response = null; 
		
		if (supplier != null ){
		supplier.setLongittude(longittude);
		supplier.setLattiude(lattiude);
		supplier.setUpdatedAt(new Date());
		supplierDao.save(supplier);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_UPDATING_MESSAGE , id);
		}else{
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_UPDATING_MESSAGE );	
		}
		
		return response;
	}
	@Override
	public ResponseObject updateSupplierStatus(long id , boolean status) {
	Supplier supplier  = supplierDao.findBySupplierId(id);
		ResponseObject response = null;
		if (supplier !=null)
		{
			supplier.setStatus(status);
			if(status == false)
		    supplier.setDeletedAt(new Date());
			else{
				supplier.setDeletedAt(null);
				supplier.setUpdatedAt(new Date());	
			}
			
			
			
			supplierDao.save(supplier);
		 response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE , id );
    }else {
    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_UPDATING_MESSAGE );
    }
    
    return response;
	}

	@Override
	public ResponseObject getOnlineSupplier() {
		List<Supplier> allSuppliers = supplierDao.findByStatus(true);
		ResponseObject response = null;
		
		if (allSuppliers.isEmpty()){
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
			}else{
				collectUnNessarySuppliers (allSuppliers);
				if (allSuppliers.isEmpty()){
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
				}
				else
				response = new ResponseObjectAll<Supplier>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , allSuppliers);
			}
			return response;
	}

	@Override
	public ResponseObject updateSupplierOnline(long id, boolean online) {
		Supplier supplier  = supplierDao.findBySupplierId(id);
		ResponseObject response = null;
		if (supplier !=null)
		{
			supplier.setOnline(online);
			supplier.setUpdatedAt(new Date());
			supplierDao.save(supplier);
		    response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE , id );
         }else {
         response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_UPDATING_MESSAGE );
    }
    
    return response;
	
	
	
	}
	
	private void collectUnNessarySuppliers (List<Supplier> suppliers){
	
		for (Iterator<Supplier> iterator = suppliers.iterator(); iterator.hasNext();){
			Supplier value = iterator.next();
			if(!value.isOnline())
	    		iterator.remove();
		}
	
	
	}

	@Override
	public ResponseObject logOutSuppliers(long id) {
		Supplier supplier = supplierDao.findBySupplierId(id);
		ResponseObject response = null;
		if (supplier !=  null){
		supplier.setToken("0000");
		supplier.setOnline(false);
		supplierDao.save(supplier);
		
		supplierTraceableDao.save(new SupplierTraceable(supplier.getFullName() , supplier.getPhoneNumber(),supplier.getLongittude() , supplier.getLattiude() ,supplier.isOnline(), null ,new Date(),null));
		response = new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , "logged out successfully");
		}else 
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "faild logging out");	
		
		return response;
	}

	@Override
	public ResponseObject offlineAllSuppliers() {
		List<Supplier> allSupplier = supplierDao.findAll(); 
		ResponseObject response = null;
	    
		if (allSupplier.isEmpty())
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
			else{
			offlineAll(allSupplier);
			response = new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , "all suppliers are offline now " );
			}
			return response;
		
	}

	private void offlineAll(List<Supplier> allSupplier) {
		
		for (Iterator<Supplier> iterator = allSupplier.iterator();iterator.hasNext();){			
			Supplier value = iterator.next();
			value.setOnline(false);
			supplierDao.save(value);
			
			
		}
	}

}
