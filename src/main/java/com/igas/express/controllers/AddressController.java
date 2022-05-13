package com.igas.express.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.igas.express.model.entitiy.Address;
import com.igas.express.service.AddressService;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/address")
@CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	//create new address
	@RequestMapping( method = RequestMethod.POST )
	public ResponseObject createAddress(@RequestBody Address address) {
		
		return addressService.createAddress(address);
		
	}
	
	//update address
	@PutMapping( "/{id}")
	public ResponseObject updateAddress(@PathVariable long id,@RequestBody Address address) {
		
		return addressService.updateAddress(id, address);
	}
	
	
	//delete address
	@RequestMapping(method = RequestMethod.DELETE , value ="/{id}")
	public ResponseObject deleteAddress(@PathVariable long id) {
		
		return addressService.deleteAddress(id);
	}
	
	
	//get all address
	@RequestMapping( method = RequestMethod.GET )
	public ResponseObject getAllAddress() {
		
		return addressService.getAllAddress();
		
	}
	
	//get address by specific id
	@RequestMapping( method = RequestMethod.GET , value = "/{addressId}")
	public ResponseObject getAddressById(@PathVariable long addressId) {
		
		return addressService.getAddressById(addressId);
		
	}

}
