package com.igas.express.service;



import org.springframework.stereotype.Service;
import com.igas.express.model.entitiy.Item;
import com.igas.express.util.ResponseObject;


@Service
public interface ItemService {
	
	/*
	 * *Task : return all items 
	 * @param : ---
	 * @return : collection of GasType as List<GasType> else empty list and response massages , code and status
	 */
  public ResponseObject getItems(); 
  /*
	 * *Task : return item with specified id 
	 * @param : id as long 
	 * @return : item as Item object else null response massages , code and status
	 */
  public ResponseObject getItem(long id);
  /*
	 * *Task : create Item with specified id 
	 * @param : item as Item object
	 * @return : id for created object as long  and response massages , code and status
	 */
  public ResponseObject createItem(Item item);
  /*
	 * *Task : update gas type with specified id 
	 * @param : id as long , gasType as GasType object
	 * @return : id for updated object and response massages , code and status
	 */
  public ResponseObject updateItem(Item item , long id );
  
  
  public ResponseObject updateItemStatus(long id , boolean status);
  /*
	 * *Task : delete gas type with specified id 
	 * @param : id as long 
	 * @return : id for deleted object and response massages , code and status
	 */
  public ResponseObject deleteItem(long id);
  /*
	 * *Task : update gas type  status with specified id 
	 * @param : id as long , gasType as GasType object
	 * @return : id for updated object and response massages , code and status
	 */
  public ResponseObject updateItemsStatus(boolean status, long id); 

}
