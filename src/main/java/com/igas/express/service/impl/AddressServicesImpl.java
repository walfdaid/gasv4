package com.igas.express.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.AddressDao;
import com.igas.express.model.entitiy.Address;
import com.igas.express.service.AddressService;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class AddressServicesImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	public ResponseObject createAddress(Address address) {
		boolean valid = ((addressDao.findByAddressId(address.getAddressId()) == null) ? true : false);

		ResponseObject response = null;
		long id = -1;
		if (valid) {

			addressDao.save(address);
			id = address.getAddressId();
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_CREATING_MESSAGE, id);
		} else
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"address Id already exist");

		return response;
	}

	@Override
	public ResponseObject updateAddress(long id, Address address) {
		Address addressId = addressDao.findByAddressId(id);
//		String addressCity=address.getCity();
//		String addressCuontry=address.getCountry();
//
//		boolean valid = false;
		ResponseObject response = null;

		

		if (addressId !=null) {
			
			
		 
			addressDao.save(address);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_UPDATING_MESSAGE, id);
		}
		 else  {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"missing wright id");
		}

		return response;
	}

	@Override
	public ResponseObject deleteAddress(long id) {

		Address addressId = addressDao.findByAddressId(id);
		ResponseObject response = null;

		if (addressId == null) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"Id not found");
		} else {
			addressDao.deleteById(id);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_UPDATING_MESSAGE, id);
		}
		return response;
	}

	@Override
	public ResponseObject getAllAddress() {
		List<Address> allAddress = addressDao.findAll();
		ResponseObject response = null;

		if (allAddress.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {

			response = new ResponseObjectAll<Address>(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_RESPONSE_CODE,
					ResponseMessage.SUCCESS_GETTING_MESSAGE,allAddress);
		}
		return response;
	}

	@Override
	public ResponseObject getAddressById(long addressId) {
		ResponseObject response = null;
		Address address = addressDao.findByAddressId(addressId);

		if (address == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {

			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, address);
		}
		return response;
	}

}
