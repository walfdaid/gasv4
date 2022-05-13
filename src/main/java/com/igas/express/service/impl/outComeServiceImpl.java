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
import com.igas.express.dao.outComeDao;
import com.igas.express.model.entitiy.outCome;
import com.igas.express.service.outComeServices;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;

@Service
@Component
public class outComeServiceImpl implements outComeServices {

	@Autowired
	private outComeDao outcomeDao;

	@Override
	public ResponseObject createOutCome(outCome outcome) {
		ResponseObject response = null;
		outcome.setDate(new Date());

		outcomeDao.save(outcome);
		long id = outcome.getOutComeId();

		if (id > 0)
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS, ResponseCode.SUCCESS_CREATE_CODE,
					ResponseMessage.SUCCESS_CREATING_MESSAGE, id);
		else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_CREATING_MESSAGE);
		}

		return response;

	}

	@Override
	public ResponseObject updateOutCome(long id, outCome outcome) {
		ResponseObject response = null;

		if (outcomeDao.findByoutComeId(id) != null) {
			outcome.setOutComeId(id);
			outcomeDao.save(outcome);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_UPDATING_MESSAGE, id);
		} else
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_RESPONSE_CODE,
					ResponseMessage.FAILED_UPDATING_MESSAGE);

		return response;
	}

	@Override
	public ResponseObject getAllOutCome() {
		List<outCome> allOutcome = outcomeDao.findAll();
		ResponseObject response = null;

		if (allOutcome.isEmpty()) {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		} else {

			response = new ResponseObjectAll<outCome>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, allOutcome);
		}
		return response;
	}

	@Override
	public ResponseObject getAllOutComeFlight(String startDate, String endDate) {
		List<outCome> alloutCome = outcomeDao.findAll();
		ResponseObject response = null;
		boolean validDate = (isValidFormat(startDate) && isValidFormat(endDate));
		boolean validExist = !alloutCome.isEmpty();

		if (validDate && validExist) {
			atFlightDate(alloutCome, startDate, endDate);
			if (!alloutCome.isEmpty())
				response = new ResponseObjectAll<outCome>(ResponseStatus.SUCCESS_RESPONSE_STATUS,
						ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, alloutCome);
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

	private boolean isValidFormat(String date) {
		boolean valid = false;
		if ((date.length() == 10) && Character.isDigit(date.charAt(0)) && Character.isDigit(date.charAt(1))
				&& Character.isDigit(date.charAt(2)) && Character.isDigit(date.charAt(3))
				&& Character.isDigit(date.charAt(5)) && Character.isDigit(date.charAt(6))
				&& Character.isDigit(date.charAt(8)) && Character.isDigit(date.charAt(9)))
			valid = true;
		return valid;
	}

	private void atFlightDate(List<outCome> outcomes, String startDate, String endDate) {

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

		for (Iterator<outCome> iterator = outcomes.iterator(); iterator.hasNext();) {

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

	}

	@Override
	public ResponseObject getOutComeId(long id) {

		ResponseObject response = null;

		outCome outcome = outcomeDao.findByoutComeId(id);

		if (outcome == null)
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS, ResponseCode.FAILED_GET_CODE,
					ResponseMessage.FAILED_GETTING_MESSAGE);
		else {
			response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS,
					ResponseCode.SUCCESS_RESPONSE_CODE, ResponseMessage.SUCCESS_GETTING_MESSAGE, outcome);
		}
		return response;
	}

}
