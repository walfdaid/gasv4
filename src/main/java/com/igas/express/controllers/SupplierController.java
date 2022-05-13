package com.igas.express.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.igas.express.model.entitiy.Supplier;
import com.igas.express.service.SuppliersService;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {
	@Autowired
	private SuppliersService suppliersService;
	
	
	// get all suppliers 
	@RequestMapping(method = RequestMethod.GET )
	public ResponseObject getAllSuppliers() {
		return suppliersService.getAllSupplier();
	}
	
	// get by id 
	@RequestMapping( method = RequestMethod.GET , value = "/{id}" )
	public ResponseObject getSupplierInfoId(@PathVariable Long id )
	{
		return suppliersService.getSupplierById(id);		
	}
	
	@RequestMapping( method = RequestMethod.GET , value = "/online")
	public ResponseObject getOnlineSupplierInfo()
	{
		return suppliersService.getOnlineSupplier();		
	} 
		
	// update supplier location 
	@RequestMapping( method = RequestMethod.PUT , value = "/location/{id}" )
	public ResponseObject updateSupplierInfoLocation(@RequestBody Supplier supplier , @PathVariable long id)
	{
		return suppliersService.updateSupplierLocation(supplier.getLattiude() , supplier.getLongittude() , id);	

	}
		
	// create new Supplier
	@RequestMapping( method = RequestMethod.POST )
	public ResponseObject createSupplier (@RequestBody Supplier supplier ){			
		return suppliersService.createSupplier(supplier);
		}
	// update  Supplier 
	@RequestMapping( method = RequestMethod.PUT , value = "/{id}")
	public ResponseObject updateSupplier  (@PathVariable long id ,@RequestBody Supplier supplier ){
		
		
		return suppliersService.updateSupplier( id , supplier);	
		}
	
	// delete Supplier
	@RequestMapping( method = RequestMethod.DELETE , value = "/{id}")		
	public ResponseObject deleteSupplier  (@PathVariable long id){
	return suppliersService.deleteSupplier(id); }
	
	// login supplier 
	@RequestMapping( method = RequestMethod.POST , value = "/login" )		
	public ResponseObject loginSupplier(@RequestBody Supplier supplier ){
	return suppliersService.loginSupplier(supplier.getUserName(),supplier.getPassword());
		 }
	// logout supplier 
		@RequestMapping( method = RequestMethod.POST , value = "/logout/{id}" )		
		public ResponseObject logOutSupplier(@PathVariable long id ){
		return suppliersService.logOutSuppliers(id);
			 }
	
	// change supplier password 
	@RequestMapping( method = RequestMethod.PUT , value = "/password/{id}" )		
	public ResponseObject changeSupplierPassword  (@PathVariable long id ,@RequestBody Supplier supplier){
		return suppliersService.changePassword(supplier.getPassword() , id); }
	

	// update Supplier status
	@RequestMapping( method = RequestMethod.PUT , value ="/status/{id}")
	public ResponseObject supllierStatus(@PathVariable long id,@RequestBody Supplier supplier){
		return suppliersService.updateSupplierStatus(id, supplier.isStatus());
	}
	// update Supplier status
		@RequestMapping( method = RequestMethod.PUT , value ="/online/{id}")
		public ResponseObject supplierOnline(@PathVariable long id,@RequestBody Supplier supplier){
			return suppliersService.updateSupplierOnline(id, supplier.isOnline());
		}
		//make all suppliers offline
		@RequestMapping( method = RequestMethod.PUT , value ="/endofwork")
		public ResponseObject suppliersOfline(){
			return suppliersService.offlineAllSuppliers();
		}
		
	
	 
	 
}
