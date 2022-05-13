package com.igas.express.service;



import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.Order;
import com.igas.express.util.ResponseObject;

@Service
public interface OrderServices {
	
	/*
	 * *Task : return all orders 
	 * @param : ---
	 * @return : collection of orders as List<Order> else empty list  and response massages , code and status
	 */
    public ResponseObject getAllOrder(); 
    /*
 	 * *Task : find Order with specified id 
 	 * @param : id as long 
 	 * @return : order as Order object else null and response massages , code and status 
 	 */
	public ResponseObject getOrderId(long id);
	/*
 	 * *Task : find Order with specified Status 
 	 * @param : status as integer
 	 * @return : order as Order object else null and response massages , code and status
 	 */
	public ResponseObject getOrderStatus(int status);
	/*
 	 * *Task : create new order  
 	 * @param :  order as order object
 	 * @return : created order id  and response massages , code and status
 	 */
	public ResponseObject createOrder(Order order);
	/*
 	 * *Task : update Order with specified id 
 	 * @param : id as long , order as order object , status as integer 
 	 * @return : updated order id and response massages , code and status
 	 */
	public ResponseObject updateOrder(long id , int status);
	/*
 	 * *Task : find order with specified status and created at  
 	 * @param : status as integer , created at as date object
 	 * @return : collection of orders as List<Order> else empty list  and response massages , code and status
 	*/
	public ResponseObject getOrderStatusAndCreatedAt(int status, String createdAt);
	/*
 	 * *Task : calculate orders total amount  with specified status and created at  
 	 * @param : status as integer , created at as date object
 	 * @return : total amount as double else zero and response massages , code and status
 	*/
	public ResponseObject getOrderTotalStatusAndCreatedAt(int status, String createdAt);
	/*
 	 * *Task : find orders by user id and  order status   
 	 * @param : status as integer , userId as long 
 	 * @return : collection of orders as List<Order> else empty list  and response massages , code and status
 	*/
	public ResponseObject getAllOrdersInfoByStatusAndUserId(int status, long userId);
	/*
 	 * *Task : find orders by supplier id    
 	 * @param :  supplierId as long 
 	 * @return : collection of orders as List<Order> else empty list  and response massages , code and status
 	*/
	public ResponseObject getAllOrdersInfoBySupplierId(long supplierId);
	/*
 	 * *Task : find orders by supplier id  and status    
 	 * @param :  supplierId as long 
 	 * @return : collection of orders as List<Order> else empty list  and response massages , code and status
 	*/
	public ResponseObject getAllOrdersInfoBySupplierIdAndStatus(long supplierId, boolean active);
	/*
 	 * *Task : find all orders quantity with specified date and item id (optional)     
 	 * @param :  createdDate as String , itemId as long 
 	 * @return : all quantity as integer and response massages , code and status
 	*/
	public ResponseObject getAllOrdersInfoByQuantityAndDate(String createdDate , long itemId);
	/*
 	 * *Task : find all orders quantity with specified supplier id and specified date and item id (optional)    
 	 * @param :  supplierId as long , createdDate as String , itemId as long   
 	 * @return : all quantity as integer and response massages , code and status
 	*/
	public ResponseObject getAllOrdersInfoByQuantityForSupplierAndDate(long supplierId, String createdDate , long itemId);
	/*
 	 * *Task : find all orders with flight date     
 	 * @param :  startDate as String , endDate as String    
 	 * @return : list<Order> else empty list and response massages , code and status 
 	*/
	public ResponseObject ordersFlightDateAndStatus(String startDate, String endDate , int status);
	public ResponseObject ordersFlightDate(String startDate, String endDate);
	public ResponseObject updateOrderSupplier(long id , long supplierId);
	public ResponseObject updateOrder(long id, Order order);
	public ResponseObject userActiveOrder(long id);
	public ResponseObject getOrderByUserPhoneNumber(String phoneNumber);
}
