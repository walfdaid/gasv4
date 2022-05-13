package com.igas.express.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.igas.express.model.entitiy.SupplierTraceable;
import com.igas.express.service.SupplierTraceableService;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/suppliertraceable")
@CrossOrigin(origins = "*")
public class SupplierTracableController {
	@Autowired
	private SupplierTraceableService supplierTraceableService;
	
	
	@RequestMapping(method = RequestMethod.GET )
	public ResponseObject getAllSuppliersTraceable() {
		return supplierTraceableService.getAllSupplierTracable();
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseObject createSuppliersTraceable(@RequestBody SupplierTraceable supplierTracable) {
		return supplierTraceableService.createSupplierTracable(supplierTracable);
	}
	
	
	

}
