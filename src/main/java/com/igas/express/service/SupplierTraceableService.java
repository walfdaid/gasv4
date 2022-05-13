package com.igas.express.service;

import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.SupplierTraceable;
import com.igas.express.util.ResponseObject;
@Service
public interface SupplierTraceableService {
	
	 public ResponseObject getAllSupplierTracable();
	 public ResponseObject createSupplierTracable(SupplierTraceable supplierTracable);
	

}
