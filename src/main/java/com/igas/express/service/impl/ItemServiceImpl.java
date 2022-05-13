package com.igas.express.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.ItemDao;
import com.igas.express.model.entitiy.Item;
import com.igas.express.service.ItemService;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public ResponseObject getItems() {
		
		List<Item> items = itemDao.findAll();
		ResponseObject response = null;
		
		if (items.isEmpty())
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
			else
			response = new ResponseObjectAll<Item>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , items);
	
			return response;	
	}
	@Override
	public ResponseObject getItem(long id) {
       Item item = itemDao.findByItemId(id);
		
		ResponseObject response = null;
		
		if (item == null)
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
		else
		response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE ,item);
		
		return response;
			}
	

	@Override
	public ResponseObject createItem(Item item) {		
		ResponseObject response = null;
		
		item.setCreatedAt(new Date());
		itemDao.save(item);
		long id = item.getItemId();
		if(id > 0)
	    response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_CREATE_CODE , ResponseMessage.SUCCESS_CREATING_MESSAGE ,id );  
		else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_CREATING_MESSAGE );  }

		return response;
		
	}

	@Override
	public ResponseObject updateItem(Item item , long id ) {
		ResponseObject response = null;
		
		if (itemDao.findByItemId(id) !=  null && item.isStatus()){
			item.setItemId(id);
			item.setUpdatedAt(new Date());
			itemDao.save(item);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE ,id ); 
		}else if (!item.isStatus()){
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , "Deactivated item" );
		}
		else
	    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_UPDATING_MESSAGE );  
		
		
		return response;
	}

	@Override
	public ResponseObject deleteItem(long id) {
		Item item = itemDao.findByItemId(id);
		ResponseObject response = null; 
		
		if (item != null && item.isStatus()){
		item.setStatus(false);
		item.setDeletedAt(new Date());
		itemDao.save(item);
		response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS ,ResponseCode.SUCCESS_RESPONSE_CODE ,ResponseMessage.SUCCESS_DELETTING_MESSAGE , id);
		}else{
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE ,ResponseMessage.FAILED_DELETTING_MESSAGE );	
		}		
		return response;
		
	}
	@Override
	public ResponseObject updateItemsStatus(boolean status, long id) {
           Item item = itemDao.findByItemId(id);
		   ResponseObject response = null;
		if (item  !=  null){
			item.setItemId(id);
			item.setUpdatedAt(new Date());
			item.setStatus(status);
			itemDao.save(item);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE ,id ); 
		}
		else
	    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_UPDATING_MESSAGE );  
		
		
		return response;
	}
	@Override
	public ResponseObject updateItemStatus(long id, boolean status) {
		Item item   = itemDao.findByItemId(id);
		ResponseObject response = null;
		if (item !=null)
		{
		item.setStatus(status);
		item.setUpdatedAt(new Date());
		itemDao.save(item);
		 response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE , id );
    }else {
    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_UPDATING_MESSAGE );
    }
    
    return response;
	}
	


}
