package com.igas.express.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.ItemDao;
import com.igas.express.dao.OrderDao;
import com.igas.express.dao.OrderItemsDao;
import com.igas.express.dao.SupplierDao;
import com.igas.express.dao.UserDao;
import com.igas.express.model.entitiy.Item;
import com.igas.express.model.entitiy.Order;
import com.igas.express.model.entitiy.OrderItems;
import com.igas.express.model.entitiy.Supplier;
import com.igas.express.model.entitiy.User;
import com.igas.express.service.OrderServices;
import com.igas.express.util.ResponseCreateOrder;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class OrderServicesImpl implements OrderServices {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemsDao orederItemsDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private ItemDao itemDao;

	@Override
	public ResponseObject getAllOrder() {
		List<Order> allOrders = orderDao.findAll();
		ResponseObject response = null;

		if (allOrders.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {
			response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
		}
		return response;
	}

	@Override
	public ResponseObject getOrderId(long id) {
		Order order = orderDao.findByOrderId(id);
		ResponseObject response = null;

		if (order == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, order);
		}
		return response;
	}

	@Override
	public ResponseObject getOrderStatus(int status) {

		List<Order> allOrders = orderDao.findByStatus(status);
		ResponseObject response = null;

		if (allOrders.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {
			response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
		}
		return response;
	}

	@Override
	public ResponseObject getOrderTotalStatusAndCreatedAt(int status, String createdAt) {

		List<Order> allOrders = orderDao.findByStatus(status);
		ResponseObject response = null;

		if (isValidFormat(createdAt)) {
			atSpecificDay(allOrders, createdAt);
			if (allOrders.isEmpty()) {
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);
			} else {
				response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE,
						calculateTotalAmount(allOrders));
			}
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		}

		return response;
	}

	@Override
	public ResponseObject getOrderStatusAndCreatedAt(int status, String createdAt) {
		List<Order> allOrders = orderDao.findByStatus(status);
		ResponseObject response = null;

		if (isValidFormat(createdAt)) {
			atSpecificDay(allOrders, createdAt);
			if (allOrders.isEmpty()) {
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);
			} else {
				response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
			}
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		}

		return response;

	}

	@Override
	public ResponseObject getAllOrdersInfoByStatusAndUserId(int status, long userId) {
		List<Order> allOrders = orderDao.findByStatus(status);
		ResponseObject response = null;

		if (allOrders.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {
			collectUnNessaryUsers(allOrders, userId);
			if (!allOrders.isEmpty()) {
				Collections.reverse(allOrders);
				response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
			} else {
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);
			}
		}

		return response;
	}

	@Override
	public ResponseObject getAllOrdersInfoBySupplierId(long supplierId) {
		List<Order> allOrders = orderDao.findBySupplierId(supplierId);
		ResponseObject response = null;

		if (allOrders.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {
			response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
		}
		return response;

	}

	@Override
	public ResponseObject getAllOrdersInfoBySupplierIdAndStatus(long supplierId, boolean active) {

		List<Order> allOrders = orderDao.findBySupplierId(supplierId);
		ResponseObject response = null;

		if (allOrders.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {
			collectUnNessarySuppliers(allOrders, active);
			if (!allOrders.isEmpty()) {
				Collections.reverse(allOrders);
				response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
			} else
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);
		}
		return response;

	}

	@Override
	public ResponseObject getAllOrdersInfoByQuantityAndDate(String createdDate, long itemId) {
		ResponseObject response = null;
		int allQuantity = -1;
		List<Order> allOrders = orderDao.findAll();
		boolean validExist = !(allOrders.isEmpty());
		boolean validDate = (createdDate.equals("all")) || isValidFormat(createdDate);
		boolean validItem = (itemId == -1) || (itemDao.findByItemId(itemId) != null);

		if (validExist && validDate && validItem) {

			if (createdDate.equals("all") && itemId == -1) {
				allQuantity = getAllQuantity(allOrders);
			} else if (createdDate.equals("all") && itemId != -1) {
				atSpecificItem(allOrders, itemId);
				if (!allOrders.isEmpty())
					allQuantity = getAllQuantity(allOrders);
			}

			else if (!createdDate.equals("all") && itemId != -1) {
				atSpecificDay(allOrders, createdDate);
				if (!allOrders.isEmpty())
					atSpecificItem(allOrders, itemId);
				if (!allOrders.isEmpty())
					allQuantity = getAllQuantity(allOrders);

			} else {
				atSpecificDay(allOrders, createdDate);
				if (!allOrders.isEmpty())
					allQuantity = getAllQuantity(allOrders);
			}
		} else if (validExist && validDate) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"invalid item");

		} else if (validExist && validItem) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		}

		if (allQuantity >= 0)
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allQuantity);
		else
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);

		return response;
	}

	@Override
	public ResponseObject createOrder(Order order) {

		boolean valid = true;
		User orderUser = userDao.findByCustomerId(order.getCustomerId());
		Supplier orderSupplier = supplierDao.findBySupplierId(order.getSupplierId());
		order.setUser(orderUser);
		order.setSupplier(orderSupplier);
		List<OrderItems> Orderitems = order.getOrderItems();

		for (Iterator<OrderItems> iterator = Orderitems.iterator(); iterator.hasNext() && valid;) {
			OrderItems value = iterator.next();
			Item item = itemDao.findByItemId(value.getItemId());
			if (item != null)
				value.setItem(item);
			else
				valid = false;
		}

		if (valid && orderUser.isStatus() && orderSupplier.isStatus()) {
			orederItemsDao.saveAll(order.getOrderItems());
			orderDao.save(order);

			return new ResponseCreateOrder(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_CREATING_MESSAGE, order.getOrderId(), orderSupplier.getEmail());
		} else
			return new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					"error in the item");
	}

	@Override
	public ResponseObject updateOrder(long id, int status) {
		ResponseObject response = null;

		if (orderDao.findByOrderId(id) != null) {
			Order order = orderDao.findByOrderId(id);
			order.setUpdatedAt(new Date());
			if (status == 2) {
				order.setDeliveredAt(new Date());
			}
			order.setStatus(status);
			orderDao.save(order);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_UPDATING_MESSAGE, order.getOrderId());
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_UPDATING_MESSAGE);
		}

		return response;
	}

	private void atSpecificDay(List<Order> orders, String date) {

		int yearDate = Integer.valueOf(date.substring(0, 4));
		int monthDate = Integer.valueOf(date.substring(5, 7));
		int dayDate = Integer.valueOf(date.substring(8, 10));
		//
		int yearCreatedAt = 0;
		int monthCreatedAt = 0;
		int dayCreatedAt = 0;

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {

			Order value = iterator.next();
			Date createdAt = value.getCreatedAt();
			Calendar cal = Calendar.getInstance();
			cal.setTime(createdAt);
			yearCreatedAt = cal.get(Calendar.YEAR);
			monthCreatedAt = cal.get(Calendar.MONTH) + 1;
			dayCreatedAt = cal.get(Calendar.DAY_OF_MONTH);

			if ((dayDate != dayCreatedAt) || (monthCreatedAt != monthDate) || (yearCreatedAt != yearDate)) {
				iterator.remove();
			}
		}
	}

	private boolean isValidFormat(String date) {
		boolean valid = false;
		if ((date.length() == 10) && Character.isDigit(date.charAt(0)) && Character.isDigit(date.charAt(1))
				&& Character.isDigit(date.charAt(2)) && Character.isDigit(date.charAt(3))
				&& Character.isDigit(date.charAt(5)) && Character.isDigit(date.charAt(6))
				&& Character.isDigit(date.charAt(8)) && Character.isDigit(date.charAt(9)))
			valid = true;
		return valid;
	}

	private double calculateTotalAmount(List<Order> orders) {
		double totalAmount = 0.0;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			totalAmount += value.getTotalAmount();
		}
		return totalAmount;

	}

	private void collectUnNessaryUsers(List<Order> orders, long userId) {
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			if (value.getCustomerId() != userId)
				iterator.remove();
		}

	}

	private void collectUnNessarySuppliers(List<Order> orders, boolean active) {
		if (active) {
			for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
				Order value = iterator.next();
				if (value.getStatus() == 2 || value.getStatus() == 3 || value.getStatus() == 5)
					iterator.remove();
			}

		} else {
			for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
				Order value = iterator.next();
				if (value.getStatus() == 0 || value.getStatus() == 1 || value.getStatus() == 4)
					iterator.remove();
			}

		}
	}

	private int getAllQuantity(List<Order> orders) {
		int allQuantity = 0;
		List<OrderItems> orderItems = null;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			orderItems = value.getOrderItems();
			for (Iterator<OrderItems> iteratorItems = orderItems.iterator(); iteratorItems.hasNext();) {
				OrderItems valueItem = iteratorItems.next();
				allQuantity += valueItem.getQuantity();
			}

		}

		return allQuantity;
	}

	@Override
	public ResponseObject getAllOrdersInfoByQuantityForSupplierAndDate(long supplierId, String createdDate,
			long itemId) {
		ResponseObject response = null;
		int allQuantity = -1;
		List<Order> allOrders = orderDao.findBySupplierId(supplierId);
		boolean validExist = !(allOrders.isEmpty());
		boolean validDate = (createdDate.equals("all")) || isValidFormat(createdDate);

		if (validExist && validDate) {

			if (createdDate.equals("all")) {
				allQuantity = getAllQuantity(allOrders);
			} else {
				atSpecificDay(allOrders, createdDate);
				if (!allOrders.isEmpty())
					allQuantity = getAllQuantity(allOrders);
			}
			if (allQuantity >= 0)
				response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allQuantity);
			else
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);

		} else if (validExist)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		else
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);

		return response;
	}

	@Override
	public ResponseObject ordersFlightDateAndStatus(String startDate, String endDate, int status) {
		List<Order> allOrders = orderDao.findByStatus(status);
		ResponseObject response = null;
		boolean validDate = (isValidFormat(startDate) && isValidFormat(endDate));
		boolean validExist = !allOrders.isEmpty();

		if (validDate && validExist) {
			atFlightDate(allOrders, startDate, endDate);
			if (!allOrders.isEmpty())
				response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
			else
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);
		} else if (!validDate) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		}

		return response;
	}

	private void atFlightDate(List<Order> orders, String startDate, String endDate) {

		int startDateSplited = Integer
				.valueOf(startDate.substring(0, 4) + startDate.substring(5, 7) + startDate.substring(8, 10));
		int endDateSplited = Integer
				.valueOf(endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8, 10));

		//
		int yearCreatedAt = 0;
		int monthCreatedAt = 0;
		int dayCreatedAt = 0;

		String createdAtString = "";
		int createdDateValue = 0;

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {

			Order value = iterator.next();
			Date createdAt = value.getCreatedAt();
			Calendar cal = Calendar.getInstance();
			cal.setTime(createdAt);
			yearCreatedAt = cal.get(Calendar.YEAR);
			monthCreatedAt = cal.get(Calendar.MONTH) + 1;
			dayCreatedAt = cal.get(Calendar.DAY_OF_MONTH);

			if (monthCreatedAt <= 9)
				yearCreatedAt *= 10;
			if (dayCreatedAt <= 9)
				monthCreatedAt *= 10;

			createdAtString = "" + yearCreatedAt + monthCreatedAt + dayCreatedAt;
			createdDateValue = Integer.parseInt(createdAtString);

			if (startDateSplited > createdDateValue || createdDateValue > endDateSplited) {
				iterator.remove();
			}

		}

	}

	private void atSpecificItem(List<Order> orders, long id) {
		List<OrderItems> orderItems = null;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			orderItems = value.getOrderItems();
			for (Iterator<OrderItems> iteratorItems = orderItems.iterator(); iteratorItems.hasNext();) {
				OrderItems valueItem = iteratorItems.next();
				if (valueItem.getItemId() != id) {
					iterator.remove();
					break;
				}
			}

		}

	}

	@Override
	public ResponseObject ordersFlightDate(String startDate, String endDate) {
		ResponseObject response = null;
		List<Order> allOrders = orderDao.findAll();

		if (isValidFormat(startDate) && isValidFormat(endDate) && !allOrders.isEmpty()) {
			atFlightDate(allOrders, startDate, endDate);
			if (!allOrders.isEmpty())
				response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOrders);
			else
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);

		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);

		}

		return response;

	}

	public ResponseObject updateOrderSupplier(long id, long supplierId) {
		ResponseObject response = null;
		Supplier supplier = supplierDao.findBySupplierId(supplierId);
		Order order = orderDao.findByOrderId(id);
		if (orderDao.findByOrderId(id) != null && supplier != null) {

			order.setUpdatedAt(new Date());
			order.setSupplierId(supplierId);
			order.setSupplier(supplier);
		}
		if (supplier != null) {
			orderDao.save(order);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_UPDATING_MESSAGE, order.getOrderId());
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_NO_SUPPlIER);
		}
		return response;
	}

	public ResponseObject updateOrder(long id, Order order) {

		ResponseObject response = null;

		boolean valid = true;
		User orderUser = userDao.findByCustomerId(order.getCustomerId());
		Supplier orderSupplier = supplierDao.findBySupplierId(order.getSupplierId());
		order.setUser(orderUser);
		order.setSupplier(orderSupplier);
		List<OrderItems> Orderitems = order.getOrderItems();

		for (Iterator<OrderItems> iterator = Orderitems.iterator(); iterator.hasNext() && valid;) {
			OrderItems value = iterator.next();
			Item item = itemDao.findByItemId(value.getItemId());
			if (item != null)
				value.setItem(item);
			else
				valid = false;
		}

		if (valid && orderUser.isStatus() && orderSupplier.isStatus()) {
			orederItemsDao.saveAll(order.getOrderItems());
			order.setOrderId(id);
			orderDao.save(order);
			return new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_UPDATING_MESSAGE, order.getOrderId());
		} else
			return new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					"errror in the item");
	}

	@Override
	public ResponseObject userActiveOrder(long id) {

		ResponseObject response = null;
		List<Order> orders = orderDao.findByCustomerId(id);
		collectUnNessarySuppliers(orders, true);
		if (orders.size() > 0)
			response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, orders);

		else
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);

		return response;
	}

	@Override
	public ResponseObject getOrderByUserPhoneNumber(String phoneNumber) {
		ResponseObject response = null;
		List<Order> orderUser = orderDao.findByUserPhoneNumber(phoneNumber);
		if (orderUser.size() > 0) {
			response = new ResponseObjectAll<Order>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, orderUser);

		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		}
		return response;
	}

}
