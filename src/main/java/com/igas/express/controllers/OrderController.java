package com.igas.express.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.igas.express.model.entitiy.Order;
import com.igas.express.service.OrderServices;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin(origins = "*")
public class OrderController {
	
	
	@Autowired
	private OrderServices orderServices ;
	
	// get all orders 
	@RequestMapping(method = RequestMethod.GET )
	public ResponseObject getAllOrdersInfo(){
		return orderServices.getAllOrder();
	}
	// get order by id 
	@RequestMapping(method = RequestMethod.GET , value = "/{id}")
	public ResponseObject getAllOrdersInfoById(@PathVariable long id){
		return orderServices.getOrderId(id);
	}
	// get order by status 
	@RequestMapping(method = RequestMethod.GET  , params = "status")
	public ResponseObject getAllOrdersInfoByStatus(@RequestParam int status){
		return orderServices.getOrderStatus(status);
	}
	// get order by status and created at 
	@RequestMapping(method = RequestMethod.GET , params = {"status" , "createdAt"})
	public ResponseObject getAllOrdersInfoByStatusAndCratedAt(@RequestParam int status , @RequestParam String createdAt){
		return orderServices.getOrderStatusAndCreatedAt(status , createdAt);
	}
	// get order  by user id and order status 
	@RequestMapping(method = RequestMethod.GET , params = "status" , value = "/user/{userId}")
	public ResponseObject getAllOrdersInfoByStatusAndUser (@RequestParam int status , @PathVariable long userId){
		return orderServices.getAllOrdersInfoByStatusAndUserId(status , userId);
	}
	// get all orders by supplier id 
	@RequestMapping(method = RequestMethod.GET , value = "/supplier/{supplierId}")
	public ResponseObject getAllOrdersInfoBySupplierId ( @PathVariable long supplierId){
		return orderServices.getAllOrdersInfoBySupplierId(supplierId);
	}
	@RequestMapping(method = RequestMethod.GET , value = "/supplier/{supplierId}" , params = "active")
	public ResponseObject getAllOrdersInfoByStatusAndUser ( @PathVariable long supplierId , @RequestParam boolean active){
		return orderServices.getAllOrdersInfoBySupplierIdAndStatus(supplierId , active);
	}
	// get total amount for all orders 
	@RequestMapping(method = RequestMethod.GET , value = "/totalamount" , params = {"status" , "createdAt"})
	public ResponseObject getAllOrdersTotalInfoByStatusAndCratedAt(@RequestParam int status , @RequestParam String createdAt){
		return orderServices.getOrderTotalStatusAndCreatedAt(status, createdAt);
	}
	// get all orders by quantity and a specified created at date (optional)and specified item (optional)
	@RequestMapping(method = RequestMethod.GET , value = "/quantity")
	public ResponseObject getAllOrdersInfoByQuantityAndCreatedDate (@RequestParam String createdAt , @RequestParam long itemId){
		return orderServices.getAllOrdersInfoByQuantityAndDate(createdAt , itemId);
		}
	// get all orders by quantity for specified supplier and  specified created at date (optional)and specified item (optional)
	@RequestMapping(method = RequestMethod.GET , value = "/quantity/supplier/{supplierId}")
	public ResponseObject getAllOrdersInfoByQuantityForSupplierAndCreatedDate (@PathVariable long supplierId , @RequestParam String createdAt , @RequestParam long itemId){
		return orderServices.getAllOrdersInfoByQuantityForSupplierAndDate(supplierId,createdAt,itemId);
			}
	// create new order
	@RequestMapping(method = RequestMethod.POST)
	public ResponseObject createNewOrder(@RequestBody Order order){
		return orderServices.createOrder(order);
	}
	// update order status
	@RequestMapping(method = RequestMethod.PUT , value ="/{id}")
	public ResponseObject updateOrderStarus(@PathVariable long id ,@RequestBody Order order ){
		return orderServices.updateOrder( id, order.getStatus());
	}
	
	
	//////////////////////////////////////////////////////////// 
	//all orders with start and end date 
	@RequestMapping(method = RequestMethod.GET, value = "/flight/date" )
	public ResponseObject getAllOrdersFlightDate(@RequestParam String startDate ,@RequestParam String endDate , @RequestParam int status){
		return orderServices.ordersFlightDateAndStatus(startDate, endDate , status);
	}
	//./././././././././././/.///////////////////.........
	@RequestMapping(method = RequestMethod.GET, value = "/flight/all" )
	public ResponseObject getAllOrderFlightDate(@RequestParam String startDate ,@RequestParam String endDate){
		return orderServices.ordersFlightDate(startDate, endDate );
	}
	

	@RequestMapping(method = RequestMethod.PUT , value ="/supplier/{id}")
	public ResponseObject updateOrderSupplier(@PathVariable long id ,@RequestBody Order order ){
		return orderServices.updateOrderSupplier( id, order.getSupplierId());
	}
	
	@RequestMapping(method = RequestMethod.PUT , value ="/all/{id}")
	public ResponseObject updateOrder(@PathVariable long id ,@RequestBody Order order ){
		return orderServices.updateOrder( id, order);
	}
	
	//get order that the user make(order) it 
	@RequestMapping(method = RequestMethod.GET , value ="/user/{id}")
	public ResponseObject updateOrderSupplier(@PathVariable long id  ){
		return orderServices.userActiveOrder(id);
	}
	
	@RequestMapping(method = RequestMethod.GET , params = "phoneNumber"  )
	public ResponseObject getOrderByUserPhoneNumber(@RequestParam String phoneNumber ){
		return orderServices.getOrderByUserPhoneNumber(phoneNumber);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	}
