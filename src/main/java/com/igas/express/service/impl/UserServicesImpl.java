package com.igas.express.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.igas.express.constant.ResponseCode;
import com.igas.express.constant.ResponseMessage;
import com.igas.express.constant.ResponseStatus;
import com.igas.express.dao.AddressDao;
import com.igas.express.dao.UserDao;
import com.igas.express.model.entitiy.Address;
import com.igas.express.model.entitiy.User;
import com.igas.express.service.UserServices;
import com.igas.express.util.CustomLoginUser;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;

	@Override
	public ResponseObject getAllUsers() {
		List<User> allUsers = userDao.findAll();
		ResponseObject response = null;

		if (allUsers.isEmpty())
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {
			nullPassword1(allUsers);

			response = new ResponseObjectAll<User>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allUsers);
		}
		return response;
	}

	@Override
	public ResponseObject getAllUsersInActive() {
		List<User> allUsers = userDao.findAll();
		collectAvtive(allUsers);

		ResponseObject response = null;

		if (allUsers.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {
			nullPassword1(allUsers);
			response = new ResponseObjectAll<User>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allUsers);
		}

		return response;

	}

	@Override
	public ResponseObject getUserById(long id) {
		User user = userDao.findByCustomerId(id);

		ResponseObject response = null;

		if (user == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {
			user.setPassword(null);
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, user);
		}
		return response;
	}

	@Override
	public ResponseObject getUserByPhoneNumber(String phoneNumber) {

		User user = userDao.findByPhoneNumber(phoneNumber);

		ResponseObject response = null;

		if (user == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {
			user.setPassword(null);
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, user);
		}
		return response;
	}

	@Override
	public ResponseObject getUserBycreatedAt(String createdAt) {
		List<User> allUsers = userDao.findAll();
		ResponseObject response = null;

		if (isValidFormat(createdAt)) {
			atSpecificDay(allUsers, createdAt);
			if (allUsers.isEmpty()) {
				response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
						ResponseMessage.FAILED_GETTING_MESSAGE);
			} else {
				response = new ResponseObjectAll<User>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allUsers);
			}
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_STRING_MESSAGE);
		}

		return response;
	}

	@Override
	public ResponseObject createUser(User user) {

		User userA = userDao.findByPhoneNumber(user.getPhoneNumber());
		User userB = userDao.findByEmail(user.getEmail());
		boolean valid = (((userA == null) && (userB == null)) ? true : false);

		ResponseObject response = null;
		long id = 0;

		if (valid) {
			userDao.save(user);
			id = user.getCustomerId();
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_CREATING_MESSAGE, id);
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"phone number or email is already exist");
		}
		return response;
	}

	public ResponseObject updateUser(long id, User user) {

		User userId = userDao.findByCustomerId(id);
		User userPhone = userDao.findByPhoneNumber(user.getPhoneNumber());
		User userEmail = userDao.findByEmail(user.getEmail());

		Address address = user.getAddress();

		if (userId.getAddress() != null)
			address.setAddressId(userId.getAddress().getAddressId());

		boolean valid = false;
		ResponseObject response = null;

		if (userId != null)
			valid = (userPhone == null && userEmail == null)
					|| (userEmail != null && userPhone == null && userId.equals(userEmail))
					|| (userPhone != null && userEmail == null && userId.equals(userPhone))
					|| ((userPhone != null && userEmail != null) && (userId.equals(userEmail))
							&& userId.equals(userPhone));

		if (valid && userId.isStatus()) {
			User userPass = userDao.findByCustomerId(id);
			user.setPassword(userPass.getPassword());
			user.setUpdatedAt(new Date());
			user.setCreatedAt(userId.getCreatedAt());
			user.setCustomerId(id);
			addressDao.save(address);
			user.setAddress(address);

			userDao.save(user);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_UPDATING_MESSAGE, id);
		} else if (!userId.isStatus()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"Deactivated user");

		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"phone number or email is already exist");
		}

		return response;
	}

	@Override
	public ResponseObject deleteUser(long id) {

		User user = userDao.findByCustomerId(id);
		ResponseObject response = null;

		if (user != null && user.isStatus()) {
			user.setDeletedAt(new Date());
			user.setStatus(false);
			userDao.save(user);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_DELETTING_MESSAGE, id);
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_DELETTING_MESSAGE);
		}
		return response;
	}

	@Override
	public ResponseObject loginUser(String phoneNumber, String password) {
		User user = userDao.findByPhoneNumber(phoneNumber);
		ResponseObject response = null;
		if (user == null) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_LOGIN_MESSAGE);
		} else if (!user.isStatus()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_AUTH_CODE,
					"Deactivated customer");

		} else if ((user != null) && (password.equals(user.getPassword())) && user.isStatus()) {
			String token = generateToken();
			user.setToken(token);
			userDao.save(user);
			CustomLoginUser userLogin = new CustomLoginUser(user.getCustomerId(), user.getEmail(),
					user.getPhoneNumber(), user.getFullName(), user.getAddress(), user.getToken());
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_LOGIN_MESSAGE, userLogin);

		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_LOGIN_MESSAGE);
		}

		return response;

	}

	@Override
	public ResponseObject changePassword(long id, String password) {
		User user = userDao.findByCustomerId(id);
		ResponseObject response = null;

		if (user != null && user.isStatus()) {
			user.setPassword(password);
			user.setUpdatedAt(new Date());
			userDao.save(user);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_UPDATING_MESSAGE, id);
		} else if (!user.isStatus()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"Deactivated user");
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_UPDATING_MESSAGE);
		}
		return response;
	}

	private void collectAvtive(List<User> users) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User value = iterator.next();
			if (value.isStatus())
				iterator.remove();
		}

	}

	private void nullPassword1(List<User> users) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User value = iterator.next();
			value.setPassword(null);

		}
	}

	private void atSpecificDay(List<User> users, String date) {

		int yearDate = Integer.valueOf(date.substring(0, 4));
		int monthDate = Integer.valueOf(date.substring(5, 7));
		int dayDate = Integer.valueOf(date.substring(8, 10));
		//
		int yearCreatedAt = 0;
		int monthCreatedAt = 0;
		int dayCreatedAt = 0;

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {

			User value = iterator.next();
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

	@Override
	public ResponseObject getAllUsersFlight(boolean status, String startDate, String endDate) {
		List<User> allUsers = userDao.findByStatus(status);
		ResponseObject response = null;
		boolean validDate = (isValidFormat(startDate) && isValidFormat(endDate));
		boolean validExist = !allUsers.isEmpty();

		if (validDate && validExist) {
			atFlightDate(allUsers, startDate, endDate);
			if (!allUsers.isEmpty())
				response = new ResponseObjectAll<User>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allUsers);
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

	private void atFlightDate(List<User> users, String startDate, String endDate) {

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

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {

			User value = iterator.next();
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

	@Override
	public ResponseObject getUserByEmail(String email) {
		User user = userDao.findByEmail(email);

		ResponseObject response = null;

		if (user == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {
			user.setPassword(null);
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, user);
		}
		return response;
	}

	@Override
	public ResponseObject UpdateUserStatus(long id, boolean status) {
		User user = userDao.findByCustomerId(id);
		ResponseObject response = null;
		if (user != null) {
			user.setStatus(status);
			if (status == false)
				user.setDeletedAt(new Date());
			else {
				user.setUpdatedAt(new Date());
				user.setDeletedAt(null);
			}

			userDao.save(user);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_UPDATING_MESSAGE, id);
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_UPDATING_MESSAGE);
		}

		return response;
	}

	private String generateToken() {
		String token = "";
		char[] charSet = "ABCDEFGHIJKLMNOPQRSTUZWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		while (token.length() < 20) {
			token += charSet[(int) (Math.random() * 60)];
		}
		return token;
	}

	@Override
	public ResponseObject existPhoneNumberOrEmail(String phoneNumber, String email) {

		ResponseObject response = null;
		User userPhoneNumber = null;
		User userEmail = null;
		userPhoneNumber = userDao.findByPhoneNumber(phoneNumber);
		userEmail = userDao.findByEmail(email);
		if ((userPhoneNumber != null) || (userEmail != null)) {
			response = new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_RESPONSE_CODE,
					ResponseMessage.SUCCESS_GETTING_MESSAGE);
		} else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		}
		return response;
	}

	@Override
	public ResponseObject logoutUser(long id) {
		User user = userDao.findByCustomerId(id);
		ResponseObject response = null;
		if (user != null) {
			user.setToken("0000");
			userDao.save(user);
			response = new ResponseObject(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_RESPONSE_CODE,
					"logged out successfully");
		} else
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					"faild logging out");

		return response;

	}

	@Override
	public ResponseObject getUserByDate(String startDate, String endDate) {
		List<User> allUsers = userDao.findAll();
		ResponseObject response = null;
		boolean validDate = (isValidFormat(startDate) && isValidFormat(endDate));
		boolean validExist = !allUsers.isEmpty();

		if (validDate && validExist) {
			atFlightDate(allUsers, startDate, endDate);
			if (!allUsers.isEmpty())
				response = new ResponseObjectAll<User>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allUsers);
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

}
