package com.igas.express.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.OrderDao;
import com.igas.express.dao.SupplierDao;
import com.igas.express.dao.UserDao;
import com.igas.express.dao.inComeDao;
import com.igas.express.dao.outComeDao;
import com.igas.express.model.entitiy.InCome;
import com.igas.express.model.entitiy.Order;
import com.igas.express.model.entitiy.OrderItems;
import com.igas.express.model.entitiy.Supplier;
import com.igas.express.model.entitiy.User;
import com.igas.express.model.entitiy.outCome;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectData;
import com.igas.express.util.dashborad;
import com.igas.express.util.SupplierSales;

@Service
public class DshboardServicesImpl {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private inComeDao incomeDao;

	@Autowired
	private outComeDao outcomeDao;

	@Autowired
	private SupplierDao supplierDao;

	// total amount for orders
	public double calculateTotalAmount(List<Order> orders) {
		double totalAmount = 0.0;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			if (value.getStatus() == 2) {
				totalAmount += value.getTotalAmount();
			}
		}
		return totalAmount;

	}

	// return all number of users
	public int userRegistration(List<User> Users) {

		// Users = userDao.findAll();

		return Users.size();

	}

	// return Total Amount income
	public double calculateTotalIncome(List<InCome> income) {
		double totalAmount = 0.0;
		for (Iterator<InCome> iterator = income.iterator(); iterator.hasNext();) {
			InCome value = iterator.next();
			totalAmount += value.getAmount();
		}
		return totalAmount;

	}

	// return Total Amount Outcome
	public double calculateTotalOutcome(List<outCome> outcome) {
		double totalAmount = 0.0;
		for (Iterator<outCome> iterator = outcome.iterator(); iterator.hasNext();) {
			outCome value = iterator.next();
			totalAmount += value.getAmount();
		}
		return totalAmount;

	}

	// return number of orders

	public int ordersNumber(List<Order> Orders) {
		// Orders = orderDao.findAll();
		return Orders.size();

	}

	// return number all cylinder
	public int totalCylinder(List<Order> orders) {

		int totalCylinder = 0;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			if (value.getStatus() == 2) {
				List<OrderItems> items = value.getOrderItems();
				for (Iterator<OrderItems> iterator1 = items.iterator(); iterator1.hasNext();) {
					OrderItems value1 = iterator1.next();
					totalCylinder += value1.getQuantity();

				}
			}
		}
		return totalCylinder;

	}

	// return number normal cylinder
	public int normalCylinder(List<Order> orders) {

		int totalCylinder = 0;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			if (value.getStatus() == 2) {
				List<OrderItems> items = value.getOrderItems();

				for (Iterator<OrderItems> iterator1 = items.iterator(); iterator1.hasNext();) {
					OrderItems value1 = iterator1.next();
					if (value1.getItemId() == 1)
						totalCylinder += value1.getQuantity();

				}
			}
		}

		return totalCylinder;

	}

	// return number squeeze cylinder
	public int squeezeCylinder(List<Order> orders) {

		int totalCylinder = 0;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();

			if (value.getStatus() == 2) {
				List<OrderItems> items = value.getOrderItems();
				for (Iterator<OrderItems> iterator1 = items.iterator(); iterator1.hasNext();) {
					OrderItems value1 = iterator1.next();
					if (value1.getItemId() == 2)
						totalCylinder += value1.getQuantity();

				}
			}
		}

		return totalCylinder;

	}

//return all
	public ResponseObject getAll() {
		ResponseObject response = null;
		dashborad all = new dashborad(userRegistration(userDao.findAll()), normalCylinder(orderDao.findAll()),
				squeezeCylinder(orderDao.findAll()), ordersNumber(orderDao.findAll()),
				calculateTotalAmount(orderDao.findAll()), totalCylinder(orderDao.findAll()),
				calculateTotalIncome(incomeDao.findAll()), calculateTotalOutcome(outcomeDao.findAll()));

		response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_RESPONSE_CODE,
				ResponseMessage.SUCCESS_GETTING_MESSAGE, all);

		return response;

	}

