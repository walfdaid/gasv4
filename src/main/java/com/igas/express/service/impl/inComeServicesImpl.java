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
import com.igas.express.dao.inComeDao;
import com.igas.express.model.entitiy.InCome;
import com.igas.express.service.inComeServices;
import com.igas.express.util.ResponseObject;
import com.igas.express.util.ResponseObjectAll;
import com.igas.express.util.ResponseObjectCrud;
import com.igas.express.util.ResponseObjectData;
@Service
@Component
public class inComeServicesImpl implements inComeServices {

	@Autowired
	private inComeDao incomeDao;
	@Override
	public ResponseObject createInCome(InCome Income) {
		ResponseObject response = null;
		Income.setDate(new Date());
		
		incomeDao.save(Income);
		long id = Income.getInComeId();
		
		
		if(id > 0)
	    response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_CREATE_CODE , ResponseMessage.SUCCESS_CREATING_MESSAGE ,id );  
		else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_CREATING_MESSAGE );  }

		return response;
	}

	@Override
	public ResponseObject updateInCome(long id, InCome Income) {
        ResponseObject response = null;
		
		if (incomeDao.findByinComeId(id) !=  null){
			Income.setInComeId(id);
			incomeDao.save(Income);
			response = new ResponseObjectCrud(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_UPDATING_MESSAGE ,id ); 
		}
		else
	    response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS , ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_UPDATING_MESSAGE );  
		
		
		return response;
	}

	@Override
	public ResponseObject getAllInCome() {
		List<InCome> allIncome = incomeDao.findAll();
		ResponseObject response = null;
		
		
		if (allIncome.isEmpty()){
		response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
		}else{
		
		response = new ResponseObjectAll<InCome>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE ,allIncome);
		}
		return response;
	}

	@Override
	public ResponseObject getAllInComeFlight( String startDate, String endDate) {
		List<InCome> allInCome = incomeDao.findAll();
		ResponseObject response = null;
		boolean validDate = (isValidFormat(startDate) && isValidFormat(endDate));
		boolean validExist = !allInCome.isEmpty();
		
		if (validDate && validExist){
			atFlightDate(allInCome , startDate , endDate);
			if (!allInCome.isEmpty())
			response = new ResponseObjectAll<InCome>(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , allInCome);
			else 
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_GETTING_MESSAGE);  	
		}else if (!validDate){
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_STRING_MESSAGE);  	
		}else {
			response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_RESPONSE_CODE , ResponseMessage.FAILED_GETTING_MESSAGE);  	
		}
		return response;
		
	}
	private boolean isValidFormat(String date){
		boolean valid = false;
		if ((date.length() == 10) && Character.isDigit(date.charAt(0)) && Character.isDigit(date.charAt(1)) && Character.isDigit(date.charAt(2))&& Character.isDigit(date.charAt(3))&& Character.isDigit(date.charAt(5)) && Character.isDigit(date.charAt(6))&& Character.isDigit(date.charAt(8))&& Character.isDigit(date.charAt(9)))
			valid = true;
		return valid; 
	}
	
private void atFlightDate(List<InCome> incomes , String startDate , String endDate){
		
	//
    int startDateSplited = Integer.valueOf(startDate.substring(0,4) + startDate.substring(5,7) + startDate.substring(8,10)); 
	int endDateSplited = Integer.valueOf(endDate.substring(0,4) + endDate.substring(5,7) + endDate.substring(8,10));
	
    // 
    int yearCreatedAt = 0; 
	int monthCreatedAt = 0;
	int dayCreatedAt = 0;
	
	String createdAtString = "";
	int createdDateValue = 0;
    
        
        
        
        for (Iterator<InCome> iterator = incomes.iterator(); iterator.hasNext(); ) {
    		
        	InCome value = iterator.next();
    		Date createdAt = value.getDate();
    		Calendar cal = Calendar.getInstance();
    	    cal.setTime(createdAt);
    	    yearCreatedAt = cal.get(Calendar.YEAR);
    	    monthCreatedAt = cal.get(Calendar.MONTH)+1;
    	    dayCreatedAt = cal.get(Calendar.DAY_OF_MONTH);
    	    
    	    if (monthCreatedAt <= 9)
    	    	yearCreatedAt *= 10;
    	    if (dayCreatedAt <= 9)
    	    	monthCreatedAt *= 10;
    	    
    	    createdAtString = ""+yearCreatedAt + monthCreatedAt + dayCreatedAt  ;
    	    createdDateValue = Integer.parseInt(createdAtString);
    	    
    	    if (startDateSplited > createdDateValue || createdDateValue > endDateSplited )
    	    {iterator.remove();}
    	    
    	    
    	  }
        
        
	}

@Override
public ResponseObject getIncomeById(long id) {
	
	InCome income = incomeDao.findByinComeId(id); 
	ResponseObject response = null;
	
	if (income == null)
	response = new ResponseObject(ResponseStatus.FAILED_RESPONSE_STATUS ,ResponseCode.FAILED_GET_CODE , ResponseMessage.FAILED_GETTING_MESSAGE );  	
	else{
	response = new ResponseObjectData(ResponseStatus.SUCCESS_RESPONSE_STATUS , ResponseCode.SUCCESS_RESPONSE_CODE , ResponseMessage.SUCCESS_GETTING_MESSAGE , income);
	}
	return response;
}


	

}
