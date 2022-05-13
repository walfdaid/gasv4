package com.igas.express.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.igas.express.model.entitiy.Item;
import com.igas.express.service.ItemService;
import com.igas.express.util.ResponseObject;


@RestController
@RequestMapping("api/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {
	
    @Autowired
    private ItemService  itemService ;
	
	 // get all items
     @RequestMapping(method = RequestMethod.GET )
     public ResponseObject getAllItems(){
		return itemService.getItems();
	   } 
	// get item by id 
	@RequestMapping( method = RequestMethod.GET , value = "/{id}")
	public ResponseObject getGasTypeInfo(@PathVariable long id )
	{return itemService.getItem(id);
		}
	// create new item 
	@RequestMapping( method = RequestMethod.POST)
	public ResponseObject createGasType (@RequestBody Item item ){
		return itemService.createItem(item);
		}
	// update item 
	@RequestMapping( method = RequestMethod.PUT , value = "/{id}")
	public  ResponseObject updateGasType (@RequestBody Item item , 
			@PathVariable long id  ){
		return itemService.updateItem(item , id );	
		}
	// delete item
	@RequestMapping( method = RequestMethod.DELETE ,value = "/{id}")
	public ResponseObject deleteGasType (@PathVariable long id){
		return itemService.deleteItem(id) ;
			}

	// update item status
	@RequestMapping( method = RequestMethod.PUT , value ="/status/{id}")
	public ResponseObject UserStatus(@PathVariable long id,@RequestBody Item item){
		return itemService.updateItemStatus(id,item.isStatus());
	}
	
	 
	 
}