//get between
	public ResponseObject getBetween(String start, String End) {
		ResponseObject response = null;
		dashborad all = new dashborad();
		all.setUsers(userRegistration(userBetween(start, End)));
		all.setNormalCylinder(normalCylinder(orderBetween(start, End)));
		all.setSqueezCylinder(squeezeCylinder(orderBetween(start, End)));
		all.setTotalOrders(ordersNumber(orderBetween(start, End)));
		all.setTotalAmount(calculateTotalAmount(orderBetween(start, End)));
		all.setTotalCylinder(totalCylinder(orderBetween(start, End)));
		all.setTotalIncome(calculateTotalIncome(incomeBetween(start, End)));
		all.setTotalOutcome(calculateTotalOutcome(outcomeBetween(start, End)));

		if (all == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);

		else {
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, all);
		}
		return response;

	}

//return today
	public ResponseObject getToday() {
		ResponseObject response = null;

		dashborad Today = new dashborad();
		Today.setUsers(userRegistration(TodayUser()));
		Today.setNormalCylinder(normalCylinder(TodayOrder()));
		Today.setSqueezCylinder(squeezeCylinder(TodayOrder()));
		Today.setTotalOrders(ordersNumber(TodayOrder()));
		Today.setTotalAmount(calculateTotalAmount(TodayOrder()));
		Today.setTotalCylinder(totalCylinder(TodayOrder()));
		Today.setTotalIncome(calculateTotalIncome(TodayIncome()));
		Today.setTotalOutcome(calculateTotalOutcome(TodayOutcome()));
		if (Today == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);

		else {
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, Today);
		}
		return response;

	}

//----return order Today
	private List<Order> TodayOrder() {
		List<Order> orders = orderDao.findAll();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.AM_PM);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		//
		int yearToday = cal.get(Calendar.YEAR);
		;
		int monthToday = cal.get(Calendar.MONTH) + 1;
		;
		int dayToday = cal.get(Calendar.DAY_OF_MONTH);
		;

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {

			Order value = iterator.next();
			Date createdAt = value.getCreatedAt();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(createdAt);
			int yearCreatedAt = cal1.get(Calendar.YEAR);
			int monthCreatedAt = cal1.get(Calendar.MONTH) + 1;
			int dayCreatedAt = cal1.get(Calendar.DAY_OF_MONTH);

			if ((yearCreatedAt != yearToday) || (monthCreatedAt != monthToday) || (dayCreatedAt != dayToday)) {
				iterator.remove();
			}
		}
		return orders;
	}

	// ----return order Today
	private List<User> TodayUser() {
		List<User> users = userDao.findAll();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.AM_PM);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		//
		int yearToday = cal.get(Calendar.YEAR);
		;
		int monthToday = cal.get(Calendar.MONTH) + 1;
		;
		int dayToday = cal.get(Calendar.DAY_OF_MONTH);
		;

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {

			User value = iterator.next();
			Date createdAt = value.getCreatedAt();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(createdAt);
			int yearCreatedAt = cal1.get(Calendar.YEAR);
			int monthCreatedAt = cal1.get(Calendar.MONTH) + 1;
			int dayCreatedAt = cal1.get(Calendar.DAY_OF_MONTH);

			if ((yearCreatedAt != yearToday) || (monthCreatedAt != monthToday) || (dayCreatedAt != dayToday)) {
				iterator.remove();
			}
		}
		return users;
	}

	// -----income today
	private List<InCome> TodayIncome() {
		List<InCome> InCome = incomeDao.findAll();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.AM_PM);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		//
		int yearToday = cal.get(Calendar.YEAR);
		;
		int monthToday = cal.get(Calendar.MONTH) + 1;
		;
		int dayToday = cal.get(Calendar.DAY_OF_MONTH);
		;

		for (Iterator<InCome> iterator = InCome.iterator(); iterator.hasNext();) {

			InCome value = iterator.next();
			Date createdAt = value.getDate();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(createdAt);
			int yearCreatedAt = cal1.get(Calendar.YEAR);
			int monthCreatedAt = cal1.get(Calendar.MONTH) + 1;
			int dayCreatedAt = cal1.get(Calendar.DAY_OF_MONTH);

			if ((yearCreatedAt != yearToday) || (monthCreatedAt != monthToday) || (dayCreatedAt != dayToday)) {
				iterator.remove();
			}
		}
		return InCome;
	}

	// outcome today
	private List<outCome> TodayOutcome() {
		List<outCome> Outcome = outcomeDao.findAll();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.AM_PM);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		//
		int yearToday = cal.get(Calendar.YEAR);
		;
		int monthToday = cal.get(Calendar.MONTH) + 1;
		;
		int dayToday = cal.get(Calendar.DAY_OF_MONTH);
		;

		for (Iterator<outCome> iterator = Outcome.iterator(); iterator.hasNext();) {

			outCome value = iterator.next();
			Date createdAt = value.getDate();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(createdAt);
			int yearCreatedAt = cal1.get(Calendar.YEAR);
			int monthCreatedAt = cal1.get(Calendar.MONTH) + 1;
			int dayCreatedAt = cal1.get(Calendar.DAY_OF_MONTH);

			if ((yearCreatedAt != yearToday) || (monthCreatedAt != monthToday) || (dayCreatedAt != dayToday)) {
				iterator.remove();
			}
		}
		return Outcome;
	}

