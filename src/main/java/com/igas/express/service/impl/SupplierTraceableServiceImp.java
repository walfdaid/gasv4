package com.igas.express.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.SupplierTraceableDao;
import com.igas.express.model.entitiy.SupplierTraceable;
import com.igas.express.service.SupplierTraceableService;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
@Service
@Component
public class SupplierTraceableServiceImp implements SupplierTraceableService {
	@Autowired
	private SupplierTraceableDao supplierTraceableDao;

	@Override
	public ResponseObject getAllSupplierTracable() {
		List<SupplierTraceable> allSupplierTracable = (List<SupplierTraceable>) supplierTraceableDao.findAll(); 
		ResponseObject response = null;
	    
		if (allSupplierTracable.isEmpty())
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
			else{
			response = new ResponseObjectAll<SupplierTraceable>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , allSupplierTracable);
			}
			return response;
	}

	@Override
	public ResponseObject createSupplierTracable(SupplierTraceable supplierTracable) {
		supplierTraceableDao.save(supplierTracable);
		
		return new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_CREATE_CODE , ResponseMessage.SUCCESS_CREATING_MESSAGE);
		
	}

}