//-------from to Between 
	private List<Order> orderBetween(String start, String end) {
		List<Order> orders = orderDao.findAll();

		//
		int startDateSplited = Integer.valueOf(start.substring(0, 4) + start.substring(5, 7) + start.substring(8, 10));
		int endDateSplited = Integer.valueOf(end.substring(0, 4) + end.substring(5, 7) + end.substring(8, 10));

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

		return orders;
	}

////////////////// user between 
	private List<User> userBetween(String start, String end) {
		List<User> users = userDao.findAll();

		//
		int startDateSplited = Integer.valueOf(start.substring(0, 4) + start.substring(5, 7) + start.substring(8, 10));
		int endDateSplited = Integer.valueOf(end.substring(0, 4) + end.substring(5, 7) + end.substring(8, 10));

		//
		int yearCreatedAt = 0;
		int monthCreatedAt = 0;
		int dayCreatedAt = 0;

		String createdAtString = "";
		int createdDateValue = 0;

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {

			User value = iterator.next();
			Date createdAt = value.getCreatedAt();
			Calendar cal = Calendar.getInstance();
			cal.setTime(createdAt);
			yearCreatedAt = cal.get(Calendar.YEAR);
			
			/*add 1 because java always assign a value 
			of zero to the first item in a group for example
			the first character in a string is at location zero not one
			so we must add 1 to the returned value to get the right month  */ 
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

		return users;
	}

/////////////////// income betweeeen 
	private List<InCome> incomeBetween(String start, String end) {
		List<InCome> incomes = incomeDao.findAll();

		//
		int startDateSplited = Integer.valueOf(start.substring(0, 4) + start.substring(5, 7) + start.substring(8, 10));
		int endDateSplited = Integer.valueOf(end.substring(0, 4) + end.substring(5, 7) + end.substring(8, 10));

		//
		int yearCreatedAt = 0;
		int monthCreatedAt = 0;
		int dayCreatedAt = 0;

		String createdAtString = "";
		int createdDateValue = 0;

		for (Iterator<InCome> iterator = incomes.iterator(); iterator.hasNext();) {

			InCome value = iterator.next();
			Date createdAt = value.getDate();
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

		return incomes;
	}

/////////////////////// outcome 
	private List<outCome> outcomeBetween(String start, String end) {
		List<outCome> outComes = outcomeDao.findAll();

		//
		int startDateSplited = Integer.valueOf(start.substring(0, 4) + start.substring(5, 7) + start.substring(8, 10));
		int endDateSplited = Integer.valueOf(end.substring(0, 4) + end.substring(5, 7) + end.substring(8, 10));

		//
		int yearCreatedAt = 0;
		int monthCreatedAt = 0;
		int dayCreatedAt = 0;

		String createdAtString = "";
		int createdDateValue = 0;

		for (Iterator<outCome> iterator = outComes.iterator(); iterator.hasNext();) {

			outCome value = iterator.next();
			Date createdAt = value.getDate();
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

		return outComes;
	}

	public ResponseObject getSales() {
		ResponseObject response = null;
		List<Supplier> supliers = supplierDao.findAll();
		validSupplier(supliers);
		List<Order> orders;
		List<SupplierSales> allSales = new ArrayList<SupplierSales>();

		for (Iterator<Supplier> iterator = supliers.iterator(); iterator.hasNext();) {

			Supplier value = iterator.next();
			orders = orderDao.findBySupplierId(value.getSupplierId());

			//number 2 this mean the status of delivered 
			allSales.add(new SupplierSales(value.getFullName(), orders.size(),
					getQuantity(orderDao.findByStatusAndSupplierId(2, value.getSupplierId())),
					getTotalAmount(orderDao.findByStatusAndSupplierId(2, value.getSupplierId())),
					orderDao.findByStatusAndSupplierId(0, value.getSupplierId()).size(),
					orderDao.findByStatusAndSupplierId(1, value.getSupplierId()).size(),
					orderDao.findByStatusAndSupplierId(2, value.getSupplierId()).size(),
					orderDao.findByStatusAndSupplierId(3, value.getSupplierId()).size(),
					orderDao.findByStatusAndSupplierId(4, value.getSupplierId()).size(),
					orderDao.findByStatusAndSupplierId(5, value.getSupplierId()).size()));

		}

		response = new ResponseObjectAll<SupplierSales>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
				ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allSales);

		return response;
	}
//

	public ResponseObject getSalesFlight(String startDate, String endDate) {
		ResponseObject response = null;
		List<Supplier> supliers = supplierDao.findAll();
		validSupplier(supliers);

		boolean validDate = (isValidFormat(startDate) && isValidFormat(endDate));
		boolean validExist = !supliers.isEmpty();
		List<Order> orders;
		List<SupplierSales> allSales = new ArrayList<SupplierSales>();

		if (validDate && validExist) {
			for (Iterator<Supplier> iterator = supliers.iterator(); iterator.hasNext();) {

				Supplier value = iterator.next();
				orders = atFlightDate(orderDao.findBySupplierId(value.getSupplierId()), startDate, endDate);
				// atFlightDate(orderDao.findByStatusAndSupplierId(0 ,
				// value.getSupplierId()).size(), startDate , endDate)
				allSales.add(new SupplierSales(value.getUserName(), orders.size(),
						getQuantity(atFlightDate(orderDao.findByStatusAndSupplierId(2, value.getSupplierId()),
								startDate, endDate)),
						getTotalAmount(atFlightDate(orderDao.findByStatusAndSupplierId(2, value.getSupplierId()),
								startDate, endDate)),
						atFlightDate(orderDao.findByStatusAndSupplierId(0, value.getSupplierId()), startDate, endDate)
								.size(),
						atFlightDate(orderDao.findByStatusAndSupplierId(1, value.getSupplierId()), startDate, endDate)
								.size(),
						atFlightDate(orderDao.findByStatusAndSupplierId(2, value.getSupplierId()), startDate, endDate)
								.size(),
						atFlightDate(orderDao.findByStatusAndSupplierId(3, value.getSupplierId()), startDate, endDate)
								.size(),
						atFlightDate(orderDao.findByStatusAndSupplierId(4, value.getSupplierId()), startDate, endDate)
								.size(),
						atFlightDate(orderDao.findByStatusAndSupplierId(5, value.getSupplierId()), startDate, endDate)
								.size()));
			}
			response = new ResponseObjectAll<SupplierSales>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allSales);
		} else if (!validDate) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		}

		return response;
	}

	private int getQuantity(List<Order> orders) {
		int quantity = 0;

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			{
				List<OrderItems> items = value.getOrderItems();
				for (Iterator<OrderItems> iterator1 = items.iterator(); iterator1.hasNext();) {
					OrderItems value1 = iterator1.next();
					quantity += value1.getQuantity();

				}
			}
		}
		return quantity;

	}

	private double getTotalAmount(List<Order> orders) {

		double totalAmount = 0.0;
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order value = iterator.next();
			totalAmount += value.getTotalAmount();

		}
		return totalAmount;

	}

	// this method return the date of start delivery order 
	private List<Order> atFlightDate(List<Order> orders, String startDate, String endDate) {

		//
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
		return orders;

	}

	//check if the format of date is valid 
	private boolean isValidFormat(String date) {
		boolean valid = false;
		/*the if statement will process like this the character's from 0 to 3 this for YYYY ,
		the character 4 will by for /
		the character's 5 and 6 this for MM
		the character 7 will by for /
		the character's 8 and 9 this for DD*/
		if ((date.length() == 10) && Character.isDigit(date.charAt(0)) && Character.isDigit(date.charAt(1))
				&& Character.isDigit(date.charAt(2)) && Character.isDigit(date.charAt(3))
				&& Character.isDigit(date.charAt(5)) && Character.isDigit(date.charAt(6))
				&& Character.isDigit(date.charAt(8)) && Character.isDigit(date.charAt(9)))
			valid = true;
		return valid;
	}

	// return if this supplier is valid or not based on the supplier status 
	public void validSupplier(List<Supplier> allSupplier) {
		for (Iterator<Supplier> iterator = allSupplier.iterator(); iterator.hasNext();) {
			Supplier value = iterator.next();
			if (value.isStatus() == false)
				iterator.remove();
		}

	}

}
